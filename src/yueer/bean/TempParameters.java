package yueer.bean;
/**
 * 对应于io-data.html页面
 * @author Silence
 *
 * 2019年2月18日 上午6:56:36
 */
public class TempParameters {
	private String user_id;
	// 为了方便显示,将它转化为字符串
	private String  water_input_temperature;
	private String water_output_temperature;
	private String data_range; // 数据库存储为long型
	private String is_normal;
	//  only used search
	private String start_data;
	private String end_data;
	public TempParameters() {
	}
	
	public TempParameters(String user_id, String start_data, String end_data) {
		super();
		this.user_id = user_id;
		this.start_data = start_data;
		this.end_data = end_data;
	}



	public TempParameters(String user_id, String water_input_temperature, String water_output_temperature,
			String data_range, String is_normal, String start_data, String end_data) {
		this.user_id = user_id;
		this.water_input_temperature = water_input_temperature;
		this.water_output_temperature = water_output_temperature;
		this.data_range = data_range;
		this.is_normal = is_normal;
		this.start_data = start_data;
		this.end_data = end_data;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getWater_input_temperature() {
		return water_input_temperature;
	}
	public void setWater_input_temperature(String water_input_temperature) {
		this.water_input_temperature = water_input_temperature;
	}
	public String getWater_output_temperature() {
		return water_output_temperature;
	}
	public void setWater_output_temperature(String water_output_temperature) {
		this.water_output_temperature = water_output_temperature;
	}
	public String getData_range() {
		return data_range;
	}
	public void setData_range(String data_range) {
		this.data_range = data_range;
	}
	public String getIs_normal() {
		return is_normal;
	}
	public void setIs_normal(String is_normal) {
		this.is_normal = is_normal;
	}
	public String getStart_data() {
		return start_data;
	}
	public void setStart_data(String start_data) {
		this.start_data = start_data;
	}
	public String getEnd_data() {
		return end_data;
	}
	public void setEnd_data(String end_data) {
		this.end_data = end_data;
	}
}
