package nl.avans.ras.network;

public class NetworkConnections {

	// URLS
	private static final String BASE_URL = "http://api.steampowered.com/ISteamApps/GetAppList/v2";
	// Small extensions
	private static final String APP_ID = "appid=";
	
	
	/****************************************************************
	 * 
	 *                  Functions to get URL's
	 * 
	 ****************************************************************/
	
	public static String getSteamLoginURL() {
		return BASE_URL;
	}
}
