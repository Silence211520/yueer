package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import yueer.bean.UserInfo;
import yueer.dao.UserInfoDao;
import yueer.utils.StringUtils;
/**
 * 用户信息录入
 * @author Silence
 *
 * 2019年2月24日 下午7:31:06
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
		request.setCharacterEncoding(StringUtils.request_format);
		String user_name = request.getParameter("user_name").trim();
		String user_id = request.getParameter("user_id").trim();
		String area = request.getParameter("area");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		UserInfo info = new UserInfo(user_name, user_id, area, address, phone);
		boolean isOk = UserInfoDao.configUser(info);
		response.setContentType(StringUtils.response_format);
		Writer write = response.getWriter();
		String msg = "";
		if(isOk) {
			msg = "操作成功,请自行关闭窗口";
		}
		else {
			msg = "操作失败";
		}
		JsonObject obj = new JsonObject();
		obj.addProperty("msg", msg);
		write.write(obj.toString());
		write.flush();
	}

}
