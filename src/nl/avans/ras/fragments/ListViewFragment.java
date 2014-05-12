package nl.avans.ras.fragments;

import static nl.avans.ras.database.DatabaseNodes.*;

import java.util.Date;

import nl.avans.ras.R;
import nl.avans.ras.activities.CompareActivity;
import nl.avans.ras.activities.VaultActivity;
import nl.avans.ras.adapter.CustomCursorAdapter;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.enums.AdapterKind;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewFragment extends Fragment implements OnItemClickListener {

	// Abstract methods
	OnProfileSelectedListener mSelectedProfileListener;
	OnDateSelectedListener mSelectedDateListener;
	OnVaultSelectedListener mSelectedVaultListener;
	OnCompareVaultSelectedListener mSelectedCompareVault;
	
	// Fields
	private CustomCursorAdapter customAdapter;
	private AdapterKind kind;
	private ListView list;
	
	// Setters
	public void setAdapterKind(AdapterKind kind) {
		if (kind != null) {
			this.kind = kind;
		}
	}
	
	// Interfaces
	public interface OnProfileSelectedListener {
		public void OnProfileSelected(int position, int gymnastId);
	}
	
	public interface OnDateSelectedListener {
		public void OnDateSelected(int position, Date date);
	}
	
	public interface OnVaultSelectedListener {
		public void OnVaultSelected(int position, int vaultId);
	}
	
	public interface OnCompareVaultSelectedListener {
		public void onCompareVaultSelected(int vaultId);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			switch (kind) {
			case DATES:
				mSelectedDateListener = (OnDateSelectedListener) activity;
				break;
			case PROFILES:
				mSelectedProfileListener = (OnProfileSelectedListener) activity;
				break;
			case VAULTS:
				mSelectedVaultListener = (OnVaultSelectedListener) activity;
				break;
			case COMPARE:
				mSelectedCompareVault = (OnCompareVaultSelectedListener) activity;
			}
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_list, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		switch (kind) {
		case PROFILES:
			customAdapter = new CustomCursorAdapter(getActivity(), new DatabaseHelper(getActivity()).getAllGymnast(), kind);
			break;
		case VAULTS:
			if (getActivity() instanceof VaultActivity) {
				VaultActivity mActivity = (VaultActivity) getActivity();
				customAdapter = new CustomCursorAdapter(mActivity, new DatabaseHelper(mActivity).getAllVaultsFromGymnast(mActivity.getGymnastId(), new Date()), kind);
			}
			break;
		case DATES:
			if (getActivity() instanceof VaultActivity) {
				VaultActivity mActivity = (VaultActivity) getActivity();
				customAdapter = new CustomCursorAdapter(mActivity, new DatabaseHelper(mActivity).getAllVaultsFromGymnast(mActivity.getGymnastId()), kind);
			} else if (getActivity() instanceof CompareActivity) {
				CompareActivity mActivity = (CompareActivity) getActivity();
				customAdapter = new CustomCursorAdapter(mActivity, new DatabaseHelper(mActivity).getAllVaultsFromGymnast(mActivity.getGymnastId()), kind);
			}
			break;
		case COMPARE:
			if (getActivity() instanceof CompareActivity) {
				CompareActivity mActivity = (CompareActivity) getActivity();
				customAdapter = new CustomCursorAdapter(mActivity, new DatabaseHelper(mActivity).getAllVaultsFromGymnast(mActivity.getGymnastId()), kind);
			}
			break;
		}
		
		// Set the list
		list = (ListView) getActivity().findViewById(R.id.listview);
		list.setAdapter(customAdapter);
		list.setOnItemClickListener(this);
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		switch (kind) {
		case DATES:
			onDateClick(list, view, position, id);
			break;
		case PROFILES:
			onProfileClick(list, view, position, id);
			break;
		case VAULTS:
			onVaultClick(list, view, position, id);
			break;
		case COMPARE:
			onCompareVaultClick(list, view, position, id);
			break;
		}
	}
	
	public void onProfileClick(ListView list, View view, int position, long id) {
		Cursor cursor = (Cursor) list.getAdapter().getItem(position);
		int gymnastId = cursor.getInt(cursor.getColumnIndex(COL_GYMNAST_ID));
		
		// Notify the activity of selected item
		mSelectedProfileListener.OnProfileSelected(position, gymnastId);
	}
	
	public void onDateClick(ListView list, View view, int position, long id) {
		Cursor cursor = (Cursor) list.getAdapter().getItem(position);
		Date date = new Date(cursor.getLong(cursor.getColumnIndex(COL_DATE)));
		
		// Notify the activity of selected item
		mSelectedDateListener.OnDateSelected(position, date);
	}
	
	public void onVaultClick(ListView list, View view, int position, long id) {
		Cursor cursor = (Cursor) list.getAdapter().getItem(position);
		int vaultId = cursor.getInt(cursor.getColumnIndex(COL_VAULT_ID));
		
		// Notify the activity of selected item
		mSelectedVaultListener.OnVaultSelected(position, vaultId);
	}
	
	public void onCompareVaultClick(ListView list, View view, int position, long id) {
		Cursor cursor = (Cursor) list.getAdapter().getItem(position);
		int vaultId = cursor.getInt(cursor.getColumnIndex(COL_VAULT_ID));
		
		// Notify the activity of selected item
		mSelectedCompareVault.onCompareVaultSelected(vaultId);
	}
}
