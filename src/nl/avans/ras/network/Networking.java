package nl.avans.ras.network;

import static nl.avans.ras.network.NetworkConnections.*;

import java.util.ArrayList;

import nl.avans.ras.model.enums.AdapterKind;
import nl.avans.ras.parser.JSONParser;
import android.content.Context;

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
	
	public Networking() {
		
	}
	
	public void getGames() {
		this.execute(new String[] {getSteamLoginURL(), "GET"});
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
