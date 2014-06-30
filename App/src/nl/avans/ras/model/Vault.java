package nl.avans.ras.model;

import java.util.ArrayList;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Vault implements Parcelable {
	
	// Constants
	public static final String VAULT_ID = "vault_id";
	
	// Fields
	private int id, gymnastId;
	private String name, location, time, kind;
	private double dScore, eScore, duration, penalty;
	private String data;
	private ArrayList<Double> speedData, distanceData;
	private Date date;
	
	// Constructor
	public Vault(int id, int gymnastId, String name, double dScore, double eScore, double penalty, String location, String kind, Date date, String time, String data) {
		this.id = id;
		this.gymnastId = gymnastId;
		this.name = name;
		this.dScore = dScore;
		this.eScore = eScore;
		this.penalty = penalty;
		this.location = location;
		this.kind = kind;
		this.date = date;
		this.data = data;
		this.time = time;
		
		// Convert the data to speedData and distanceData
		convertDataToSpeedAndDistanceData();
		
		// Test data
		this.duration = 13.94;
	}
	
	// The following methods that are required for using Parcelable
    private Vault(Parcel in) {
    	id = in.readInt();
    	gymnastId = in.readInt();
    	name = in.readString();
    	dScore = in.readDouble();
    	eScore = in.readDouble();
    	date = new Date(in.readLong());
    	data = in.readString();
    	
    	convertDataToSpeedAndDistanceData();
    }
	
	// Getters
	public int 		getId() 		{ return id; }
	public int 		getGymnastId() 	{ return gymnastId; }
	public double 	getEScore()		{ return eScore > 0 ? eScore : 0; }
	public double 	getDScore() 	{ return dScore > 0 ? dScore : 0; }
	public double	getDuration()	{ return duration; }
	public double 	getPenalty()	{ return penalty < 0 ? 0 : penalty; }
	public String 	getName() 		{ return name; }
	public String 	getLocation()	{ return location; }
	public String 	getData()		{ return data; }
	public String	getTime()		{ return time; }
	public String	getKind()		{ return kind; }
	public Date		getDate()		{ return date; }
	
	public ArrayList<Double> getSpeedData() 	{ return speedData; }
	public ArrayList<Double> getDistanceData() { return distanceData; }
	
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
		out.writeString(data);
	}
	
	private void convertDataToSpeedAndDistanceData() {
		speedData = new ArrayList<Double>();
		distanceData = new ArrayList<Double>();
		
		String[] dataCollection = data.split(" ");
		for(String data: dataCollection) {
			String[] speedAndDistance = data.split(",");
			if (speedAndDistance.length > 1) {
				// Convert the string to a double
				Double distance = Double.valueOf(speedAndDistance[0]);
				Double speed = Double.valueOf(speedAndDistance[1]);
				
				// Add the data to the collection
				distanceData.add(distance);
				speedData.add(speed);
			} else {
				Double speed = Double.valueOf(speedAndDistance[0]);
				speedData.add(speed);
			}
		}
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
		double[] graphData = new double[speedData != null ? speedData.size() : 0];
		for (int i = 0; i < graphData.length; i++) {
			graphData[i] = speedData.get(i);
		}
		return graphData;
	}
	
	public double[] getDistanceGraphData() {
		double[] graphData = new double[distanceData != null ? distanceData.size() : 0];
		for (int i = 0; i < graphData.length; i++) {
			graphData[i] = distanceData.get(i);
		}
		return graphData;	
	}
}
