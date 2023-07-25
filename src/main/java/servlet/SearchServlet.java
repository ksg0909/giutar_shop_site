package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ItemDao;
import model.ItemBean;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String para = request.getParameter("search");
		
		ItemDao dao;

		//キーワードが入力されていない場合
		if (para == null || para == "") {

			try {

				dao = new ItemDao();
				List<ItemBean> bean = dao.getItemAll();
				
				request.setAttribute("searchItem", bean);
				request.setAttribute("searchSnt", "全て");
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			//入力されている場合
		} else {

			try {

				dao = new ItemDao();
				List<ItemBean> bean = dao.selectItem(para);

				request.setAttribute("searchItem", bean);
				request.setAttribute("searchSnt", para);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/search.jsp");
		rd.forward(request, response);
	}

}
