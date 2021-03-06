package nl.avans.ras.fragments;

import nl.avans.ras.R;
import nl.avans.ras.model.Gymnast;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

	// Abstract methods
	OnShowVaultsListener mShowVaultsListener;
		
	// Fields
	private Gymnast gymnast;
	
	// Setter
	public void setGymnast(Gymnast gymnast) {
		if (gymnast != null) {
			this.gymnast = gymnast;
		}
	}
	
	// Interfaces
	public interface OnShowVaultsListener {
		public void OnShowVaults(int gymnastId);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mShowVaultsListener = (OnShowVaultsListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnShowVaultsListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// Set the a listener to the button
		Button showVaultsButton = (Button) getActivity().findViewById(R.id.vault_button);
		showVaultsButton.setOnClickListener(this);
		
		// Create the titles
		TextView ageTitle = (TextView) getActivity().findViewById(R.id.profile_age_title);
		TextView lengthTitle = (TextView) getActivity().findViewById(R.id.profile_length_title);
		TextView weightTitle = (TextView) getActivity().findViewById(R.id.profile_weight_title);
		TextView traingLocationTitle = (TextView) getActivity().findViewById(R.id.profile_training_location_title);
		
		// Create the containers
		TextView nameContainer = (TextView) getActivity().findViewById(R.id.profile_name_container);
		TextView ageContainer = (TextView) getActivity().findViewById(R.id.profile_age_container);
		TextView lengthContainer = (TextView) getActivity().findViewById(R.id.profile_length_container);
		TextView weightContainer = (TextView) getActivity().findViewById(R.id.profile_weight_container);
		TextView traingLocationContainer = (TextView) getActivity().findViewById(R.id.profile_training_location_container);
	
		// Create a new font
		Typeface tfl = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
		
		// Set the font
		ageTitle.setTypeface(tfl);
		lengthTitle.setTypeface(tfl);
		weightTitle.setTypeface(tfl);
		traingLocationTitle.setTypeface(tfl);
		nameContainer.setTypeface(tfl);
		ageContainer.setTypeface(tfl);
		lengthContainer.setTypeface(tfl);
		weightContainer.setTypeface(tfl);
		traingLocationContainer.setTypeface(tfl);
		
		// Set the content of the containers
		nameContainer.setText(gymnast.getName());
		ageContainer.setText(gymnast.getBirthdayString());
		lengthContainer.setText(gymnast.getLength() + " cm");
		weightContainer.setText(gymnast.getWeight() + " kg");
		traingLocationContainer.setText(gymnast.getTurnbondId());
		
		// Set the profile image
		ImageView profileImageContainer = (ImageView) getActivity().findViewById(R.id.profile_image);
		profileImageContainer.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.vault_button:
			mShowVaultsListener.OnShowVaults(gymnast.getId());
			break;
		}
	}
}
