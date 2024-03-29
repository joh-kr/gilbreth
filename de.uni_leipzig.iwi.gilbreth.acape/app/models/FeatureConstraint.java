package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * Abstract class for representing binary constraints
 * @author Max Lillack
 *
 */
@Entity
public abstract class FeatureConstraint extends Model {
	
	public FeatureConstraint(String object, String subject) {
		this.object = object;
		this.subject = subject;
	}

	@Required
    public String object;
	
	@Required
	public String subject;
}
