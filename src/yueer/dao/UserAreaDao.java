package yueer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import yueer.bean.UserArea;
import yueer.utils.DbUtils;

/**
 * 处理用户区域信息业务逻辑
 * @author Silence
 *
 * 2019年2月26日 上午1:48:13
 */
public class UserAreaDao {
 public static boolean updateRecord(UserArea ua) {
	 boolean flag = UserAreaDao.isExist(ua);
	 boolean isOk = false;
	 Connection conn = DbUtils.getConnection();
	 // 存在则更新记录
	 if(flag) {
		 String sql = "update user_area set area_info = ?";
		 PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ua.getArea_info());
			isOk = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 else {
		 String sql = "insert into user_area (user_name,area_info) values(?,?)";
		 try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ua.getUser_name());
			ps.setString(2, ua.getArea_info());
			isOk = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 return isOk;
 }
  private static boolean isExist(UserArea ua) {
	  boolean isOk = false;
	  Connection conn = DbUtils.getConnection();
	  String sql = "Select * from user_area where user_name = ?";
	  try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, ua.getUser_name());
		isOk = ps.executeQuery().next();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return isOk;
  }
}
