package nl.avans.ras.adapter;

import java.util.ArrayList;

import nl.avans.ras.fragments.ChartFragment;
import nl.avans.ras.model.Vault;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.viewpagerindicator.IconPagerAdapter;

public class FragmentSlideAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    
	// Fields
	private ArrayList<Fragment> fragmentCollection;
	
	public FragmentSlideAdapter(FragmentManager fm, ArrayList<Fragment> fragmentCollection) {
        super(fm);
        this.fragmentCollection = fragmentCollection;
    }
	
	@Override 
	public Fragment getItem(int position) {
		return fragmentCollection.get(position);
	}

	@Override
	public int getCount() {
		return this.fragmentCollection.size();
	}

	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
}
