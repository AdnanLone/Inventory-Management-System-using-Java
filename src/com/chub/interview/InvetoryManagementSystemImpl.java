package com.chub.interview;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class InvetoryManagementSystemImpl  implements InvetoryManagementSystem {
	InvetoryDAO invetoryDAO = null;
	public InvetoryManagementSystemImpl(InvetoryDAO invetoryDAO ) {
		// TODO Auto-generated constructor stub
		this.invetoryDAO =invetoryDAO;
	}

	@Override
	public  PickingResult pickProduct(String productId, int amountToPick) {
		// TODO Auto-generated method stub
		PickingResult pickingResult = new PickingResult();
		List<Product> updateProduct = new ArrayList<Product>();
		Product newProduct = new Product();
		Hashtable<String, List<Product>>  productList = invetoryDAO.getProduct();
		// I assume only one product type (AppleProducts)
		List<Product> products = productList.get("AppleProducts");
		for(Product prod : products){
			if(prod.getProductID().equals(productId)){
					// Update Hashtable here....
				newProduct.setProductID(productId);
				newProduct.setProductLocation(prod.getProductLocation());
				newProduct.setProductName(prod.getProductName());
				newProduct.setReloadLevel(prod.getReloadLevel());
				newProduct.setQty(prod.getQty() - amountToPick );
				updateProduct.add(newProduct);
				
				pickingResult.setProduct(newProduct);
				pickingResult.setPickedUPDate(new Date());
				pickingResult.setPickupStatus("Processed");
							
			} else {
				updateProduct.add(prod);
			}
		}
		
		products.clear();
		products.addAll(updateProduct);
		System.out.println("Transaction completed....");
		
		
		return pickingResult;
		
	}

	@Override
	public  RestockingResult restockProduct(String productId, int amountToRestock) {
		boolean productIDFound = false;
		RestockingResult reStockresult= new RestockingResult();
		Hashtable<String, List<Product>>  productList = invetoryDAO.getProduct();
		// I assume only one product type (AppleProducts)
		List<Product> products = productList.get("AppleProducts");
		for(Product prod : products){
			if(prod.getProductID().equals(productId)){
				// Update quantity.
				prod.setQty(prod.getQty() + amountToRestock );
				productIDFound = true;
				reStockresult.setProduct(prod);
				reStockresult.setReStockMsg("Restocked Product " +productId);
				
				
				
			} 
		}
		// If no previous Product ID found Add it to list or throw an exception, In my case I am throwing exception
		if(!productIDFound){
			System.out.println("No Such Product found...");
		}
	
		return null;
	}
	

}
