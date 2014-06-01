package nl.avans.ras.network;

public class NetworkConnections {
	private static final String BASE_URL = "http://192.168.25.1:3000/";

	/**************
	 *    User    *
	 **************/
	
	public static String getLogin(String username, String password) {
		return BASE_URL + "login/" + username + "/" + password;
	}
	
	/******************
	 *    Gymnasts    *
	 ******************/
	
	public static String getAllGymnasts() {
		return BASE_URL + "gymnasts";
	}
	
	public static String getSpecificGymnast(int id) {
		return BASE_URL + "gymnast/" + id;
	}
	
	/******************
	 *    Location    *
	 ******************/
	
	public static String getAllLocations() {
		return BASE_URL + "locations";
	}
	
	public static String getSpecificLocation(int id) {
		return BASE_URL + "location/" + id;
	}
	
	/***************
	 *    Vault    *
	 ***************/
	
	public static String getAllVaults() {
		return BASE_URL + "vaults";
	}
	
	public static String getVaultsOfSpecificGymnast(int id) {
		return BASE_URL + "vaults/gymnast/" + id;
	}
	
	public static String getSpecificVault(int id) {
		return BASE_URL + "vault/" + id;
	}
	
	/*********************
	 *    Vault number   *
	 *********************/
	
	public static String getAllVaultnumber() {
		return BASE_URL + "vaultnumbers";
	}
	
	public static String getSpecificVaultnumber(int id) {
		return BASE_URL + "vaultnumber/" + id;
	}
}