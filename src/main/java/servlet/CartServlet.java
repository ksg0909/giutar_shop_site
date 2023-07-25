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

import database.ItemDao;
import model.ItemBean;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CartServlet() {
        super();
        
    }

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String para = request.getParameter("button");
		
		
		
		
		HttpSession session = request.getSession();
		
		//カートを空にする処理(セッションの破棄）
		if (para.equals("empty")) {
			
			session.removeAttribute("CartList");
		
		//カートの特定の商品のみ削除する処理
		}else if(para.equals("delete")) {
			
			String itemId = request.getParameter("item_id");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			ArrayList<ItemBean> cartlist = (ArrayList<ItemBean>) session.getAttribute("CartList");
			ItemBean dbib = null;
			String Id = "";
			
			//指定された商品の特定、削除
			for(int i = 0 ;i < cartlist.size() ; i++) {
				
				dbib = cartlist.get(i);
				Id = dbib.getItem_code();
				//指定された商品の特定
				if(Id.equals(itemId)) {
					//指定された数引く処理
					dbib.setStock(dbib.getStock() - quantity);
					
					//カートの商品の個数が0以下の場合、商品情報を削除
					if(dbib.getStock() <= 0) {
						cartlist.remove(i);
					}
					
					
					
					
					
				}
			}
			//カートの中身が0の場合セッションのカートを削除
			if(cartlist.isEmpty()) {
				session.removeAttribute("CartList");
			}
			
			
			session.setAttribute("CartList", cartlist);
		}else {
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/cart.jsp");
		rd.forward(request, response);
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		ArrayList<ItemBean> cartlist = new ArrayList<ItemBean>();
		ItemBean bean = new ItemBean();
		String para = request.getParameter("item_id");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		HttpSession session = request.getSession();
		try {
			ItemDao dao = new ItemDao();
			
			
			
			if(session.getAttribute("CartList") == null) {
				bean = dao.getItemByCode(para);
				bean.setStock(quantity);
				cartlist.add(bean);
				session.setAttribute("CartList", cartlist);
				
			}else {
				
				cartlist = (ArrayList<ItemBean>) session.getAttribute("CartList");
				bean = dao.getItemByCode(para);
				bean.setStock(quantity);
				cartlist.add(bean);
				session.setAttribute("CartList",cartlist);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/jsp/cart.jsp");
		rd.forward(request, response);
		
		
	}

}
