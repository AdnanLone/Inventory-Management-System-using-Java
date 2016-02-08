package com.chub.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InvetoryManagementClient {
	public static InvetoryDAO productRepository= new InvetoryDAO();
	public static Lock lock = new ReentrantLock();

	public InvetoryManagementClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Initialize your Database object here
		 productRepository = 	initialize();
		 new Thread(new runner()).start();

	}
	
	
	static class runner implements Runnable {
        public void run(){
        	
        	Scanner in = new Scanner(System.in);
            System.out.println("Please pick an option");
            System.out.println("1 = Picking out an item");
            System.out.println("2 = Restocking out an item");
            System.out.println("3 = Cancel");
            int option = Integer.parseInt(in.nextLine());
            System.out.println("You Picked up" + option);
           	execute(option);
        }
	}
	public static void execute(int option){
         
		lock.lock();
		try {
			if(option==1){
				System.out.println("Enter Product ID:....... ");
				Scanner prodIDIn = new Scanner(System.in);
				String productID= prodIDIn.nextLine();

				System.out.println("Enter Product Qty:....... ");
				Scanner qtyIn = new Scanner(System.in);
				int Qty = Integer.parseInt(qtyIn.nextLine());

				// Call Method to pick up product
				System.out.println("Calling Pickup Product");
				PickingResult pickingResult = processPickProductFromSore(productID,Qty,productRepository);
				if(null!=pickingResult && pickingResult.getProduct().getQty() < pickingResult.getProduct().getReloadLevel()){

					System.out.println("Send a notifiaction or Alert to  manager ....");

				}
				System.out.println("Calling Pickup Product Done");
			} else if(option==2) {
				System.out.println("Restock Item");
				System.out.println("Enter Product ID:....... ");
				Scanner prodIDIn = new Scanner(System.in);
				String productID= prodIDIn.nextLine();
				System.out.println("Enter Amount to restock:....... ");
				Scanner amountToRestockIn = new Scanner(System.in);
				int amountToRestock = Integer.parseInt(amountToRestockIn.nextLine());

				RestockingResult restockresult = processRestocking(productID,amountToRestock, productRepository);
				if (null!= restockresult){

					System.out.println("Restock Result is: " +restockresult.getClass());
				}



			} else {

				System.out.println("Exit");
				// Do nothing 
			}
		} finally {
        lock.unlock();
    }
	}
	
	private static InvetoryDAO initialize(){
		
		InvetoryDAO invetoryDAO = new InvetoryDAO();
		List<Product> productList = new ArrayList<Product>();
		
		Product prod1 = new Product("IPH6","iPhone6", 2,10, "Walmart NY");
		Product prod2 = new Product("IPH6S","iPhone6S", 2,10, "Walmart NY"); 
		Product prod3 = new Product("ITV","Apple TV", 2,10, "Walmart NY");
		Product prod4 = new Product("iMac","Mac Book", 2,10, "Walmart NY");
		productList.add(prod1);
		productList.add(prod2);
		productList.add(prod3);
		productList.add(prod4);
		invetoryDAO.setProduct(productList);
		
		return invetoryDAO;
		
	}
	
	//public static synchronized  PickingResult processPickProductFromSore(String productID, int Qty, InvetoryDAO invetoryDAO ){
	public static PickingResult processPickProductFromSore(String productID, int Qty, InvetoryDAO invetoryDAO ){
		InvetoryManagementSystem ims = new InvetoryManagementSystemImpl(invetoryDAO); 
		PickingResult pickingResult=null;
		System.out.println("Product Id is:  "+productID);
		System.out.println("QTY Id is:  "+Qty);
		ProductValidator validator = new ProductValidator();
		Product  product = validator.checkIfProductExists(productID,productRepository);
		if(null!=product){
			System.out.println("Product" +productID +"  Found" );
			boolean qtyExist = validator.checkQuantity(productID, product, Qty);
			if(!qtyExist){
				System.out.println("Not enough Quanty...." );
			} else {
				pickingResult = ims.pickProduct(productID, Qty);
				
				System.out.println("############### PICKING RESULT   ###############");
				
				System.out.println(" On "  +pickingResult.getPickedUPDate()+ " "  +Qty + " "  + pickingResult.getProduct().getProductName() + " were picked up from " + pickingResult.getProduct().getProductLocation());
				
				System.out.println("###################");
							
				
			}
			
		}else {
			// Throw and exception that no product found. 
			System.out.println("Product" +productID +"  Not FOund in repository....." );
			
		}
		
		return pickingResult;
		
	}
	
	public static RestockingResult processRestocking(String productID, int amountToRestock, InvetoryDAO invetoryDAO){
		RestockingResult restockresult = null;
		
		System.out.println("Product ID for Restocking is "+productID);
		System.out.println("Amount  to Restock...  "+amountToRestock);
		InvetoryManagementSystem imsImpl = new InvetoryManagementSystemImpl(invetoryDAO);
		restockresult = imsImpl.restockProduct(productID, amountToRestock);
		
		
		return restockresult;
		
	} 

}
