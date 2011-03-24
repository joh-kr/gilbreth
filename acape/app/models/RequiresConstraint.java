package models;
import javax.persistence.Entity;


@Entity
public class RequiresConstraint extends Constraint {
	public RequiresConstraint(String object, String subject) {
		super(object, subject);
	}
}
