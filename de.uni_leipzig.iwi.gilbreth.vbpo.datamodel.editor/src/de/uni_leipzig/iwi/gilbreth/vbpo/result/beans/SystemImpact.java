package de.uni_leipzig.iwi.gilbreth.vbpo.result.beans;

/**
 * contains the impact of a system to the profit of a whole SPL.
 * 
 * @author Johannes MŸller
 *
 */
public class SystemImpact {
	public SystemImpact(String systemName, double impact) {
		super();
		this.systemName = systemName;
		this.impact = impact;
	}
	String systemName;
	double impact;
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public double getImpact() {
		return impact;
	}
	public void setImpact(double impact) {
		this.impact = impact;
	}

}
