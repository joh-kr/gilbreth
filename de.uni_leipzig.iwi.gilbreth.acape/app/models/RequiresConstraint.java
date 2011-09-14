package models;
import javax.persistence.Entity;

/**
 * Constraint where the presence of feature object implies the presence of feature subject
 * @author Max Lillack
 *
 */
@Entity
public class RequiresConstraint extends FeatureConstraint {
	public RequiresConstraint(String object, String subject) {
		super(object, subject);
	}
}
