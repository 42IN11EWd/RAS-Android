package nl.avans.ras.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import nl.avans.ras.R;
import nl.avans.ras.R.animator;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.FilterDialogFragment;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.fragments.ProfileFragment;
import nl.avans.ras.fragments.ChartFragment;
import nl.avans.ras.fragments.VaultFragment;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.enums.AdapterKind;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class VaultActivity extends Activity implements ListViewFragment.OnDateSelectedListener,
													   ListViewFragment.OnVaultSelectedListener,
													   VaultFragment.OnCompareVaultListener,
													   VaultFragment.OnChartVaultListener,
													   FilterDialogFragment.OnSaveFilterListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private int gymnastId;
	private MenuItem filterMenuItem;
	private boolean filterMenuItemVisible = false;
	
	// Getters
	public int getGymnastId() {
		return gymnastId;
	}

	// Setters
	public void setFilterMenuItem(boolean visibility) {
		filterMenuItemVisible = visibility;
		invalidateOptionsMenu();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		// Create a test list of vaults
		ArrayList<Vault> tempList = new ArrayList<Vault>();
		int counter = 0;
		for(int i = 0; i < 50; i++) {
			for(int x = 0; x < 50; x++) {
				tempList.add(new Vault(counter, i, "Salto", 4.123, 8.0235, new Date()));
				counter++;
			}
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
     	
     	// Set the back button in the actionbar
     	getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		filterMenuItem = (MenuItem) menu.findItem(R.id.filter_menu_item);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (filterMenuItem != null) {
			if (filterMenuItemVisible) {
				filterMenuItem.setVisible(true);
			} else {
				filterMenuItem.setVisible(false);
			}
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
		case R.id.filter_menu_item:
			FilterDialogFragment dialog = new FilterDialogFragment();
	        dialog.show(getFragmentManager(), "NoticeDialogFragment");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void OnDateSelected(int position, Date date) {
		// Set the menu
		setFilterMenuItem(true);
				
		// Create a new profile list fragment
    	ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.VAULTS);
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.setCustomAnimations(animator.slide_in_left, animator.slide_out_right, animator.slide_in_right, animator.slide_out_left);
     	transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
	}

	@Override
	public void OnVaultSelected(int position, int vaultId) {
		// Set the menu
		setFilterMenuItem(false);
		
		// Create a new vault fragment
    	VaultFragment vaultFragment = new VaultFragment();
     	
    	// Get the gymnast and vault
    	Vault vault = dbHelper.getVault(vaultId);
    	Gymnast gymnast = dbHelper.getGymnast(gymnastId);
    	vaultFragment.setVault(vault);
    	vaultFragment.setGymnast(gymnast);
    	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.setCustomAnimations(animator.slide_in_left, animator.slide_out_right, animator.slide_in_right, animator.slide_out_left);
     	transaction.replace(R.id.fragment_container, vaultFragment);
     	transaction.addToBackStack(null);
     	transaction.commit();
	}

	@Override
	public void OnCompareVault(Vault vault) {
		Intent intent = new Intent(this, CompareActivity.class);
		intent.putExtra(Vault.VAULT_ID, vault.getId());
		startActivity(intent);
	}

	@Override
	public void SeeChart(Vault vault) {
		Intent intent = new Intent(this, ChartActivity.class);
		intent.putExtra(Vault.VAULT_ID, vault.getId());
		startActivity(intent);
	}

	@Override
	public void OnSaveFilter(String password) {
		// Create a new profile list fragment
    	ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.VAULTS);
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.commit();
	}
}
