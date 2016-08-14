package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CodeDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ForgetPwd
 */
@WebServlet("/ForgetPwd")
public class ForgetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPwd() {
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
		int code = Integer.parseInt(request.getParameter("code").toString());
		CodeDao dao = new CodeDao();
		int rightCode = dao.queryCode(phoneNumber);
		int status = 1;
		String passed = "";
		PrintWriter writer = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if (rightCode == 0) {
			status = 0;
		}else {
			if (rightCode == code) {
				passed = "TRUE";
			} else {
				passed = "FALSE";
			}
		}
		jsonObject.put("passed", passed);
		JSONObject result = new JSONObject();
		result.put("status", status);
		result.put("data", jsonObject);
		writer.print(result.toString());
	}

}
