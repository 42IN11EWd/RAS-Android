package nl.avans.ras.activities;

import nl.avans.ras.R;
import nl.avans.ras.fragments.SettingsFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsActivity extends Activity {

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
}
