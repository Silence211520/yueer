package yueer.bean;
/**
 * 用户区域信息
 * @author Silence
 *
 * 2019年2月26日 上午1:45:05
 */
public class UserArea {
 private String user_name;
 private String  area_info;
 
public UserArea() {

}

public UserArea(String user_name, String area_info) {
	this.user_name = user_name;
	this.area_info = area_info;
}

public String getUser_name() {
	return user_name;
}

public void setUser_name(String user_name) {
	this.user_name = user_name;
}

public String getArea_info() {
	return area_info;
}

public void setArea_info(String area_info) {
	this.area_info = area_info;
}

 
}
