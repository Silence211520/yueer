package yueer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import yueer.bean.User;
import yueer.utils.DbUtils;
import yueer.utils.QQUtils;

/**
 * 处理用户注册逻辑
 * @author Silence
 *
 * 2019年2月18日 上午9:44:11
 */
public class RegisterDao {
	/**
	 * 判断邮箱是否占用
	 * @param email
	 * @return 
	 * true--> 邮箱已经占用
	 * false-->邮箱账号可以使用
	 */
	public static boolean isAvaiable(String email) {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from user_account where email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
		  return  ! ps.executeQuery().next();
		} catch (SQLException e) {
			return false;
		}
		
		
	}
	/**
	 * 处理用户注册逻辑
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public static boolean isRegister(User user)  {
		  Connection conn  = DbUtils.getConnection();
		  String sql = "insert into user_account values(?,?,?)";
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	  }
	/**
	 * 注册提醒邮件
	 * @param username  用户名
	 * @param password  密码
	 * @param subject   主题
	 * @param to   收件人  
	 */
	public static boolean sendMail(String username,String password,String subject,String to) {
		StringBuilder sb = new StringBuilder();
		sb.append("<font color='green'>尊敬的<i>"+username+"</i>,您好:</font><br>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;您刚刚注册了一个新的账号,账户信息如下:<br>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;用户名:&nbsp;<font color='red'>"+username+"</font><br>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;码:&nbsp;<font color='red'>"+password+"</font><br>");
		sb.append("<font color='red' size = 2 >温馨提示:本邮件由系统自动发送,无需回复!</font>");
		return QQUtils.sendMail(to, sb.toString(), subject);
	}

}
