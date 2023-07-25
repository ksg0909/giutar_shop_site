package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryBean {

	private String code;
	private String buyerCode;
	private String itemCode;
	private java.util.Date buyDate;
	private int quantity;
	
	public HistoryBean() {
		this.code = null;
		this.buyerCode = null;
		this.itemCode = null;
		this.buyDate = null;
		this.quantity = 0;
	}
	
	
	public HistoryBean(String code, String buyCode, String itemCode, int quantity) {
		this.code = code;
		this.buyerCode = buyCode;
		this.itemCode = itemCode;
		this.buyDate = new Date();
		this.quantity = quantity;
	}
	
	public HistoryBean(String code, String buyCode, String itemCode, Date buyDate, int quantity) {
		this.code = code;
		this.buyerCode = buyCode;
		this.itemCode = itemCode;
		this.buyDate = buyDate;
		this.quantity = quantity;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public java.util.Date getBuyDate() {
		return buyDate;
	}
	
	public String getStrBuyDate() {
		//フォーマット生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = sdf.format(this.buyDate);
		
		return strDate;
	}
	
	public java.sql.Date getSqlBuyDate() {
		
		return new java.sql.Date(this.buyDate.getTime());
		
	}

	public void setBuyDate(java.util.Date buyDate) {
		this.buyDate = buyDate;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
