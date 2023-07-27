package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ItemBean;

public class ItemDao {

	//定数定義
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://guitarshopdb.cnxdndwifwma.us-east-1.rds.amazonaws.com:3306/shop?userSSL=false";
	private final String USER = "root";
	private final String PASSWORD = "rootroot";

	//変数定義

	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public ItemDao() throws SQLException {

	}

	public ItemBean getItemByCode(String itemCode) {

		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//Beanインスタンス生成
		ItemBean item = new ItemBean();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String Mysql = "SELECT * FROM item WHERE item_code = ?";
			this.ps = con.prepareStatement(Mysql);

			this.ps.setString(1, itemCode);
			this.rs = ps.executeQuery();

			while (rs.next()) {

				String item_code = this.rs.getString("item_code");
				String maker = this.rs.getString("maker");
				String name = this.rs.getString("name");
				String serial = this.rs.getString("serial");
				int price = this.rs.getInt("price");
				String type = this.rs.getString("type");
				int stock = this.rs.getInt("stock");

				

				//BeanインスタンスにMySQLから受け取ったデータをセットする
				item.setItem_code(item_code);
				item.setMaker(maker);
				item.setName(name);
				item.setSerial(serial);
				item.setPrice(price);
				item.setType(type);
				item.setStock(stock);

			}

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return item;

	}

	public int buyItemByCode(String itemCode, int subStock) {
		
		int count = 0;
		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}


		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String sql = "UPDATE item SET  stock = ? WHERE item_code = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, subStock);
			pstmt.setString(2, itemCode);

			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;

	}

	public ArrayList<ItemBean> getItemAll() {

		ArrayList<ItemBean> itemlist = new ArrayList<>();

		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			//在庫があるもののみ表示する
			String sql = "SELECT * FROM item where stock > 0";
			this.ps = con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();

			while (rs.next()) {

				String itemCode = rs.getString("item_code");
				String maker = rs.getString("maker");
				String name = rs.getString("name");
				String serial = rs.getString("serial");
				int price = rs.getInt("price");
				String type = rs.getString("type");
				int stock = rs.getInt("stock");

				ItemBean item = new ItemBean();

				item.setItem_code(itemCode);
				item.setMaker(maker);
				item.setName(name);
				item.setSerial(serial);
				item.setPrice(price);
				item.setType(type);
				item.setStock(stock);

				itemlist.add(item);
			}

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return itemlist;

	}

	public ArrayList<ItemBean> selectItem(String s) {

		ArrayList<ItemBean> itemlist = new ArrayList<>();
		String[] sql = {
				"SELECT * FROM item where item_code like ? and stock > 0",
				"SELECT * FROM item where maker like ? and stock > 0",
				"SELECT * FROM item where name like ? and stock > 0",
				"SELECT * FROM item where serial like ? and stock > 0",
				"SELECT * FROM item where type like ? and stock > 0" };

		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			for (String sqlS : sql) {

				this.ps = con.prepareStatement(sqlS);
				this.ps.setString(1, "%" + s + "%");
				this.rs = ps.executeQuery();
				//データの被りチェック用カウント
				boolean dup = false;

				while (rs.next()) {
					String itemCode = this.rs.getString("item_code");
					String maker = this.rs.getString("maker");
					String name = this.rs.getString("name");
					String serial = this.rs.getString("serial");
					int price = this.rs.getInt("price");
					String type = this.rs.getString("type");
					int stock = this.rs.getInt("stock");

					if (itemlist.isEmpty()) {
					} else {
						for (ItemBean check : itemlist) {
							if (check.getItem_code() == itemCode) {
								dup = true;
								break;
							}
						}
						if (dup == true) {
							continue;
						}
					}

					ItemBean item = new ItemBean();

					item.setItem_code(itemCode);
					item.setMaker(maker);
					item.setName(name);
					item.setSerial(serial);
					item.setPrice(price);
					item.setType(type);
					item.setStock(stock);

					itemlist.add(item);

				}
			}

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return itemlist;
	}

	public int buyItem(ArrayList<ItemBean> cartItem) {
		
		int count = 0;
		
		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}


		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			
			
			for(ItemBean ib : cartItem) {
				
				String itemCode = ib.getItem_code();
				ItemBean cBean = new ItemBean();
				cBean = this.getItemByCode(itemCode);
				//在庫を引いてその結果をint型に保持
				int subStock = cBean.getStock() - ib.getStock();
				
				String sql = "UPDATE item SET  stock = ? WHERE item_code = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, subStock);
				pstmt.setString(2, itemCode);

				count =+ pstmt.executeUpdate();
			}

		} catch (SQLException e) {

			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;

	}

}
