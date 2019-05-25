package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * 获取当当前登录的用户信息
 * @author Silence
 *
 * 2019年2月18日 下午12:52:15
 */
@WebServlet("/GetLoginUser")
public class GetLoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLoginUser() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		String login_user = (String)this.getServletContext().getAttribute("login-user");
		JsonObject obj = new JsonObject();
		Writer write  = response.getWriter();
		if (login_user == null) 
		{
			obj.addProperty("user", "");
		}
		else {
			obj.addProperty("user", login_user);
		}
		System.out.println(obj.toString());
		write.write(obj.toString());
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
