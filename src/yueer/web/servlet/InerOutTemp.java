package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 是内室外温度实时监控
 * @author Silence
 *
 * 2019年3月7日 下午6:48:03
 */
@WebServlet("/InerOutTemp")
public class InerOutTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InerOutTemp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		Writer write = response.getWriter();
		long  x = System.currentTimeMillis();
		Random r = new Random();
		double outner = -15.0 + r.nextDouble();
		double inner = 18.0 + r.nextDouble();
		JSONObject obj = new JSONObject();
		obj.put("x", x);
		obj.put("outner", outner);
		obj.put("inner", inner);
		write.write(obj.toString());
		System.out.println(obj.toString());
		write.flush();
		
	}

}
