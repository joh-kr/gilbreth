package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public abstract class Constraint extends Model {
	
	public Constraint(String object, String subject) {
		this.object = object;
		this.subject = subject;
	}

	@Required
    public String object;
	
	@Required
	public String subject;
}
