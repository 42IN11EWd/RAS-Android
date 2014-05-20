package nl.avans.ras.activities;

import java.util.ArrayList;

import nl.avans.ras.R;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.ChartFragment;
import nl.avans.ras.fragments.ListViewFragment;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.enums.AdapterKind;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

public class ChartActivity extends FragmentActivity {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private ArrayList<Vault> vaultCollection;
	private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final int NUM_PAGES = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		
		// Set the action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        // Get the vault collection
        Bundle bundle = this.getIntent().getExtras();
        if (bundle.containsKey(CompareActivity.VAULT_COLLECTION)) {
        	vaultCollection = bundle.getParcelableArrayList(CompareActivity.VAULT_COLLECTION);
        } else if (bundle.containsKey(Vault.VAULT_ID)) {
        	vaultCollection = new ArrayList<Vault>();
        	int vaultId = bundle.getInt(Vault.VAULT_ID);
        	Vault vault = dbHelper.getVault(vaultId);
        	vaultCollection.add(vault);
        }
        
//        // Create a new profile list fragment
//    	ChartFragment chartFragment = new ChartFragment();
//    	chartFragment.setVaultCollection(vaultCollection);
//		FragmentTransaction transaction = getFragmentManager().beginTransaction();
//     	transaction.replace(R.id.fragment_container, chartFragment);
//     	transaction.commit();
        
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
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
		case R.id.action_settings:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return ChartFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
