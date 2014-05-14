package nl.avans.ras.fragments;

import nl.avans.ras.R;
import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment implements View.OnClickListener {
	
	// Abstract methods
	OnLoginListener mLoginListener;
	
	public interface OnLoginListener {
		public void OnLogin(String username, String password);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mLoginListener = (OnLoginListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
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
		return inflater.inflate(R.layout.fragment_login, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		EditText usernameContainer = (EditText) getActivity().findViewById(R.id.username_textfield);
		EditText passwordContainer = (EditText) getActivity().findViewById(R.id.password_textfield);
		Button loginButton = (Button) getActivity().findViewById(R.id.login_button);
	
		// Create a new font
		Typeface tfl = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
		
		// Set the font
		usernameContainer.setTypeface(tfl);
		passwordContainer.setTypeface(tfl);
		loginButton.setTypeface(tfl);
		
		// Set the on click listener
		loginButton.setOnClickListener(this);
	}
	
	/*
	 * This function will try to login
	 */
	public void Login(View view) {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_button:
				EditText usernameContainer = (EditText) getActivity().findViewById(R.id.username_textfield);
				EditText passwordContainer = (EditText) getActivity().findViewById(R.id.password_textfield);
				
				mLoginListener.OnLogin(usernameContainer.getText().toString(), passwordContainer.getText().toString());		
				break;
		}
	}
}
