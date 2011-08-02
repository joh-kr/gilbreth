import org.junit.Test;

import play.test.UnitTest;
import models.Clustering;
import weka.core.Instances;

public class ClusteringTest extends UnitTest {
	@Test
	public void TestClustering() throws Exception {
		
		String[] labels = {"P1_U", "P1_wtp", "P2_U", "P2_wtp", "P3_U", "P3_wtp"};
		double[][] wtps = {
				{ 1, 1, 10, 2 },
				{ 2, 3, 20, 3},
				{ 3, 2, 30, 5}
		};
		
		double[][] utilities = {
				{2, 2, 2, 2},
				{3, 4, 3, 3},
				{4, 3, 4, 4}
		};
		
		Clustering c = new Clustering(wtps, utilities, 2);
		
		int[] result = c.getAssignments();
		
		assertTrue(result[0] == result[1]);
		assertTrue(result[1] == result[3]);
		assertTrue(result[1] != result[2]);
		
	}
}
