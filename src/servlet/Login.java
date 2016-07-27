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
import util.LoginResults;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
       
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
		
		String phoneNumber = request.getAttribute("phone_number").toString();
		String password = request.getAttribute("password").toString();
		UserDao userDao = new UserDao();
		UserInfo info = userDao.queryUser(phoneNumber, password);
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (info == null) {
			jsonObject.put("loginResult", LoginResults.FAIL);
			writer.print(jsonObject.toString());
		} else if (info.getStatus().equals(StatusEnum.expired)) {
			jsonObject.put("loginResult", LoginResults.EXPIRED);
			jsonObject.put("nickName", info.getNickName());
			writer.print(jsonObject.toString());
		} else if (info.getStatus().equals(StatusEnum.normal)){
			jsonObject.put("loginResult", LoginResults.SUCESS);
			jsonObject.put("nickName", info.getNickName());
			writer.print(jsonObject.toString());
		}
	}

}
