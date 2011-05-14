package models;

import javax.persistence.Entity;

@Entity
public class ExcludesConstraint extends FeatureConstraint {

	public ExcludesConstraint(String object, String subject) {
		super(object, subject);
	}

}
