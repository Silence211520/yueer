package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import yueer.bean.MailLog;
import yueer.bean.User;
import yueer.dao.RegisterDao;
import yueer.utils.MailUtils;

/**
 * 处理注册逻辑
 * 
 * @author Silence
 *
 *         2019年2月17日 下午11:21:34
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		Writer writer = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		System.out.println(email);
		User user = new User(username, password, email);
		JSONObject obj = new JSONObject();
		String msg = "";
		String subject = "用户注册提醒";
		if (RegisterDao.isAvaiable(email)) {
			boolean ok = RegisterDao.isRegister(user);
			if (ok) {
				MailLog log = new MailLog();
				log.setUser_name(username);
				log.setMail_type("用户注册");
				log.setSubject(subject);
				// 记录用户注册的邮件日志
				MailUtils.registerLog(log);
				msg = "注册成功";
			} else {
				msg = "注册失败";
			}
		} else {
			msg = "邮箱已占用,请选择一个新的邮箱账户";
		}
		obj.put("msg", msg);
		System.out.println(obj.toString());
		writer.write(obj.toString());
		writer.flush();
	}

}
