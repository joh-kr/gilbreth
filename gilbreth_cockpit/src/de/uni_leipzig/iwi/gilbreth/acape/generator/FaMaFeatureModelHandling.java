package de.uni_leipzig.iwi.gilbreth.acape.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import acape.Attribute;
import acape.Level;

import es.us.isa.FAMA.Reasoner.QuestionTrader;
import es.us.isa.FAMA.Reasoner.questions.CommonalityQuestion;
import es.us.isa.FAMA.Reasoner.questions.FilterQuestion;
import es.us.isa.FAMA.Reasoner.questions.NumberOfProductsQuestion;
import es.us.isa.FAMA.Reasoner.questions.SetQuestion;
import es.us.isa.FAMA.Reasoner.questions.ValidQuestion;
import es.us.isa.FAMA.models.featureModel.GenericFeature;
import es.us.isa.FAMA.models.featureModel.GenericFeatureModel;

public class FaMaFeatureModelHandling {

	private GenericFeatureModel featureModel;

	private QuestionTrader qt;
	
	private double numberOfProducts;
	
	public FaMaFeatureModelHandling(String modelPath){
		qt = new QuestionTrader();
		featureModel = (GenericFeatureModel) qt.openFile(modelPath);
		qt.setVariabilityModel(featureModel);
		
		numberOfProducts =  calculateNumberOfProducts();
	}
	
	// ---- Public section
	
	@SuppressWarnings("unchecked")
	public List<String> selectAttributeFeatures(){
		
		List<VarIFFeatureCorrespondence> varIFFeatureList = new ArrayList<VarIFFeatureCorrespondence>();
		List<String> nameList = new ArrayList<String>();
		double treshold = 0.2d;
		
		for(GenericFeature f :featureModel.getFeatures()){
			varIFFeatureList.add(new VarIFFeatureCorrespondence(calculateVarIF(f), f.getName()));
		}
		Collections.sort(varIFFeatureList);
		Collections.reverse(varIFFeatureList);
		
		for(VarIFFeatureCorrespondence o : varIFFeatureList){
			if(o.varIF < treshold) break;		
			nameList.add(o.name);
		}
		
		return nameList;
		
	}
	
	
	
	// ---- Utility Functions
	
	private boolean modelIsValid() {
		ValidQuestion vq = (ValidQuestion) qt.createQuestion("Valid");
		qt.ask(vq);
		return vq.isValid();
	}

	private long calculateCommonalityOf(GenericFeature f) {
		CommonalityQuestion q = (CommonalityQuestion) qt
				.createQuestion("Commonality");
		q.setFeature(f);
		qt.ask(q);
		return q.getCommonality();
	}

	private double calculateNumberOfProductsWithout(GenericFeature f) {
		FilterQuestion fq = (FilterQuestion) qt.createQuestion("Filter");
		fq.removeValue(f);
		

		NumberOfProductsQuestion npq = (NumberOfProductsQuestion) qt
				.createQuestion("#Products");

		// You need to create a set question to see the results of applying the
		// filter defined before
		SetQuestion sq = (SetQuestion) qt.createQuestion("Set");
		sq.addQuestion(fq);
		sq.addQuestion(npq);
		qt.ask(sq);
		return npq.getNumberOfProducts();
	}

	private double calculateNumberOfProducts() {
		
		if(numberOfProducts > 0.0d)	return numberOfProducts;

		// Check whether the feature model is valid before
		// calling the method
		NumberOfProductsQuestion npq = (NumberOfProductsQuestion) qt
				.createQuestion("#Products");
		qt.ask(npq);
		numberOfProducts = npq.getNumberOfProducts();
		return numberOfProducts;
	}

	private double calculateVarIF(GenericFeature f) {
		double result =  calculateCommonalityOf(f) / calculateNumberOfProducts();
	//	System.out.println(f.getName() + ": " + result);
		return result;
	}

	
	private static class VarIFFeatureCorrespondence implements Comparable{
		private double varIF;
		private String name;
		
		public VarIFFeatureCorrespondence(double varIF, String name){
			this.varIF = varIF;
			this.name = name;
		}

		@Override
		public int compareTo(Object arg) {
			return new Double(this.varIF).compareTo(((VarIFFeatureCorrespondence)arg).varIF);
		}
	
		
	
	}
	
	// --- maybe delete or move th EMFHandling
	private List<GenericFeature> identifyAttributeFeatures(){
		
		return null;
	}
	
	private String createAttributeName(GenericFeature f){
		
		return null;
	}
	
	private Attribute createAttribute(GenericFeature f){
		
		return null;
	}
	
	private List<Level> identifyAttributeLevels(GenericFeature f){
		
		return null;
	}
}

