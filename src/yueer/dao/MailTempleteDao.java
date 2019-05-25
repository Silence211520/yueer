package yueer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import yueer.bean.MailTemplete;
import yueer.utils.DbUtils;

/**
 * 操作邮件模板
 * @author Silence
 *
 * 2019年2月23日 下午7:30:09
 */
public class MailTempleteDao {
	/**
	 * 新增邮件模板
	 * @param templete
	 * @return
	 */
	public static boolean addTemplete(MailTemplete templete) {
		boolean isOk = true;
		Connection conn = DbUtils.getConnection();
		String sql = "insert into mail_templete values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, templete.getContent_type());
			ps.setString(2, templete.getType());
			ps.setString(3, templete.getSubject());
			ps.setString(4, templete.getMail_head());
			ps.setString(5, templete.getUser());
			 isOk = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			isOk = false;
		}
		return isOk;
	}

}
