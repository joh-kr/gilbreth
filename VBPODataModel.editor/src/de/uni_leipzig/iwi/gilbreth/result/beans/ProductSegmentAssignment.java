package de.uni_leipzig.iwi.gilbreth.result.beans;


public class ProductSegmentAssignment {
	
	
	private String product;
	private String segment;
	private double price;
	
	public ProductSegmentAssignment(){
		
	}
	

	public ProductSegmentAssignment(String product, String segment, double price) {
		super();
		this.product = product;
		this.segment = segment;
		this.price = price;
	}

	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	

}
