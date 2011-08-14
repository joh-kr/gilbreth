package models;

import java.math.BigDecimal;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.*;

/**
 * The minimum and maximum price to be used in the price etimation stage is a configuration parameter
 * to be set by a domain expert
 * 
 * @author Max Lillack
 *
 */
@Entity
public class PriceSettings extends Model {
	@Required
	public BigDecimal minimumPrice;
	
	@Required
	public BigDecimal maximumPrice;
}
