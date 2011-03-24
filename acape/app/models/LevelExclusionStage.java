package models;

import java.util.List;

/**
 * Represents the Stage LEvel Exclusion in the ACA-Survey. In that stage, the respondent is asked to exclude levels
 * that are absolutely unacceptable for him. For example if a respondent would not buy a car with electric motor, 
 * then he would exclude this level from a attribute motor in a ACA-Survey on cars.
 * 
 * @author Johannes Müller
 * @version
 *
 */
public class LevelExclusionStage extends Stage{

	public LevelExclusionStage(Interview interview, Result result){
		super(interview, result);
	}
	
	public void excludeLevels(List<Long> excludeLevelIds) throws Exception{
	
		long levelId = 0l;
		Level level = null;
		
		for (int i = 0; excludeLevelIds != null && i < excludeLevelIds.size(); i++) {
			levelId = excludeLevelIds.get(i);
			level = Level.findById(levelId);
			
			if(level == null){
				throw new Exception("An passed Id is not a valid Level Id. Id was " + levelId);
			}else{
				result.addExcludedLevel(level);
			}
		}
		
	}
	
}
