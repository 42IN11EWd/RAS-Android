package nl.avans.ras.adapter;

import static nl.avans.ras.database.DatabaseNodes.*;
import nl.avans.ras.R;
import nl.avans.ras.model.enums.AdapterKind;
import android.content.Context;
import android.database.Cursor;
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
		TextView listTitle = null;
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
			listTitle.setTypeface(tfl);
			
			// Set the profile cell
			setVaultCell(cursor, listTitle);
			break;
		case DATES:
			listTitle = (TextView) view.findViewById(R.id.cell_title);
			listTitle.setTypeface(tfl);
			
			// Set the profile cell
			setDateCell(cursor, listTitle);
			break;
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
	}
	
	private void setVaultCell(Cursor cursor, TextView title) {
		title.setText(cursor.getString(cursor.getColumnIndex(COL_GYMNAST_ID)));
	}

	private void setDateCell(Cursor cursor, TextView title) {
		title.setText(cursor.getString(cursor.getColumnIndex(COL_DATE)));
	}
}
