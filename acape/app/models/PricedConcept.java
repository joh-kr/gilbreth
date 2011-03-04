package models;

import java.math.BigDecimal;

/**
 * A concept that has a price assigned.
 * 
 * @author Johannes Müller
 */
public class PricedConcept extends Concept {
	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
