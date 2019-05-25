 package yueer.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import yueer.bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * 操作数据库的工具类
 * @author Silence
 *
 * 2018年12月6日 下午7:57:23
 */
public class DbUtils {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/yueer";
	private static final String user = "root";
	private static final String password = "yp@520";
	private static Connection conn = null;
    static {
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  public static Connection getConnection() {
	  return conn;
  }
  @Deprecated
  public static boolean isLogin(User user) {
	  Connection conn = DbUtils.conn;
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
  @Deprecated
  public static boolean isRegister(User user) throws SQLException {
	  Connection conn  = DbUtils.conn;
	  String sql = "insert into user_account values(?,?,?)";
	 
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		return ps.executeUpdate() > 0;
  }
 public static void main(String[] args) {
	
}
}
