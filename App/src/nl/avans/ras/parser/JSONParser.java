package nl.avans.ras.parser;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Location;
import nl.avans.ras.model.User;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.VaultNumber;
import nl.avans.ras.model.enums.UserType;
import static nl.avans.ras.parser.JSONNodes.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	
	/******************
	 *    Gymnasts    *
	 ******************/
	
	public static ArrayList<Gymnast> parseGymnasts(String json) {
		ArrayList<Gymnast> list = new ArrayList<Gymnast>();
		
		try {
			JSONArray content = new JSONArray(json);
			
			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
				
				Gymnast gymnast = new Gymnast(
					element.getInt(NODE_GYMNAST_ID),
					element.getString(NODE_NAME),
					element.getString(NODE_SURNAME),
					(element.isNull(NODE_SURNAME_PREFIX)) ? null : element.getString(NODE_SURNAME_PREFIX),
					null,
					(element.isNull(NODE_LENGTH)) ? 0 : element.getInt(NODE_LENGTH),
					(element.isNull(NODE_WEIGHT)) ? 0 : element.getInt(NODE_WEIGHT),
					(element.isNull(NODE_TURNBOND_ID)) ? null : element.getString(NODE_TURNBOND_ID),
//					(element.isNull(NODE_PROFILE_IMAGE)) ? new byte[0] : element.getString(NODE_PROFILE_IMAGE).getBytes(),
					new byte[0],
					new byte[0]
//					(element.isNull(NODE_THUMBNAIL)) ? new byte[0] : element.getString(NODE_THUMBNAIL).getBytes()
				);
				
				list.add(gymnast);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
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
					element.getInt(NODE_LOCATION_ID),
					element.getString(NODE_NAME),
					(element.isNull(NODE_DESCRIPTION)) ? null : element.getString(NODE_DESCRIPTION)
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
				element.getInt(NODE_LOCATION_ID),
				element.getString(NODE_NAME),
				(element.isNull(NODE_DESCRIPTION)) ? null : element.getString(NODE_DESCRIPTION)
			);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return location;
	}

	/****************
	 *    Vaults    *
	 ****************/

	public static ArrayList<Vault> parseVaults(String json) {
		ArrayList<Vault> list = new ArrayList<Vault>();
		
		try {
			JSONArray content = new JSONArray(json);

			for(int i = 0; i < content.length(); i++) {
				JSONObject element = content.getJSONObject(i);
				
				int vaultId = element.getInt(NODE_VAULT_ID);
				int gymnastId = element.getInt(NODE_GYMNAST_ID);
				String name = element.getString(NODE_VAULT_NUMBER);
				double dScore = (element.isNull(NODE_D_RATING)) ? -1 : element.getDouble(NODE_D_RATING); 
				double eScore = (element.isNull(NODE_E_RATING)) ? -1 : element.getDouble(NODE_E_RATING);
				double penalty = (element.isNull(NODE_PENALTY)) ? -1 : element.getDouble(NODE_PENALTY);
				String location = element.getString(NODE_LOCATION);
				String data = (element.isNull(NODE_GRAPH_DATA)) ? "" : element.getString(NODE_GRAPH_DATA);
				
				// Parse timestamp
				Date date = null;
				String time = "";
				try {
					String timestamp = (element.isNull(NODE_VAULT_DATE)) ? "" : element.getString(NODE_VAULT_DATE);
//					String timestamp = "2014-06-18 09:04:27";
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					date = format.parse(timestamp);
					
					String[] array = timestamp.split("T");
					array = array[1].split("\\.");
					
					time = array[0];
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Vault vault = new Vault(vaultId, gymnastId, name, dScore, eScore, location, date, time, data);
				
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
	
	/***************
	 *    Login    *
	 ***************/

	public static User parseUser(String json) {
		User user = null;
		
		try {
			JSONArray content = new JSONArray(json);
			JSONObject element = content.getJSONObject(0);
			
			String userTypeString = (element.isNull(NODE_USER_TYPE)) ? null : element.getString(NODE_USER_TYPE);
			UserType userType = null;
			
			if (userTypeString.equals("gymnast")) {
				userType = UserType.GYMNAST;
			} else if (userTypeString.equals("coach")) {
				userType = UserType.TRAINER;
			}
			
			if (userType != null) {
				user = new User(
					element.getInt(NODE_USER_ID),
					userType,
					(element.isNull(NODE_GYMNAST_ID)) ? -1 : element.getInt(NODE_GYMNAST_ID)
				);
				
				Log.i("USER", user.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	/*************************
	 *    Change password    *
	 *************************/
	
	public static boolean parseSuccesChangePassword(String json) {
		boolean succes = false;
		
		try {
			JSONArray content = new JSONArray(json);
			
			if (content.length() >= 1) {
				succes = true;
			}
			
			Log.i("PASSWORD CHANGE", "" + succes);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return succes;
	}
}