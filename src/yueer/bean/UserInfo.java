package yueer.bean;
/**
 * 用户信息表
 * @author Silence
 *
 * 2019年2月24日 下午7:45:21
 */
public class UserInfo {
	private String user_name;
	private String user_id;
	private String area;
	private String address;
	private String phone;
	
	public UserInfo() {
	}
	public UserInfo(String user_name, String user_id, String area, String address, String phone) {
		super();
		this.user_name = user_name;
		this.user_id = user_id;
		this.area = area;
		this.address = address;
		this.phone = phone;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
