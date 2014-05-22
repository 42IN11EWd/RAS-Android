package nl.avans.ras.fragments;

import java.util.ArrayList;

import nl.avans.ras.R;
import nl.avans.ras.model.Vault;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChartFragment extends Fragment {

	// Fields
	protected static ArrayList<Vault> vaultCollection = new ArrayList<Vault>();
    
	// Setters
	public void setVaultCollection(ArrayList<Vault> vaultCollection) {
		if (vaultCollection != null) {
			ChartFragment.vaultCollection = vaultCollection;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_chart, container, false);
	}
	
	protected int getColor(int index) {
		int color;
		switch (index) {
		case 1:
			color = Color.BLACK;
			break;
		case 2:
			color = Color.YELLOW;
			break;
		case 3:
			color = Color.GREEN;
			break;
		case 4:
			color = Color.RED;
			break;
		case 5:
			color = Color.WHITE;
			break;
		case 6:
			color = Color.MAGENTA;
			break;
		case 7:
			color = Color.GRAY;
			break;
		case 8:
			color = Color.CYAN;
			break;
		case 9:
			color = Color.BLUE;
			break;
		default:
			color = -1;
			break;
		}
		return color;
	}
}
