package nl.avans.ras.parser;

public class JSONNodes {
	
	// JSON nodes for the games
	static final String NODE_APPS = "applist";
	static final String NODE_APP_LIST = "apps";
	static final String NODE_APP_ID = "appid";
	static final String NODE_APP_NAME = "name";
	
	// JSON nodes for the news items
	static final String NODE_NEWS = "appnews";
	static final String NODE_NEWS_LIST = "newsitems";
	static final String NODE_NEWS_ID = "gid";
	static final String NODE_NEWS_TITLE = "title";
	static final String NODE_NEWS_CONTENT = "contents";
	static final String NODE_NEWS_AUTHOR = "author";
	static final String NODE_NEWS_DATE = "date";
	
	// JSON nodes for the user items
	static final String NODE_USER = "response";
	static final String NODE_USER_PLAYER = "players";
	static final String NODE_USER_USERNAME = "personaname";
	static final String NODE_USER_REALNAME = "realname";
	static final String NODE_USER_PROFILE_URL = "profileurl";
}
