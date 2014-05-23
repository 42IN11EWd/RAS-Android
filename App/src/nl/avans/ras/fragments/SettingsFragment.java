package nl.avans.ras.fragments;

import nl.avans.ras.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SettingsFragment extends Fragment implements View.OnClickListener {

	// Abstract methods
	OnSavePasswordListener mOnSavePasswordListener;
	
	// Interfaces
	public interface OnSavePasswordListener {
		public void OnSavePassword(String password);
	}	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnSavePasswordListener = (OnSavePasswordListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnShowVaultsListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		return inflater.inflate(R.layout.fragment_settings, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// Get the settings items
		Button changePassword = (Button) getActivity().findViewById(R.id.change_password_button);
		Button saveNewPassword = (Button) getActivity().findViewById(R.id.save_new_password_button);
		EditText oldPasswordContainer = (EditText) getActivity().findViewById(R.id.old_password_textfield);
		EditText newPasswordContainer = (EditText) getActivity().findViewById(R.id.new_password_textfield);
		EditText confirmNewPasswordContainer = (EditText) getActivity().findViewById(R.id.confirm_new_password_textfield);
		
		// Set the visibility
		hideChangePassword();
		
		// Create a custom font
		Typeface tfl = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
		
		// Set the custom font
		changePassword.setTypeface(tfl);
		saveNewPassword.setTypeface(tfl);
		oldPasswordContainer.setTypeface(tfl);
		newPasswordContainer.setTypeface(tfl);
		confirmNewPasswordContainer.setTypeface(tfl);
		
		// Set the listener
		changePassword.setOnClickListener(this);
		saveNewPassword.setOnClickListener(this);
	}
	
	private void showChangePassword() {
		// Get the settings items
		Button changePassword = (Button) getActivity().findViewById(R.id.change_password_button);
		Button saveNewPassword = (Button) getActivity().findViewById(R.id.save_new_password_button);
		EditText oldPasswordContainer = (EditText) getActivity().findViewById(R.id.old_password_textfield);
		EditText newPasswordContainer = (EditText) getActivity().findViewById(R.id.new_password_textfield);
		EditText confirmNewPasswordContainer = (EditText) getActivity().findViewById(R.id.confirm_new_password_textfield);
		
		// Set the visibility
		changePassword.setVisibility(View.GONE);
		saveNewPassword.setVisibility(View.VISIBLE);
		oldPasswordContainer.setVisibility(View.VISIBLE);
		newPasswordContainer.setVisibility(View.VISIBLE);
		confirmNewPasswordContainer.setVisibility(View.VISIBLE);
	}
	
	private void saveNewPassword() {
		// Get the password fields
		EditText oldPasswordContainer = (EditText) getActivity().findViewById(R.id.old_password_textfield);
		EditText newPasswordContainer = (EditText) getActivity().findViewById(R.id.new_password_textfield);
		EditText confirmNewPasswordContainer = (EditText) getActivity().findViewById(R.id.confirm_new_password_textfield);
		
		// Check if the old password is right
		String oldPassword = oldPasswordContainer.getText().toString();
		// TODO: Send API request to check if the old password is matching the current password
		if (oldPassword.equals("")) {
			// Check if the new password is the same as the confirm new password
			String newPassword = newPasswordContainer.getText().toString();
			String confirmNewPassword = confirmNewPasswordContainer.getText().toString();
			if (newPassword.length() > 5) {
				if (newPassword.equals(confirmNewPassword)) {
					mOnSavePasswordListener.OnSavePassword(newPassword);
					
					// Empty the containers
					oldPasswordContainer.setText("");
					newPasswordContainer.setText("");
					confirmNewPasswordContainer.setText("");
					
					// Get the settings items
					Button changePassword = (Button) getActivity().findViewById(R.id.change_password_button);
							
					// Set the visibility
					changePassword.setVisibility(View.VISIBLE);
					hideChangePassword();
				} else {
					buildDialog("The two new passwords don't match");
				}
			} else {
				buildDialog("The password needs to be 6  characters or longer");
			}
		} else {
			buildDialog("The old password is not matching the current password");
		}
	}
	
	private void buildDialog(String text) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(text)
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                //do things
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void hideChangePassword() {
		// Get the settings items
		Button saveNewPassword = (Button) getActivity().findViewById(R.id.save_new_password_button);
		EditText oldPasswordContainer = (EditText) getActivity().findViewById(R.id.old_password_textfield);
		EditText newPasswordContainer = (EditText) getActivity().findViewById(R.id.new_password_textfield);
		EditText confirmNewPasswordContainer = (EditText) getActivity().findViewById(R.id.confirm_new_password_textfield);
		
		// Set the visibility
		saveNewPassword.setVisibility(View.GONE);
		oldPasswordContainer.setVisibility(View.GONE);
		newPasswordContainer.setVisibility(View.GONE);
		confirmNewPasswordContainer.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save_new_password_button:
			saveNewPassword();
			break;
		case R.id.change_password_button:
			showChangePassword();
			break;
		}
	}
}
