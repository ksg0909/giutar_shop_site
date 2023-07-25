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
import logic.CodeGen;
import model.UserBean;


@WebServlet("/InsertAccount")
public class InsertAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			UserBean user = new UserBean();
			UserDao dao = new UserDao();
			CodeGen cg = new CodeGen();
			HttpSession session = request.getSession();
			request.setCharacterEncoding("UTF-8");
			
			user.setCode(cg.getUserCode());
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setStrBirthday(request.getParameter("birthday"));
			user.setPrefecture(request.getParameter("prefecture"));
			user.setAddress(request.getParameter("address"));
			user.setTel(request.getParameter("tel"));
			
			//ユーザー情報をインサート
			dao.insertUser(user);
			
			//セッションにユーザー情報を保存
			session.setAttribute("user_bean", user);
			// ログイン状態
			session.setAttribute("login_state", "login");
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/createdAccount.jsp");
			rd.forward(request, response);
			
		} catch (ParseException|SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
