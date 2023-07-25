package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.UserDao;

public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String code;
	private String loginId;
	private String password;
	private String name;
	private java.util.Date birthday;
	private String prefecture;
	private String address;
	private String tel;

    public UserBean() {
        this.code = "";
        this.loginId = "";
        this.password = "";
        this.name ="";
        this.birthday =null;
        this.prefecture ="";
        this.address ="";
        this.tel ="";
    }
    
    public UserBean(String loginId, String password, String name,String birthday, 
			String prefecture, String address, String tel) throws ParseException, SQLException {
		
    	
    	UserDao dao = new UserDao();
    	//codeを自動生成
    	int userCnt = 1 + dao.countUser();
    	
    	final String FORMAT = "%06";
    	String code = String.format(FORMAT, userCnt);
    	
    	
    	//引数をフィールドにセット
		this.setCode(code);
		this.setLoginId(loginId);
		this.setPassword(password);
		this.setName(name);
		this.setStrBirthday(birthday);
		this.setPrefecture(prefecture);
		this.setAddress(address);
		this.setTel(tel);
	}

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrBirthday() {
		//フォーマット生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(birthday);
		
		return strDate;
	}
	
	public java.sql.Date getSqlBirthday() {
		
		return new java.sql.Date(this.birthday.getTime());
		
	}
	
	
	public void setStrBirthday(String Birthday) throws ParseException {
		//フォーマット生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//文字列をDate型に変換
		java.util.Date date = sdf.parse(Birthday);
		this.birthday = date;
		
	}

	public void setUtilBirthday(java.util.Date birthday) {
		
		this.birthday = birthday;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
    

}
