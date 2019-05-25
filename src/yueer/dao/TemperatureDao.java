package yueer.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import yueer.bean.TempParameters;
import yueer.utils.DbUtils;
import yueer.utils.PageUtils;

/**
 * 处理进出水口温度的业务逻辑
 * @author Silence
 *
 * 2019年2月18日 下午10:57:27
 */
public class TemperatureDao {
	private static final String ERROR_START_TAG = "<font color = 'red'>";
	private static final String ERROR_END_TAG = "</font>";
	private static final String  MARK_START_TAG = "<mark>";
	private static final String MARK_END_TAG = "</mark>";
	/**
	 * 处理进出水口温度逻辑
	 * @param page
	 * @param temp
	 * @return
	 */
	public static JSONArray search(PageUtils page, TempParameters temp) {
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		JSONArray array = new JSONArray();
		sb.append("select user_id,water_input_temperature,water_output_temperature,data_range,is_normal from tep_parameters ");
		boolean isSearch = false;
		if (temp.getUser_id() != null && temp.getUser_id().trim() != "") {
			sb.append("and user_id like '%" + temp.getUser_id() + "%'");
			isSearch = true;
		}
		// 日期的区间范围
		if (temp.getStart_data()!= null && temp.getStart_data().trim() != "") {
			// 日期字段要加''
			sb.append("and data_range between '").append(temp.getStart_data()).append("' and '")
					.append(temp.getEnd_data()+"'");
			isSearch = true;
		}
		// 分页信息
		if (page.getIndex() >= 0 && page.getRows() >= 0) {
			sb.append(" ").append("limit " + page.getIndex()).append(",").append(page.getRows());
		}
		String sql = sb.toString().replaceFirst("and", "where");
		sb = new StringBuilder(sql);
		System.out.println(sb.toString());
		Statement state;
		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(sb.toString());
			if (isSearch) {
				while (rs.next()) {
					/**
					 * select user_id,
					 * water_input_temperature,
					 * water_output_temperature,
					 * data_range,
					 * is_normal 
					 * from tep_parameters
					 */
					TempParameters tp = new TempParameters();
					String tep = rs.getString(5);
					if("否".equals(tep)) {
						tp.setUser_id(TemperatureDao.ERROR_START_TAG+rs.getString(1)+TemperatureDao.ERROR_END_TAG);
						tp.setWater_input_temperature(TemperatureDao.ERROR_START_TAG+String.valueOf(rs.getFloat(2)).concat("℃")+TemperatureDao.ERROR_END_TAG);
						tp.setWater_output_temperature(TemperatureDao.ERROR_START_TAG+String.valueOf(rs.getFloat(3)).concat("℃")+TemperatureDao.ERROR_END_TAG);
						tp.setData_range(TemperatureDao.ERROR_START_TAG+String.valueOf(rs.getDate(4))+TemperatureDao.ERROR_END_TAG);
						tp.setIs_normal(TemperatureDao.ERROR_START_TAG+tep+TemperatureDao.ERROR_END_TAG);		
					}
					else{
						tp.setUser_id(TemperatureDao.MARK_START_TAG+rs.getString(1)+TemperatureDao.MARK_END_TAG);
						tp.setWater_input_temperature(String.valueOf(rs.getFloat(2)).concat("℃"));
						tp.setWater_output_temperature(String.valueOf(rs.getFloat(3)).concat("℃"));
						tp.setData_range(TemperatureDao.MARK_START_TAG+String.valueOf(rs.getDate(4))+TemperatureDao.MARK_END_TAG);
						tp.setIs_normal(tep);
					}
					array.add(tp);
				}
			} else {
				while (rs.next()) {
					TempParameters tp = new TempParameters();
					
					String tep = rs.getString(5);
					if("否".equals(tep)) {
						tp.setUser_id(TemperatureDao.ERROR_START_TAG+rs.getString(1)+TemperatureDao.ERROR_END_TAG);
						tp.setWater_input_temperature(TemperatureDao.ERROR_START_TAG+rs.getFloat(2)+"℃"+TemperatureDao.ERROR_END_TAG);
						tp.setWater_output_temperature(TemperatureDao.ERROR_START_TAG+String.valueOf(rs.getFloat(3)).concat("℃")+TemperatureDao.ERROR_END_TAG);
						tp.setData_range(TemperatureDao.ERROR_START_TAG+String.valueOf(rs.getDate(4))+TemperatureDao.ERROR_END_TAG);
						tp.setIs_normal(TemperatureDao.ERROR_START_TAG+tep+TemperatureDao.ERROR_END_TAG);
					}
					else {
						tp.setUser_id(rs.getString(1));
						tp.setWater_input_temperature(String.valueOf(rs.getFloat(2)).concat("℃"));
						tp.setWater_output_temperature(String.valueOf(rs.getFloat(3)).concat("℃"));
						tp.setData_range(String.valueOf(rs.getDate(4)));
						tp.setIs_normal(tep);
					}
					array.add(tp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return array;
	}

	/**
	 * 获取满足条件的记录数目
	 */
	public static int getRecordNum(TempParameters tp) {
		int nums = 0;
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select  count(*)  from tep_parameters ");
		if (tp.getUser_id() != null && tp.getUser_id().trim() != "") {
			sb.append("and user_id like '%").append(tp.getUser_id().trim()).append("%' ");
		}
		if (tp.getStart_data() != null && tp.getStart_data().trim() != "") {
			sb.append("and data_range between '");
			sb.append(tp.getStart_data() + "'  and  '");
			sb.append(tp.getEnd_data()+"'");
		}
		String sql = sb.toString().replaceFirst("and", "where");
		sb = new StringBuilder(sql);
		System.out.println(sb.toString());
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sb.toString());
			while (rs.next()) {
				nums = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nums;
	}
}
