package de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing;

import org.opt4j.operator.neighbor.BasicNeighborModule;
import org.opt4j.viewer.VisualizationModule;

import com.google.inject.Provides;

public class VbpoLinOpModule extends VbpoModule {


	@Override
	protected void config() {
		bindProblem(VbpoCreator.class, VbpoDecoder.class, VbpoEvaluator.class);

		BasicNeighborModule.addNeighbor(this.binder(),
				VbpoLinOpNeighbor.class);

		VisualizationModule.addIndividualMouseListener(binder(),
				VbpoProblemVisualization.class);

	}
}
