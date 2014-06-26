package com.example.datagatherer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	/* Declaring Variables */
	Button ControlButton;
	TextView RecordTopic;
	TextView xValue;
	TextView yValue;
	TextView zValue;
	TextView speedValue;
	SensorManager sensorManager;
	int iIndex, iLoop1, iTrackNo;
	float x, y, z, fSpeed, fSpeedChange, fInitialTime, fDeltaTime,
			fInitialAcceleration;
	Toast toast;
	ArrayList<Character> alPotHole = new ArrayList<Character>();
	ArrayList<Character> alspeed = new ArrayList<Character>();
	ArrayList<Character> alSmoothRoad = new ArrayList<Character>();
	ArrayList<Character> alSpeedBump = new ArrayList<Character>();
	ArrayList<Character> alCurbHit = new ArrayList<Character>();
	ArrayList<Character> alPhoneDrop = new ArrayList<Character>();
	ArrayList<Float> alXValues = new ArrayList<Float>();
	ArrayList<Float> alYValues = new ArrayList<Float>();
	ArrayList<Float> alZValues = new ArrayList<Float>();
	ArrayList<String> alXWrapper = new ArrayList<String>();
	ArrayList<String> alYWrapper = new ArrayList<String>();
	ArrayList<String> alZWrapper = new ArrayList<String>();
	String MatlabFileName;
	/* Declaring the location on external storage for saving the obtained data */
	String root = Environment.getExternalStorageDirectory().getPath();
	String extention = "/Accelerometer_Data/";
	File myDir = new File(root + extention);
	char speed = 'l';
	boolean bButtonFlag = true;
	boolean bPotHoleFlag = false;
	boolean bSmoothRoadFlag = false;
	boolean bSpeedBumpFlag = false;
	boolean bCurbHitFlag = false;
	boolean bPhoneDropFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* Linking view to the objects created */
		ControlButton = (Button) findViewById(R.id.controlbutton);
		RecordTopic = (TextView) findViewById(R.id.RecordTopic);
		xValue = (TextView) findViewById(R.id.xvalue);
		yValue = (TextView) findViewById(R.id.yvalue);
		zValue = (TextView) findViewById(R.id.zvalue);
		speedValue = (TextView) findViewById(R.id.speedvalue);
		/*
		 * Creating object of Sensor Manager and registering it with
		 * accelerometer type at Game delay mode
		 */
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);

		/* Calling function to track the files created so far by the application */
		iTrackNo = ReadInternalFile();
		fSpeed = 0;
		fDeltaTime = 0;
		fInitialAcceleration = 0;
		fInitialTime = System.nanoTime();
		Toast.makeText(MainActivity.this,
				"Check: " + System.nanoTime() * (float) Math.pow(10, -9),
				Toast.LENGTH_SHORT).show();

	}

	/* Toggling visibility and text on button with corresponding press of button */
	/* Method called when Start/Stop button is pressed */

	public void buttonClick(View view) {
		if (bButtonFlag == true) {
			RecordTopic.setVisibility(View.VISIBLE);
			RecordTopic.setText("Showing Values Fetched From Accelerometer");
			xValue.setVisibility(View.VISIBLE);
			yValue.setVisibility(View.VISIBLE);
			zValue.setVisibility(View.VISIBLE);
			bButtonFlag = false;
			ControlButton.setText("Stop");

		} else {
			xValue.setVisibility(View.INVISIBLE);
			yValue.setVisibility(View.INVISIBLE);
			zValue.setVisibility(View.INVISIBLE);

			bButtonFlag = true;
			ControlButton.setText("Start");
			WriteToFile();
			zValue.setText("Done");
		}
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?

		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.radioButton1:
			if (checked) {
				speed = 'l'; // low speed are the best
				Toast.makeText(MainActivity.this, "Low Speed Detected",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.radioButton2:
			if (checked) {
				speed = 'h'; // high speed
				Toast.makeText(MainActivity.this, "High Speed Detected",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	/* Method called when Email button is pressed */
	public void PopulateList(View view) {

		/*
		 * Calling method to populate the dialog with files present in the
		 * folder
		 */
		PopulateListMethod();

	}

	/* Method called when Email button is pressed */
	public void potholeDetected(View view) {

		/*
		 * Calling method to populate the dialog with files present in the
		 * folder
		 */
		bPotHoleFlag = true;
		/*
		 * Toast.makeText( MainActivity.this, "PotHole Detected",
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void SpeedBumpDetected(View view) {

		/*
		 * Calling method to populate the dialog with files present in the
		 * folder
		 */
		bSpeedBumpFlag = true;
		/*
		 * Toast.makeText( MainActivity.this, "PotHole Detected",
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void CurbHitDetected(View view) {

		/*
		 * Calling method to populate the dialog with files present in the
		 * folder
		 */
		bCurbHitFlag = true;
		/*
		 * Toast.makeText( MainActivity.this, "PotHole Detected",
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void SmoothRoadDetected(View view) {

		/*
		 * Calling method to populate the dialog with files present in the
		 * folder
		 */
		bSmoothRoadFlag = true;
		/*
		 * Toast.makeText( MainActivity.this, "PotHole Detected",
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void PhoneDropDetected(View view) {

		/*
		 * Calling method to populate the dialog with files present in the
		 * folder
		 */
		bPhoneDropFlag = true;
		/*
		 * Toast.makeText( MainActivity.this, "PotHole Detected",
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	/* Method to return the number of files created by the application */
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
	private void WriteToInternalFile(int iTempTrackNo) {
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

	/*
	 * Method to write the generated accelerometer data into a file stored at
	 * external storage
	 */
	public void WriteToFile() {

		try {

			if (!myDir.exists()) {
				myDir.mkdirs();

			}
			/* Creating the file name based on the file track number */
			String fname = "Accelerometer_Data_" + iTrackNo + ".txt";
			iTrackNo++;
			WriteToInternalFile(iTrackNo);
			File file = new File(myDir, fname);
			FileOutputStream fOutputFile = new FileOutputStream(file);
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(
					fOutputFile);
			iIndex = alXValues.size();
			/*
			 * Writing the time stamp inside the file displaying the time the
			 * file is created
			 */
			String format = "MM/dd/yyyy @ HH:mm:ss";
			SimpleDateFormat dateFormat = new SimpleDateFormat(format,
					Locale.US);

			outStreamWriter.write("% Generated on "
					+ dateFormat.format(new Date()) + "\n");
			/* Writing the Name of the Student */
			outStreamWriter
					.write("% By Dhawal Srivastava - PotHole - Smooth Road - CurbHit - PhoneDrop - SpeedBump - Speed\n");

			for (iLoop1 = 0; iLoop1 < iIndex; iLoop1++) {

				outStreamWriter.write(alXValues.get(iLoop1).toString() + "\t"
						+ alYValues.get(iLoop1).toString() + "\t"
						+ alZValues.get(iLoop1).toString() + "\t"
						+ alPotHole.get(iLoop1).toString() + "\t"
						+ alSmoothRoad.get(iLoop1).toString() + "\t"
						+ alCurbHit.get(iLoop1).toString() + "\t"
						+ alPhoneDrop.get(iLoop1).toString() + "\t"
						+ alSpeedBump.get(iLoop1).toString() + "\t"
						+ alspeed.get(iLoop1).toString() + "\n");
			}
			outStreamWriter.flush();
			outStreamWriter.close();
			RecordTopic.setText("Data Saved Under File Name: " + fname);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Method to read the data from stored file and save into arrays */
	public void WrappingToMatlab(String TextFileName) {

		String filename = myDir.getAbsolutePath() + "/" + TextFileName;
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					new File(filename)));
			String read;

			while ((read = bufferedReader.readLine()) != null) {
				/* Dis-regarding the lines which starts with '%' */
				if (read.charAt(0) != '%') {
					/* Splitting the read line by single tab */
					String[] separated = read.split("\t");
					alXWrapper.add(separated[0]);
					alYWrapper.add(separated[1]);
					alZWrapper.add(separated[2]);
				}
			}
			bufferedReader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Method for generating a matlab script file with the arrays generated
	 * containg the data from the file
	 */
	@SuppressWarnings("finally")
	public String CreatingMFile(String TextFileName) {
		/* Generating the .m file name from text file name */
		String[] separated = TextFileName.split("\\.");

		String[] FileNumber = separated[0].split("_");
		String MatlabFileName = "Accelerometer_Matlab_" + FileNumber[2] + ".m";

		try {

			if (!myDir.exists()) {
				myDir.mkdirs();

			}
			File file = new File(myDir, MatlabFileName);
			FileOutputStream fOut = new FileOutputStream(file);
			/*
			 * Generating the .m file with X axis array value populated with
			 * X-axis value from accelerometer
			 */
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(fOut);
			iIndex = alXWrapper.size();
			outStreamWriter.write("X = [");
			for (iLoop1 = 0; iLoop1 < iIndex - 1; iLoop1++) {
				outStreamWriter.write(alXWrapper.get(iLoop1) + ",");
			}
			outStreamWriter.write(alXWrapper.get(iLoop1) + "];\n");

			/*
			 * Generating the .m file with X axis array value populated with
			 * X-axis value from accelerometer
			 */
			outStreamWriter.write("Y = [");
			for (iLoop1 = 0; iLoop1 < iIndex - 1; iLoop1++) {
				outStreamWriter.write(alYWrapper.get(iLoop1) + ",");
			}
			outStreamWriter.write(alYWrapper.get(iLoop1) + "];\n");

			/*
			 * Generating the .m file with X axis array value populated with
			 * X-axis value from accelerometer
			 */
			outStreamWriter.write("Z = [");
			for (iLoop1 = 0; iLoop1 < iIndex - 1; iLoop1++) {
				outStreamWriter.write(alZWrapper.get(iLoop1) + ",");
			}
			outStreamWriter.write(alZWrapper.get(iLoop1) + "];\n");

			/* Generating the .m file with plot3 and scatter3 method called */
			outStreamWriter
					.write("h1 = figure;\nplot3(X,Y,Z);\nh2 = figure;\nscatter3(X,Y,Z);\n");

			outStreamWriter.flush();
			outStreamWriter.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return MatlabFileName;
		}

	}

	/* Method called every time Accelerometer sensor register a change */
	@Override
	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			if ((event.values[1] > 0 && event.values[1] > 0.5)
					|| (event.values[1] < 0 && event.values[1] < -0.5)) {
				fDeltaTime = System.nanoTime() - fInitialTime;
				/*
				 * Toast.makeText( MainActivity.this, "Check Time: " +
				 * fDeltaTime* (float)Math.pow(10, -9),
				 * Toast.LENGTH_SHORT).show();
				 */
				fInitialTime = System.nanoTime();
				fSpeedChange = ((event.values[1]) * (fDeltaTime * (float) Math
						.pow(10, -9)));
				/*
				 * Toast.makeText( MainActivity.this, "Check Acceleration: " +
				 * event.values[0] , Toast.LENGTH_SHORT).show();
				 */
				fInitialAcceleration = event.values[0];
				fSpeed = fSpeed + fSpeedChange;
				speedValue.setText("Speed: " + fSpeed);
			}
			if (!bButtonFlag) {

				/* Taking data for X Y and Z axis */
				x = event.values[0];
				y = event.values[1];
				z = event.values[2];
				xValue.setText("X: " + x);
				yValue.setText("Y: " + y);
				zValue.setText("Z: " + z);
				alXValues.add(x);
				alYValues.add(y);
				alZValues.add(z);
				alspeed.add(speed);
				if (bPotHoleFlag) {
					Toast.makeText(MainActivity.this, "PotHole Detected",
							Toast.LENGTH_SHORT).show();
					alPotHole.add('Y');
					bPotHoleFlag = false;
				} else {
					alPotHole.add('N');
				}
				if (bPhoneDropFlag) {
					Toast.makeText(MainActivity.this, "PhoneDrop Detected",
							Toast.LENGTH_SHORT).show();
					alPhoneDrop.add('Y');
					bPhoneDropFlag = false;
				} else {
					alPhoneDrop.add('N');
				}
				if (bSpeedBumpFlag) {
					Toast.makeText(MainActivity.this, "Speed Bump Detected",
							Toast.LENGTH_SHORT).show();
					alSpeedBump.add('Y');
					bSpeedBumpFlag = false;
				} else {
					alSpeedBump.add('N');
				}
				if (bCurbHitFlag) {
					Toast.makeText(MainActivity.this, "CurbHit Detected",
							Toast.LENGTH_SHORT).show();
					alCurbHit.add('Y');
					bCurbHitFlag = false;
				} else {
					alCurbHit.add('N');
				}
				if (bSmoothRoadFlag) {
					Toast.makeText(MainActivity.this, "Smooth Road Detected",
							Toast.LENGTH_SHORT).show();
					alSmoothRoad.add('Y');
					bSmoothRoadFlag = false;
				} else {
					alSmoothRoad.add('N');
				}

			}
		}
	}

	/* Method called to get the list of all files present in the folder */
	public ArrayList<String> GetFiles(String DirectoryPath) {
		ArrayList<String> MyFiles = new ArrayList<String>();
		File fileDir = new File(DirectoryPath);
		File[] files = fileDir.listFiles();
		if (files.length == 0) {

			Toast.makeText(
					MainActivity.this,
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

	/* Method for populating list on an alertdialog */
	public void PopulateListMethod() {
		if (myDir.exists()) {
			final ArrayList<String> FilesInFolder = GetFiles(myDir
					.getAbsolutePath());
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, FilesInFolder);
			/*
			 * Generating alert dialog object for listing files available to be
			 * mailed
			 */
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("List of files to be e-mailed.");
			builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int item) {

					/*
					 * Calling method based on the selection made from the
					 * dialog list
					 */
					UserOption(FilesInFolder.get(item));

				}
			});

			AlertDialog alert = builder.create();
			alert.show();
		} else {
			/* Generating alert dialog object for no folder use case */
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Folder Doesn't Exist!");
			alert.setMessage("Either you have just installed the app or someone has deleted the folder and previous data. Click on Start/Stop to generate new data.");
			alert.setPositiveButton("OK", null);
			alert.show();
		}
	}

	/* Method called to Email the file (Either Text or Matlab) */
	public void Email(String fname) {
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(Intent.EXTRA_STREAM,
		/* Attachment to be sent with the mail */
		Uri.parse("file://" + myDir.getAbsolutePath() + "/" + fname));
		/* Subject of the mail */
		sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending File:" + fname);
		/* Body of the mail */
		sharingIntent.putExtra(Intent.EXTRA_TEXT,
				"Please find attached the file: " + fname);
		startActivity(Intent.createChooser(sharingIntent, "Send email"));

	}

	/*
	 * Method called to give choice to user of sending either as Text file or
	 * wrapping into a Matlab script file
	 */
	public void UserOption(final String fname) {
		/* Generating alert dialog object */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"Does user wants to send " + fname
						+ " file as a text document or as a .m (matlab) file?")
				.setCancelable(true)
				/* Giving choices to user */

				.setPositiveButton("Text File",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {

								Email(fname);
							}
						})
				.setNegativeButton("Matlab File",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {

								WrappingToMatlab(fname);
								MatlabFileName = CreatingMFile(fname);
								Email(MatlabFileName);

							}
						});
		AlertDialog alert = builder.create();
		alert.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
