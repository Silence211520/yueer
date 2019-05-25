package yueer.bean;
/**
 * 邮件发送历史
 * @author Silence
 *
 * 2019年2月24日 上午11:04:00
 */
public class MailLog {
	// 用户名
	private String user_name ;
	// 邮件类别
	private String mail_type;
	// 邮件主题
	private String subject;
	// 邮件发送日期
	private String send_time;
	// 练习方式
	private String contract;
	/**
	 * 以下字段仅仅为了查询
	 */
    private String start; // 开始时间
    private String end;   // 结束时间
	
	public MailLog() {
	}
	
	

	public MailLog(String user_name, String mail_type, String start, String end) {
		this.user_name = user_name;
		this.mail_type = mail_type;
		this.start = start;
		this.end = end;
	}



	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMail_type() {
		return mail_type;
	}
	public void setMail_type(String mail_type) {
		this.mail_type = mail_type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
