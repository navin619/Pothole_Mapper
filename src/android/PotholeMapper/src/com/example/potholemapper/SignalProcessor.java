/* Author: Dhawal Srivastava
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import java.util.ArrayList;

public class SignalProcessor {
	AccelerometerFetch AccelerometerContext;
	int iLoop1, iLoop2, iLoop3;
	public ArrayList<Float> alZFilteredOutputValues = new ArrayList<Float>();
	public ArrayList<Float> alXFilteredOutputValues = new ArrayList<Float>();
	public ArrayList<Float> alZMatchedOutputValues = new ArrayList<Float>();
	public ArrayList<Float> alXMatchedOutputValues = new ArrayList<Float>();
	float fTemp;
	float fZFilterCoeff = (float) 0.3;
	float fXFilterCoeff = (float) 0.5;
	float fZPhoneDropThreshold = (float) 27.0;
	float fZPotholeThreshold;
	float[] fZMatchFilter = new float[] { (float) 2.7186, (float) 0.6265,
			(float) -0.2277, (float) 1.7269, (float) -2.9554, (float) 0.1778,
			(float) -0.7461 };
	float[] fXMatchFilter = new float[] { (float) -1.1180, (float) 1.0101,
			(float) -2.0300 };

	public SignalProcessor(AccelerometerFetch accelerometerFetch) {

		AccelerometerContext = accelerometerFetch;
	}

	/* Implementing High Pass Filter for Z direction sensor values and cleaning the predefined variable values*/
	public void firstProcessing() {
		alZFilteredOutputValues.clear();
		alXFilteredOutputValues.clear();
		alZMatchedOutputValues.clear();
		alXMatchedOutputValues.clear();
		fTemp = ((1 + fZFilterCoeff)
				* AccelerometerContext.alZValuesFile.get(0) / 2);
		alZFilteredOutputValues.add(fTemp);
		// Toast.makeText(AccelerometerContext, "firstProcessing",
		// Toast.LENGTH_SHORT).show();
		for (iLoop1 = 1; iLoop1 < AccelerometerContext.iIndex; iLoop1++) {
			fTemp = (fZFilterCoeff * alZFilteredOutputValues.get(iLoop1 - 1))
					+ ((1 + fZFilterCoeff)
							* AccelerometerContext.alZValuesFile.get(iLoop1) / 2)
					- ((1 + fZFilterCoeff)
							* AccelerometerContext.alZValuesFile
									.get(iLoop1 - 1) / 2);
			alZFilteredOutputValues.add(fTemp);
		}
		fTemp = 0;
	}

/* Implementing high pass filter for X direction sensor values*/
	public void secondProcessing() {
		fTemp = ((1 + fXFilterCoeff)
				* AccelerometerContext.alXValuesFile.get(0) / 2);
		alXFilteredOutputValues.add(fTemp);
		// Toast.makeText(AccelerometerContext, "firstProcessing",
		// Toast.LENGTH_SHORT).show();
		for (iLoop1 = 1; iLoop1 < AccelerometerContext.iIndex; iLoop1++) {
			fTemp = (fXFilterCoeff * alXFilteredOutputValues.get(iLoop1 - 1))
					+ ((1 + fXFilterCoeff)
							* AccelerometerContext.alXValuesFile.get(iLoop1) / 2)
					- ((1 + fXFilterCoeff)
							* AccelerometerContext.alXValuesFile
									.get(iLoop1 - 1) / 2);
			alXFilteredOutputValues.add(fTemp);
		}
		fTemp = 0;
	}

	/*Implementing match filter for Z direction sensor values*/
	public void thirdProcessing() {
		for (iLoop1 = 0; iLoop1 < fZMatchFilter.length; iLoop1++) {
			fTemp = 0;
			for (iLoop2 = 0; iLoop2 < fZMatchFilter.length; iLoop2++) {
				if (iLoop1 >= iLoop2) {

					fTemp += fZMatchFilter[iLoop2]
							* alZFilteredOutputValues.get(iLoop1 - iLoop2);
				}
			}
			alZMatchedOutputValues.add(fTemp);
		}
		for (iLoop1 = fZMatchFilter.length; iLoop1 < AccelerometerContext.iIndex; iLoop1++) {
			fTemp = 0;
			for (iLoop2 = 0; iLoop2 < fZMatchFilter.length; iLoop2++) {
				fTemp += fZMatchFilter[iLoop2]
						* alZFilteredOutputValues.get(iLoop1 - iLoop2);
			}
			alZMatchedOutputValues.add(fTemp);
		}
	}

	
	/*Implementing match filter for X direction sensor values*/
	public void fourthProcessing() {
		for (iLoop1 = 0; iLoop1 < fXMatchFilter.length; iLoop1++) {
			fTemp = 0;
			for (iLoop2 = 0; iLoop2 < fXMatchFilter.length; iLoop2++) {
				if (iLoop1 >= iLoop2) {

					fTemp += fXMatchFilter[iLoop2]
							* AccelerometerContext.alXValuesFile.get(iLoop1
									- iLoop2);
				}
			}
			alXMatchedOutputValues.add(fTemp);
		}
		for (iLoop1 = fXMatchFilter.length; iLoop1 < AccelerometerContext.iIndex; iLoop1++) {
			fTemp = 0;
			for (iLoop2 = 0; iLoop2 < fXMatchFilter.length; iLoop2++) {
				fTemp += fXMatchFilter[iLoop2]
						* AccelerometerContext.alXValuesFile.get(iLoop1
								- iLoop2);
			}
			alXMatchedOutputValues.add(fTemp);
		}
	}


	/*Implementing threshold calculation for Pothole detection*/
public void fifthProcessing() {
		String sTempSpeed = "Slow";
		String sTempIntensity = "Low";
		for (iLoop1 = 9; iLoop1 < AccelerometerContext.iIndex - 11; iLoop1++) {
			if (AccelerometerContext.alSpeedFile.get(iLoop1) > 3) {
				sTempSpeed = "Slow";
				fZPotholeThreshold = (float) 12.5;
				if (AccelerometerContext.alSpeedFile.get(iLoop1) > 13) {
					fZPotholeThreshold = 18;
					sTempSpeed = "Fast";
				}
				if (alZMatchedOutputValues.get(iLoop1) > fZPotholeThreshold
						&& alZMatchedOutputValues.get(iLoop1) < fZPhoneDropThreshold) {
					for (iLoop3 = iLoop1 - 9; iLoop3 < iLoop1 + 11; iLoop3++) {

						if (alXFilteredOutputValues.get(iLoop3) < -0.75) {
							for (iLoop2 = iLoop1 - 9; iLoop2 < iLoop1 + 11; iLoop2++) {
								if (alXMatchedOutputValues.get(iLoop2) > 5.9) {
									if (alZMatchedOutputValues.get(iLoop1) < fZPotholeThreshold + 2) {
										sTempIntensity = "Low";
									} else if (alZMatchedOutputValues
											.get(iLoop1) < fZPotholeThreshold + 5) {
										sTempIntensity = "Medium";

									} else {
										sTempIntensity = "High";

									}
									AccelerometerContext.alZPotholeEvent
											.add(iLoop1);
									AccelerometerContext.alPotholeSpeedIntensity
											.add(sTempSpeed);
									AccelerometerContext.alPotholeIntensity
											.add(sTempIntensity);
									break;
								}
							}

							break;
						}
					}
				}
			}

		}

	}

}
