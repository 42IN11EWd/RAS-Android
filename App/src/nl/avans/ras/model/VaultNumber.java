package nl.avans.ras.model;

public class VaultNumber {
	private int id;
	private int code;
	private String description;
	private String gender;
	private int difficulty;
	
	public VaultNumber(int id, int code, String description, String gender, int difficulty) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.gender = gender;
		this.difficulty = difficulty;
	}

	public int getID() {
		return id;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, code: %d, description: %s, gender: %s, difficulty: %d", 
				id, code, description, gender, difficulty);
	}
}