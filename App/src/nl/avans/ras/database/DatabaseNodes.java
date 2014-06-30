package nl.avans.ras.database;

public class DatabaseNodes {

	// Database name Node
	static final String DB_NAME = "RASDatabase";
		
	// Tables
	static final String VAULT_TABLE = "vault";
	static final String GYMNAST_TABLE = "gymnast";
	static final String REFRSH_GYMNAST_DATE_TABLE = "refreshGymnastDate";
	static final String REFRSH_VAULT_DATE_TABLE = "refreshVaultDate";

	// Common Columns
	public static final String COL_ID = "_id";
	
	// Columns refresh date table
	public static final String COL_GYMNAST_DATE = "gymnastDate";
	public static final String COL_VAULT_DATE = "vaultDate";
	
	// Columns vault table
	public static final String COL_VAULT_ID = "vaultId";
	public static final String COL_VAULT_NAME = "name";
	public static final String COL_D_SCORE = "dScore";
	public static final String COL_E_SCORE = "eScore";
	public static final String COL_PENALTY = "penalty";
	public static final String COL_VAULT_KIND = "kind";
	public static final String COL_DATE = "date";
	public static final String COL_TIME = "time";
	public static final String COL_DATA = "data";
	
	// Columns gymnast table
	public static final String COL_GYMNAST_ID = "gymnastId";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_SURNAME = "surname";
	public static final String COL_SURNAME_PREFIX = "surnamePrefix";
	public static final String COL_BIRTHDAY = "birthday";
	public static final String COL_LENGTH = "length";
	public static final String COL_WEIGHT = "weight";
	public static final String COL_LOCATION = "location";
	public static final String COL_PROFILE_IMAGE = "profile_image";
	public static final String COL_THUMBNAIL = "thumbnail";
	
	// Create table Strings	
	static final String CREATE_VAULT_TABLE = 
	"CREATE TABLE IF NOT EXISTS " + VAULT_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_VAULT_ID + " INTEGER, " 
								  + COL_GYMNAST_ID + " INTEGER, "  + COL_VAULT_NAME + " TEXT, " + COL_D_SCORE + " DECIMAL, " 
								  + COL_E_SCORE + " DECIMAL, " + COL_PENALTY + " DECIMAL, " + COL_LOCATION + " TEXT, " 
								  + COL_VAULT_KIND + " TEXT, " + COL_DATE + " LONG, " + COL_TIME + " TEXT, " + COL_DATA + " TEXT)";
	
	static final String CREATE_GYMNAST_TABLE = 
	"CREATE TABLE IF NOT EXISTS " + GYMNAST_TABLE + " (" + COL_ID + " TEXT, " 
					    		  + COL_GYMNAST_ID + " INTEGER, " + COL_FIRSTNAME + " TEXT, " + COL_SURNAME + " TEXT, " 
					    		  + COL_SURNAME_PREFIX + " TEXT, " + COL_BIRTHDAY + " LONG, " + COL_LENGTH + " LONG, " + COL_WEIGHT + " LONG, "
					    		  + COL_LOCATION + " TEXT, " + COL_PROFILE_IMAGE + " BLOB, " + COL_THUMBNAIL + "BLOB)";
}