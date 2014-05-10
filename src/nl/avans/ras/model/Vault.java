package nl.avans.ras.model;

import java.util.Date;

public class Vault {
	
	// Fields
	private int id, gymnastId;
	private String name, type, location;
	private double dScore, eScore, duration;
	private Date date;
	
	// Constructor
	public Vault(int id, int gymnastId, String name, double dScore, double eScore, Date date) {
		this.id = id;
		this.gymnastId = gymnastId;
		this.name = name;
		this.dScore = dScore;
		this.eScore = eScore;
		this.date = date;
		
		// Test data
		this.duration = 13.94;
		this.type = "Salto";
		this.location = "Flik Flak";
	}
	
	// Getters
	public int 		getId() 		{ return id; }
	public int 		getGymnastId() 	{ return gymnastId; }
	public double 	getEScore()		{ return eScore; }
	public double 	getDScore() 	{ return dScore; }
	public double	getDuration()	{ return duration; }
	public String 	getName() 		{ return name; }
	public String 	getType() 		{ return type; }
	public String 	getLocation()	{ return location; }
	public Date 	getDate() 		{ return date; }

}
