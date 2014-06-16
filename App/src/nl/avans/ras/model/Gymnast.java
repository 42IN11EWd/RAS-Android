package nl.avans.ras.model;

import java.util.Date;

public class Gymnast {

	// Fields
	public static final String GYMNAST_ID = "gymnast_id";
	private int id;
	private String firstname, surname, surnamePrefix, name;
	private String location;
	private Date birthday;
	private int length, weight;
	
	// Constructor
	public Gymnast(int id, String firstname, String surname, String surnamePrefix, 
				   Date birthday, int length, int weight, String location) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.surnamePrefix = surnamePrefix;
		this.birthday = birthday;
		this.length = length;
		this.weight = weight;
		this.location = location;
		
		// Set the name
		if (surnamePrefix != null && !surnamePrefix.isEmpty())
			this.name = firstname + " " + surnamePrefix + " " + surname;
		else
			this.name = firstname + " " + surname;
	}
	
	// Getters
	public int 		getId() 			{ return id; }
	public Date		getBirthday() 		{ return birthday; }
	public int 		getLength() 		{ return length; }
	public int 		getWeight() 		{ return weight; }
	public String 	getFirstname() 		{ return firstname; }
	public String 	getSurname() 		{ return surname; }
	public String 	getSurnamePrefix()	{ return surnamePrefix; }
	public String 	getName() 			{ return name; }
	public String 	getLocation() 		{ return location; }
}
