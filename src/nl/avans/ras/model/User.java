package nl.avans.ras.model;

import nl.avans.ras.model.enums.UserType;

public class User {

	// Fields
	public static final String USER_TYPE = "user_type";	
	private UserType type;
	private int id, gymnastId = 10;
	private String name;
	
	// Constructor
	public User(UserType type) {
		this.type = type;
	}
	
	// Getters
	public UserType getType() {
		return type;
	}
	
	public int getGymnastId() {
		return gymnastId;
	}
}
