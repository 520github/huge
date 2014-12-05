package me.power.speed.test.cache.dict;

public class Product {
	public  int productid;
	private  String productname;
	
	public Product(int productid, String productname) {
		this.productid = productid;
		this.productname = productname;
	}
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
}
