/* Author: Navin Chaganti
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class Gps implements LocationListener {

	AccelerometerFetch AccelerometerContext;
	Location location;
	double latitude; // latitude
	double longitude; // longitude
	double speed;

	public Gps(AccelerometerFetch accelerometerFetchContext) {
		// TODO Auto-generated constructor stub
		AccelerometerContext = accelerometerFetchContext;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		speed = location.getSpeed();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
