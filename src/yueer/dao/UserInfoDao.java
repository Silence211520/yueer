package yueer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import net.sf.json.JSONArray;
import yueer.bean.UserInfo;
import yueer.utils.DbUtils;
import yueer.utils.PageUtils;
import yueer.utils.StringUtils;

/**
 * 用户信息添加,查询
 * @author Silence
 *
 * 2019年2月24日 下午7:48:55
 */
public class UserInfoDao {
	private static String start_tag = "<mark>";
	private static String end_tag = "</mark>";
	/**
	 * 新增用户信息
	 * @param info
	 * @return
	 */
	public static boolean configUser(UserInfo info) {
		boolean isOk = false;
		Connection conn = DbUtils.getConnection();
		String sql = "insert into user_info (user_name,user_id,area,address,phone) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, info.getUser_name());
			ps.setString(2, info.getUser_id());
			ps.setString(3, info.getArea());
			ps.setString(4, info.getAddress());
			ps.setString(5, info.getPhone());
			isOk = ps.executeUpdate() > 0 ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isOk;
	}
 public static JSONArray search(PageUtils page,UserInfo info) {
	 JSONArray array = new JSONArray();
	 Connection conn = DbUtils.getConnection();
	 StringBuilder sb = new StringBuilder();
		sb.append("select user_id,user_name,area,address,phone from user_info ");
		boolean isSearch = false;
		if (info.getUser_name()!= null && info.getUser_name().trim() != "") {
			sb.append(" and user_name like '%" +info.getUser_name() + "%'");
			isSearch = true;
		}
		if (info.getPhone() != null && info.getPhone().trim() != "") {
			sb.append(" and phone like '%").append(info.getPhone()+"%' ");
			isSearch = true;
		}
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
					// select user_id,user_name,area,
					// address,phone from user_info
					UserInfo item = new UserInfo();
					item.setUser_id(start_tag+rs.getString(1)+end_tag);
					item.setUser_name(start_tag+rs.getString(2)+end_tag);
					item.setArea(start_tag+rs.getString(3)+end_tag);
					item.setAddress(start_tag+rs.getString(4)+end_tag);
					item.setPhone(start_tag+StringUtils.phoneFormat(rs.getString(5))+end_tag);
					array.add(item);
				}
			} else {
				while (rs.next()) {
					UserInfo item = new UserInfo();
					item.setUser_id(rs.getString(1));
					item.setUser_name(rs.getString(2));
					item.setArea(rs.getString(3));
					item.setAddress(rs.getString(4));
					item.setPhone(StringUtils.phoneFormat(rs.getString(5)));
					array.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(array);
		return array;
 }
 /**
	 * 获取满足条件的记录数目
	 */
	public static int getRecordNum(UserInfo item) {
		int nums = 0;
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select  count(*)  from user_info ");
		if (item.getUser_name() != null && item.getUser_name().trim() != "") {
			sb.append("and user_name like '%").append(item.getUser_name().trim()).append("%' ");
		}
		if (item.getPhone() != null && item.getPhone().trim() != "") {
			sb.append(" and phone like '%").append(item.getPhone()+"%' ");
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
