package yueer.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yueer.bean.MailLog;

/**
 * 处理邮件发送日志
 * @author Silence
 *
 * 2019年2月25日 下午1:21:02
 */
public class MailUtils {
	public static void modifyLog(MailLog log) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into mail_send_log(user_name,mail_type,subject,send_time,contract) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, log.getUser_name());
			ps.setString(2, log.getMail_type());
			ps.setString(3, log.getSubject());
			Date date = new Date(System.currentTimeMillis());
		    ps.setDate(4, date);
		    // 设置用户手机号码
		    ps.setString(5, MailUtils.getPhone(log.getUser_name()));
		    ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 用户注册邮件记录
	 * @param log
	 */
	public static void registerLog(MailLog log) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into mail_send_log (user_name,mail_type,subject,send_time,contract) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, log.getUser_name());
		    ps.setString(2, log.getMail_type());
		    ps.setString(3, log.getSubject());
		    Date date = new Date(System.currentTimeMillis());
		    ps.setDate(4, date);
		    // 设置用户手机号码
		    ps.setString(5, MailUtils.getPhone(log.getUser_name()));
		    ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取用户的邮箱账号
	 * @param user
	 * @return
	 */
	public static String getMail(String user) {
		Connection conn = DbUtils.getConnection();
		ResultSet rs = null;
		String sql= "select email from user_account where username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			else {
				return "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 获取用户手机号码,没有找到,返回为""
	 * @param user
	 * @return
	 */
 public static String getPhone(String user) {
	 Connection conn = DbUtils.getConnection();
	 String sql = "select phone from user_info where user_name = ? ";
	 boolean isfind = false;
	 ResultSet rs = null;
	 try {
		PreparedStatement  ps = conn.prepareStatement(sql);
		ps.setString(1, user);
		rs =  ps.executeQuery();
		isfind = rs.next();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 if (isfind) {
		 try {
			return  rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	 }
	 else {
		 return "";
	 }
	 
 }
}
