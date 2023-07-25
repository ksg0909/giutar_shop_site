package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import error.ValueNullException;


@WebServlet("/AccountConfirmation")
public class AccountConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AccountConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		
		try {
			
			String[] inputData = new String[7]; 
			
			
			inputData[0] = request.getParameter("loginId");
			inputData[1] = request.getParameter("password");
			inputData[2] = request.getParameter("name");
			inputData[3] = request.getParameter("birthday");
			inputData[4] = request.getParameter("prefecture");
			inputData[5] = request.getParameter("address");
			inputData[6] = request.getParameter("tel");
			
			for(String dataCheck: inputData) {
				if(nullCheck(dataCheck)) {
					throw new ValueNullException();
				}
			}
			
			request.setAttribute("inputData", inputData);
			
			rd = request.getRequestDispatcher("./WEB-INF/jsp/AccountConfirmation.jsp");
			
		} catch (Exception e) {
			
			request.setAttribute("errorMsg", "エラーが発生しました。");
			
			rd = request.getRequestDispatcher("./WEB-INF/jsp/createAnAccount.jsp");
			
		}
		
		rd.forward(request, response);
	}
	
	protected boolean nullCheck(String value) {
		
		if(value == null||value == "") {
			
			return true;
		}
		
		return false;
	}
	
	

}
