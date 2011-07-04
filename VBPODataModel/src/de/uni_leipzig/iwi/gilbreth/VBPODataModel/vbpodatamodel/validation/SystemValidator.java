/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.validation;

import de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.Asset;

import java.math.BigDecimal;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link de.uni_leipzig.iwi.gilbreth.VBPODataModel.vbpodatamodel.System}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SystemValidator {
	boolean validate();

	boolean validateImplementationCost(BigDecimal value);
	boolean validateSystemUsesAsset(EList<Asset> value);
}
