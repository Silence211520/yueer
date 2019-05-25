package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import yueer.dao.UserAreaDao;
import yueer.utils.StringUtils;

/**
 * 用户所在区域信息配置
 * @author Silence
 *
 * 2019年2月26日 上午1:29:48
 */
@WebServlet("/UserArea")
public class UserArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserArea() {
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
		/**
		 * user_name: user_name,
		    area : area
		 */
		String user_name = request.getParameter("user_name").trim();
		String area_info = request.getParameter("area");
		area_info = StringUtils.changeArea(area_info);
		yueer.bean.UserArea ua = new yueer.bean.UserArea(user_name, area_info);
		boolean isOk = UserAreaDao.updateRecord(ua);
		String msg = "";
		msg = isOk == true ? "添加成功" :"添加失败";
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		Writer write = response.getWriter();
		write.write(obj.toString());
		write.flush();
		System.out.println("user_name:"+user_name);
		System.out.println("area:"+area_info);	
	}

}
