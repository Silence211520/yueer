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
import yueer.dao.MailConfigDao;
import yueer.utils.MailConfig;
import yueer.utils.MailUtils;
import yueer.utils.QQUtils;

/**
 * 邮件高级配置
 * @author Silence
 *
 * 2019年2月23日 下午8:11:42
 */
@WebServlet("/AdvanceConfig")
public class AdvanceConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvanceConfig() {
    	
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
		/**
		 * 						notice_time : notice_time,
								repeate : repeate,
								copy_mail : copy_mail,
								private_set : private_set
			获取请求参数
		 */
		String notice_time_str = request.getParameter("notice_time");
		String  repeate_str = request.getParameter("repeate");
		String copy_mail_str = request.getParameter("copy_mail");
		String  private_set_str = request.getParameter("private_set");
		int notice_time = MailConfig.noticeTimeTransform(notice_time_str);
		int repeate = MailConfig.repeateTransform(repeate_str);
		int copy_mail = MailConfig.copyMailTransform(copy_mail_str);
		int private_set = MailConfig.privateSet(private_set_str);
		String user =(String)this.getServletContext().getAttribute("login-user");
		System.out.println("notice_time:"+notice_time);
		System.out.println("repeate:"+repeate);
		System.out.println("copy_mail:"+copy_mail);
		System.out.println("private_set:"+private_set);
		yueer.bean.MailConfig config = new yueer.bean.MailConfig(notice_time, notice_time_str, repeate, repeate_str, copy_mail, copy_mail_str, private_set, private_set_str,user );
		boolean isOk = MailConfigDao.advanceConfig(config);
		response.setContentType("text/json;charset=utf-8");
		Writer write = response.getWriter();
		JSONObject obj = new JSONObject();
		String msg = "";
		msg = isOk == true? "设置成功,请关闭窗口！":"设置失败";
		MailLog log = new MailLog();
		log.setUser_name((String)this.getServletContext().getAttribute("login-user"));
		log.setMail_type("账户修改");
		log.setSubject("修改邮件模板");
		MailUtils.modifyLog(log);
		/**
		 * 发送修改账号邮件
		 */
		String email = MailUtils.getMail(user);
		if (!"".equals(email)) {
			StringBuilder sb = new StringBuilder();
			sb.append("notice_time:"+"<i>"+notice_time+"</i>").append("<br>");
			sb.append("repeate:"+"<i>"+repeate+"</i>").append("<br>");
			sb.append("copy_mail:"+"<i>"+copy_mail+"</i>").append("<br>");
			sb.append("private_set:"+"<i>"+private_set+"</i>").append("<br>");
			QQUtils.sendMail(email, sb.toString(), "修改邮件模板");
		}
		obj.put("msg", msg);
		write.write(obj.toString());
		write.flush();
	}

}
