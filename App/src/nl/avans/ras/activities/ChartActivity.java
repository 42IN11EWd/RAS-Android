package nl.avans.ras.activities;

import java.util.ArrayList;

import nl.avans.ras.R;
import nl.avans.ras.adapter.FragmentSlideAdapter;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.ChartFragment;
import nl.avans.ras.fragments.DistanceChartFragment;
import nl.avans.ras.fragments.SpeedChartFragment;
import nl.avans.ras.model.Vault;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

public class ChartActivity extends FragmentActivity {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private ArrayList<Vault> vaultCollection;
	private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
	
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
        
        // Create the speed fragment
        ChartFragment speedChartFragment = new SpeedChartFragment();
        speedChartFragment.setVaultCollection(vaultCollection);
        
        // Create the distance fragment
        ChartFragment distanceChartFragment = new DistanceChartFragment();
        distanceChartFragment.setVaultCollection(vaultCollection);
        
        // Create an arraylist with both fragments
        ArrayList<Fragment> chartFragmentContainer = new ArrayList<Fragment>();
        chartFragmentContainer.add(speedChartFragment);
        chartFragmentContainer.add(distanceChartFragment);
        
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new FragmentSlideAdapter(getSupportFragmentManager(), chartFragmentContainer);
        mPager.setAdapter(mPagerAdapter);
        
        // Instantiate the viewpager indicator
        CirclePageIndicator circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(mPager);
        circleIndicator.setFillColor(Color.BLACK);
        circleIndicator.setSnap(true);
        circleIndicator.setStrokeColor(Color.LTGRAY);
        circleIndicator.setStrokeWidth(2);
        circleIndicator.notifyDataSetChanged();
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
