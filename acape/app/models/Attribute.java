/*
 * This program is licensed under the terms of the GNU GPL v2.0.
 * 
 * (c) Johannes Müller, Information Systems Institute, University of Leipzig
 */

package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

/**
 * An attribute is a structuring element of a ACA survey, organizinh Levels.
 * 
 * @author Johannes Müller
 */
@Entity
public class Attribute extends Model{

	@Required
    public String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Required
	@Lob
    public String description;
	
    @OneToMany(mappedBy="attribute", cascade=CascadeType.ALL)
    public List<Level> levels;
	
	public Attribute(String name, String description){
		this.levels = new ArrayList<Level>();
		this.name = name;
		this.description = description;
	}
	
	public List<Level> getLevels(){
		return this.levels;
	}
	
}
