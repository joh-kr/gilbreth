import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class RespondentTest extends UnitTest {

	
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
	
	@Test
	public void addExcludedLevel() {
		Fixtures.load("data.yml");
		Respondent r = Respondent.all().first();
		
		assertEquals(1, r.excludedLevels.size());
		
		Level l = Level.find("byName", "red").first();
	
		r.addExcludedLevel(l);
		assertEquals(2, r.excludedLevels.size());
	}	
	
	@Test
	public void addLevelRate() {
		Fixtures.load("data.yml");
		Respondent r = Respondent.all().first();
		
		assertEquals(2, r.levelRates.size());
		
		Level l = Level.find("byName", "blue").first();
	
		r.addLevelRate(l, 5.0d);
		assertEquals(3, r.levelRates.size());

	}
	
	@Test
	public void getHighestRatedLevelOf(){
		Fixtures.load("data.yml");
		Attribute a = Attribute.find("byName", "Transmission").first();
		
		Level l = Level.find("attribute = ?1 AND name = ?2", a, "automatic").first();
		
		Respondent r = Respondent.all().first();
		Level best = r.getHighestRatedLevelOf(a);
		
		assertEquals(l, best);
	}
	
	@Test
	public void getLowestRatedLevelOf(){
		Fixtures.load("data.yml");
		Attribute a = Attribute.find("byName", "Transmission").first();
		
		Level l = Level.find("attribute = ?1 AND name = ?2", a, "manual").first();
		
		Respondent r = Respondent.all().first();
		Level best = r.getLowestRatedLevelOf(a);
		
		assertEquals(l, best);
	}
	
	@Test
	public void normalizeAndUnitizeLevelUtilityOf(){
		Fixtures.load("data.yml");
		Attribute a = Attribute.find("byName", "Transmission").first();
		Respondent r = Respondent.all().first();
		
		// Hard coded the values from the data.yml LevelRates rate1 and rate2
		double avg = (7.0d + 1.0d) / 2;
		double stdDev =Math.sqrt( (Math.pow(7.0d - avg, 2) + Math.pow(1.0d - avg, 2))/2 );
		double[] vals = new double[2];
		vals[0] = (7.0d - avg)/stdDev;
		vals[1] = (1.0d - avg)/stdDev;
		
		r.normalizeAndUnitizeLevelUtilityOf(a);
		
		List<LevelRate> nuLevelRates = Level.find("SELECT lr "
				+ "FROM Respondent r JOIN r.levelRates lr JOIN lr.level l JOIN l.attribute a 	"
				+ "WHERE r = ?1 AND a = ?2 ORDER BY lr.rate DESC", r, a).fetch();
		
		for(int i = 0; i < nuLevelRates.size(); i++){
				assertEquals(vals[i], nuLevelRates.get(i).rate, 0.1);
		}
		
	}
	
	@Test
	public void weightLevelUtilityOf(){
		Fixtures.load("data.yml");
		Attribute a = Attribute.find("byName", "Transmission").first();
				
		double weight = 3.0d;
		Respondent r = Respondent.all().first();
		
		r.weightLevelUtilityOf(a, weight);
		
		double[] vals = new double[2];
		vals[0] = 7.0d * weight;
		vals[1] = 1.0d * weight;
		
		List<LevelRate> nuLevelRates = Level.find("SELECT lr "
				+ "FROM Respondent r JOIN r.levelRates lr JOIN lr.level l JOIN l.attribute a 	"
				+ "WHERE r = ?1 AND a = ?2 ORDER BY lr.rate DESC", r, a).fetch();
		
		for(int i = 0; i < nuLevelRates.size(); i++){
			assertEquals(vals[i], nuLevelRates.get(i).rate, 0.1);
		}
	}
	
	@Test
	public void createInitialUtilityObservations(){
		Fixtures.load("data.yml");
		Respondent r = Respondent.all().first();
		
		//r.createInitialUtilityObservations();
		
		//assertEquals(5, r.obsMatrices.size());
		
	}

	@Test
	public void determineAttributePairs(){
		Fixtures.load("data.yml");
		Respondent r = Respondent.all().first();
		
		List<Attribute> la = Attribute.all().fetch();
		
		AttributePairingFrequency.setFrequencyFor(r, la.get(0), la.get(1), 2);
		
		List<Attribute> l = r.determineAttributePairs(2);
		assertEquals(2, l.size());
		
	}
	
	@Test
	public void determineLevelPairs(){
		Fixtures.load("data.yml");
		Respondent r = Respondent.all().first();
		
		List<Attribute> la = Attribute.all().fetch();
		
		List<Level[]> levels = r.determineLevelPairs(la);
		
		assertNotNull(levels);
		assertEquals(2, levels.size());
	}
	
	
}
