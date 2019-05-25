package yueer.utils;
/**
 * 字符串工具类
 * @author Silence
 *
 * 2019年2月24日 下午1:19:06
 */
public class StringUtils {
   public static String response_format = "text/json;charset=utf-8";
   public static String request_format = "utf-8";
	/**
	 * 格式手机号,使其显示格式为130***1102
	 * @return
	 */
	public static String phoneFormat(String phone) {
		StringBuilder result  = new StringBuilder();
		if(phone.matches("\\d{11}")) {
			result.append(phone.substring(0, 3)).append("***");
			result.append(phone.substring(7));
		}
		else {
			new RuntimeException("手机号码格式有误");
		}
		return result.toString();
	}
	/**
	 * 邮件类型转换
	 * @param mail_type
	 * @return
	 */
	public static String changMailType(String mail_type) {
		String result = "";
		switch(mail_type) {
		case "add_account" : result = "用户注册";break;
		case "modify_mail": result = "账户修改";break;
		case "alert_mail" : result = "预警邮件";break;
		case "all_mail" : result = "全部";break;
		}
		return result;
	}
	public static String changeArea(String area) {
		String result = "";
		/**
		 * <option value="dongbei" >东北地区</option>
			<option value="neimenggu">内蒙古地区</option>
			<option value ='xinjiang'>新疆地区</option>
			<option value ='xibei'>西北地区</option>	
		 */
		switch(area) {
		case "dongbei" : result = "东北地区"; break;
		case "neimenggu" : result = "内蒙古地区"; break;
		case "xinjiang" : result = "新疆地区"; break;
		case "xibei" : result = "西北地区"; break;
		}
		return result;
	}
}
