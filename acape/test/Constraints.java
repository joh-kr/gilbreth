import models.Attribute;
import models.Concept;
import models.Level;
import models.Respondent;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Constraints extends UnitTest {
	
    @Before
    public void setup() throws Exception {
        Fixtures.deleteAll();
        Fixtures.load("testdata.yml");
    } 
       
	@Test
	public void testExclude() throws Exception {
	   
		Concept concept = new Concept();
		
		Attribute forum = Attribute.find("byName", "Forum").first();
		Level forumPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", forum, "Present").first();
		
		Attribute recommenderSystem = Attribute.find("byName", "Recommender System").first();
		Level rsPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", recommenderSystem, "Present").first();
		
		assertNotNull(rsPresent);
		assertNotNull(recommenderSystem);
		assertNotNull(forumPresent);
		assertNotNull(forum);
		
		concept.addLevel(forumPresent);
		concept.addLevel(rsPresent);
		
		assertTrue(concept.getAttributes().contains(forum));
		assertTrue(concept.getLevelOf(forum) == forumPresent);
		assertTrue(concept.getLevelOf(recommenderSystem) == rsPresent);
		assertTrue(concept.getAttributes().contains(recommenderSystem));
		
		assertFalse(concept.isValid());
		
		concept = new Concept();
		concept.addLevel(forumPresent);
		assertTrue(concept.isValid());
		
		Attribute paymentMethod = Attribute.find("byName", "Payment Method").first();
		Level pmCC = Level.find("select l from Level l where l.attribute = ? and l.name = ?", paymentMethod, "Credit Card").first();
		
		concept.addLevel(pmCC);
		assertFalse(concept.isValid());
		
		Attribute fraudDetection = Attribute.find("byName", "Payment with Fraud Detection").first();
		Level fdPresent = Level.find("select l from Level l where l.attribute = ? and l.name = ?", fraudDetection, "Present").first();
		
		concept.addLevel(fdPresent);
		assertTrue(concept.isValid());
	}
}
