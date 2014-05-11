package nl.avans.ras.model;

public class Gymnast {

	// Fields
	public static final String GYMNAST_ID = "gymnast_id";
	private int id;
	private String firstname, surname, surnamePrefix, name;
	private String location;
	private int age, length, weight;
	
	// Constructor
	public Gymnast(int id, String firstname, String surname, String surnamePrefix, 
				   int age, int length, int weight, String location) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.surnamePrefix = surnamePrefix;
		this.age = age;
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
	public int 		getAge() 			{ return age; }
	public int 		getLength() 		{ return length; }
	public int 		getWeight() 		{ return weight; }
	public String 	getFirstname() 		{ return firstname; }
	public String 	getSurname() 		{ return surname; }
	public String 	getSurnamePrefix()	{ return surnamePrefix; }
	public String 	getName() 			{ return name; }
	public String 	getLocation() 		{ return location; }
}
