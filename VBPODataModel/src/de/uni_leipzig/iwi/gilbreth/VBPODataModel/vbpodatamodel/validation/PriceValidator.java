/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.validation;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Product;

import java.math.BigDecimal;

/**
 * A sample validator interface for {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Price}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface PriceValidator {
	boolean validate();

	boolean validateValue(BigDecimal value);
	boolean validatePriceForProduct(Product value);
}
