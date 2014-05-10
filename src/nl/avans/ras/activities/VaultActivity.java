package nl.avans.ras.activities;

import java.util.ArrayList;
import java.util.Date;

import nl.avans.ras.R;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.fragments.ProfileFragment;
import nl.avans.ras.fragments.VaultFragment;
import nl.avans.ras.model.AdapterKind;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Vault;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class VaultActivity extends Activity implements ListViewFragment.OnDateSelectedListener,
													   ListViewFragment.OnVaultSelectedListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private int gymnastId;
	
	// Getters
	public int getGymnastId() {
		return gymnastId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		// Create a test list of vaults
		ArrayList<Vault> tempList = new ArrayList<Vault>();
		for(int i = 0; i < 50; i++) {
			tempList.add(new Vault(i, i, "Salto", 4.123, 8.0235, new Date()));
		}
		dbHelper.insertVaultCollection(tempList);
		
		// Check if there is an gymnast set
		Bundle bundle = getIntent().getExtras();
		gymnastId = bundle.getInt(ProfileActivity.GYMNAST_ID);
		
		// Create a new profile list fragment
    	ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.DATES);
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.commit();
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
	public void OnDateSelected(int position, Date date) {
		// Create a new profile list fragment
    	ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.VAULTS);
    	// TODO: function to set the date
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
	}

	@Override
	public void OnVaultSelected(int position, int vaultId) {
		// Create a new vault fragment
    	VaultFragment vaultFragment = new VaultFragment();
     	
    	// Get the gymnast and vault
    	Vault vault = dbHelper.getVault(vaultId);
    	Gymnast gymnast = dbHelper.getGymnast(gymnastId);
    	vaultFragment.setVault(vault);
    	vaultFragment.setGymnast(gymnast);
    	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, vaultFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
	}
}
