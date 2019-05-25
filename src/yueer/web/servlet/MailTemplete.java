package yueer.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;
import yueer.dao.MailTempleteDao;

/**
 * 邮件模板配置
 * @author Silence
 *
 * 2019年2月23日 下午6:23:36
 */
@WebServlet("/MailTemplete")
public class MailTemplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailTemplete() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		/**
		 * 邮件的类型
		 * html  html格式的
		 * text  文本格式的
		 */
		String content_type = request.getParameter("content_type");
		/**
		 * 邮件类型
		 *  register_mail : 注册邮件
		 *  modify_mail：账户更改信息提醒邮件
		 *  alert_mail : 异常预警提醒邮件
		 */
		String type = request.getParameter("type");
		String subject = request.getParameter("subject");
		String mail_head = request.getParameter("mail_head");
		String user = (String)this.getServletContext().getAttribute("login-user");
		yueer.bean.MailTemplete templete = new yueer.bean.MailTemplete(content_type, type, subject, mail_head, user);
		boolean isOk = MailTempleteDao.addTemplete(templete);
		response.setContentType("text/json;charset=utf-8");
		JSONObject obj = new JSONObject();
		
		Writer write = response.getWriter();
		String msg = "";
//		System.out.println("content_type:"+content_type);
//		System.out.println("type:"+type);
//		System.out.println("subject:"+subject);
//		System.out.println("mail_head:"+mail_head);
		if (isOk) {
			msg = "模板添加成功,请关闭窗口!";
		}
		else {
			msg = "模板添加失败";
		}
		obj.put("msg", msg);
		write.write(obj.toString());
		write.flush();
	}

}
