package nl.avans.ras.activities;

import java.util.ArrayList;
import java.util.Date;

import nl.avans.ras.R;
import nl.avans.ras.R.animator;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.FilterDialogFragment;
import nl.avans.ras.fragments.ListFilterDialogFragment;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.fragments.VaultFragment;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.User;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.enums.AdapterKind;
import nl.avans.ras.model.enums.UserType;
import nl.avans.ras.network.Networking;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class VaultActivity extends Activity implements ListViewFragment.OnDateSelectedListener,
													   ListViewFragment.OnVaultSelectedListener,
													   VaultFragment.OnCompareVaultListener,
													   VaultFragment.OnChartVaultListener,
													   FilterDialogFragment.OnSaveFilterListener,
													   FilterDialogFragment.OnCancleDialogListener,
													   ListFilterDialogFragment.OnSaveLocationListener,
													   ListFilterDialogFragment.OnSaveVaultTypeListener,
													   ListFilterDialogFragment.OnCancleListDialogListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private int gymnastId;
	private Date date;
	private MenuItem filterMenuItem;
	private boolean filterMenuItemVisible = false;
	private String vaultTypeFilter = "", locationFilter = "";
	protected static ProgressDialog mProgressDialog;
	
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
		
		// Check if there is an gymnast set
		Bundle bundle = getIntent().getExtras();
		gymnastId = bundle.getInt(ProfileActivity.GYMNAST_ID);
		
		// Check if the user is a gymnast or a trainer
		SharedPreferences sharedPreferences =  this.getSharedPreferences(LoginActivity.ACTIVE_USER, 0);
		UserType type = sharedPreferences.getInt(User.USER_TYPE, 1) == 0 ? UserType.TRAINER : UserType.GYMNAST;
		
		// Get the list of vaults
		if (dbHelper.hasVaults(gymnastId)) {
//			if (CacheHandler.updateCache(dbHelper.getVaultUpdateDate(), new Date())) {
//				// Clear all the vaults
//				dbHelper.clearVaultCache();
//				
//				// Get the vaults
//				if (type == UserType.GYMNAST) {
//					new Networking(this).getVaultsOfSpecificGymnast(gymnastId);
//				} else if (type == UserType.TRAINER) {
//					new Networking(this).getVaultsOfSpecificGymnast(gymnastId);
//				}
//			} else {
			insertFragment();
//			}
		} else {
			mProgressDialog = ProgressDialog.show(this, null, "Loading Vaults...", false);
			if (type == UserType.GYMNAST) {
				new Networking(this).getVaultsOfSpecificGymnast(gymnastId);
			} else if (type == UserType.TRAINER) {
				new Networking(this).getAllVaults();
			}
		}
     	
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
			showFilterDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void insertVaults(ArrayList<Vault> vaultCollection) {
		mProgressDialog.dismiss();
		
		dbHelper.insertVaultCollection(vaultCollection);
		dbHelper.insertUpdateVaultDate(new Date());
		
		insertFragment();
	}
	
	private void insertFragment() {
		// Create a new profile list fragment
    	ListViewFragment vaultListFragment = new ListViewFragment();
    	vaultListFragment.setAdapterKind(AdapterKind.DATES);
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, vaultListFragment);
     	transaction.commit();
	}
	
	@Override
	public void OnDateSelected(int position, Date date) {
		if (date != null) {
			this.date = date;
		}
		
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

	private void showFilterDialog() {
		FilterDialogFragment dialog = new FilterDialogFragment();
		dialog.setVaultType(vaultTypeFilter);
		dialog.setLocation(locationFilter);
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
	}
	
	@Override
	public void OnSaveFilter(String vaultType, String location) {
		if (location != null && !location.isEmpty() || vaultType != null && !vaultType.isEmpty()) {
			if (vaultType != null) {
				this.vaultTypeFilter = vaultType;
			} else {
				this.vaultTypeFilter = "";
			}
			if (location != null) {
				this.locationFilter = location;
			} else {
				this.locationFilter = "";
			}
			
			// Create a new cursor
			Cursor cursor = dbHelper.getAllVaultsFromGymnastFilter(gymnastId, date, vaultTypeFilter, locationFilter);
			
			// Create a new profile list fragment
	    	ListViewFragment vaultListFragment = new ListViewFragment();
	    	vaultListFragment.setAdapterKind(AdapterKind.VAULTS);
	    	vaultListFragment.setCursor(cursor);
	     	
	     	// Replace the fragment
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
	     	transaction.replace(R.id.fragment_container, vaultListFragment);
	     	transaction.commit();
		} else {
			this.locationFilter = "";
			this.vaultTypeFilter = "";
			
			// Create a new profile list fragment
	    	ListViewFragment vaultListFragment = new ListViewFragment();
	    	vaultListFragment.setAdapterKind(AdapterKind.VAULTS);
	     	
	     	// Replace the fragment
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
	     	transaction.replace(R.id.fragment_container, vaultListFragment);
	     	transaction.commit();
		}
	}

	@Override
	public void OnSaveVaultType(String vaultType) {
		if (vaultType != null) {
			if (vaultType.equals(this.vaultTypeFilter)) {
				this.vaultTypeFilter = "";
			} else {
				this.vaultTypeFilter = vaultType;
			}
		}
		showFilterDialog();
	}

	@Override
	public void OnSaveLocation(String location) {
		if (location != null) {
			if (location.equals(this.locationFilter)) {
				this.locationFilter = "";
			} else {
				this.locationFilter = location;
			}
		}
		showFilterDialog();
	}

	@Override
	public void OnCancleListDialog() {
		showFilterDialog();
	}

	@Override
	public void onCancleDialog() {
		locationFilter = "";
		vaultTypeFilter = "";
	}
}
