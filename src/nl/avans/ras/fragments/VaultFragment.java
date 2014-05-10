package nl.avans.ras.fragments;

import nl.avans.ras.R;
import nl.avans.ras.model.Gymnast;
import nl.avans.ras.model.Vault;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class VaultFragment extends Fragment implements View.OnClickListener {

	// Fields
	private Gymnast gymnast;
	private Vault vault;
	
	// Setters
	public void setVault(Vault vault) {
		if (vault != null) {
			this.vault = vault;
		}
	}
	
	public void setGymnast(Gymnast gymnast) {
		if (gymnast != null) {
			this.gymnast = gymnast;
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
		return inflater.inflate(R.layout.fragment_vault, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// Set the a listener to the button
		Button compareVaultButton = (Button) getActivity().findViewById(R.id.compare_button);
		compareVaultButton.setOnClickListener(this);
				
		// Create the titles
		TextView durationTitle = (TextView) getActivity().findViewById(R.id.vault_duration_title);
		TextView dScoreTitle = (TextView) getActivity().findViewById(R.id.vault_d_score_title);
		TextView eScoreTitle = (TextView) getActivity().findViewById(R.id.vault_e_score_title);
		TextView vaultTypeTitle = (TextView) getActivity().findViewById(R.id.vault_type_title);
		TextView vaultLocationTitle = (TextView) getActivity().findViewById(R.id.vault_location_title);
				
		// Create the containers
		TextView nameContainer = (TextView) getActivity().findViewById(R.id.profile_name_container);
		TextView dateContainer = (TextView) getActivity().findViewById(R.id.date_container);
		TextView durationContainer = (TextView) getActivity().findViewById(R.id.vault_duration_container);
		TextView dScoreContainer = (TextView) getActivity().findViewById(R.id.vault_d_score_container);
		TextView eScoreContainer = (TextView) getActivity().findViewById(R.id.vault_e_score_container);
		TextView vaultTypeContainer = (TextView) getActivity().findViewById(R.id.vault_type_container);
		TextView vaultLocationContainer = (TextView) getActivity().findViewById(R.id.vault_location_container);
			
		// Create a new font
		Typeface tfl = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
				
		// Set the font
		durationTitle.setTypeface(tfl);
		dScoreTitle.setTypeface(tfl);
		eScoreTitle.setTypeface(tfl);
		vaultTypeTitle.setTypeface(tfl);
		vaultLocationTitle.setTypeface(tfl);
		nameContainer.setTypeface(tfl);
		dateContainer.setTypeface(tfl);
		durationContainer.setTypeface(tfl);
		dScoreContainer.setTypeface(tfl);
		eScoreContainer.setTypeface(tfl);
		vaultTypeContainer.setTypeface(tfl);
		vaultLocationContainer.setTypeface(tfl);
				
		// Set the content of the containers
		nameContainer.setText(gymnast.getName());
		durationContainer.setText("" + vault.getDuration() + " sec");
		dScoreContainer.setText("" + vault.getDScore());
		eScoreContainer.setText("" + vault.getEScore());
		vaultTypeContainer.setText(vault.getType());
		vaultLocationContainer.setText(vault.getLocation());
	}

	@Override
	public void onClick(View v) {
		
	}
}
