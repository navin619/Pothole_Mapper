/* Author: Navin Chaganti
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {
	GoogleMap map;
	MapFragment mapFrag;

	List<RoadEvent> list = new ArrayList<RoadEvent>();
	double lat, lon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mapFrag = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map));

		// mapFrag.setRetainInstance(true);

		map = mapFrag.getMap();
		list = this.getIntent().getParcelableArrayListExtra("list");

		for (RoadEvent event : list) // use for-each loop
		{
			// String title = null;
			lat = event.getLatitude();
			lon = event.getlongitude();

			map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
					.title(event.getType()));

		}

		CameraUpdate center = CameraUpdateFactory
				.newLatLng(new LatLng(lat, lon));
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

		map.moveCamera(center);
		map.animateCamera(zoom);

	}

}
