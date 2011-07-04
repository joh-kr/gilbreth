package de.uni_leipzig.iwi.gilbreth.result.beans;

public class ProductFeatures {
	
	private String product;
	
	private String[] features;
	
	private double price;
	
	

	public ProductFeatures(String product, String[] features, double price) {
		super();
		this.product = product;
		this.features = features;
		this.price = price;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String[] getFeatures() {
		return features;
	}

	public void setFeatures(String[] features) {
		this.features = features;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
