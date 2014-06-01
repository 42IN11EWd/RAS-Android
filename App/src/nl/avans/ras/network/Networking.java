package nl.avans.ras.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Location;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.VaultNumber;
import nl.avans.ras.model.enums.AdapterKind;
import nl.avans.ras.parser.JSONParser;
import android.content.Context;
import android.util.Log;
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
	
	public ArrayList<Gymnast> getAllGymnasts() {
		ArrayList<Gymnast> list = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getAllGymnasts(), "GET"}).get();
			list = JSONParser.parseAllGymnasts(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Gymnast getSpecificGymnast(int id) {
		Gymnast gymnast = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getSpecificGymnast(id), "GET"}).get();
			gymnast = JSONParser.parseSpecificGymnast(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return gymnast;
	}
	
	/******************
	 *    Location    *
	 ******************/
	
	public List<Location> getAllLocations() {
		List<Location> list = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getAllLocations(), "GET"}).get();
			list = JSONParser.parseAllLocations(json);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Location getSpecificLocation(int id) {
		Location location = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getSpecificLocation(id), "GET"}).get();
			location = JSONParser.parseSpecificLocation(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return location;
	}
	
	/***************
	 *    Vault    *
	 ***************/
	
	public ArrayList<Vault> getAllVaults() {
		ArrayList<Vault> list = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getAllVaults(), "GET"}).get();
			list = JSONParser.parseAllVaults(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<Vault> getVaultsOfSpecificGymnast(int id) {
		ArrayList<Vault> list = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getVaultsOfSpecificGymnast(id), "GET"}).get();
			list = JSONParser.parseVaultsOfSpecificGymnast(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Vault getSpecificVault(int id) {
		Vault vault = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getSpecificVault(id), "GET"}).get();
			vault = JSONParser.parseSpecificVault(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return vault;
	}

	/*********************
	 *    Vault number   *
	 *********************/
	
	public ArrayList<VaultNumber> getAllVaultnumber() {
		ArrayList<VaultNumber> list = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getAllVaultnumber(), "GET"}).get();
			list = JSONParser.parseAllVaultNumber(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public VaultNumber getSpecificVaultnumber(int id) {
		VaultNumber vaultNumber = null;
		
		try {
			String json = this.execute(new String[] {NetworkConnections.getSpecificVaultnumber(id), "GET"}).get();
			vaultNumber = JSONParser.parseSpecificVaultNumber(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return vaultNumber;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		//JSONParser parser = new JSONParser();
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