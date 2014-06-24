package nl.avans.ras.model;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gymnast {

	// Fields
	public static final String GYMNAST_ID = "gymnast_id";
	private int id;
	private String firstname, surname, surnamePrefix, name;
	private String turnbondId;
	private Date birthday;
	private int length, weight;
	private byte[] profileImage, thumbnail;
	
	// Constructor
	public Gymnast(int id, String firstname, String surname, String surnamePrefix, 
				   Date birthday, int length, int weight, String turnbondId, byte[] profileImage, byte[] thumbnail) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.surnamePrefix = surnamePrefix;
		this.birthday = birthday;
		this.length = length;
		this.weight = weight;
		this.turnbondId = turnbondId;
		this.profileImage = profileImage;
		this.thumbnail = thumbnail;
		
		// Set the name
		if (surnamePrefix != null && !surnamePrefix.isEmpty())
			this.name = firstname + " " + surnamePrefix + " " + surname;
		else
			this.name = firstname + " " + surname;
	}
	
	// Getters
	public int 		getId() 			{ return id; }
	public int 		getLength() 		{ return length; }
	public int 		getWeight() 		{ return weight; }
	public String 	getFirstname() 		{ return firstname; }
	public String 	getSurname() 		{ return surname; }
	public String 	getSurnamePrefix()	{ return surnamePrefix; }
	public String 	getName() 			{ return name; }
	public String 	getTurnbondId()		{ return turnbondId; }
	public byte[] 	getProfileImage() 	{ return profileImage; }
	public byte[] 	getThumbnail() 		{ return thumbnail; }
	public Date 	getBirthday()		{ return birthday; }
	
	public String getBirthdayString() {
		if (birthday != null) {
			Format formatter = new SimpleDateFormat("dd-MM-yyyy");
			String birthdayText = formatter.format(birthday);
			return birthdayText;
		}
		return "";
	}
}
