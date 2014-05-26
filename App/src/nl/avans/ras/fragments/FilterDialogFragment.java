package nl.avans.ras.fragments;

import nl.avans.ras.R;
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
import android.widget.TextView;

public class FilterDialogFragment extends DialogFragment {

	// Abstract methods
	OnSaveFilterListener mOnSaveFilterListener;
	
	// Interfaces
	public interface OnSaveFilterListener {
		public void OnSaveFilter(String password);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnSaveFilterListener = (OnSaveFilterListener) activity;
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
	                   mOnSaveFilterListener.OnSaveFilter("");
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   FilterDialogFragment.this.getDialog().cancel();
	               }
	           });
	    
	    // return the dialog
	    return builder.create();
	}
	
	private View setView(View view) {
		// Get the view items
		TextView titleContainer = (TextView) view.findViewById(R.id.filter_dialog_title);
	    
	    // Create a custom font
	    Typeface tfl = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
	    
	    // Set the fonts
	    titleContainer.setTypeface(tfl);
	    
	    // Return the view
	    return view;
	}
}
