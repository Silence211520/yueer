package yueer.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import yueer.bean.FlowParameters;
import yueer.utils.DbUtils;
import yueer.utils.PageUtils;

/**
 * 处理水流量业务逻辑
 * 
 * @author Silence
 *
 *         2019年2月18日 下午11:41:02
 */
public class FlowDao {
	private static final String ERROR_START_TAG = "<font color = 'red'>";
	private static final String ERROR_END_TAG = "</font>";
	private static final String MARK_START_TAG = "<mark>";
	private static final String MARK_END_TAG = "</mark>";
	/**
	 * 查询水流量信息
	 * 
	 * @param page
	 * @param flow
	 * @return
	 */
	public static JSONArray search(PageUtils page, FlowParameters flow) {
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		JSONArray array = new JSONArray();
		sb.append("select  user_id, water_flow, data_range,is_normal from  flow_parameters ");
		boolean isSearch = false;
		if (flow.getUser_id() != null && flow.getUser_id().trim() != "") {
			sb.append("and user_id like '%" + flow.getUser_id() + "%'");
			isSearch = true;
		}
		// 日期的区间范围
		if (flow.getStart_data_range() != null && flow.getStart_data_range().trim() != "") {
			// 日期字段要加''
			sb.append("and da    ta_range between '").append(flow.getStart_data_range()).append("' and '")
					.append(flow.getEnd_data_range()+"'");
			isSearch = true;
		}
		// 分页信息
		if (page.getIndex() >= 0 && page.getRows() >= 0) {
			sb.append(" ").append("limit " + page.getIndex()).append(",").append(page.getRows());
		}
		String sql = sb.toString().replaceFirst("and", "where");
		sb = new StringBuilder(sql);
		Statement state;
		try {
			state = conn.createStatement();
			System.out.println(sb.toString());
			ResultSet rs = state.executeQuery(sb.toString());
			if (isSearch) {
				while (rs.next()) {
					/**
					 * user_id : 查询字段 data_range:查询字段 
					 * select user_id, water_flow,
					 * data_range,is_normal
					 */
					String tep = rs.getString(4);
					if("否".equals(tep)) {
						 tep = FlowDao.ERROR_START_TAG + tep + FlowDao.ERROR_END_TAG;
						 FlowParameters fp = new FlowParameters(FlowDao.ERROR_START_TAG+rs.getString(1) + FlowDao.ERROR_END_TAG, rs.getFloat(2),
									 String.valueOf(rs.getDate(3)) , tep);
						 array.add(fp);
					}
					else {
						FlowParameters fp = new FlowParameters(FlowDao.ERROR_START_TAG+FlowDao.MARK_START_TAG + rs.getString(1) + FlowDao.MARK_END_TAG+FlowDao.ERROR_END_TAG, rs.getFloat(2),
								FlowDao.MARK_START_TAG + String.valueOf(rs.getDate(3)) + FlowDao.MARK_END_TAG, tep);
						array.add(fp);
					}
				}
			} else {
				while (rs.next()) {
					String tep = rs.getString(4);
					if("否".equals(tep)) {
						tep = FlowDao.ERROR_START_TAG + tep + FlowDao.ERROR_END_TAG;
						FlowParameters fp = new FlowParameters(FlowDao.ERROR_START_TAG+rs.getString(1)+FlowDao.ERROR_END_TAG, rs.getFloat(2),
								String.valueOf(rs.getDate(3)), tep);
						array.add(fp);
					}
					else {
						FlowParameters fp = new FlowParameters(rs.getString(1), rs.getFloat(2),
								String.valueOf(rs.getDate(3)), tep);
						array.add(fp);
					}
					
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
	public static int getRecordNum(FlowParameters flow) {
		int nums = 0;
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select  count(*)  from flow_parameters  ");
		if (flow.getUser_id() != null && flow.getUser_id().trim() != "") {
			sb.append("and user_id like '%").append(flow.getUser_id().trim()).append("%' ");
		}
		if (flow.getStart_data_range() != null && flow.getStart_data_range().trim() != "") {
			sb.append("and data_range between ");
			sb.append(flow.getStart_data_range() + " and ");
			sb.append(flow.getEnd_data_range());
		}
		String sql = sb.toString().replaceFirst("and", "where");
		sb = new StringBuilder(sql);
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
