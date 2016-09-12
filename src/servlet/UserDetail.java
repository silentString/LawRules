package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserInfo;
import bean.UserInfo.StatusEnum;
import dao.UserDao;
import net.sf.json.JSONObject;
import util.CommonEnums.LoginResults;

/**
 * Servlet implementation class UserDetail
 */
@WebServlet("/UserDetail")
public class UserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phoneNumber = request.getParameter("phone_number");
		UserDao dao = new UserDao();
		UserInfo info = dao.queryUser(phoneNumber);
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		int status = 1;
		if (info == null) {//server error
			status = 0;
		} else if (info.getPhoneNumber().equals("0")) {//login failed
			jsonObject.put("loginResult", LoginResults.FAIL);
		} else {
			if (info.getStatus().equals(StatusEnum.expired)) {//login success but expired
				jsonObject.put("loginResult", LoginResults.EXPIRED);
			} else if (info.getStatus().equals(StatusEnum.normal)) {//login success and can use
				jsonObject.put("loginResult", LoginResults.SUCCESS);
			}
			jsonObject.put("nick_name", info.getNickName());
			jsonObject.put("register_time", info.getRegisterDate());
			jsonObject.put("user_status", info.getStatus());
			jsonObject.put("score", info.getPoints());
			jsonObject.put("end_time", info.getEndDate());
		}
		
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("data", jsonObject);
		writer.print(result.toString());
	}

}
