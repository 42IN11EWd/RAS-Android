package nl.avans.ras.activities;

import java.util.ArrayList;

import nl.avans.ras.R;
import nl.avans.ras.R.animator;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.fragments.ProfileFragment;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.User;
import nl.avans.ras.model.enums.AdapterKind;
import nl.avans.ras.model.enums.UserType;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends Activity implements View.OnClickListener,
														 ListViewFragment.OnProfileSelectedListener,
														 ProfileFragment.OnShowVaultsListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private boolean doubleBackToExitPressedOnce = false;
	private UserType type;
	public static final String GYMNAST_ID = "gymnast_id";
	
	// Navigation drawer
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
    
	// Getters
	public UserType getUserType() {
		return type;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		// Create a test list of gymnasts
//		ArrayList<Gymnast> tempList = new ArrayList<Gymnast>();
//		for(int i = 0; i < 50; i++) {
//			tempList.add(new Gymnast(i, "Epke", "Zonderland", "", 28, 173, 69, "Lemmer"));
//		}
//		dbHelper.insertGymnastCollection(tempList);
		
		// Create a new fragment
		Fragment fragment;
		
		// Check if the user is a gymnast or a trainer
		SharedPreferences sharedPreferences =  this.getSharedPreferences(LoginActivity.ACTIVE_USER, 0);
		type = sharedPreferences.getInt(User.USER_TYPE, 1) == 0 ? UserType.TRAINER : UserType.GYMNAST;
		
		if (type == UserType.GYMNAST) {
			// Create a new profile list fragment
	    	ProfileFragment profileFragment = new ProfileFragment();
	    	Gymnast gymnast = dbHelper.getGymnast(sharedPreferences.getInt(Gymnast.GYMNAST_ID, 0));
	    	profileFragment.setGymnast(gymnast);
	    	fragment = profileFragment;
		} else {
			// Create a new profile list fragment
	    	ListViewFragment profileListFragment = new ListViewFragment();
	    	profileListFragment.setAdapterKind(AdapterKind.PROFILES);
	     	fragment = profileListFragment;
		}
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, fragment);
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
        setMenu();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    // Sync the toggle state after onRestoreInstanceState has occurred.
	    mDrawerToggle.syncState();
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
	
	private void setMenu() {
		Button logoutButton = (Button) findViewById(R.id.logout_button);
		logoutButton.setOnClickListener(this);
	}
	
	private void logout() {
		SharedPreferences sharedPreferences = this.getSharedPreferences(LoginActivity.ACTIVE_USER, MODE_PRIVATE);
		sharedPreferences.edit().clear().commit();
		startActivity(new Intent(this, LoginActivity.class));
		this.finish();
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
		transaction.setCustomAnimations(animator.slide_in_left, animator.slide_out_right, animator.slide_in_right, animator.slide_out_left);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logout_button:
			logout();
			break;
		}
	}
}
