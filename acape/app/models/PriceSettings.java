package models;

import java.math.BigDecimal;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class PriceSettings extends Model {
	@Required
	public BigDecimal minimumPrice;
	
	@Required
	public BigDecimal maximumPrice;
}
