package yueer.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 百度地图地址解析工具类
 * @author Silence
 *
 * 2019年2月27日 下午11:36:56
 */
public class LocationUtils {
	private static  final String key = "tupIuemGLmC1i8ZQnE1Bast1wpMpq0eO";
	private static String baseUrl = "http://api.map.baidu.com/geocoder/v2/";
	/**
	 * 根据地址获取它的经纬度值
	 * @param address
	 * @return
	 */
    public static void main(String[] args) {
		getlat("黑龙江省哈尔滨市南岗区学府路理工大学家属楼28栋");
	}
	public static String getlat(String address) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();

	    String entityStr = null;
	    CloseableHttpResponse response = null;

	    try {
	        URIBuilder uriBuilder = new URIBuilder(baseUrl);
	        // 添加请求的参数
	        List<NameValuePair> list = new LinkedList<>();
	        BasicNameValuePair param1 = new BasicNameValuePair("address", address);
	        BasicNameValuePair param2 = new BasicNameValuePair("output", "json");
	        BasicNameValuePair param3 = new BasicNameValuePair("ak", key);
	        BasicNameValuePair param4 = new BasicNameValuePair("output", "json");
	        list.add(param1);
	        list.add(param2);
	        list.add(param3);
	        list.add(param4);
	        uriBuilder.setParameters(list);
	        HttpGet httpGet = new HttpGet(uriBuilder.build());
	        // 浏览器表示
	        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
	        // 传输的类型
	        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
	        response = httpClient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        entityStr = EntityUtils.toString(entity, "UTF-8");
	        result = parseJsonStr(entityStr);
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    } catch (URISyntaxException e) {
	    } catch (IOException e) {
	    } finally {
	        // 释放连接
	        if (null != response) {
	            try {
	                response.close();
	                httpClient.close();
	            } catch (IOException e) {
	                System.err.println("释放连接出错");
	                e.printStackTrace();
	            }
	        }
	    }

	    // 打印响应内容
	    System.out.println(result); 
		
		return result;
	}
  private static String parseJsonStr(String json) {
	  String result = "";
	  JsonParser parse = new JsonParser();
	 JsonObject obj =  (JsonObject) parse.parse(json);
	 int status = obj.get("status").getAsInt();
	 if(status == 0) {
	 obj.get("result").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsString();
	 JsonObject tmp = obj.get("result").getAsJsonObject().get("location").getAsJsonObject();
	 result += tmp.get("lng").getAsString();
	 result += ",";
	 result += tmp.get("lat").getAsString();
	 }
	 else if (status == 1) {
		 result = "地址解析失败!";
	 }
	  return result;
  }
}
