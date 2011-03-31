/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package acape;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link acape.Attribute#getLevels <em>Levels</em>}</li>
 * </ul>
 * </p>
 *
 * @see acape.AcapePackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends Entity {
	/**
	 * Returns the value of the '<em><b>Levels</b></em>' containment reference list.
	 * The list contents are of type {@link acape.Level}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Levels</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Levels</em>' containment reference list.
	 * @see acape.AcapePackage#getAttribute_Levels()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	EList<Level> getLevels();

} // Attribute
