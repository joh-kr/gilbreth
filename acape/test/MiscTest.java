import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import models.Attribute;
import play.test.UnitTest;


public class MiscTest extends UnitTest {
	
	private static Logger jlog = Logger.getLogger("de.iwi.uni_leipzig.gilbreth");
	
	@Test
	public void permut() {
		int count = 1;
		
		int a[][] = new int[3][];
		a[0] = new int[]{1, 2};
		a[1] = new int[]{3, 4, 5, 6};
		a[2] = new int[]{7, 8};
		
		
		int temp[] = new int[3];
		int levelSizes[] = new int[3];
		
		for(int i = 0; i < 3; i++) {
			levelSizes[i] = a[i].length - 1;
			temp[i] = 0;
			count *= levelSizes[i] + 1;
		}
		
		int j = temp.length - 1;
		for(int i = 0; i < count; i++) {
			
			jlog.log(java.util.logging.Level.INFO, 
					Arrays.toString(temp));
			
			j = temp.length - 1;
			
			temp[j]++;
			while(temp[j] > levelSizes[j] && j > 0) {
				temp[j] = 0;
				temp[--j]++;
			}
		}
	}
}
