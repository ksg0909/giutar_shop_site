package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.HistoryDao;
import database.ItemDao;
import model.HistoryBean;
import model.ItemBean;
import model.UserBean;


@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//ログインしてない状態での処理
		if(session.getAttribute("login_state").equals(null)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		//ログインしてる状態での処理
		}else {
			UserBean user = (UserBean) session.getAttribute("user_bean");
			ArrayList<ItemBean> iblist = new ArrayList<>();
			 new ArrayList<>();
			
			try {
				ItemDao  itDao = new ItemDao();
				HistoryDao hiDao = new HistoryDao();
				
				//ユーザーコードから履歴をsqlから呼び出し
				ArrayList<HistoryBean> hblist = hiDao.getHistoryAll(user.getCode());
				session.setAttribute("History", hblist);
				
				//履歴がない場合の処理
				if(hblist.isEmpty()) {
					RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/myPageHistory.jsp");
					rd.forward(request, response);
				}
				//履歴ありの処理
				//履歴のアイテムコードからDaoを使い商品情報をItemBeanのArrayListに格納
				for(HistoryBean hb :hblist) {
					boolean dup = false;
					String itemcode = hb.getItemCode();
					ItemBean ib = itDao.getItemByCode(itemcode);
					//被り防止のチェック
					for(ItemBean ibcheck :iblist) {
						if(ibcheck.getItem_code().equals(ib)) {
							dup = true;
						}
						
					}
					//被りがあった場合リストに入れずループを回す
					if(dup) {
						continue;
					}
					iblist.add(ib);
				}
				
				
				
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} 
			session.setAttribute("HisItem", iblist);
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/myPageHistory.jsp");
			rd.forward(request, response);
		}
	}

	
	

}
