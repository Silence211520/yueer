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
import yueer.bean.MailLog;
import yueer.dao.MailHistoryDao;
import yueer.utils.PageUtils;
import yueer.utils.StringUtils;

/**
 * 邮件搜索
 * @author Silence
 *
 * 2019年2月24日 上午11:24:37
 */
@WebServlet("/MailSerach")
public class MailSerach extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSerach() {
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
		Writer write = response.getWriter();
		// 获取每页显示的记录数目
		String rows = request.getParameter("rows");
		// 获取第几页, 页码从1开始
		String page = request.getParameter("page");
		String user_id = request.getParameter("user_id");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String mail_type = request.getParameter("mail_type");
		if(mail_type!=null) {
			mail_type = StringUtils.changMailType(mail_type);
		}
		System.out.println("user_id:"+user_id);
		System.out.println("start_date:"+start_date);
		System.out.println("end_date:"+end_date);
		System.out.println("mail_type:"+mail_type);
		// mail_type = StringUtils.changMailType(mail_type);
		MailLog log = new MailLog(user_id, mail_type, start_date, end_date);
		PageUtils utils = new PageUtils(Integer.parseInt(page), Integer.parseInt(rows));
		JSONObject result = new JSONObject();
		JSONArray array = MailHistoryDao.search(utils, log); 
		int total = MailHistoryDao.getRecordNum(log);
		result.put("total", total);
		result.put("rows",array);
		write.write(result.toString());
		write.flush();
		
	}

}
