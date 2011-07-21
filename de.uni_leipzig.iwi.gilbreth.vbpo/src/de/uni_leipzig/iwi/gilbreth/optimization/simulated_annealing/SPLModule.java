/*   
 * Copyright 2011 Johannes Müller, University of Leipzig
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.uni_leipzig.iwi.gilbreth.optimization.simulated_annealing;

import org.opt4j.core.problem.ProblemModule;
import org.opt4j.operator.neighbor.BasicNeighborModule;
import org.opt4j.start.Constant;
import org.opt4j.viewer.VisualizationModule;

import com.google.inject.Provides;

/**
 * Compiles all configurations for the SPL optimization problem.
 * 
 * @author Johannes Müller
 *
 */
public class SPLModule extends ProblemModule {

	private SPLProblemDescription problemDescription = new SPLProblemDescription();
	
	
	@Provides
	public SPLProblemDescription getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(SPLProblemDescription problemDescription) {
		this.problemDescription = problemDescription;
	}

	@Override
	protected void config() {
		bindProblem(SPLCreator.class, SPLDecoder.class, SPLEvaluator.class);

		BasicNeighborModule.addNeighbor(this.binder(),
				NeighborSPLGenotype.class);

		VisualizationModule.addIndividualMouseListener(binder(),
				SPLProblemVisualization.class);

	}
}
