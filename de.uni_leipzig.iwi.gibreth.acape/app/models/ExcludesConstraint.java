package models;

import javax.persistence.Entity;

/**
 * Constraint where the presence of feature object excludes the presence of feature subject
 * @author Max Lillack
 *
 */
@Entity
public class ExcludesConstraint extends FeatureConstraint {

	public ExcludesConstraint(String object, String subject) {
		super(object, subject);
	}

}
