package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yueer.bean.Location;

/**
 * 没有查询条件是返回的界面
 * @author Silence
 *
 * 2019年2月27日 下午8:03:55
 */
@WebServlet("/DefaultArea")
public class DefaultArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefaultArea() {
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
		JSONArray array = new JSONArray();
		Writer write = response.getWriter();
		Location l4 = new Location(126.63332642356125,45.713170402458448);
		Location l5 = new Location(126.64694115175192,45.69678757397129);
		Location l6 = new Location(126.64155393276104,45.71782783120475);
		Location l7 = new Location(126.62921331619046,45.72000466248705);
		JSONObject obj = new JSONObject();
		array.add(l4);
		array.add(l5);
		array.add(l6);
		array.add(l7);
		obj.put("code", 200);
		obj.put("data", array);
		write.write(obj.toString());
		write.flush();
		System.out.println(obj.toString());
		System.out.println(obj.toString());
		
	}

}
