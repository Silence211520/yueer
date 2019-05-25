package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yueer.bean.Location;


/**
 * 异常区域查询Servlet
 * @author Silence
 *
 * 2019年2月27日 下午12:48:15
 */
@WebServlet("/ExceptionArea")
public class ExceptionArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExceptionArea() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		System.out.println("start_date:"+start_date);
		System.out.println("end_date:"+end_date);
		response.setContentType("text/json;charset=utf-8");
		JSONArray array = new JSONArray();
		Location  l1 = new Location(126.627917, 45.713925);
		Location  l2 = new Location(126.640727, 45.71198);
		Location  l3 = new Location(126.637741, 45.711138);
//		Location l4 = new Location(126.624713, 45.714789);
//		Location l5 = new Location(126.622018, 45.714789);
//		Location l6 = new Location(126.624839,45.7122108);
//		Location l7 = new Location(126.62342, 45.716677);
		array.add(l1);
		array.add(l2);
		array.add(l3);
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("data", array);
		System.out.println(obj.toString());
		Writer write = response.getWriter();
		write.write(obj.toString());
		write.flush();

	}

}
