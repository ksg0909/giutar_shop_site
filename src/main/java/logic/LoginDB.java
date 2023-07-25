package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.UserDao;
import model.UserBean;

public class LoginDB {

	public UserBean getUserData(String id, String pass) {
		UserBean bean = null;
		UserDao dao = null;
		ResultSet rs;
		
		try {
			// DAOクラスをインスタンス化
			dao = new UserDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs = dao.selectUser(id, pass);

			while (rs.next()) {

				// 検索結果が存在する場合はbeanに値をセット（結果が1件しか返らないことを想定）
				bean = new UserBean();

				
				
				
				java.util.Date birthday = rs.getTimestamp("birthday");
				
				//DtoインスタンスにMySQLから受け取ったデータをセットする
				bean.setCode(rs.getString("code"));
				bean.setLoginId(rs.getString("login_id"));
				bean.setPassword(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setUtilBirthday(birthday);
				bean.setPrefecture(rs.getString("prefecture"));
				bean.setAddress(rs.getString("address"));
				bean.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			if (dao != null) {
				dao.close();
			}
		}
		return bean;
	}
}
