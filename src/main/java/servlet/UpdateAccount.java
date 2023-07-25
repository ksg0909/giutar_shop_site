package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.UserDao;
import model.UserBean;


@WebServlet("/UpdateAccount")
public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String state = (String)request.getParameter("state");
		
		if(state.equals("before")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/UpdateAccount.jsp");
			rd.forward(request, response);
			
		}else if(state.equals("after")) {
			
			HttpSession session = request.getSession();
			UserBean user = new UserBean();
			
			UserBean idBean = (UserBean)session.getAttribute("user_bean");
			
			
			try {
				user.setCode(idBean.getCode());
				user.setLoginId(request.getParameter("loginId"));
				user.setPassword(request.getParameter("password"));
				user.setName(request.getParameter("name"));
				user.setStrBirthday(request.getParameter("birthday"));
				user.setPrefecture(request.getParameter("prefecture"));
				user.setAddress(request.getParameter("address"));
				user.setTel(request.getParameter("tel"));
				
				request.setAttribute("Bean", user);
				
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/UpdateConf.jsp");
			rd.forward(request, response);
			
		}else if(state.equals("conf")) {
			
			try {
				UserDao dao = new UserDao();
				
				HttpSession session = request.getSession();
				UserBean user = (UserBean) session.getAttribute("provBean");
				
				dao.updateUser(user);
				session.setAttribute("user_bean", user);
				
				
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/UpdateSucces.jsp");
			rd.forward(request, response);
			
		}
		
		
		
	}

}
