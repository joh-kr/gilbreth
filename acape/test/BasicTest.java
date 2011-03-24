import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	
    @Before
    public void setup() {
        Fixtures.deleteAll();
    }
	
	@Test
	public void createAndRetrieveRespondent() {
	   
		Respondent r = new Respondent().save();
		Long id = r.id;
		
		r = Respondent.find("byId", id).first();
	    // Test 
	    assertNotNull(r);
	}
	

}
