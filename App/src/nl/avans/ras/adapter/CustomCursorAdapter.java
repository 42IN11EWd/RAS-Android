package nl.avans.ras.adapter;

import static nl.avans.ras.database.DatabaseNodes.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.avans.ras.R;
import nl.avans.ras.activities.CompareActivity;
import nl.avans.ras.activities.VaultActivity;
import nl.avans.ras.model.Vault;
import nl.avans.ras.model.enums.AdapterKind;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {
	
	// Fields
	private AdapterKind kind;
	private Typeface tfl;
	
	// Constructor
	public CustomCursorAdapter(Context context, Cursor cursor, AdapterKind kind) {
		super(context, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		this.tfl = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		this.kind = kind;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		
		View convertView;
		switch (kind) {
		case PROFILES:
			convertView = inflater.inflate(R.layout.cell_user, parent, false);
			break;
		default:
			convertView = inflater.inflate(R.layout.cell, parent, false);
			break;
		}
		
		return convertView;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView listTitle, listSubtitle = null;
		ImageView imageContainer = null;
		
		switch (kind) {
		case PROFILES:
			imageContainer = (ImageView) view.findViewById(R.id.cell_profile_imageview);
			listTitle = (TextView) view.findViewById(R.id.cell_profile_username);
			listTitle.setTypeface(tfl);
			
			// Set the profile cell
			setProfileCell(cursor, imageContainer, listTitle);
			break;
		case VAULTS:
			listTitle = (TextView) view.findViewById(R.id.cell_title);
			listSubtitle = (TextView) view.findViewById(R.id.cell_subtitle);
			listTitle.setTypeface(tfl);
			listSubtitle.setTypeface(tfl);
			
			// Set the profile cell
			setVaultCell(cursor, listTitle, listSubtitle);
			break;
		case DATES:
			listTitle = (TextView) view.findViewById(R.id.cell_title);
			listSubtitle = (TextView) view.findViewById(R.id.cell_subtitle);
			listTitle.setTypeface(tfl);
			
			// Hide subtitle
			listSubtitle.setVisibility(View.GONE);
			
			// Set the profile cell
			setDateCell(cursor, listTitle);
			break;
		case VAULT_TYPES:
			listTitle = (TextView) view.findViewById(R.id.cell_title);
			listSubtitle = (TextView) view.findViewById(R.id.cell_subtitle);
			listTitle.setTypeface(tfl);
			
			// Hide subtitle
			listSubtitle.setVisibility(View.GONE);
			
			// Set the profile cell
			setVaultTypeCell(cursor, listTitle);
			break;
		case LOCATIONS:
			listTitle = (TextView) view.findViewById(R.id.cell_title);
			listSubtitle = (TextView) view.findViewById(R.id.cell_subtitle);
			listTitle.setTypeface(tfl);
			
			// Hide subtitle
			listSubtitle.setVisibility(View.GONE);
			
			// Check if a location isset
//			VaultActivity mActivity = (VaultActivity) context;
//			mActivity.getFragmentManager().getFragment(arg0, arg1)
//			if ( == cursor.getString(cursor.getColumnIndex(COL_LOCATION))) {
//				listTitle.setTextColor(Color.WHITE);
//				view.setBackgroundResource(R.color.orange);
//				break;
//			} else {
//				listTitle.setTextColor(Color.BLACK);
//				view.setBackgroundResource(android.R.color.transparent);
//			}
			
			// Set the profile cell
			setLocationCell(cursor, listTitle);
			break;		
		case COMPARE:
			if (context instanceof CompareActivity) {
				listTitle = (TextView) view.findViewById(R.id.cell_title);
				listSubtitle = (TextView) view.findViewById(R.id.cell_subtitle);
				listTitle.setTypeface(tfl);
				listSubtitle.setTypeface(tfl);
				
				// Set the profile cell
				setVaultCell(cursor, listTitle, listSubtitle);
				
				// Check if the item is in the list
				CompareActivity mActivity = (CompareActivity) context;
				ArrayList<Vault> vaultCollection = mActivity.getVaultCollection();
				for (Vault vault: vaultCollection) {
					if (vault.getId() == cursor.getInt(cursor.getColumnIndex(COL_VAULT_ID))) {
						listTitle.setTextColor(Color.WHITE);
						view.setBackgroundResource(R.color.orange);
						break;
					} else {
						listTitle.setTextColor(Color.BLACK);
						view.setBackgroundResource(android.R.color.transparent);
					}
				}
				break;
			}
		}
	}
	
	private void setProfileCell(Cursor cursor, ImageView image, TextView title) {
		String firstname = cursor.getString(cursor.getColumnIndex(COL_FIRSTNAME));
		String surname = cursor.getString(cursor.getColumnIndex(COL_SURNAME));
		String surnamePrefix = cursor.getString(cursor.getColumnIndex(COL_SURNAME_PREFIX));
		
		if (surnamePrefix != null && !surnamePrefix.isEmpty()) {
			title.setText(firstname + " " + surnamePrefix + " " + surname);
		} else {
			title.setText(firstname + " " + surname);
		}
		
		image.setImageResource(R.drawable.test);
	}
	
	private void setVaultCell(Cursor cursor, TextView title, TextView subtitle) {
		// Set the title
		long dateTime = cursor.getLong(cursor.getColumnIndex(COL_DATE));
		Date date = new Date(dateTime);
		Format formatter = new SimpleDateFormat("HH:mm:ss");
		String dateText = formatter.format(date);
		title.setText(dateText);
		
		// Set the subtitle
		String vaultType = cursor.getString(cursor.getColumnIndex(COL_VAULT_NAME));
		subtitle.setText(vaultType);
	}

	private void setDateCell(Cursor cursor, TextView title) {
		long dateTime = cursor.getLong(cursor.getColumnIndex(COL_DATE));
		Date date = new Date(dateTime);
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateText = formatter.format(date);
		title.setText(dateText);
	}
	
	private void setVaultTypeCell(Cursor cursor, TextView title) {
		// Set the title
		String vaultType = cursor.getString(cursor.getColumnIndex(COL_VAULT_NAME));
		title.setText(vaultType);
	}
	
	private void setLocationCell(Cursor cursor, TextView title) {
		// Set the title
		String location = cursor.getString(cursor.getColumnIndex(COL_LOCATION));
		title.setText(location);
	}
}
