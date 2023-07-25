package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.HistoryBean;

public class HistoryDao {

	//定数定義
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/shop?userSSL=false";
	private final String USER = "root";
	private final String PASSWORD = "root";

	//変数定義
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public int countHistory() {

		int count = 0;

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String Mysql = "SELECT count(*) FROM order_history";
			this.ps = con.prepareStatement(Mysql);
			this.rs = this.ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;

	}

	public ArrayList<HistoryBean> getHistoryAll(String buyerCode) throws SQLException {

		ArrayList<HistoryBean> historyList = new ArrayList<>();
		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String sql = "SELECT * from order_history where buyer_code = ?";

			this.ps = con.prepareStatement(sql);
			this.ps.setString(1, buyerCode);

			this.rs = this.ps.executeQuery();

			while (rs.next()) {

				String code = rs.getString("code");
				String itemCode = rs.getString("item_code");
				java.util.Date buyDate = rs.getTimestamp("buy_date");
				int quantity = rs.getInt("quantity");

				HistoryBean his = new HistoryBean();

				his.setCode(code);
				his.setBuyerCode(buyerCode);
				his.setItemCode(itemCode);
				his.setBuyDate(buyDate);
				his.setQuantity(quantity);

				historyList.add(his);
			}

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return historyList;
	}

	public int insertHistory(HistoryBean bean) throws SQLException {

		int count = 0;

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			
				String sql = "insert into order_history values(?,?,?,?,?)";
				this.ps = con.prepareStatement(sql);

				this.ps.setString(1, bean.getCode());
				this.ps.setString(2, bean.getBuyerCode());
				this.ps.setString(3, bean.getItemCode());
				this.ps.setDate(4, bean.getSqlBuyDate());
				this.ps.setInt(5, bean.getQuantity());
				count = this.ps.executeUpdate();
			

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;
	}
}
