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
import dao.CodeDao;
import dao.UserDao;
import net.sf.json.JSONObject;
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
		String phoneNumber = request.getParameter("phone_number").toString();
		String password = request.getParameter("password").toString();
		String nickName = request.getParameter("nick_name").toString();
		int code = Integer.parseInt(request.getParameter("code").toString());
		if (nickName == null || nickName.equals("")) {
			nickName = "afatiao";
		}
		UserInfo info = new UserInfo(phoneNumber, password, cur, 0, cur, nickName, StatusEnum.expired);
		UserDao dao = new UserDao();
		CodeDao codeDao = new CodeDao();
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (dao.userExist(phoneNumber)) {
			jsonObject.put("data", RegisterResults.EXIST);//用户名已存在
		} else {
			int rightCode = codeDao.queryCode(phoneNumber);
			if (code == rightCode) {
				if (dao.addUser(info)){
					jsonObject.put("data", RegisterResults.SUCCESS);//register sucess
				} else {
					jsonObject.put("data", RegisterResults.FAIL);
				}
			} else {
				jsonObject.put("data", RegisterResults.WRONGCODE);
			}
		
		}
		jsonObject.put("status", 1);
		writer.print(jsonObject.toString());
	
	}

}
