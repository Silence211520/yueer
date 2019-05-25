package yueer.bean;
/**
 * 邮件配置类
 * @author Silence
 *
 * 2019年2月23日 下午8:53:54
 */
public class MailConfig {
	private  int notice_time;
	private  String notice_time_str;
	private  int repeate;
	private  String repeate_str;
	private  int copy_mail ;
	private  String copy_mail_str;
	private int private_set ;
	private String private_set_str;
	private String user;
	
	public MailConfig() {

	}
	
	public MailConfig(int notice_time, String notice_time_str, int repeate, String repeate_str, int copy_mail,
			String copy_mail_str, int private_set, String private_set_str,String user) {
		this.notice_time = notice_time;
		this.notice_time_str = notice_time_str;
		this.repeate = repeate;
		this.repeate_str = repeate_str;
		this.copy_mail = copy_mail;
		this.copy_mail_str = copy_mail_str;
		this.private_set = private_set;
		this.private_set_str = private_set_str;
		this.user = user;
	}

	public int getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(int notice_time) {
		this.notice_time = notice_time;
	}
	public String getNotice_time_str() {
		return notice_time_str;
	}
	public void setNotice_time_str(String notice_time_str) {
		this.notice_time_str = notice_time_str;
	}
	public int getRepeate() {
		return repeate;
	}
	public void setRepeate(int repeate) {
		this.repeate = repeate;
	}
	public String getRepeate_str() {
		return repeate_str;
	}
	public void setRepeate_str(String repeate_str) {
		this.repeate_str = repeate_str;
	}
	public int getCopy_mail() {
		return copy_mail;
	}
	public void setCopy_mail(int copy_mail) {
		this.copy_mail = copy_mail;
	}
	public String getCopy_mail_str() {
		return copy_mail_str;
	}
	public void setCopy_mail_str(String copy_mail_str) {
		this.copy_mail_str = copy_mail_str;
	}
	public int getPrivate_set() {
		return private_set;
	}
	public void setPrivate_set(int private_set) {
		this.private_set = private_set;
	}
	public String getPrivate_set_str() {
		return private_set_str;
	}
	public void setPrivate_set_str(String private_set_str) {
		this.private_set_str = private_set_str;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
