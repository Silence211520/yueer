package yueer.bean;
/**
 * 地图
 * @author Silence
 *
 * 2019年2月27日 下午6:02:12
 */
public class Location {
	// 经度
	private double longitude;
	private double latitude;
	private String tip;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}
	
	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
   
	public Location(double longitude, double latitude, String tip) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.tip = tip;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
}
