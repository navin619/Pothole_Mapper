/* Author: Dhawal Srivastava
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class AccelerometerFetch implements SensorEventListener {
	/* Array List for Holding Values of Float and Double */
	ArrayList<Float> alXValuesAccelerometer = new ArrayList<Float>();
	ArrayList<Float> alYValuesAccelerometer = new ArrayList<Float>();
	ArrayList<Float> alZValuesAccelerometer = new ArrayList<Float>();
	ArrayList<Float> alXValuesFile = new ArrayList<Float>();
	ArrayList<Float> alYValuesFile = new ArrayList<Float>();
	ArrayList<Float> alZValuesFile = new ArrayList<Float>();
	public ArrayList<Float> alZProcessOne = new ArrayList<Float>();
	public ArrayList<Float> alXProcessTwo = new ArrayList<Float>();
	public ArrayList<Float> alZProcessThree = new ArrayList<Float>();
	public ArrayList<Float> alXProcessFour = new ArrayList<Float>();
	ArrayList<Double> alSpeedGps = new ArrayList<Double>();
	ArrayList<Double> alLatGps = new ArrayList<Double>();
	ArrayList<Double> alLongGps = new ArrayList<Double>();
	public ArrayList<Double> alSpeedFile = new ArrayList<Double>();
	public ArrayList<Double> alLatFile = new ArrayList<Double>();
	public ArrayList<Double> alLongFile = new ArrayList<Double>();
	public ArrayList<String> alPotholeIntensity = new ArrayList<String>();
	public ArrayList<String> alPotholeSpeedIntensity = new ArrayList<String>();
	public ArrayList<Integer> alZPotholeEvent = new ArrayList<Integer>();
	String FileNameFromPopUp;
	public SignalProcessor sp;
	Gps gps;
	String root = Environment.getExternalStorageDirectory().getPath();
	String extention = "/PotholeMapper/";
	boolean bFlag;
	File myDir = new File(root + extention);
	float x, y, z;
	int iIndex = 0, iLoop1;
	private int iStartFlag = 0;
	Context MainContext;
	MainActivity MainActivityContext;

	public AccelerometerFetch(Context context, MainActivity maincontext) {
		MainActivityContext = maincontext;
		MainContext = context;
		SensorManager sensorManager;
		sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		sp = new SignalProcessor(this);
		gps = new Gps(this);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/* Message Sent to Start the fetching data */
	public void FetchStart() {
		iStartFlag = 1;
		ResetForNewTask();
		Toast.makeText(MainContext, "App Started", Toast.LENGTH_SHORT).show();
	}

	public void FetchStop() {
		iStartFlag = 0;

		try {

			if (!myDir.exists()) {
				myDir.mkdirs();

			}
			/* Creating the file name based on the file track number */
			String fname = "Accelerometer_Data_" + MainActivityContext.iTrackNo
					+ ".txt";
			File file = new File(myDir, fname);
			Toast.makeText(MainContext, "File Just Generated: " + fname,
					Toast.LENGTH_SHORT).show();
			FileOutputStream fOutputFile = new FileOutputStream(file);
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(
					fOutputFile);
			iIndex = alXValuesAccelerometer.size();
			/*
			 * Writing the time stamp inside the file displaying the time the
			 * file is created
			 */
		
			for (iLoop1 = 0; iLoop1 < iIndex; iLoop1++) {

				outStreamWriter.write(alXValuesAccelerometer.get(iLoop1)
						.toString()
						+ "\t"
						+ alYValuesAccelerometer.get(iLoop1).toString()
						+ "\t"
						+ alZValuesAccelerometer.get(iLoop1).toString()
						+ "\t"
						+ alLatGps.get(iLoop1).toString()
						+ "\t"
						+ alLongGps.get(iLoop1).toString()
						+ "\t"
						+ alSpeedGps.get(iLoop1).toString() + "\n");
			}
			outStreamWriter.flush();
			outStreamWriter.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* Single method calling all five algorithm together */
	public void Process() {
		sp.firstProcessing();
		sp.secondProcessing();
		sp.thirdProcessing();
		sp.fourthProcessing();
		sp.fifthProcessing();
	}

	/* Method calling first algorithm */
	public void StepOne() {
		// Toast.makeText(MainContext, "Toast in AS",
		// Toast.LENGTH_SHORT).show();
		sp.firstProcessing();
	}

	/* Method calling second algorithm */
	public void StepTwo() {

		sp.secondProcessing();
	}

	/* Method calling third algorithm */
	public void StepThree() {
		sp.thirdProcessing();
	}

	/* Method calling fourth algorithm */
	public void StepFour() {
		sp.fourthProcessing();
	}

	/* Method calling fifth algorithm */
	public void StepFive() {
		sp.fifthProcessing();
	}

	/* Method called to get the list of all files present in the folder */
	public ArrayList<String> GetFiles(String DirectoryPath) {
		ArrayList<String> MyFiles = new ArrayList<String>();
		File fileDir = new File(DirectoryPath);
		File[] files = fileDir.listFiles();
		if (files.length == 0) {

			Toast.makeText(
					MainContext,
					"No data found. Go back and click on Start/Stop to generate new files",
					Toast.LENGTH_LONG).show();

			return MyFiles;
		} else {
			for (iLoop1 = 0; iLoop1 < files.length; iLoop1++) {
				/* Listing all .txt files */
				String[] separated = files[iLoop1].getName().split("\\.");
				if (separated[1].equals("txt")) {
					MyFiles.add(files[iLoop1].getName());
				}
			}
			return MyFiles;
		}

	}

	/* Method called to populate the list with the file name */
	public void FilePopulate() {
		if (myDir.exists()) {
			final ArrayList<String> FilesInFolder = GetFiles(myDir
					.getAbsolutePath());
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					MainContext, android.R.layout.simple_list_item_1,
					FilesInFolder);
			/*
			 * Generating alert dialog object for listing files available to be
			 * mailed
			 */
			AlertDialog.Builder builder = new AlertDialog.Builder(MainContext);
			builder.setTitle("List of files to be used for processing.");
			builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {

					FileNameFromPopUp = FilesInFolder.get(item);
					SelectFileApp();
					// MainContext.
				}
			});

			AlertDialog alert = builder.create();
			alert.show();
		} else {
			/* Generating alert dialog object for no folder use case */
			AlertDialog.Builder alert = new AlertDialog.Builder(MainContext);
			alert.setTitle("Folder Doesn't Exist!");
			alert.setMessage("Either you have just installed the app or someone has deleted the folder and previous data. Click on Start/Stop to generate new data.");
			alert.setPositiveButton("OK", null);
			alert.show();
		}
	}

	/* Populating the arrays with user defined file */
	public void AppRun() {
		FilePopulate();

	}

	/* Populating the arrays with internal file for testing */
	public void AppTest() {
		SelectFileTest();

	}

	/* Selecting the file */
	public void SelectFileApp() {
		ResetForNewTask();

		String TextFileName = FileNameFromPopUp;
		String filename = myDir.getAbsolutePath() + "/" + TextFileName;
		try {

			BufferedReader bufferedReaderApp = new BufferedReader(
					new FileReader(new File(filename)));
			// Toast.makeText(MainContext, "Index Value from Choose: " + iIndex
			// , Toast.LENGTH_SHORT).show();
			String read;

			while ((read = bufferedReaderApp.readLine()) != null) {
				/* Dis-regarding the lines which starts with '%' */
				if (read.charAt(0) != '%') {
					/* Splitting the read line by single tab */
					String[] separated = read.split("\t");
					alXValuesFile.add(Float.parseFloat(separated[1]));
					alYValuesFile.add(Float.parseFloat(separated[0]));
					alZValuesFile.add(Float.parseFloat(separated[2]));
					alLatFile.add(Double.parseDouble(separated[3]));
					alLongFile.add(Double.parseDouble(separated[4]));
					alSpeedFile.add(Double.parseDouble(separated[5]));
				}
				// Toast.makeText(MainContext, "File Fetched",
				// Toast.LENGTH_SHORT).show();
				MainActivityContext.ButtonVisibility();

			}
			bufferedReaderApp.close();
			iIndex = alXValuesFile.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	/* Selecting test files */
	public void SelectFileTest() {
		ResetForNewTask();

		InputStream inputStream = MainContext.getResources().openRawResource(
				R.raw.accelerometerraw);
		InputStream inputStream1 = MainContext.getResources().openRawResource(
				R.raw.accelerometerprocessfull);
		try {
			InputStreamReader inputreader = new InputStreamReader(inputStream);
			InputStreamReader inputreader1 = new InputStreamReader(inputStream1);
			BufferedReader bufferedReader = new BufferedReader(inputreader);
			BufferedReader bufferedReader1 = new BufferedReader(inputreader1);
			String line;
			String line1;

			while ((line = bufferedReader.readLine()) != null) {
				/* Dis-regarding the lines which starts with '%' */
				if (line.charAt(0) != '%') {
					/* Splitting the read line by single tab */
					String[] separated = line.split("\t");
					alXValuesFile.add(Float.parseFloat(separated[1]));
					alYValuesFile.add(Float.parseFloat(separated[0]));
					alZValuesFile.add(Float.parseFloat(separated[2]));
					alLatFile.add(Double.parseDouble(separated[3]));
					alLongFile.add(Double.parseDouble(separated[4]));
					alSpeedFile.add(Double.parseDouble(separated[5]));
				}
			}
			bufferedReader.close();
			iIndex = alXValuesFile.size();
			while ((line1 = bufferedReader1.readLine()) != null) {
				/* Disregarding the lines which starts with '%' */
				if (line1.charAt(0) != '%') {
					/* Splitting the read line by single tab */
					String[] separated = line1.split("\t");
					alZProcessOne.add(Float.parseFloat(separated[0]));
					alXProcessTwo.add(Float.parseFloat(separated[1]));
					alZProcessThree.add(Float.parseFloat(separated[2]));
					alXProcessFour.add(Float.parseFloat(separated[3]));
				}
			}
			bufferedReader.close();
			iIndex = alXValuesFile.size();
			bFlag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Toast.makeText(MainContext, "File Fetched",
			// Toast.LENGTH_SHORT).show();

		}

	}

	/*Cleaning variables for new tasks*/
	public void ResetForNewTask() {
		iIndex = 0;
		alXValuesFile.clear();
		alYValuesFile.clear();
		alZValuesFile.clear();
		alLatFile.clear();
		alLongFile.clear();
		alSpeedFile.clear();
		alZProcessOne.clear();
		alXProcessTwo.clear();
		alZProcessThree.clear();
		alXProcessFour.clear();
		alXValuesAccelerometer.clear();
		alYValuesAccelerometer.clear();
		alZValuesAccelerometer.clear();
		alSpeedGps.clear();
		alLatGps.clear();
		alLongGps.clear();
		alPotholeIntensity.clear();
		alPotholeSpeedIntensity.clear();
		alZPotholeEvent.clear();
	}

	/*Recording the sensor data into Arrays*/
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

		if ((event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
				&& (iStartFlag == 1)) {

			/* Taking data for X Y and Z axis */
			x = event.values[0];
			y = event.values[1];
			z = event.values[2];
			alXValuesAccelerometer.add(x);
			alYValuesAccelerometer.add(y);
			alZValuesAccelerometer.add(z);
			alSpeedGps.add(gps.speed);
			alLatGps.add(gps.latitude);
			alLongGps.add(gps.longitude);
			iIndex++;
		}

	}

}
