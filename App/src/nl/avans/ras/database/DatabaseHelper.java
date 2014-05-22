package nl.avans.ras.database;

import static nl.avans.ras.database.DatabaseNodes.*;

import java.util.ArrayList;
import java.util.Date;

import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Vault;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_REFRSH_DATE_TABLE);
		db.execSQL(CREATE_GYMNAST_TABLE);
		db.execSQL(CREATE_VAULT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	/******************************************************************
	 * 
	 *                         Inserts 
	 *
	 ******************************************************************/
	
	// Insert a gymnast collection
	public void insertGymnastCollection(ArrayList<Gymnast> gymnastCollection) {
		SQLiteDatabase db = this.getWritableDatabase();
		// Insert the data
		String sql = "INSERT INTO "+ GYMNAST_TABLE +" VALUES (?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (Gymnast gymnast: gymnastCollection) {
                  statement.clearBindings();
                  statement.bindLong(2, gymnast.getId());
                  statement.bindString(3, gymnast.getFirstname());
                  statement.bindString(4, gymnast.getSurname());
                  statement.bindString(5, gymnast.getSurnamePrefix());
                  statement.bindLong(6, gymnast.getAge());
                  statement.bindLong(7, gymnast.getLength());
                  statement.bindLong(8, gymnast.getWeight());
                  statement.bindString(9, gymnast.getLocation());
                  statement.execute();
        }
        db.setTransactionSuccessful();	
        db.endTransaction();
	}
	
	// Insert a vault collection
	public void insertVaultCollection(ArrayList<Vault> vaultCollection) {
		SQLiteDatabase db = this.getWritableDatabase();
		// Insert the data
		String sql = "INSERT INTO "+ VAULT_TABLE +" VALUES (?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        for (Vault vault: vaultCollection) {
                  statement.clearBindings();
                  statement.bindLong(2, vault.getId());
                  statement.bindLong(3, vault.getGymnastId());
                  statement.bindString(4, vault.getName());
                  statement.bindDouble(5, vault.getDScore());
                  statement.bindDouble(6, vault.getEScore());
                  statement.bindLong(7, vault.getDate().getTime());
                  statement.execute();
        }
        db.setTransactionSuccessful();	
        db.endTransaction();
	}
	
	public void insertUpdateDate(Date date) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		// Add the content
		cv.put(COL_DATE, date.getTime());
		// Insert the content
		db.insert(REFRSH_DATE_TABLE, null, cv);
	}
//	
//	/******************************************************************
//	 * 
//	 *                         Delete
//	 *
//	 ******************************************************************/
//	
//	public void clearCache() {
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(REFRSH_DATE_TABLE, "", null);
//		db.delete(GAME_TABLE, "", null);
//		db.delete(NEWS_TABLE, "", null);
//	}
//	
//	/******************************************************************
//	 * 
//	 *                         Getters 
//	 *
//	 ******************************************************************/
//	
	// Get a gymnast
	public Gymnast getGymnast(int id) {
		// Local variables
		Gymnast mGymnast = null;
		// Define the select query
		String selectQuery = "SELECT * FROM " + GYMNAST_TABLE + " WHERE " + COL_GYMNAST_ID + " = '" + id + "'";
		// Create the database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Get all the users
		if(cursor != null && cursor.moveToNext()) {
			int gymnastId = cursor.getInt(cursor.getColumnIndex(COL_GYMNAST_ID));
			String firstname = cursor.getString(cursor.getColumnIndex(COL_FIRSTNAME));
			String surname = cursor.getString(cursor.getColumnIndex(COL_SURNAME));
			String surnamePrefix = cursor.getString(cursor.getColumnIndex(COL_SURNAME_PREFIX));
			int age = cursor.getInt(cursor.getColumnIndex(COL_AGE));
			int length = cursor.getInt(cursor.getColumnIndex(COL_LENGTH));
			int weight = cursor.getInt(cursor.getColumnIndex(COL_WEIGHT));
			String location = cursor.getString(cursor.getColumnIndex(COL_LOCATION));
			mGymnast = new Gymnast(gymnastId, firstname, surname, surnamePrefix, age, length, weight, location);
		}
		cursor.close();
		// Return the game
		return mGymnast;
	}

	// Get all the games
	public Cursor getAllGymnast() {
		// Define the select query
		String selectQuery = "SELECT * FROM " + GYMNAST_TABLE;
		// Create the database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Return the cursor
		return cursor;
	}
	
	// Get a vault
	public Vault getVault(int id) {
		// Local variables
		Vault mVault = null;
		// Define the select query
		String selectQuery = "SELECT * FROM " + VAULT_TABLE + " WHERE " + COL_VAULT_ID + " = '" + id + "'";
		// Create the database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Get the news item
		if(cursor != null && cursor.moveToNext()) {
			int vaultId = cursor.getInt(cursor.getColumnIndex(COL_VAULT_ID));
			int gymnastId = cursor.getInt(cursor.getColumnIndex(COL_GYMNAST_ID));
			String name = cursor.getString(cursor.getColumnIndex(COL_VAULT_NAME));
			double dScore = cursor.getDouble(cursor.getColumnIndex(COL_D_SCORE));
			double eScore = cursor.getDouble(cursor.getColumnIndex(COL_E_SCORE));
			Date date = new Date(cursor.getLong(cursor.getColumnIndex(COL_DATE)));
			mVault = new Vault(vaultId, gymnastId, name, dScore, eScore, date);
		}
		cursor.close();
		// Return the news item
		return mVault;
	}
//	
//	public Cursor getNewsOfGame(int id, int count) {
//		// Define the select query
//		String selectQuery = "SELECT * FROM " + NEWS_TABLE + " WHERE " + COL_GAME_ID + " = '" + id + "' LIMIT " + count;
//		// Create the database
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		// Return the cursor
//		return cursor;
//	}
//	
	// Get all vaults
	public Cursor getAllVaults() {
		// Define the select query
		String selectQuery = "SELECT * FROM " + VAULT_TABLE;
		// Create the database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Return the cursor
		return cursor;
	}
	
	// Get all vaults
	public Cursor getAllVaultsFromGymnast(int id) {
		// Define the select query
		String selectQuery = "SELECT * FROM " + VAULT_TABLE + " WHERE " + COL_GYMNAST_ID + " = " + id;
		// Create the database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Return the cursor
		return cursor;
	}
	
	// Get all vaults
	public Cursor getAllVaultsFromGymnast(int id, Date date) {
		// Define the select query
		String selectQuery = "SELECT * FROM " + VAULT_TABLE + " WHERE " + COL_GYMNAST_ID + " = '" + id + "'";
		// Create the database
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Return the cursor
		return cursor;
	}
//	
//	// Get the input date
//	public Date getDate() {
//		// Local variables
//		Date refreshDate = null;
//		// Define the select query
//		String selectQuery = "SELECT * FROM " + REFRSH_DATE_TABLE;
//		// Create the database
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		// Get the refresh date item
//		if(cursor != null && cursor.moveToNext()) {
//			refreshDate = new Date(cursor.getLong(cursor.getColumnIndex(COL_DATE)));
//		}
//		cursor.close();
//		// Return the refresh date item
//		return refreshDate;
//	}
//
//	/******************************************************************
//	 * 
//	 *                         Checkers 
//	 *
//	 ******************************************************************/
//	
//	public boolean hasGames() {
//		// Define the select query
//		String selectQuery = "SELECT * FROM " + GAME_TABLE + " LIMIT 1";
//		// Create the database
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		// Return the cursor
//		if (cursor != null && cursor.moveToNext())
//			return true;
//		else 
//			return false;
//	}
//	
//	public boolean hasNews(int id) {
//		// Define the select query
//		String selectQuery = "SELECT * FROM " + NEWS_TABLE + " WHERE " + COL_GAME_ID + " = '" + id + "'" + " LIMIT 1";
//		// Create the database
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		// Return the cursor
//		if (cursor != null && cursor.moveToNext())
//			return true;
//		else 
//			return false;
//	}
}