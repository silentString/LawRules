package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserInfo;
import bean.UserInfo.StatusEnum;
import dao.UserDao;
import util.CommonEnums.RegisterResults;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		Date cur = new Date();
		String phoneNumber = request.getAttribute("phone_number").toString();
		String password = request.getAttribute("password").toString();
		String nickName = request.getAttribute("nick_name").toString();
		if (nickName == null || nickName.equals("")) {
			nickName = "afatiao";
		}
		UserInfo info = new UserInfo(phoneNumber, password, cur, 0, cur, nickName, StatusEnum.expired);
		UserDao dao = new UserDao();
		PrintWriter writer = response.getWriter();
		if (dao.userExist(phoneNumber)) {
			writer.print(RegisterResults.EXIST);//用户名已存在
		} else {
			if (dao.addUser(info)){
				writer.print(RegisterResults.SUCESS);
			} else {
				writer.print(RegisterResults.FAIL);
			}
		}
	
	}

}
