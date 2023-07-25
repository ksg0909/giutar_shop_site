package model;

import java.io.Serializable;

public class ItemBean implements Serializable {

	
	private String item_code;
	private String maker;
	private String name;
	private String serial;
	private int price;
	private String type;
	private int stock;
	
	public ItemBean() {
		setItem_code(null);
		setMaker(null);
		setName(null);
		setSerial(null);
		setPrice(0);
		setType(null);
		setStock(0);
	}
	
	public ItemBean(String item_code, String maker, String name, String serial, 
			int price, String type, int stock) {
		setItem_code(item_code);
		setMaker(maker);
		setName(name);
		setSerial(serial);
		setPrice(price);
		setType(type);
		setStock(stock);
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
