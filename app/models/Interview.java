package models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Interview extends Model{
	
	@OneToOne
	@Required
	public Interviewee interviewee;
	
	@OneToOne
	@Required
	public Result result;
	
	@Transient
	private Map<String, Stage> stages;

	
	private Interview(Interviewee interviewee){
		this.interviewee = interviewee;
		this.result = Result.createNewResult();
	}

	public Stage getStage(String name) throws Exception{		
		if(stages == null || stages.isEmpty()) initializeStages();
		if(!stages.containsKey(name)) throw new Exception("The interview does not contain a stage with name " + name);
		
		return stages.get(name);
	}
	
	private void initializeStages(){
		stages = new Hashtable<String, Stage>();
		stages.put("LevelExclusion", new LevelExclusionStage(this, result));
		stages.put("AttributeRatingStage", new LevelRatingStage(this, result));
		stages.put("StageWeightAttributes", new AttributeWeightingStage(this, result));
		stages.put("PairsUtilityStage", new PairsUtilityStage(this, result));
		
		
	}
	
	public static Interview createNewInterview(Interviewee interviewee){
		Interview interview = new Interview(interviewee);
		interview.save();
		return interview;
	}
}
