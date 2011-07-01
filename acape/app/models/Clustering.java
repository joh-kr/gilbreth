package models;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Use Weka library to apply k-means clustering algorithm for customer segmentation
 * 
 * @author Max Lillack
 */
public class Clustering {
	
	private SimpleKMeans clusterer;
	
	/**
	 * @param wtps array of willingnes to pay for every product for every respondent
	 * @param utilities array of utility for every product for every respondent
	 * @param number of cluster to be created
	 */
	public Clustering(double[][] wtps, double[][] utilities, int clusterCount) throws Exception {
		FastVector labels = new FastVector();
		// Build attribute names
		for(int i = 0; i < wtps.length; i++)
		{
			labels.addElement(new Attribute("P" + i + "_U"));
			labels.addElement(new Attribute("P" + i + "_wtp"));
		}
		// Create dataset with given attribute names
		Instances data = new Instances("Dataset", labels, 0);
		
		// for every respondent
		for(int i = 0; i < wtps[0].length; i++)
		{
			// create a row in the dataset with every wtps and utilities for every product 
			// for this repondent
			double[] row = new double[wtps.length + utilities.length];
			int a = 0;
			for(int j = 0; j < row.length; j += 2)
			{
				row[j] = utilities[a][i];
				row[j+1] = wtps[a][i];
				a++;
			}
			// add to to the dataset
			data.add(new Instance(1.0, row));
			
		}
		// Choose k-means algorithm
		clusterer = new SimpleKMeans();
		
		String[] options = new String[2];
		// required, keeps to order of data entry
		options[0] = "-O";
		// sets the number ob clusters to be determined
		options[1] = "-N " + clusterCount;
		// apply options
		clusterer.setOptions(options);
		// create clusterer with the created dataset
		clusterer.buildClusterer(data);

	}
	/**
	 * Return an array with a cluster number for every respondent
	 */
	public int[] getAssignments() throws Exception
	{
		return clusterer.getAssignments();
	}
}
