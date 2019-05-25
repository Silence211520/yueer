package yueer.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import yueer.bean.MailConfig;
import yueer.utils.DbUtils;

/**
 * 邮件高级配置业务实现
 * @author Silence
 *
 * 2019年2月23日 下午9:08:40
 */
public class MailConfigDao {
	public static boolean advanceConfig(MailConfig mcf) {
		boolean option = MailConfigDao.isExist(mcf);
		// 存在配置,直接更新
		if(option) {
			return MailConfigDao.updateConfig(mcf);
		}
		// 不存在,新增配置
		else {
			return  MailConfigDao.addConfig(mcf);
		}
	}
	/**
	 *  查询用户是否存在相关的配置
	 * @param user
	 * @return
	 */
	private static boolean isExist(MailConfig mcf) {
		boolean isOk = false;
		Connection conn = DbUtils.getConnection();
		String sql = "select * from mail_config where user = ? ";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mcf.getUser());
			isOk = ps.executeQuery().next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
	/**
	 * 更新配置
	 * @param mcf
	 * @return
	 */
	private static boolean updateConfig(MailConfig mcf) {
		boolean isOk = false;
		Connection conn = DbUtils.getConnection();
		String sql = "update mail_config set notice_time = ? , notice_time_str = ? , repeate = ?, repeate_str = ?, copy_mail = ?, copy_mail_str = ?, private_set = ?, private_set_str = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mcf.getNotice_time());
			ps.setString(2, mcf.getNotice_time_str());
			ps.setInt(3, mcf.getRepeate());
			ps.setString(4, mcf.getRepeate_str());
			ps.setInt(5, mcf.getCopy_mail());
			ps.setString(6, mcf.getCopy_mail_str());
			ps.setInt(7, mcf.getPrivate_set());
			ps.setString(8, mcf.getPrivate_set_str());
			isOk = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
	/**
	 * 新增配置
	 * @param mcf
	 * @return
	 */
	private  static boolean addConfig(MailConfig mcf) {
		boolean isOk = true;
		Connection conn = DbUtils.getConnection();
		String sql = "insert into mail_config values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, mcf.getNotice_time());
			ps.setString(2, mcf.getNotice_time_str());
			ps.setInt(3, mcf.getRepeate());
			ps.setString(4, mcf.getRepeate_str());
			ps.setInt(5, mcf.getCopy_mail());
			ps.setString(6, mcf.getCopy_mail_str());
			ps.setInt(7, mcf.getPrivate_set());
			ps.setString(8, mcf.getPrivate_set_str());
			ps.setString(9, mcf.getUser());
			isOk = ps.executeUpdate()>0 ? true:false;
		} catch (SQLException e) {
			System.out.println(e);
			isOk = false;
		}
		return isOk;
	}
}
