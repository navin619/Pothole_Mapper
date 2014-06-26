/* Author: Dhawal Srivastava
 * Author: Navin Chaganti
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Parcelable;

public class MainActivity extends Activity {
	Button controlToggle;
	Button buttonOne;
	Button buttonTwo;
	Button buttonThree;
	Button buttonFour;
	Button buttonFive;
	Button buttonTest;
	Button buttonRun;
	Button buttonProcess;
	Button buttonDemo;
	public AccelerometerFetch As;
	public PopulatePopUp Pop;
	int iLoop1 = 0;
	public ArrayList<RoadEvent> RoadEventList = new ArrayList<RoadEvent>();
	public ArrayList<Float> DemoAccelerometerX = new ArrayList<Float>();
	public ArrayList<Float> DemoAccelerometerY = new ArrayList<Float>();
	public ArrayList<Float> DemoAccelerometerZ = new ArrayList<Float>();
	boolean ButtonOneFlag = false;
	int iTrackNo;

	/* Assigning view with instances on create */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iTrackNo = ReadInternalFile();
		As = new AccelerometerFetch(this, this);
		Pop = new PopulatePopUp(this, this);
		controlToggle = (Button) findViewById(R.id.controlToggle);
		buttonOne = (Button) findViewById(R.id.buttonone);
		buttonTwo = (Button) findViewById(R.id.buttontwo);
		buttonThree = (Button) findViewById(R.id.buttonthree);
		buttonFour = (Button) findViewById(R.id.buttonfour);
		buttonFive = (Button) findViewById(R.id.buttonfive);
		buttonTest = (Button) findViewById(R.id.test);
		buttonRun = (Button) findViewById(R.id.choose);
		buttonProcess = (Button) findViewById(R.id.process);
		buttonDemo = (Button) findViewById(R.id.buttondemo);
		buttonOne.setVisibility(View.INVISIBLE);
		buttonTwo.setVisibility(View.INVISIBLE);
		buttonThree.setVisibility(View.INVISIBLE);
		buttonFour.setVisibility(View.INVISIBLE);
		buttonFive.setVisibility(View.INVISIBLE);

		buttonProcess.setVisibility(View.INVISIBLE);
	}

	/* Uploading the internal file into array for testing scenario */
	public void TestUpload(View view) {
		buttonProcess.setText("Process");
		As.AppTest();
		if (As.bFlag) {
			As.bFlag = false;
			buttonOne.setVisibility(View.INVISIBLE);
			buttonTwo.setVisibility(View.INVISIBLE);
			buttonThree.setVisibility(View.INVISIBLE);
			buttonFour.setVisibility(View.INVISIBLE);
			buttonFive.setVisibility(View.INVISIBLE);
			buttonProcess.setVisibility(View.INVISIBLE);
			buttonOne.setVisibility(View.VISIBLE);
			buttonProcess.setVisibility(View.VISIBLE);
		}
	}

	/* Uploading the external file into array for running scenario */
	public void FileUpload(View view) {
		ButtonOneFlag = false;
		As.AppRun();
		buttonProcess.setText("Process");
	}

	/* Setting the visibility for buttons */
	public void ButtonVisibility() {
		buttonOne.setVisibility(View.INVISIBLE);
		buttonTwo.setVisibility(View.INVISIBLE);
		buttonThree.setVisibility(View.INVISIBLE);
		buttonFour.setVisibility(View.INVISIBLE);
		buttonFive.setVisibility(View.INVISIBLE);
		buttonProcess.setVisibility(View.INVISIBLE);
		if (ButtonOneFlag) {
			buttonOne.setVisibility(View.VISIBLE);

		}
		buttonProcess.setText("Process");
		buttonProcess.setVisibility(View.VISIBLE);

	}

	/* Calling all five process for algorithm together */
	public void Process(View view) {
		if (buttonProcess.getText().toString().equals("Process")) {
			buttonProcess.setText("Plot On Map");
			As.Process();
			RoadEventStore();
			buttonFive.setVisibility(View.INVISIBLE);
		} else {
			As.alZPotholeEvent.clear();
			buttonProcess.setText("Process");
			Intent showMapIntent = new Intent(MainActivity.this,
					MapActivity.class);
			showMapIntent.putParcelableArrayListExtra("list",(ArrayList<? extends Parcelable>)
					RoadEventList);
			startActivity(showMapIntent);

		}
	}

	@SuppressWarnings("finally")
	private int ReadInternalFile() {
		int iTempReadNumber = 0;
		String Internalfilename = "fInternalTrack.txt";
		/* Getting internal file with name "fInternalTrack.txt" */
		File file = getBaseContext().getFileStreamPath(Internalfilename);
		/* if file doesn't exist, then creating one */
		if (!file.exists()) {
			try {

				BufferedWriter bufferedWriter = new BufferedWriter(
						new FileWriter(new File(getFilesDir() + File.separator
								+ "fInternalTrack.txt")));
				bufferedWriter.write("0");
				bufferedWriter.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return 0;
			}
		} else {
			try {
				/*
				 * if file exist then returning the number of files generated so
				 * far
				 */
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(new File(getFilesDir() + File.separator
								+ "fInternalTrack.txt")));
				String read;
				StringBuilder builder = new StringBuilder("");

				while ((read = bufferedReader.readLine()) != null) {
					builder.append(read);
				}
				bufferedReader.close();

				iTempReadNumber = Integer.parseInt(builder.toString());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				return iTempReadNumber;
			}
		}
	}

	/* Method to update the files genrated by the application */
	void WriteToInternalFile(int iTempTrackNo) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					new File(getFilesDir() + File.separator
							+ "fInternalTrack.txt")));
			bufferedWriter.write(Integer.toString(iTempTrackNo));
			bufferedWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* About the application and developer */
	public void About(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"The App Pothole Mapper is developed by: \nDhawal Srivastava and Navin Chaganti\nUniversity Of Arizona\nGroup Name: Droiders")
				.setCancelable(false)
				/* Giving choices to user */

				.setPositiveButton("Got it!",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}

	/* Demo mode of the application */
	public void Demo(View view) {
		buttonProcess.setText("Process");
		Intent DemoIntent = new Intent(MainActivity.this, DemoActivity.class);
		startActivityForResult(DemoIntent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			ArrayList<String> stringsX = data
					.getStringArrayListExtra("AccelerometerX");

			for (iLoop1 = 0; iLoop1 < stringsX.size(); iLoop1++) {
				DemoAccelerometerX.add(Float.parseFloat(stringsX.get(iLoop1)));
			}
			ArrayList<String> stringsY = data
					.getStringArrayListExtra("AccelerometerY");

			for (iLoop1 = 0; iLoop1 < stringsY.size(); iLoop1++) {
				DemoAccelerometerY.add(Float.parseFloat(stringsY.get(iLoop1)));
			}
			ArrayList<String> stringsZ = data
					.getStringArrayListExtra("AccelerometerZ");

			for (iLoop1 = 0; iLoop1 < stringsZ.size(); iLoop1++) {
				DemoAccelerometerZ.add(Float.parseFloat(stringsZ.get(iLoop1)));
			}

		}
	}

	/* Main control switch to monitor data fetching */
	public void controlToggle(View view) {
		if (controlToggle.getText().toString().equals("Start")) {
			As.FetchStart();
			// Toast.makeText(this, "Button Pressed: " +
			// As.alZValues.get(iLoop).toString(), Toast.LENGTH_SHORT).show();
			// iLoop++;
			controlToggle.setText("Stop");
			buttonRun.setVisibility(View.INVISIBLE);
			buttonTest.setVisibility(View.INVISIBLE);
			buttonOne.setVisibility(View.INVISIBLE);
			buttonTwo.setVisibility(View.INVISIBLE);
			buttonThree.setVisibility(View.INVISIBLE);
			buttonFour.setVisibility(View.INVISIBLE);
			buttonFive.setVisibility(View.INVISIBLE);
			buttonProcess.setVisibility(View.INVISIBLE);
			buttonDemo.setVisibility(View.INVISIBLE);
		} else {
			buttonProcess.setText("Process");
			As.FetchStop();
			controlToggle.setText("Start");
			buttonTest.setVisibility(View.VISIBLE);
			buttonRun.setVisibility(View.VISIBLE);
			buttonDemo.setVisibility(View.VISIBLE);
			iTrackNo++;
			WriteToInternalFile(iTrackNo);
		}
	}

	/* Button for Algorithm -I */
	public void Buttonone(View view) {
		As.StepOne();
		buttonTwo.setVisibility(View.VISIBLE);
		buttonOne.setVisibility(View.INVISIBLE);
		buttonProcess.setVisibility(View.INVISIBLE);

	}

	/* Button for Algorithm -II */
	public void Buttontwo(View view) {
		As.StepTwo();
		buttonTwo.setVisibility(View.INVISIBLE);
		buttonThree.setVisibility(View.VISIBLE);

	}

	/* Button for Algorithm -III */
	public void Buttonthree(View view) {
		As.StepThree();

		buttonFour.setVisibility(View.VISIBLE);
		buttonThree.setVisibility(View.INVISIBLE);
	}

	/* Button for Algorithm -IV */
	public void Buttonfour(View view) {
		As.StepFour();
		buttonFive.setVisibility(View.VISIBLE);
		buttonFour.setVisibility(View.INVISIBLE);
	}

	/* Button for Algorithm -V */
	public void Buttonfive(View view) {
		As.StepFive();
		RoadEventStore();
		buttonFive.setVisibility(View.INVISIBLE);

	}

	/* Function for populating the gps speed and location of pothole */
	public void RoadEventStore() {
		RoadEventList.clear();
		if (As.alZPotholeEvent.size() == 0) {
			Toast.makeText(this,
					"No Pothole!",
					Toast.LENGTH_LONG).show();
			buttonProcess.setText("Process");
		} else {
			for (iLoop1 = 0; iLoop1 < As.alZPotholeEvent.size(); iLoop1++) {

				RoadEventList.add(new RoadEvent(Pop.PopUp(iLoop1), As.alLatFile
						.get(As.alZPotholeEvent.get(iLoop1)), As.alLongFile
						.get(As.alZPotholeEvent.get(iLoop1)), 0, As.alSpeedFile
						.get(As.alZPotholeEvent.get(iLoop1))));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
