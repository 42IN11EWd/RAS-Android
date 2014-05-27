package nl.avans.ras.network;

import java.util.ArrayList;

import nl.avans.ras.model.enums.AdapterKind;
import nl.avans.ras.parser.JSONParser;
import android.content.Context;
import nl.avans.ras.network.NetworkConnections;

public class Networking extends AbstractNetworking {

	// Fields
	private Context context;
	private AdapterKind kind;
	private ArrayList<String> allInfo;
	
	// Constructor
	public Networking(Context context, AdapterKind kind) {
		this.context = context;
		this.kind = kind;
	}
	
	public Networking() {}

	/**************
	 *    User    *
	 **************/

	public void getLogin(String username, String password) {
		this.execute(new String[] {NetworkConnections.getLogin(username, password), "GET"});
	}
	
	/******************
	 *    Gymnasts    *
	 ******************/
	
	public void getAllGymnasts() {
		this.execute(new String[] {NetworkConnections.getAllGymnasts(), "GET"});
	}
	
	public void getSpecificGymnast(int id) {
		this.execute(new String[] {NetworkConnections.getSpecificGymnast(id), "GET"});
	}
	
	public void updateSpecificGymnast(int id) {
		this.execute(new String[] {NetworkConnections.updateSpecificGymnast(id), "GET"});
	}
	
	/******************
	 *    Location    *
	 ******************/
	
	public void getAllLocations() {
		this.execute(new String[] {NetworkConnections.getAllLocations(), "GET"});
	}
	
	public void getSpecificLocation(int id) {
		this.execute(new String[] {NetworkConnections.getSpecificLocation(id), "GET"});
	}
	
	/***************
	 *    Vault    *
	 ***************/
	
	public void getAllVaults() {
		this.execute(new String[] {NetworkConnections.getAllVaults(), "GET"});
	}
	
	public void getVaultsOfSpecificGymnast(int id) {
		this.execute(new String[] {NetworkConnections.getVaultsOfSpecificGymnast(id), "GET"});
	}
	
	public void getSpecificVault(int id) {
		this.execute(new String[] {NetworkConnections.getSpecificVault(id), "GET"});
	}
	
	public void updateSpecificVault(int id) {
		this.execute(new String[] {NetworkConnections.updateSpecificVault(id), "GET"});
	}
	
	/*********************
	 *    Vault number   *
	 *********************/
	
	public void getAllVaultnumber() {
		this.execute(new String[] {NetworkConnections.getAllVaultnumber(), "GET"});
	}
	
	public void getSpecificVaultnumber(int id) {
		this.execute(new String[] {NetworkConnections.getSpecificVaultnumber(id), "GET"});
	}
	
	public ArrayList<String> getAllUserInfo() {
		return allInfo;
	}
	
	public void setAllInfo(ArrayList<String> allInfo) {
		this.allInfo = allInfo;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		JSONParser parser = new JSONParser();
		// Check if the context is from the MainActivity
//		if (context instanceof MainActivity) {
//			MainActivity mActivity = (MainActivity) context;
//			
//			// Check which kind of data you have
//			switch (kind) {
//			case GAMES:
//				mActivity.insertGamesIntoDatabase(parser.parseGames(result));
//				break;
//			case NEWS:
//				mActivity.insertNewsIntoDatabase(parser.parseNews(result));
//				break;
//			default:
//				break;
//			}
//		} else if(context instanceof UserActivity) {
//			UserActivity mActivity = (UserActivity) context;
//			mActivity.saveUserInfo(parser.parseUserInfo(result));
//		}
	}
}
