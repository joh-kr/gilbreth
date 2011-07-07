package de.uni_leipzig.iwi.gilbreth.result.beans;

public class AssetImpact {
	public AssetImpact(String assetName, double impact) {
		super();
		this.assetName = assetName;
		this.impact = impact;
	}
	String assetName;
	double impact;
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public double getImpact() {
		return impact;
	}
	public void setImpact(double impact) {
		this.impact = impact;
	}
}
