package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.LoginDB;
import model.UserBean;

@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String para = request.getParameter("submit");

		HttpSession session = request.getSession();

		if (para.equals("ログアウト")) {
			// ④ クリックされたボタンが「logout」の場合はログアウト処理（セッションの破棄）を実施
			session.removeAttribute("login_state");
			session.removeAttribute("user_bean");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String btn = request.getParameter("submit");

		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		//入力された情報をユーザー情報(MySQL)と照合し、ステータス設定とユーザー情報の紐づけ
		if (btn.equals("ログイン")) {
			
			String id = request.getParameter("id");
			String pass = request.getParameter("password");

			
			LoginDB login = new LoginDB();

			
			UserBean bean = login.getUserData(id, pass);

			
			if (bean != null) {
				
				session.setAttribute("user_bean", bean);
				
				session.setAttribute("login_state", "login");
				
				
				rd = request.getRequestDispatcher("index.jsp");
			} else {
				
				rd = request.getRequestDispatcher("WEB-INF/jsp/loginFailed.jsp");
			}
			
			rd.forward(request, response);
			
		//ログアウト処理
		} else if (btn.equals("ログアウト")) {
			
			session.removeAttribute("login_state");
			session.removeAttribute("login_user_bean");
			
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
