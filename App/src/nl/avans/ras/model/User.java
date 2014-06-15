package nl.avans.ras.model;

import nl.avans.ras.model.enums.UserType;

public class User {

	// Fields
	public static final String USER_TYPE = "user_type";	
	private UserType type;
	private int id, gymnastId;
	
	// Constructor
	public User(int id, UserType type) {
		this.type = type;
	}
	
	public User(int id, UserType type, int gymnastId) {
		this.type = type;
	}
	
	// Getters
	public int getId() {
		return id;
	}
	
	public UserType getType() {
		return type;
	}
	
	public int getGymnastId() {
		return gymnastId;
	}
}
