package nl.avans.ras.parser;

import java.util.ArrayList;
import java.util.Date;

import static nl.avans.ras.parser.JSONNodes.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	// Fields
	private JSONObject jsonObj = null;
	private JSONArray objects = null;
	
	public JSONParser() {
		
	}

	/*
	public ArrayList<Game> parseGames(String json) {
		ArrayList<Game> gameList = new ArrayList<Game>();
		try {
			jsonObj = new JSONObject(json);
			jsonObj = jsonObj.getJSONObject(NODE_APPS);
			objects = jsonObj.getJSONArray(NODE_APP_LIST);
			
			for (int i = 0; i < objects.length(); i++) {
				JSONObject obj = objects.getJSONObject(i);
				int id = obj.getInt(NODE_APP_ID);
				String name = obj.getString(NODE_APP_NAME);
				
				// Log the information				
				Log.i("game info","id: " + id + ", name: " + name);
				
				// Add the game to the collection
				gameList.add(new Game(id, name, ""));
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return gameList;
	}

	public ArrayList<News> parseNews(String json) {
		ArrayList<News> newsList = new ArrayList<News>();
		try {
			jsonObj = new JSONObject(json);
			jsonObj = jsonObj.getJSONObject(NODE_NEWS);
			objects = jsonObj.getJSONArray(NODE_NEWS_LIST);
			
			for (int i = 0; i < objects.length(); i++) {
				JSONObject obj = objects.getJSONObject(i);
				int gameId = jsonObj.getInt(NODE_APP_ID);
				String id = obj.getString(NODE_NEWS_ID);
				String title = obj.getString(NODE_NEWS_TITLE);
				String text = obj.getString(NODE_NEWS_CONTENT);
				String writer = obj.getString(NODE_NEWS_AUTHOR);
				Date publishDate = new Date(obj.getLong(NODE_NEWS_DATE) * 1000);
				
				// Log the information				
				Log.i("news info","id: " + id + ", title: " + title);
				
				// Add the game to the collection
				newsList.add(new News(id, gameId, title, text, writer, publishDate));
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return newsList;
	}
	
	public User parseUserInfo(String json) {
		User user = null;
		try {
			jsonObj = new JSONObject(json);
			jsonObj = jsonObj.getJSONObject(NODE_USER);
			objects = jsonObj.getJSONArray(NODE_USER_PLAYER);
			for (int i = 0; i < objects.length(); i++) {
				JSONObject obj = objects.getJSONObject(i);
				String username = obj.getString(NODE_USER_USERNAME);
				String realname = "";
				if (obj.has(NODE_USER_REALNAME)) {
					realname = obj.getString(NODE_USER_REALNAME);
				}
				String profileURL = obj.getString(NODE_USER_PROFILE_URL);
				
				// Create the user
				user = new User(username, realname, profileURL);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}*/
}
