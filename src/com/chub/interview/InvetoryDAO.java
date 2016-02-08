package com.chub.interview;


import java.util.Hashtable;
import java.util.List;

public class InvetoryDAO {

	private Hashtable<String, List<Product>> productHash = new Hashtable<String, List<Product>>();
    
	public InvetoryDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Hashtable<String, List<Product>> getProduct() {
		return productHash;
	}

	public void setProduct(List<Product> product) {
		productHash.put("AppleProducts", product);
	}

	

}
