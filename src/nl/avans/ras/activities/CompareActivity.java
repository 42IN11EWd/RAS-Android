package nl.avans.ras.activities;

import java.util.ArrayList;
import java.util.Date;

import nl.avans.ras.R;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.fragments.ProfileFragment;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.User;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.enums.AdapterKind;
import nl.avans.ras.model.enums.UserType;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CompareActivity extends Activity implements ListViewFragment.OnProfileSelectedListener,
														 ListViewFragment.OnDateSelectedListener,
														 ListViewFragment.OnCompareVaultSelectedListener {
	
	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private ArrayList<Vault> vaultCollection = new ArrayList<Vault>();
	private UserType type;
	private int gymnastId;
	private MenuItem compareMenuItem;
	
	// Getters
	public ArrayList<Vault> getVaultCollection() {
		ArrayList<Vault> returnList;
		if (vaultCollection != null) {
			returnList = vaultCollection;
		} else {
			returnList = new ArrayList<Vault>();
		}
		return returnList;
	}
	
	public int getGymnastId() {
		return gymnastId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);
		
		// Check if there is an gymnast set
		Bundle bundle = getIntent().getExtras();
		int vaultId = bundle.getInt(Vault.VAULT_ID);
		Vault vault = dbHelper.getVault(vaultId);
		vaultCollection.add(vault);
		
		// Get the gymnast id
		gymnastId = vault.getGymnastId();
		
		// Check if the user is a gymnast or a trainer
		SharedPreferences sharedPreferences =  this.getSharedPreferences(LoginActivity.ACTIVE_USER, 0);
		type = sharedPreferences.getInt(User.USER_TYPE, 1) == 0 ? UserType.TRAINER : UserType.GYMNAST;
		
		// Create a new profile list fragment
		ListViewFragment compareListFragment = new ListViewFragment();
		if (type == UserType.TRAINER) {
			compareListFragment.setAdapterKind(AdapterKind.PROFILES);
		} else {
	    	compareListFragment.setAdapterKind(AdapterKind.DATES);
		}
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, compareListFragment);
     	transaction.commit();
     	
     	// Set the back button in the actionbar
     	getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.compare, menu);
		compareMenuItem = (MenuItem) menu.findItem(R.id.compare_menu_item);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (compareMenuItem != null) {
			compareMenuItem.setVisible(false);
		}
		return super.onPrepareOptionsMenu(menu);
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
		case R.id.compare_menu_item:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void OnProfileSelected(int position, int gymnastId) {
		// Create a new profile list fragment
		ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.DATES);
     	
    	// Get the gymnast
    	this.gymnastId = gymnastId;
    	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
	}
	
	@Override
	public void OnDateSelected(int position, Date date) {
		// Create a new profile list fragment
    	ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.COMPARE);
    	// TODO: function to set the date
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
     	
     	// TODO: Add compare menu item
	}

	@Override
	public void onCompareVaultSelected(int vaultId) {
		Vault vault = dbHelper.getVault(vaultId);
//		if (vaultCollection.contains(vault)) {
//			vaultCollection.remove(vault);
//		} else {
			vaultCollection.add(vault);
//		}		
	}
}
