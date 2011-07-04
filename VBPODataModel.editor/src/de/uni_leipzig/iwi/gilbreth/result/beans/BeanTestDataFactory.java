package de.uni_leipzig.iwi.gilbreth.result.beans;

import java.util.ArrayList;
import java.util.Collection;

public class BeanTestDataFactory {
	
	public static ProductSegmentAssignment[] createProductSegmentAssignments(){
		ProductSegmentAssignment[] map = new ProductSegmentAssignment[4];
		
		map[1] = new ProductSegmentAssignment("p1", "s1", 200.00d);
		map[2] = new ProductSegmentAssignment("p1", "s1", 200.00d);
		map[3] = new ProductSegmentAssignment("p1", "s1", 200.00d);
		map[4] = new ProductSegmentAssignment("p1", "s1", 200.00d);
		
		return map;
	}
	
	public static Collection createProductSegmentAssignmentsCollection(){
		Collection map = new ArrayList();
		
		map.add(new ProductSegmentAssignment("p1", "s1", 200.00d));
		map.add(new ProductSegmentAssignment("p1", "s1", 200.00d));
		map.add(new ProductSegmentAssignment("p1", "s1", 200.00d));
		map.add(new ProductSegmentAssignment("p1", "s1", 200.00d));
		
		return map;
	}
	
	public static ProductFeatures[] createProductFeatures(){
		ProductFeatures[] map = new ProductFeatures[4];
		String[] features = {"m1", "m2", "m3"};
		
		map[1] = new ProductFeatures("p1", features, 200.00d);
		map[2] = new ProductFeatures("p1", features, 200.00d);
		map[3] = new ProductFeatures("p1", features, 200.00d);
		map[4] = new ProductFeatures("p1", features, 200.00d);
		
		return map;
	}

}

