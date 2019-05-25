package yueer.bean;
/**
 * 
 * @author Silence
 *
 * 2019年2月23日 下午6:55:38
 */
public class MailTemplete {
	/**
	 * System.out.println("content_type:"+content_type);
		System.out.println("type:"+type);
		System.out.println("subject:"+subject);
		System.out.println("mail_head:"+mail_head);
	 */
	private String content_type;
	private String type;
	// 邮件的主题
	private String subject;
	// 邮件的签名
	private String mail_head;
	// 邮件所属的模
	private String user;
	
	public MailTemplete() {
	}
	
	public MailTemplete(String content_type, String type, String subject, String mail_head, String user) {
		this.content_type = content_type;
		this.type = type;
		this.subject = subject;
		this.mail_head = mail_head;
		this.user = user;
	}

	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMail_head() {
		return mail_head;
	}
	public void setMail_head(String mail_head) {
		this.mail_head = mail_head;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
