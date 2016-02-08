package com.chub.interview;

import java.util.Hashtable;
import java.util.List;

public class ProductValidator {

	public ProductValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public Product checkIfProductExists(String productID, InvetoryDAO invetoryDAO){
		// Iterate through Hahstable to find Product ID..
		Hashtable<String, List<Product>>  productList = invetoryDAO.getProduct();
		List<Product> products = productList.get("AppleProducts");
		for(Product prod : products){
			if(prod.getProductID().equals(productID)){
					return prod;
			}
		}
		
		return null;
	}
	
	public boolean checkQuantity(String productID, Product product, int Qty){
		boolean found = false;
		System.out.println("Product ID is " +product.getProductID());
		System.out.println("Qty  ID is " +product.getQty());
		if(Qty <= product.getQty()){
			found = true;
		}
		System.out.println("Quanty found is:" +found);
		return found;
	}

}
