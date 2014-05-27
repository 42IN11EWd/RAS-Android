package nl.avans.ras.activities;

import nl.avans.ras.R;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.LoginFragment;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.User;
import nl.avans.ras.model.enums.UserType;
import nl.avans.ras.network.Networking;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class LoginActivity extends Activity implements LoginFragment.OnLoginListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	public static final String ACTIVE_USER = "active_user";
	public static final String IS_LOGGED_IN	= "is_logged_in";
	private boolean doubleBackToExitPressedOnce = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (isLoggedIn()) {
			startActivity(new Intent(this, ProfileActivity.class));
			this.finish();
		} else {
			// Create a new login fragment
	    	LoginFragment loginFragment = new LoginFragment();
	    	
	    	Networking networking = new Networking();
	    	networking.getAllGymnasts();
	    	
	     	// Replace the fragment
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
	     	transaction.replace(R.id.fragment_container, loginFragment);
	     	transaction.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// Check if there is any backstack.
		if (getFragmentManager().getBackStackEntryCount() == 0) {
			if (doubleBackToExitPressedOnce) {
				super.onBackPressed();
				return;
			} else {
				doubleBackToExitPressedOnce = true;
				
				// Show the user a toast they need to press the back button
				// again to shut down the application
				Toast.makeText(this, "Press back again," + "\n" + "to shutdown the application", Toast.LENGTH_SHORT).show();
						
				// Start a handler to reset doubleBackToExitPressedOnce after 
				// the time is passed
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						doubleBackToExitPressedOnce = false;
					}					
				}, 2000);
			}
		} else {
			super.onBackPressed();
		}
	}
	
	private boolean isLoggedIn() {
		// Check if the user is logged in
		SharedPreferences sharedPreferences =  this.getSharedPreferences(LoginActivity.ACTIVE_USER, 0);
		return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
	}

	@Override
	public void OnLogin(String username, String password) {
		// TODO: Check if the login credentials are correct
		User user;
		if (username.equals("gymnast")) {
			user = new User(UserType.GYMNAST);
		} else {
			user = new User(UserType.TRAINER);
		}
		
		// Set shared preferences for user name and profile url
		SharedPreferences sharedPreferences =  this.getSharedPreferences(ACTIVE_USER, 0);
						
		// Save user data in model
		SharedPreferences.Editor mEditor = sharedPreferences.edit();
		mEditor.putInt(User.USER_TYPE, user.getType() == UserType.TRAINER ? 0 : 1);
		mEditor.putInt(Gymnast.GYMNAST_ID, user.getGymnastId());
		mEditor.putBoolean(IS_LOGGED_IN, true);
		mEditor.commit();
		
		// Start the profile activity
		startActivity(new Intent(this, ProfileActivity.class));
		this.finish();
	}
}
