package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import yueer.bean.User;
import yueer.dao.LoginDao;

/**
 * 处理登录逻辑
 * @author Silence
 *
 * 2019年2月17日 下午11:19:07
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username,password);
		boolean isOk = LoginDao.isLogin(user);
		JsonObject obj = new JsonObject();
		//JSONObject obj = new JSONObject();
		String msg= "";
		System.out.println(isOk);
		if(isOk) {
			String url = request.getRequestURI();
		    msg = url.substring(0,url.lastIndexOf("/")+1)+"main.html";
		    // 登录成功后将用户名存储
		    this.getServletContext().setAttribute("login-user", username);
		}
		else {
			msg= "用户名或密码错误";
		}
		obj.addProperty("msg", msg);
		Writer write = response.getWriter();
		write.write(obj.toString());
		write.flush();
	}

}
