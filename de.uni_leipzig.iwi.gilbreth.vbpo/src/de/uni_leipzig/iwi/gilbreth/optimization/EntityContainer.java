package de.uni_leipzig.iwi.gilbreth.optimization;


/**
 * Container Data Type saving asset numbers and their contribution to profit
 * 
 * @author Johannes Müller
 * 
 */
public class EntityContainer implements Comparable<EntityContainer> {
	
	public double delta_profit;
	public int asset;

	public EntityContainer (double delta_profit, int asset) {
		this.delta_profit = delta_profit;
		this.asset = asset;
	}

	public int compareTo(EntityContainer o) {
		return Double.compare(this.delta_profit, o.delta_profit);
	}
	
	@Override
	public String toString(){
		return "Asset: " + asset + " profit cont: " + delta_profit;
	}
}