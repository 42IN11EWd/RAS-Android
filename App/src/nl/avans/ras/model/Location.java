package nl.avans.ras.model;

public class Location {
	private int id;
	private String name;
	private String description;
	
	public Location(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, name: %s, description: %s", id, name, description);
	}
}