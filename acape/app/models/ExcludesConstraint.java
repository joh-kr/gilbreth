package models;

import javax.persistence.Entity;

@Entity
public class ExcludesConstraint extends Constraint {

	public ExcludesConstraint(String object, String subject) {
		super(object, subject);
	}

}
