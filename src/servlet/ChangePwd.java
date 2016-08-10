package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangePwd
 */
@WebServlet("/ChangePwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwd() {
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
		String phoneNumber = request.getParameter("phone_number").toString();
		String newPwd = request.getParameter("new_password").toString();
		UserDao userDao = new UserDao();
		String name = userDao.changePwd(phoneNumber, newPwd);
		int status = 1;
		JSONObject data = new JSONObject();
		if (null == name) {
			status = 0;
		} else if (name.equals("")) {
			data.put("changed", "FALSE");
		} else {
			data.put("changed", "TRUE");
			data.put("nick_name", name);
		}
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("data", data);
		PrintWriter writer = response.getWriter();
		writer.print(result.toString());
		
	}

}
