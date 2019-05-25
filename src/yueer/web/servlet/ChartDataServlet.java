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
 * 综合数据绘图
 * @author Silence
 *
 * 2019年2月18日 上午1:05:21
 */
@WebServlet("/Comprehensive/ChartDataServlet")
public class ChartDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		long time = System.currentTimeMillis();
		Random r = new Random();
		double input_water = 70 + r.nextGaussian();
		double output_water = 95 - r.nextGaussian();
		JsonObject obj = new JsonObject();
		obj.addProperty("x", time);
		obj.addProperty("s1", input_water);
		obj.addProperty("s2", output_water);
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
