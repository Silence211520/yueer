package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;


/**
 * 出水口温度实时曲线图
 * @author Silence
 *
 * 2019年2月18日 上午2:58:10
 */
@WebServlet("/WaterOutputServlet")
public class WaterOutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaterOutputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		Date d = new Date();
		Random r = new Random();
		double output_tep = 72 - r.nextFloat();
		JsonObject obj = new JsonObject();
		obj.addProperty("x", d.getTime());
		obj.addProperty("s1", output_tep);
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
