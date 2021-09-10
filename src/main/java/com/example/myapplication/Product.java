package com.example.myapplication;

public class Product {
	private String prodID;
	private String prodName;
	private int prodStock;
	private int prodPrice;
	public Product(){
		super();
	}
	public Product(String prodID, String prodName, int prodStock, int prodPrice){
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
	public int getProdStock(){
		return prodStock;
	}
	public void setProdStock(int prodStock){
		this.prodStock = prodStock;
	}
	public int getProdPrice(){
		return prodPrice;
	}
	public void setProdPrice(int prodPrice){
		this.prodPrice = prodPrice;
	}
}
