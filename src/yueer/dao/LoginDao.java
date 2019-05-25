package yueer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yueer.bean.User;
import yueer.utils.DbUtils;

/**
 * 处理用户登录的业务逻辑
 * @author Silence
 *
 * 2019年2月18日 上午7:36:08
 */
public class LoginDao {
	/**
	 * 判断用户是否登录成功
	 */
	public static boolean isLogin(User user) {
		  Connection conn = DbUtils.getConnection();
		  String sql = "select * from user_account  where username = ?  and password = ?";
		  try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
		   ResultSet rs = ps.executeQuery();
		   return rs.next();
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	  }
	/**
	 * 处理用户注册的业务逻辑
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public static boolean isRegister(User user) throws SQLException {
		  Connection conn  = DbUtils.getConnection();
		  String sql = "insert into user_account values(?,?,?)";
		 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			return ps.executeUpdate() > 0;
	  }
}
