package com.chub.interview;

public class Product {

	private String productID;
	private String productName;
	private int reloadLevel;
	private int qty;
	private String productLocation;

	public Product(){
		
	}
	public Product(String productID, String name, int level, int qty, String loc) {
		this.productID=productID;
		this.productName = name;
		this.reloadLevel = level;
		this.productLocation = loc;
		this.qty=qty;
	
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLocation() {
		return productLocation;
	}
	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}
	public int getReloadLevel() {
		return reloadLevel;
	}
	public void setReloadLevel(int reloadLevel) {
		this.reloadLevel = reloadLevel;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	
	
	

	
}
