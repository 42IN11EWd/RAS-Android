package nl.avans.ras.fragments;

import static nl.avans.ras.database.DatabaseNodes.*;

import nl.avans.ras.R;
import nl.avans.ras.adapter.CustomCursorAdapter;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.model.enums.AdapterKind;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListFilterDialogFragment extends DialogFragment implements OnItemClickListener {
	
	// Abstract methods
	OnSaveLocationListener mOnSaveLocationListener;
	OnSaveVaultTypeListener mOnSaveVaultTypeListener;
	OnCancleListDialogListener mOnCancleListener;
	
	// Interfaces
	public interface OnSaveLocationListener {
		public void OnSaveLocation(String location);
	}
	public interface OnSaveVaultTypeListener {
		public void OnSaveVaultType(String vaultType);
	}
	public interface OnCancleListDialogListener {
		public void OnCancleListDialog();
	}
	
	// Fields
	private AdapterKind kind;
	private CustomCursorAdapter adapter;
	private ListView dialogList;
	private String location = "", vaultType = "";
	
	// Setter
	public void setKind(AdapterKind kind) {
		if (kind != null) {
			this.kind = kind;
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnSaveLocationListener = (OnSaveLocationListener) activity;
			mOnSaveVaultTypeListener = (OnSaveVaultTypeListener) activity;
			mOnCancleListener = (OnCancleListDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnSaveLocationListener and OnSaveVaultTypeListener");
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
	    View view = inflater.inflate(R.layout.fragment_list_dialog, null);
	    
	    // Create an adapter
	    Context context = getActivity();
	    Cursor cursor;
	    switch (kind) {
	    case LOCATIONS:
	    	cursor = new DatabaseHelper(context).getAllLocations();
	    	break;
	    case VAULT_TYPES:
	    	cursor = new DatabaseHelper(context).getAllVaultTypes();
	    	break;
	    default:
	    	cursor = null;
	    	break;
	    }
	    adapter = new CustomCursorAdapter(context, cursor, kind);
	    
	    // Set the listview
	    dialogList = (ListView) view.findViewById(R.id.dialog_list);
	    dialogList.setAdapter(adapter);
	    dialogList.setOnItemClickListener(this);
	    
	    // Inflate and set the layout for the dialog
	    builder.setView(view)
	    	   .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   switch (kind) {
	            	   case LOCATIONS:
	            		   mOnSaveLocationListener.OnSaveLocation(location);
	            		   break;
	            	   case VAULT_TYPES:
	            		   mOnSaveVaultTypeListener.OnSaveVaultType(vaultType);
	            		   break;
	            	   default:
	            		   break;
	            	   }
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   ListFilterDialogFragment.this.getDialog().cancel();
	            	   mOnCancleListener.OnCancleListDialog();
	               }
	           });
	    
	    // return the dialog
	    return builder.create();
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		switch (kind) {
 	   case LOCATIONS:
 		   saveLocation(view, position, id);
 		   break;
 	   case VAULT_TYPES:
 		   saveVaultType(view, position, id);
 		   break;
 	   default:
 		   break;
 	   }
	}
	
	private void saveLocation(View view, int position, long id) {
		Cursor cursor = (Cursor) dialogList.getAdapter().getItem(position);
		location = cursor.getString(cursor.getColumnIndex(COL_LOCATION));
		
		// Notify the activity of selected item
		adapter.notifyDataSetChanged();
	}
	
	private void saveVaultType(View view, int position, long id) {
		Cursor cursor = (Cursor) dialogList.getAdapter().getItem(position);
		vaultType = cursor.getString(cursor.getColumnIndex(COL_VAULT_NAME));
		
		// Notify the activity of selected item
		adapter.notifyDataSetChanged();
	}
}
