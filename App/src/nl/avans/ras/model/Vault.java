package nl.avans.ras.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Vault implements Parcelable {
	
	// Constants
	public static final String VAULT_ID = "vault_id";
	
	// Fields
	private int id, gymnastId;
	private String name, type, location;
	private double dScore, eScore, duration;
	private Date date;
	
	// Constructor
	public Vault(int id, int gymnastId, String name, double dScore, double eScore, Date date) {
		this.id = id;
		this.gymnastId = gymnastId;
		this.name = name;
		this.dScore = dScore;
		this.eScore = eScore;
		this.date = date;
		
		// Test data
		this.duration = 13.94;
		this.type = "Salto";
		this.location = "Flik Flak";
	}
	
	// The following methods that are required for using Parcelable
    private Vault(Parcel in) {
    	id = in.readInt();
    	gymnastId = in.readInt();
    	name = in.readString();
    	dScore = in.readDouble();
    	eScore = in.readDouble();
    	date = new Date(in.readLong());
    }
	
	// Getters
	public int 		getId() 		{ return id; }
	public int 		getGymnastId() 	{ return gymnastId; }
	public double 	getEScore()		{ return eScore; }
	public double 	getDScore() 	{ return dScore; }
	public double	getDuration()	{ return duration; }
	public String 	getName() 		{ return name; }
	public String 	getType() 		{ return type; }
	public String 	getLocation()	{ return location; }
	public Date		getDate()		{ return date; }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeInt(gymnastId);
		out.writeString(name);
		out.writeDouble(dScore);
		out.writeDouble(eScore);
		out.writeLong(date.getTime());
	}

    public static final Parcelable.Creator<Vault> CREATOR = new Parcelable.Creator<Vault>() {
        public Vault createFromParcel(Parcel in) {
            return new Vault(in);
        }

        public Vault[] newArray(int size) {
            return new Vault[size];
        }
    };

	public double[] getSpeedGraphData() {
		double[] graphData = {0, 1, 3, 7, 10, 13, 16, 19, 21, 23, 25, 27, 30, 30.5, 31, 31, 31, 29, 27, 25, 20};
		return graphData;		
	}
	
	public double[] getDistanceGraphData() {
		double[] graphData = {0, 1, 2, 3, 4, 5.5, 7, 9, 11, 12.5, 16, 20, 24, 28, 32, 36, 40, 44, 47, 49, 51};
		return graphData;		
	}
}
