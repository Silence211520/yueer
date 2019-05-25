package yueer.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import yueer.bean.MailLog;
import yueer.utils.DbUtils;
import yueer.utils.PageUtils;
import yueer.utils.StringUtils;

/**
 * 搜索历史邮件
 * @author Silence
 *
 * 2019年2月24日 上午11:57:13
 */
public class MailHistoryDao {
	private static String error_start_tag = "<font color='red'>";
	private static String error_end_tag = "</font>";
	private static String mark_start_tag = "<mark>";
	private static String  mark_end_tag = "</mark>";
	public static JSONArray search(PageUtils page, MailLog log) {
		JSONArray array = new JSONArray();
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select user_name,mail_type,subject,send_time,contract from mail_send_log ");
		boolean isSearch = false;
		if (log.getUser_name()!= null && log.getUser_name().trim() != "") {
			sb.append(" and user_name like '%" + log.getUser_name().trim() + "%'");
			isSearch = true;
		}
		// 日期的区间范围
		if (log.getStart()!= null && log.getStart().trim() != "") {
			// 日期字段要加''
			sb.append("and send_time between '").append(log.getStart()).append("' and '")
					.append(log.getEnd()+"' ");
			isSearch = true;
		}
		if (log.getMail_type()!=null && log.getMail_type()!="") {
			// String ss = StringUtils.changMailType(log.getMail_type());
			if(!"全部".equals(log.getMail_type())) {
				sb.append(" and mail_type like '%"+log.getMail_type()+"%'");
				isSearch = true;
			}
			else {
				isSearch = true;
			}	
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
					 * select 
					 * user_name, mail_type,subject,
					 * send_date(date类型),contract 
					 * from mail_ send_log
					 */
					MailLog tp = new MailLog();
					String tep = rs.getString(2);
					if(tep.contains("预警")) {
						tp.setUser_name(error_start_tag+rs.getString(1)+error_end_tag);
						tp.setMail_type(error_start_tag+rs.getString(2)+error_end_tag);
						tp.setSubject(error_start_tag+rs.getString(3)+error_end_tag);
						tp.setSend_time(error_start_tag+String.valueOf(rs.getDate(4))+error_end_tag);
						tp.setContract(error_start_tag+StringUtils.phoneFormat(rs.getString(5))+error_end_tag);
					}
					else{
							tp.setUser_name(mark_start_tag+rs.getString(1)+mark_end_tag);
							tp.setMail_type(mark_start_tag+rs.getString(2)+mark_end_tag);
							tp.setSubject(mark_start_tag+rs.getString(3)+mark_end_tag);
							tp.setSend_time(mark_start_tag+String.valueOf(rs.getDate(4))+mark_end_tag);
							tp.setContract(mark_start_tag+StringUtils.phoneFormat(rs.getString(5))+mark_end_tag);
					}
					array.add(tp);
				}
			} else {
				while (rs.next()) {
					MailLog tp = new MailLog();	
					String tep = rs.getString(2);
					if(tep.contains("预警")) {
						tp.setUser_name(error_start_tag+rs.getString(1)+error_end_tag);
						tp.setMail_type(error_start_tag+rs.getString(2)+error_end_tag);
						tp.setSubject(error_start_tag+rs.getString(3)+error_end_tag);
						tp.setSend_time(error_start_tag+String.valueOf(rs.getDate(4))+error_end_tag);
						tp.setContract(error_start_tag+StringUtils.phoneFormat(rs.getString(5))+error_end_tag);
					}
					else {
						 tp.setUser_name(rs.getString(1));
						 tp.setMail_type(rs.getString(2));
						 tp.setSubject(rs.getString(3));
						 tp.setSend_time(String.valueOf(rs.getDate(4)));
						 tp.setContract(StringUtils.phoneFormat(rs.getString(5)));
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
	 * 获取邮件的总记录数目
	 * @param tp
	 * @return
	 */
	public static int getRecordNum(MailLog log) {
		int nums = 0;
		Connection conn = DbUtils.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select  count(*)  from mail_send_log ");
		if (log.getUser_name() != null && log.getUser_name().trim() != "") {
			sb.append(" and user_name like '%").append(log.getUser_name().trim()).append("%' ");
		}
		if (log.getStart() != null && log.getStart().trim() != "") {
			sb.append("and send_time between '");
			sb.append(log.getStart() + "'  and  '");
			sb.append(log.getEnd()+"'");
		}
		if (log.getMail_type()!=null && log.getMail_type().trim()!= "") {
			sb.append(" and mail_type like '%"+log.getMail_type()+"%'");
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
	public static void main(String[] args) {
		System.out.println(search(new PageUtils(), new MailLog()));
		System.out.println(getRecordNum(new MailLog()));
	}
	/**
	 * 注册备份
	 * @param log
	 * @return
	 */
	public static boolean registerBackUp(MailLog log) {
		boolean isOk = false;
		return isOk;
	}
}
