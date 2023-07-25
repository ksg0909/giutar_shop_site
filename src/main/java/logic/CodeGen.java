package logic;

import java.sql.SQLException;

import database.HistoryDao;
import database.UserDao;

public class CodeGen {

	/**
	 * ユーザーコードを自動生成(連番)
	 * @return コード(String型)
	 * @throws SQLException 
	 */
	public String getUserCode() throws SQLException {
		
		UserDao dao = new UserDao();
		int intCode = dao.countUser() + 1;
		
		final String FORMAT = "%06d";
		String code = String.format(FORMAT, intCode);
		
		return code;
	}
	
	
	/**
	 * 購入履歴コードを自動生成(連番)
	 * @return コード(String型)
	 * @throws SQLException 
	 */
	public String getHistoryCode() throws SQLException {
		
		HistoryDao dao = new HistoryDao();
		int intCode = dao.countHistory() + 1;
		final String FORMAT = "%07d";
		String code = String.format(FORMAT, intCode);
		
		return code;
		
	}
}
