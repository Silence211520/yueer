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
import yueer.bean.UserInfo;
import yueer.dao.UserInfoDao;
import yueer.utils.PageUtils;
import yueer.utils.StringUtils;

/**
 * 显示用户信息
 * @author Silence
 *
 * 2019年2月24日 下午10:35:26
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
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
		response.setContentType(StringUtils.response_format);
		// 获取查询的关键词
		String kw = request.getParameter("kw");
		String rows = request.getParameter("rows");
		String  page = request.getParameter("page");
		PageUtils utils = new PageUtils(Integer.parseInt(page),Integer.parseInt(rows));
		UserInfo info = new UserInfo();
		if(kw!=null && kw.trim()!="") {
			// 搜索的关键词为手机号
			if(kw.matches("\\d+")) {
				info.setPhone(kw.trim());
			}
			else {
				// 搜索的关键词为用户名
				info.setUser_name(kw.trim());
			}
		}
		JSONArray array = UserInfoDao.search(utils, info);
		//JSONArray array = UserInfoDao.search(utils, info);
		int total = UserInfoDao.getRecordNum(info);
		JSONObject obj = new JSONObject();
		Writer write = response.getWriter();
		obj.put("total", total);
		obj.put("rows", array);
		write.write(obj.toString());
		System.out.println(obj.toString());
		write.flush();
	}

}
