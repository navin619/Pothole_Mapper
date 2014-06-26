/* Author: Navin Chaganti
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import android.os.Parcel;
import android.os.Parcelable;

public class RoadEvent implements Parcelable {

	private String type;
	private double latitude, longitude;
	private double time;
	private double speed;

	public RoadEvent(String type, double latitude, double longitude,
			double time, double speed) {
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
		this.speed = speed;
	}

	public RoadEvent(Parcel source) {

		type = source.readString();
		latitude = source.readDouble();
		longitude = source.readDouble();
		time = source.readDouble();
		speed = source.readDouble();

	}

	String getType() {
		return type;
	}

	double getLatitude() {
		return latitude;
	}

	double getlongitude() {
		return longitude;
	}

	double getTime() {
		return time;
	}

	double getSpeed() {
		return speed;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(type);
		dest.writeDouble(latitude);
		dest.writeDouble(longitude);
		dest.writeDouble(time);
		dest.writeDouble(speed);
	}

	public static final Parcelable.Creator<RoadEvent> CREATOR = new Parcelable.Creator<RoadEvent>() {
		@Override
		public RoadEvent createFromParcel(Parcel in) {
			return new RoadEvent(in);
		}

		@Override
		public RoadEvent[] newArray(int size) {
			return new RoadEvent[size];
		}
	};

}
