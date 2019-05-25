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
 * 进水口温度实时曲线绘制
 * @author Silence
 *
 * 2019年2月18日 上午2:36:00
 */
@WebServlet("/WaterInputServlet")
public class WaterInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaterInputServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		JsonObject obj = new JsonObject();
		long time = System.currentTimeMillis();
		Random r = new Random();
		double input_tep = 90 + r.nextFloat() * 1.1;
		obj.addProperty("x", time);
		obj.addProperty("s1", input_tep);
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
