package yueer.bean;
/**
 * 水流量信息
 * @author Silence
 *
 * 2019年2月18日 上午7:16:00
 */
public class FlowParameters {
	private String user_id;
	private float water_flow;
	private String start_data_range;// 开始日期
	private String data_range; 
	private String end_data_range; //结束日期
	private String is_normal;
	public FlowParameters() {
	}
	public FlowParameters(String user_id, String start_data_range, String end_data_range) {
		super();
		this.user_id = user_id;
		this.start_data_range = start_data_range;
		this.end_data_range = end_data_range;
	}
	public FlowParameters(String user_id, float water_flow, String data_range, String is_normal) {
		super();
		this.user_id = user_id;
		this.water_flow = water_flow;
		this.data_range = data_range;
		this.is_normal = is_normal;
	}
	public FlowParameters(String user_id, float water_flow, String start_data_range, String data_range,
			String end_data_range, String is_normal) {
		super();
		this.user_id = user_id;
		this.water_flow = water_flow;
		this.start_data_range = start_data_range;
		this.data_range = data_range;
		this.end_data_range = end_data_range;
		this.is_normal = is_normal;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public float getWater_flow() {
		return water_flow;
	}
	public void setWater_flow(float water_flow) {
		this.water_flow = water_flow;
	}
	public String getStart_data_range() {
		return start_data_range;
	}
	public void setStart_data_range(String start_data_range) {
		this.start_data_range = start_data_range;
	}
	public String getData_range() {
		return data_range;
	}
	public void setData_range(String data_range) {
		this.data_range = data_range;
	}
	public String getEnd_data_range() {
		return end_data_range;
	}
	public void setEnd_data_range(String end_data_range) {
		this.end_data_range = end_data_range;
	}
	public String getIs_normal() {
		return is_normal;
	}
	public void setIs_normal(String is_normal) {
		this.is_normal = is_normal;
	}
	
	
}
