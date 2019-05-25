package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;


/**
 * 水流量实时监控数据
 * @author Silence
 *
 * 2019年2月18日 上午2:10:04
 */
@WebServlet("/WaterFlow")
public class WaterFlow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaterFlow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		JsonObject obj = new JsonObject();
		long time = System.currentTimeMillis();
		Random r = new Random();
		double flow = r.nextGaussian() + r.nextInt(2) + 15;
		obj.addProperty("x", time);
		obj.addProperty("s1", flow);
		Writer write = response.getWriter();
		write.write(obj.toString());
		System.out.println(obj.toString());
		write.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
