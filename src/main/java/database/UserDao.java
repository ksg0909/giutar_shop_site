package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserBean;

public class UserDao {

	//定数定義
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://guitarshopdb.cnxdndwifwma.us-east-1.rds.amazonaws.com:3306/shop?userSSL=false";
	private final String USER = "root";
	private final String PASSWORD = "rootroot";

	//変数定義
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public UserDao() throws SQLException {
		try {
			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName(JDBC_DRIVER);

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			this.con = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public void close() {
		try {
			// データベースとの接続を解除する
			if (this.con != null) {
				this.con.close();
			}
			if (this.ps != null) {
				this.ps.close();
			}
			if (this.rs != null) {
				this.rs.close();
			}
		} catch (SQLException se) {
			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}
	
	
	/**
	 * ログイン時にユーザー情報を取得する際使用するメソッド
	 * @param code
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public ResultSet selectUser(String loginId, String pass) throws SQLException {
		
		
			String Mysql = "SELECT * FROM user WHERE login_id = ? and password = ?";
			this.ps = con.prepareStatement(Mysql);
			
			this.ps.setString(1, loginId);
	        this.ps.setString(2, pass);

			this.rs = this.ps.executeQuery();
		
		return rs;
	}
	
	/**
	 * 登録されている件数を取得するメソッド
	 * @return
	 * @throws SQLException 
	 */
	public int countUser() {
		
		int count =0;
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			
			String Mysql = "SELECT count(*) FROM user";
			this.ps = con.prepareStatement(Mysql);
			
			this.rs = this.ps.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
			
			
		}catch(SQLException e){
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}
		
		return count;
		
		
	}
	
	
	/**
	 * データベースからユーザー情報をreturnするメソッド
	 * @param code	ユーザー情報を取得するのに必要なコード
	 * @return UserDto ユーザー情報を持ったUserDto
	 */
	public UserBean getUser(String code) {
		UserBean user = null;
		
		//①データベースへの接続
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			
			//②SQLステートメントの用意
			String Mysql = "SELECT * FROM user WHERE code = ?";
			PreparedStatement pstmt = con.prepareStatement(Mysql);

			//③SQLステートメントの実行（参照系）
			pstmt.setString(1, code);
			ResultSet res = pstmt.executeQuery();

			//④実行結果の受け取り 
			while (res.next()) {
				
				String loginId = res.getString("login_id");
				String password = res.getString("password");
				String name = res.getString("name");
				java.util.Date birthday = res.getTimestamp("birthday");
				String prefecture = res.getString("prefecture");
				String address = res.getString("address");
				String tel = res.getString("tel");
				
				
				
				
				//Beanインスタンス生成
				user = new UserBean();
				
				//BeanインスタンスにMySQLから受け取ったデータをセットする
				user.setCode(code);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setUtilBirthday(birthday);
				user.setPrefecture(prefecture);
				user.setAddress(address);
				user.setTel(tel);
				
			}

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * データベースからユーザー情報を全てreturnするメソッド
	 * @return
	 */
	public List<UserBean> getUserAll() {

		UserBean user = new UserBean();
		List<UserBean> userlist = new ArrayList<>();
		
		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			//②SQLステートメントの用意
			String sql = "SELECT * FROM user order by code";
			PreparedStatement pstmt = con.prepareStatement(sql);

			//③SQLステートメントの実行（参照系）
			ResultSet res = pstmt.executeQuery();

			//④実行結果の受け取り 
			while (res.next()) {

				String code = res.getString("code");
				String loginId = res.getString("login_id");
				String password = res.getString("password");
				String name = res.getString("name");
				java.util.Date birthday = res.getTimestamp("birthday");
				String prefecture = res.getString("prefecture");
				String address = res.getString("address");
				String tel = res.getString("tel");
				
				user.setCode(code);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setUtilBirthday(birthday);
				user.setPrefecture(prefecture);
				user.setAddress(address);
				user.setTel(tel);

				userlist.add(user);
			}

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return userlist;

	}

	/**
	 * データベースにユーザー情報を入力するメソッド
	 * @param user 
	 * @return int 処理数
	 */
	public int insertUser(UserBean user) {
		int count = 0;

		//JDBCドライバの読み込みを行う。
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String Mysql = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(Mysql);

			pstmt.setString(1, user.getCode());
			pstmt.setString(2, user.getLoginId());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getName());
			pstmt.setDate(5, user.getSqlBirthday());
			pstmt.setString(6, user.getPrefecture());
			pstmt.setString(7, user.getAddress());
			pstmt.setString(8, user.getTel());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			//処理が異常終了した場合
			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;

	}

	public int updateUser(UserBean user) {
		
		int count = 0;
		
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			
			String sql = "UPDATE user SET login_id = ?, password = ?, date = ?, "
					+ "prefecture = ?, address = ?, tel = ? WHERE code = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setDate(4, user.getSqlBirthday());
			pstmt.setString(5, user.getPrefecture());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getTel());
			pstmt.setString(8, user.getCode());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;

	}

	/**
	 * 指定されたコードのユーザー情報を削除するメソッド
	 * @param code 削除したいユーザーのコード
	 * @return int 処理数
	 */
	public int deleteUser(String code) {

		int count = 0;
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String sql = "DELETE from user WHERE code = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			System.out.println("処理が異常終了しました");
			e.printStackTrace();
		}

		return count;

	}

	public boolean loginLogic(String code, String password) {

		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

			String mysql = "SELECT password from user WHERE code = ?";
			PreparedStatement pstmt = con.prepareStatement(mysql);

			//SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			String pw = null;

			while (res.next()) {
				pw = res.getString("password");
			}

			if (pw.equals(password)) {
				return true;
			}

		} catch (SQLException e) {

		}

		return false;

	}

	

}