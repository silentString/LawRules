package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.SendSMS;
import dao.CodeDao;
import net.sf.json.JSONObject;
import util.Common;

/**
 * Servlet implementation class IdentifyCode
 */
@WebServlet("/IdentifyCode")
public class IdentifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentifyCode() {
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
		int content = Common.randomNum();
		SendSMS send = new SendSMS();
		String result = send.sendMessage(phoneNumber, content + "");
		CodeDao codeDao = new CodeDao();
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		int status = 1;
		if (result.startsWith("success")){
			if (!codeDao.updateCode(phoneNumber, content)){
				status = 0;
			} 
		} else {
			status = 0;
		}
		jsonObject.put("status", status);
		writer.print(jsonObject.toString());
	}

}
