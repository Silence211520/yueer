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
import yueer.bean.TempParameters;
import yueer.dao.TemperatureDao;
import yueer.utils.PageUtils;

/**
 * 处理进出水口温度的Servlet
 * @author Silence
 *
 * 2019年2月18日 上午7:24:27
 */
@WebServlet("/IoDataServlet")
public class IoDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IoDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 获取每页显示的记录数目
		String rows = request.getParameter("rows");
		// 获取第几页, 页码从1开始
		String page = request.getParameter("page");
		response.setContentType("text/json;charset=utf-8");
		System.out.println("rows:"+rows);
		System.out.println("page:"+ page);
		JSONObject obj = new JSONObject();
		String user_id = request.getParameter("user_id");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		System.out.println("user-id:"+user_id);
		System.out.println("start-date:"+start_date);
		System.out.println("end-date:"+end_date);
		PageUtils utils = new PageUtils(Integer.parseInt(page),Integer.parseInt(rows));
		TempParameters tp = new TempParameters(user_id, start_date, end_date);
		JSONArray array = TemperatureDao.search(utils, tp);
		int total = TemperatureDao.getRecordNum(tp);
		obj.put("total", total);
		obj.put("rows", array);
		Writer writer = response.getWriter();
		writer.write(obj.toString());
		System.out.println(obj.toString());
		writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
