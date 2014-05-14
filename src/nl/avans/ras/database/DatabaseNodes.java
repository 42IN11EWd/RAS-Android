package nl.avans.ras.database;

public class DatabaseNodes {

	// Database name Node
	static final String DB_NAME = "RASDatabase";
		
	// Tables
	static final String VAULT_TABLE = "vault";
	static final String GYMNAST_TABLE = "gymnast";
	static final String REFRSH_DATE_TABLE = "refreshDate";

	// Common Columns
	public static final String COL_ID = "_id";
	
	// Columns vault table
	public static final String COL_VAULT_ID = "vaultId";
	public static final String COL_VAULT_NAME = "name";
	public static final String COL_D_SCORE = "dScore";
	public static final String COL_E_SCORE = "eScore";
	public static final String COL_DATE = "date";
	
	// Columns gymnast table
	public static final String COL_GYMNAST_ID = "gymnastId";
	public static final String COL_FIRSTNAME = "firstname";
	public static final String COL_SURNAME = "surname";
	public static final String COL_SURNAME_PREFIX = "surnamePrefix";
	public static final String COL_AGE = "age";
	public static final String COL_LENGTH = "length";
	public static final String COL_WEIGHT = "weight";
	public static final String COL_LOCATION = "location";
	
	// Create table Strings
	static final String CREATE_REFRSH_DATE_TABLE = 
	"CREATE TABLE IF NOT EXISTS " + REFRSH_DATE_TABLE + " (" + COL_DATE + " LONG)";
	
	static final String CREATE_VAULT_TABLE = 
	"CREATE TABLE IF NOT EXISTS " + VAULT_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_VAULT_ID + " INTEGER, " 
								  + COL_GYMNAST_ID + " INTEGER, "  + COL_VAULT_NAME + " TEXT, " + COL_D_SCORE + " DECIMAL, " 
								  + COL_E_SCORE + " DECIMAL, " + COL_DATE + " LONG)";
	
	static final String CREATE_GYMNAST_TABLE = 
	"CREATE TABLE IF NOT EXISTS " + GYMNAST_TABLE + " (" + COL_ID + " TEXT, " 
					    		  + COL_GYMNAST_ID + " INTEGER, " + COL_FIRSTNAME + " TEXT, " + COL_SURNAME + " TEXT, " 
					    		  + COL_SURNAME_PREFIX + " TEXT, " + COL_AGE + " LONG, " + COL_LENGTH + " LONG, " + COL_WEIGHT + " LONG, "
					    		  + COL_LOCATION + " TEXT)";
}