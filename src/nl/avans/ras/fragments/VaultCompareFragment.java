package nl.avans.ras.fragments;

import java.util.ArrayList;

import nl.avans.ras.R;
import nl.avans.ras.model.Vault;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class VaultCompareFragment extends Fragment {

	// Fields
	private ArrayList<Vault> vaultContainer = new ArrayList<Vault>();
	
	// Setters
	public void addVault(Vault vault) {
		if (vault != null) {
			vaultContainer.add(vault);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// If the activity is recreated (screen rotation), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
//		if (savedInstanceState != null) {
//			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
//		}
		
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_vault_compare, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
