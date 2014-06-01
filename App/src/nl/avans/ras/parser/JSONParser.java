package nl.avans.ras.parser;

import java.util.ArrayList;
import java.util.List;

import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Location;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.VaultNumber;
import static nl.avans.ras.parser.JSONNodes.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	
	/******************
	 *    Gymnasts    *
	 ******************/
	
	public static ArrayList<Gymnast> parseAllGymnasts(String json) {
		ArrayList<Gymnast> list = new ArrayList<Gymnast>();
		
		try {
			JSONArray content = new JSONArray(json);
			
			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
				
				Gymnast gymnast = new Gymnast(
					element.getInt("gymnast_id"),
					element.getString("name"),
					element.getString("surname"),
					(element.isNull("surname_prefix")) ? null : element.getString("surname_prefix"),
					0,
					element.getInt("length"),
					element.getInt("weight"),
					null
				);
				
				list.add(gymnast);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static Gymnast parseSpecificGymnast(String json) {
		Gymnast gymnast = null;
		
		try {
			JSONArray content = new JSONArray(json);
			JSONObject element = content.getJSONObject(0);
			
			gymnast = new Gymnast(
				element.getInt("gymnast_id"),
				element.getString("name"),
				element.getString("surname"),
				(element.isNull("surname_prefix")) ? null : element.getString("surname_prefix"),
				0,
				element.getInt("length"),
				element.getInt("weight"),
				null
			);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return gymnast;
	}

	/*******************
	 *    Locations    *
	 *******************/
	
	public static List<Location> parseAllLocations(String json) {
		ArrayList<Location> list = new ArrayList<Location>();
		
		try {
			JSONArray content = new JSONArray(json);

			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
				
				Location location = new Location(
					element.getInt("location_id"),
					element.getString("name"),
					(element.isNull("description")) ? null : element.getString("description")
				);
				
				list.add(location);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static Location parseSpecificLocation(String json) {
		Location location = null;
		
		try {
			JSONArray content = new JSONArray(json);
			JSONObject element = content.getJSONObject(0);
			
			location = new Location(
				element.getInt("location_id"),
				element.getString("name"),
				(element.isNull("description")) ? null : element.getString("description")
			);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return location;
	}

	/****************
	 *    Vaults    *
	 ****************/
	
	public static Vault parseSpecificVault(String json) {
		Vault vault = null;
		
		try {
			JSONArray content = new JSONArray(json);
			JSONObject element = content.getJSONObject(0);
			
			vault = new Vault(
				element.getInt("vault_id"),
				element.getInt("gymnast_id"), 
				null,
				(element.isNull("rating_official_D")) ? -1 : element.getDouble("rating_official_D"), 
				(element.isNull("rating_official_E")) ? -1 : element.getDouble("rating_official_E"), 
				null,
				null
			);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return vault;
	}

	public static ArrayList<Vault> parseAllVaults(String json) {
		ArrayList<Vault> list = new ArrayList<Vault>();
		
		try {
			JSONArray content = new JSONArray(json);

			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
				
				Vault vault = new Vault(
					element.getInt("vault_id"),
					element.getInt("gymnast_id"), 
					null,
					(element.isNull("rating_official_D")) ? -1 : element.getDouble("rating_official_D"), 
					(element.isNull("rating_official_E")) ? -1 : element.getDouble("rating_official_E"),
					null,
					null
				);
				
				list.add(vault);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static ArrayList<Vault> parseVaultsOfSpecificGymnast(String json) {
		ArrayList<Vault> list = new ArrayList<Vault>();
		
		try {
			JSONArray content = new JSONArray(json);

			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
				
				Vault vault = new Vault(
					element.getInt("vault_id"),
					element.getInt("gymnast_id"), 
					null,
					(element.isNull("rating_official_D")) ? -1 : element.getDouble("rating_official_D"), 
					(element.isNull("rating_official_E")) ? -1 : element.getDouble("rating_official_E"), 
					null,
					null
				);
				
				list.add(vault);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**********************
	 *    VaultNumbers    *
	 **********************/
	
	public static ArrayList<VaultNumber> parseAllVaultNumber(String json) {
		ArrayList<VaultNumber> list = new ArrayList<VaultNumber>();
		
		try {
			JSONArray content = new JSONArray(json);
			
			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
			
				VaultNumber vaultNumber = new VaultNumber(
					element.getInt(NODE_VAULTNUMBER_ID),
					element.getInt(NODE_VAULTNUMBER_CODE),
					element.getString(NODE_VAULTNUMBER_DESCRIPTION),
					element.getString(NODE_VAULTNUMBER_GENDER),
					element.getInt(NODE_VAULTNUMBER_DIFFICULTY)
				);
				
				Log.i("VAULTNUMBER", vaultNumber.toString());
				list.add(vaultNumber);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static VaultNumber parseSpecificVaultNumber(String json) {
		VaultNumber vaultNumber = null;
		
		try {
			JSONArray content = new JSONArray(json);
			JSONObject element = content.getJSONObject(0);
			
			vaultNumber = new VaultNumber(
				element.getInt(NODE_VAULTNUMBER_ID),
				element.getInt(NODE_VAULTNUMBER_CODE),
				element.getString(NODE_VAULTNUMBER_DESCRIPTION),
				element.getString(NODE_VAULTNUMBER_GENDER),
				element.getInt(NODE_VAULTNUMBER_DIFFICULTY)
			);
			
			Log.i("VAULTNUMBER", vaultNumber.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return vaultNumber;
	}
}