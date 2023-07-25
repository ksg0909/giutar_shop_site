package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LinkServlet
 */
@WebServlet("/LinkServlet")
public class LinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

        String para = request.getParameter("link");
        
        
        if (para.equals("login_main")) {
            
            RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/login_main.jsp");
			rd.forward(request, response);
            
        }else if(para.equals("createAnAccount")) {
        	
        	RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/createAnAccount.jsp");
			rd.forward(request, response);
        }else if(para.equals("myPage")) {
        	
        	RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/myPage.jsp");
			rd.forward(request, response);
        }else if(para.equals("myPageAccountInfo")) {
        	
        	RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/myPageAccountInfo.jsp");
			rd.forward(request, response);
        }else if(para.equals("guide")) {
        	
        	RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/guide.jsp");
			rd.forward(request, response);
        	
        }
	}

}
