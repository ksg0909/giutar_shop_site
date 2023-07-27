package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ログインDAOクラス.
 */
public class LoginDao {

	//変数定義
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

	//定数定義
	private final String URL = "jdbc:mysql://guitarshopdb.cnxdndwifwma.us-east-1.rds.amazonaws.com:3306/shop?userSSL=false";
	private final String USER = "root";
	private final String PASSWORD = "rootroot";    
    
    public LoginDao() throws SQLException {
        try {
            // JDBCドライバのロード
            // 「com.mysql.jdbc.Driver」クラス名
            Class.forName("com.mysql.jdbc.Driver");

            // データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
            this.con = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * データベースから指定されたIDとパスワードを使ってユーザ情報を検索します.
     *
     * @param id	ログインID
     * @param pass	パスワード
     * @return	ユーザ情報（ResultSet）
     * @throws SQLException
     */
    public ResultSet selectUser(String id, String pass) throws SQLException {
        // SQL文を生成
        this.ps = this.con.prepareStatement("select name, age from user where id = ? and pass = ?");

        // 生成したSQL文の「？」の部分にIDとパスワードをセット
        this.ps.setString(1, id);
        this.ps.setString(2, pass);

        // SQLを実行
        this.rs = this.ps.executeQuery();
        
        return this.rs;
    }

    /**
     * コネクションをクローズします.
     */
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
}