package nl.avans.ras.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import nl.avans.ras.activities.CompareActivity;
import nl.avans.ras.activities.LoginActivity;
import nl.avans.ras.activities.ProfileActivity;
import nl.avans.ras.activities.SettingsActivity;
import nl.avans.ras.activities.VaultActivity;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Location;
import nl.avans.ras.model.User;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.VaultNumber;
import nl.avans.ras.parser.JSONParser;
import android.content.Context;
import android.os.AsyncTask;

public class Networking extends AbstractNetworking {

	private Context context;
	
	public Networking(Context context) {
		this.context = context;
	}
	
	/**************
	 *    User    *
	 **************/

	public void getLogin(String username, String password) {
		this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getLogin(username, password), "GET"});
	}
	
	/******************
	 *    Gymnasts    *
	 ******************/
	
	public void getAllGymnasts() {
		this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getAllGymnasts(), "GET"});
	}
	
	public void getSpecificGymnast(int id) {
		this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getSpecificGymnast(id), "GET"});
	}
	
	/******************
	 *    Location    *
	 ******************/
	
	public List<Location> getAllLocations() {
		List<Location> list = null;
		
		try {
			String json = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getAllLocations(), "GET"}).get();
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
			String json = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getSpecificLocation(id), "GET"}).get();
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
	
	public void getAllVaults() {
		this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getAllVaults(), "GET"});
	}
	
	public void getVaultsOfSpecificGymnast(int id) {
		this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getVaultsOfSpecificGymnast(id), "GET"});
	}
	
	public void getSpecificVault(int id) {
		this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getSpecificVault(id), "GET"});
	}

	/*********************
	 *    Vault number   *
	 *********************/
	
	public ArrayList<VaultNumber> getAllVaultnumber() {
		ArrayList<VaultNumber> list = null;
		
		try {
			String json = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getAllVaultnumber(), "GET"}).get();
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
			String json = this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[] {NetworkConnections.getSpecificVaultnumber(id), "GET"}).get();
			vaultNumber = JSONParser.parseSpecificVaultNumber(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return vaultNumber;
	}
	
	@Override
	protected void onPostExecute(String json) {
		super.onPostExecute(json);
		
		// Check from which activity the context is
		if (context instanceof LoginActivity) {
			LoginActivity mActivity = (LoginActivity) context;
			
			// Create a user
			User user = JSONParser.parseUser(json);
			mActivity.Login(user);
		} else if(context instanceof ProfileActivity) {
			ProfileActivity mActivity = (ProfileActivity) context;
			
			// Create a user
			ArrayList<Gymnast> list = JSONParser.parseGymnasts(json);
			mActivity.insertUsers(list);
		} else if(context instanceof VaultActivity) {
			VaultActivity mActivity = (VaultActivity) context;
			
			ArrayList<Vault> list = JSONParser.parseVaults(json);
			mActivity.insertVaults(list);
		} else if(context instanceof CompareActivity) {
			CompareActivity mActivity = (CompareActivity) context;
			
		} else if(context instanceof SettingsActivity) {
			SettingsActivity mActivity = (SettingsActivity) context;
			
		}
	}
}