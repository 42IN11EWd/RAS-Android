package nl.avans.ras.activities;

import java.util.ArrayList;

import nl.avans.ras.R;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.fragments.LoginFragment;
import nl.avans.ras.fragments.ProfileFragment;
import nl.avans.ras.model.AdapterKind;
import nl.avans.ras.model.Gymnast;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProfileActivity extends Activity implements ListViewFragment.OnProfileSelectedListener,
														 ProfileFragment.OnShowVaultsListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private boolean doubleBackToExitPressedOnce = false;
	public static final String GYMNAST_ID = "gymnast_id";
	
	// Navigation drawer
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		// Create a test list of gymnasts
		ArrayList<Gymnast> tempList = new ArrayList<Gymnast>();
		for(int i = 0; i < 50; i++) {
			tempList.add(new Gymnast(i, "Sjoerd", "Nijhof", "", 22, 190, 71, "Best"));
		}
		dbHelper.insertGymnastCollection(tempList);
		
		// Create a new profile list fragment
    	ListViewFragment profileListFragment = new ListViewFragment();
    	profileListFragment.setAdapterKind(AdapterKind.PROFILES);
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, profileListFragment);
     	transaction.commit();
     	
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
     	// Set the menu
     	mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
     	mDrawerToggle = new ActionBarDrawerToggle(
     			this, 
     			mDrawerLayout,
                R.drawable.ic_drawer, 
                R.string.drawer_open, 
                R.string.drawer_close
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void OnProfileSelected(int position, int gymnastId) {
		// Create a new profile list fragment
    	ProfileFragment profileFragment = new ProfileFragment();
     	
    	// Get the gymnast
    	Gymnast gymnast = dbHelper.getGymnast(gymnastId);
    	profileFragment.setGymnast(gymnast);
    	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, profileFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
	}

	@Override
	public void OnShowVaults(int gymnastId) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, VaultActivity.class);
		intent.putExtra(GYMNAST_ID, gymnastId);
		startActivity(intent);
	}
}
