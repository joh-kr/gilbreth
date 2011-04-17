package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Interviewee extends Model{

	String name;
	
	public String getName() {
		return name;
	}

	public String geteMail() {
		return eMail;
	}

	String eMail;
	
	private Interviewee(String name, String eMail){
		this.name = name;
		this.eMail = eMail;
	}
	
	public static Interviewee createNewInterviewee(String name, String eMail){
		Interviewee interviewee = new Interviewee(name, eMail);
		interviewee.save();
		return interviewee;
	}
}
