/* Author: Dhawal Srivastava
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class PopulatePopUp {
	String root = Environment.getExternalStorageDirectory().getPath();
	String extention = "/PotholeMapper/";
	File myDir = new File(root + extention);
	int iLoop1;
	int iItem;
	String intensity = "High"; 
	MainActivity MainClassContext;
	Context MainContext;

	public PopulatePopUp(final Context context, MainActivity MainActivityContext) {
		MainContext = context;
		MainClassContext = MainActivityContext;
	}

	public String PopUp(final int iLoop1) {
		final ArrayList<String> alOptions = new ArrayList<String>();
		alOptions.add("Pothole");
		alOptions.add("Speed Bump");
		alOptions.add("Break");
		alOptions.add("Phone Drop");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainContext,
				android.R.layout.simple_list_item_1, alOptions);

		/*
		 * Generating alert dialog object for listing files available to be
		 * mailed
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(MainContext);
		builder.setTitle(intensity + " Intensity Pothole Detected!! Select the type");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {

				iItem = item;

				Toast.makeText(MainContext,
						"Event Type: " + alOptions.get(item),
						Toast.LENGTH_SHORT).show();

				RoadEvent Temp = new RoadEvent(alOptions.get(item),
						MainClassContext.RoadEventList.get(iLoop1)
								.getLatitude(), MainClassContext.RoadEventList
								.get(iLoop1).getlongitude(),
						MainClassContext.RoadEventList.get(iLoop1).getTime(),
						MainClassContext.RoadEventList.get(iLoop1).getSpeed());
				MainClassContext.RoadEventList.set(iLoop1, Temp);
			}

		});

		AlertDialog alert = builder.create();
		alert.show();
		intensity = "Medium";
		return "Pothole";

	}

}