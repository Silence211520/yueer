package yueer.utils;
/**
 * 邮件配置
 * @author Silence
 *
 * 2019年2月23日 下午8:31:27
 */
public class MailConfig {
	public static int repeateTransform(String args) {
		int flag = 0;
		flag = "是".equals(args) ? 0 :1;
		return flag;
	}
  public static int noticeTimeTransform(String args) {
	  int flag = 0;
	  switch(args) {
	  case "time_now" : flag = 0;  break;
	  case "time_30" : flag = 1;  break;
	  case "time_constant" : flag = 2 ; break;
	  }
	 return flag;
  }
  public static int copyMailTransform(String args) {
	  int flag = 0;
	  switch(args) {
	  case "no_copy" : flag = 0; break;
	  case "admin_mail" : flag = 1 ;break;
	  }
	  return flag;
  }
  public static int privateSet(String args) {
	  int flag = 0;
	  switch (args) {
	  case "public" : flag = 0; break;
	  case "admin_look" : flag = 1; break;
	  case "private_look" : flag = 2;break;
	  }
	  return flag;
  }
}
