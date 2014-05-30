package nl.avans.ras.fragments;

import nl.avans.ras.R;
import nl.avans.ras.model.enums.AdapterKind;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FilterDialogFragment extends DialogFragment implements View.OnClickListener {

	// Abstract methods
	OnSaveFilterListener mOnSaveFilterListener;
	OnCancleDialogListener mOnCancleDialogListener;
	
	// Interfaces
	public interface OnSaveFilterListener {
		public void OnSaveFilter(String vaultType, String location);
	}
	public interface OnCancleDialogListener {
		public void onCancleDialog();
	}
	public interface OnClearListener {
		public void onClear();
	}
	
	// Fields
	private String vaultType = "", location = "";
	private Button vaultTypeButton, locationButton;
	
	// Setters
	public void setVaultType(String vaultType) {
		if (vaultType != null) {
			this.vaultType = vaultType;
		}
	}
	public void setLocation(String location) {
		if (location != null) {
			this.location = location;
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnSaveFilterListener = (OnSaveFilterListener) activity;
			mOnCancleDialogListener = (OnCancleDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnSaveFilterListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    
	    // Initialize the view
	    View view = inflater.inflate(R.layout.fragment_filter, null);
	    view = setView(view);
	    
	    // Inflate and set the layout for the dialog
	    builder.setView(view)
	    	   .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   mOnSaveFilterListener.OnSaveFilter(vaultType, location);
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   FilterDialogFragment.this.getDialog().cancel();
	            	   mOnCancleDialogListener.onCancleDialog();
	               }
	           });
	    
	    // return the dialog
	    return builder.create();
	}
	
	private View setView(View view) {
		// Get the view items
		vaultTypeButton = (Button) view.findViewById(R.id.vault_type_filter_button);
		locationButton = (Button) view.findViewById(R.id.location_filter_button);
		Button clearButton = (Button) view.findViewById(R.id.clear_button);
		TextView titleContainer = (TextView) view.findViewById(R.id.filter_dialog_title);
	    
	    // Create a custom font
	    Typeface tfl = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
	    
	    // Set the fonts
	    vaultTypeButton.setTypeface(tfl);
	    locationButton.setTypeface(tfl);
	    clearButton.setTypeface(tfl);
	    titleContainer.setTypeface(tfl);
	    
	    // Set text of the buttons
	    if (vaultType != null && !vaultType.isEmpty()) {
	    	vaultTypeButton.setText(vaultType);
	    } else {
	    	vaultTypeButton.setText("None");
	    }
	    if (location != null && !location.isEmpty()) {
	    	locationButton.setText(location);
	    } else {
	    	locationButton.setText("None");
	    }
	    
	    // Add on click listeners
	    vaultTypeButton.setOnClickListener(this);
	    locationButton.setOnClickListener(this);
	    clearButton.setOnClickListener(this);
	    
	    // Return the view
	    return view;
	}

	@Override
	public void onClick(View v) {
		ListFilterDialogFragment dialog;
		switch (v.getId()) {
		case R.id.vault_type_filter_button:
			dialog = new ListFilterDialogFragment();
			dialog.setKind(AdapterKind.VAULT_TYPES);
			dialog.show(getFragmentManager(), "");
			this.dismiss();
			break;
		case R.id.location_filter_button:
			dialog = new ListFilterDialogFragment();
			dialog.setKind(AdapterKind.LOCATIONS);
			dialog.show(getFragmentManager(), "");
			this.dismiss();
			break;
		case R.id.clear_button:
			// Clear the filter items
			vaultType = "";
			location = "";
			
			// Set text of the buttons
		    if (vaultTypeButton != null) {
		    	vaultTypeButton.setText("None");
		    }
		    if (locationButton != null) {
		    	locationButton.setText("None");
		    }
			break;
		}
	}
}
