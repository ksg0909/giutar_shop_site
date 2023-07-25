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
import logic.CodeGen;
import model.HistoryBean;
import model.ItemBean;
import model.UserBean;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		//ログインされてない場合はログイン、または会員登録のページに誘導
		if (session.getAttribute("login_state") == null) {
			
			request.setAttribute("payment_state", "logout");
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/paymentFailed.jsp");
			rd.forward(request, response);

			//ユーザー情報があるため購入手続き続行
		} else {

			try {
				//在庫不足でご購入できない商品の情報
				ArrayList<ItemBean> shortage = new ArrayList<>();
				ArrayList<ItemBean> cartlist = (ArrayList<ItemBean>) session.getAttribute("CartList");
				
				UserBean user = (UserBean) session.getAttribute("user_bean");
				HistoryDao hiDao = new HistoryDao();
				ItemDao itDao;
				itDao = new ItemDao();

				//在庫チェック
				for (ItemBean ib : cartlist) {

					String itemCode = ib.getItem_code();
					ItemBean checkBean = new ItemBean();
					checkBean = itDao.getItemByCode(itemCode);

					if (ib.getStock() > checkBean.getStock()) {
						shortage.add(ib);
					}
				}
				
				//商品不足があった場合
				if(shortage.size() >= 1) {
					
					request.setAttribute("payment_state", "shortage");
					request.setAttribute("shortage", shortage);
					RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/paymentFailed.jsp");
					rd.forward(request, response);
				
				//商品不足がなく、問題なくご購入できる場合
				}else {
					//購入の際の在庫の変動を処理
					itDao.buyItem(cartlist);
					
					//ご購入履歴を作成
					
					CodeGen cg = new CodeGen();
					for(ItemBean ib:cartlist) {
						
						HistoryBean hb = new HistoryBean
							(cg.getHistoryCode(),user.getCode(), ib.getItem_code() ,ib.getStock());
						
						hiDao.insertHistory(hb);
					}
					
					
					
					
				}
				

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//ご購入成功画面
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/paymentSucces.jsp");
			rd.forward(request, response);
		}

	}

}
