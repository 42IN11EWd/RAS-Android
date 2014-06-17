package nl.avans.ras.network;

import nl.avans.ras.services.MD5;

public class NetworkConnections {
	private static final String BASE_URL = "http://ras-rest.herokuapp.com/";

	/**************
	 *    User    *
	 **************/
	
	public static String getLogin(String username, String password) {
		return BASE_URL + "login/" + username + "/" + MD5.hashString(password + MD5.SALT);
	}
	
	public static String changePassword(int gymnast_id, String oldPassword, String newPassword) {
		return BASE_URL + "user/" + gymnast_id + "/" + oldPassword + "/" + newPassword;
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