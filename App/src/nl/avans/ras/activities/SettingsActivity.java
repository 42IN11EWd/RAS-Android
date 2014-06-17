package nl.avans.ras.activities;

import nl.avans.ras.R;
import nl.avans.ras.fragments.SettingsFragment;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.User;
import nl.avans.ras.network.Networking;
import nl.avans.ras.services.MD5;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends Activity implements SettingsFragment.OnSavePasswordListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// Create a new fragment
		SettingsFragment settingsFragment = new SettingsFragment();
		
		// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, settingsFragment);
		transaction.commit();
		
     	// Set the back button in the actionbar
     	getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			FragmentManager fm = getFragmentManager();
			if(fm.getBackStackEntryCount() > 0) {
				fm.popBackStack();
		    } else {
		    	this.finish();
		    }
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void OnSavePassword(String oldPassword, String newPassword) {
		SharedPreferences sharedPreferences =  this.getSharedPreferences(LoginActivity.ACTIVE_USER, 0);
		
		// Hash the password with a salt
		oldPassword = MD5.hashString(oldPassword + MD5.SALT);
		newPassword = MD5.hashString(newPassword + MD5.SALT);
		
		new Networking(this).changePassword(sharedPreferences.getInt(User.USER_ID, 0), oldPassword, newPassword);
	}
	
	public void SuccesSavePassword(boolean succes) {
		if (succes) {
			Toast.makeText(this, "The password is succesfully changed.", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "The old password was not correct...", Toast.LENGTH_SHORT).show();
		}
	}
}
