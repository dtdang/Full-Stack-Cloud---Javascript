package com.example.myapplication;

public class Product {
	private String prodID;
	private String prodName;
	private String prodStock;
	private String prodPrice;

	public Product(){
		super();
	}

	public Product(String prodID, String prodName, String prodStock, String prodPrice){
		super();
		this.prodID = prodID;
		this.prodName = prodName;
		this.prodStock = prodStock;
		this.prodPrice = prodPrice;
	}
	public String getProdID(){
		return prodID;
	}
	public void setProdID(String prodID){
		this.prodID = prodID;
	}
	public String getProdName(){
		return prodName;
	}
	public void setProdName(String prodName){
		this.prodName = prodName;
	}
	public String getProdStock(){
		return prodStock;
	}
	public void setProdStock(String prodStock){
		this.prodStock = prodStock;
	}
	public String getProdPrice(){
		return prodPrice;
	}
	public void setProdPrice(String prodPrice){
		this.prodPrice = prodPrice;
	}

	@Override
	public String toString(){
		return "Product {prodID:" + prodID + ", prodName:" + prodName + ", prodStock:" + prodStock + ", prodPrice:" + prodPrice + "}";
	}
}
