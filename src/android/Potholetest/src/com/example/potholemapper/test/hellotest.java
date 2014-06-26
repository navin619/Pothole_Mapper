/* Author: Navin Chaganti
 * Author: Dhawal Srivastava
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */
package com.example.potholemapper.test;

import com.example.potholemapper.DemoActivity;
import com.example.potholemapper.MainActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

public class hellotest extends ActivityInstrumentationTestCase2<MainActivity> {

	// private static final boolean True = false;
	MainActivity mActivity;
	Button check, checkbutton, algo1, algo2, algo3, algo4, algo5, algowhole;
	Button speedincr, speeddcr, gps, smooth, pothole, speedbump, buttonbreak,
			phonedrop, finish;
	String resourceString;

	float a, b;
	boolean flag = false;

	@SuppressWarnings("deprecation")
	public hellotest() {
		super("com.example.potholemapper", MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		check = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.test);
		algo1 = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.buttonone);
		algo2 = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.buttontwo);
		algo3 = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.buttonthree);
		algo4 = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.buttonfour);
		algo5 = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.buttonfive);
		algowhole = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.process);
		checkbutton = (Button) mActivity
				.findViewById(com.example.potholemapper.R.id.buttondemo);

	}

	public void testPreconditions() {

		assertNotNull(mActivity);
	}

	public void test_CheckingAlgorithm_One() {

		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TouchUtils.tapView(this, algo1);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZProcessOne.size(); i++) {
			a = mActivity.As.alZProcessOne.get(i);
			b = mActivity.As.sp.alZFilteredOutputValues.get(i);
			if (a > b) {

				if ((a - b) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}

			} else {
				if ((b - a) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		assertEquals(flag, true);

	}

	public void test_CheckingAlgorithm_Two() {

		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TouchUtils.tapView(this, algo1);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo2);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alXProcessTwo.size(); i++) {
			a = mActivity.As.alXProcessTwo.get(i);
			b = mActivity.As.sp.alXFilteredOutputValues.get(i);
			if (a > b) {

				if ((a - b) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}

			} else {
				if ((b - a) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		assertEquals(flag, true);

	}

	public void test_CheckingAlgorithm_Three() {

		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo1);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo2);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TouchUtils.tapView(this, algo3);

		try {
			synchronized (this) {
				wait(5000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = 0;
		for (i = 0; i < mActivity.As.alZProcessThree.size(); i++) {
			a = mActivity.As.alZProcessThree.get(i);
			b = mActivity.As.sp.alZMatchedOutputValues.get(i);
			if (a > b) {

				if ((a - b) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}

			} else {
				if ((b - a) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		assertEquals(flag, true);

	}

	public void test_CheckingAlgorithm_Four() {

		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo1);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo2);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo3);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo4);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alXProcessFour.size(); i++) {
			a = mActivity.As.alXProcessFour.get(i);
			b = mActivity.As.sp.alXMatchedOutputValues.get(i);
			if (a > b) {

				if ((a - b) <= 0.001) {
					flag = true;
				}

				else {
					flag = false;
					break;
				}
			} else {
				if ((b - a) <= 0.001) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		assertEquals(flag, true);

	}

	public void test_CheckingAlgorithm_Five() {
		int[] bpot = new int[] { 75, 225, 684, 749 };
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo1);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo2);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo3);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo4);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algo5);

		try {
			synchronized (this) {
				wait(4000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {

			if (mActivity.As.alZPotholeEvent.get(i) == bpot[i]) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_Checkingalgorithm_Whole() {
		int cpot = 4;
		int[] bpot = new int[] { 75, 225, 684, 749 };
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {

			if (mActivity.As.alZPotholeEvent.get(i) == bpot[i]) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		if (mActivity.As.alZPotholeEvent.size() != cpot) {
			flag = false;
		}
		assertEquals(flag, true);

	}

	public void test_Speed() {
		Double cpot;
		double[] speedpot = new double[] { 35, 35, 35, 10 };
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {
			cpot = mActivity.As.alSpeedFile.get(mActivity.As.alZPotholeEvent
					.get(i));
			if (cpot == speedpot[i]) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_CheckingPhoneDrop() {
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {

			if (mActivity.As.alZPotholeEvent.get(i) < 460
					|| mActivity.As.alZPotholeEvent.get(i) > 512) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_CheckingBreakEvent() {
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {

			if (mActivity.As.alZPotholeEvent.get(i) < 241
					|| mActivity.As.alZPotholeEvent.get(i) > 340) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_Pot_Intensity_Level() {
		String cpot;
		String[] speedpot = new String[] { "Medium", "Medium", "Medium", "Low" };
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < 3; i++) {
			cpot = mActivity.As.alPotholeIntensity.get(i);
			if (cpot == speedpot[i]) {
				flag = true;
			}

			else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_Speed_Level() {
		String cpot;
		String[] speedpot = new String[] { "Fast", "Fast", "Fast", "Slow" };
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {
			cpot = mActivity.As.alPotholeSpeedIntensity.get(i);
			if (cpot == speedpot[i]) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_Checkinggpsfetch() {
		double apot, cpot;
		double[] latpot = new double[] { 42.24, 42.24, 42.24, 57.24 };
		double[] longpot = new double[] { 120.96, 120.96, 120.96, 135.96 };
		flag = false;

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, check);

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /Single tap and wait for 2 seconds
		TouchUtils.tapView(this, algowhole);

		try {
			synchronized (this) {
				wait(20000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (i = 0; i < mActivity.As.alZPotholeEvent.size(); i++) {
			apot = mActivity.As.alLatFile.get(mActivity.As.alZPotholeEvent
					.get(i));
			cpot = mActivity.As.alLongFile.get(mActivity.As.alZPotholeEvent
					.get(i));
			if (apot == latpot[i]) {
				flag = true;
			} else {
				flag = false;
				break;
			}
			if (cpot == longpot[i]) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

	public void test_demo() {

		float[] fSampleX = new float[] { (float) 0.78453195,
				(float) 0.84337187, (float) -0.0392266, (float) 0.6472389,
				(float) -0.049033247, (float) 0.71588546, (float) 0.9120184,
				(float) -0.1372931, (float) 0.4314926, (float) 0.7256921,
				(float) -0.1372931, (float) 0.4511059, (float) 0.24516624,
				(float) 0.4314926, (float) 0.74530536, (float) -0.30400613,
				(float) 0.0784532, (float) 0.3334261, (float) -0.35303938,
				(float) 0.70607877, (float) 0.49033248, (float) 0.71588,
				(float) 2.1280, (float) 0.049033, (float) 0.4314926,
				(float) 2.10842, (float) -1.392544, (float) 0.0294199,
				(float) -0.35303, (float) -1.971136, (float) 0.843371,
				(float) 0.20593, (float) -1.6671305, (float) 0.588399,
				(float) -1.10815, (float) 1.37293, (float) 0.245166,
				(float) -0.56878, (float) -3.94227, (float) -1.098344,
				(float) -0.20593, (float) -0.52955, (float) 1.461190,
				(float) -0.912018, (float) -2.12804, (float) -0.57859,
				(float) -0.5792, (float) -0.98066497, (float) 0.2549729,
				(float) -0.92182505, (float) -2.1966896, (float) -2.0299766,
				(float) -0.84337187, (float) 0.2745862, (float) 0.57859236,
				(float) 0.49033248, (float) -0.2745862, (float) 0.4707192,
				(float) 0.24516624, (float) -0.98066497, (float) -1.343511,
				(float) 0.098066494, (float) -0.0392266, (float) -1.4219642,
				(float) -2.2064962, (float) -1.6867437, (float) -0.4314926,
				(float) 0.37265268, (float) -1.1179581, (float) -0.8139519,
				(float) -0.5295591, (float) -0.8531785, (float) -1.9613299,
				(float) -1.0493115, (float) 0.0, (float) 0.16671304,
				(float) 0.00980665, (float) -3.8147867, (float) -0.32361946,
				(float) 0.40207264, (float) -1.6082906, (float) -1.4906107,
				(float) -0.0588399, (float) -1.7651969, (float) -2.9027684,
				(float) -0.0392266, (float) -0.37265268, (float) -0.4118793,
				(float) 0.5099458, (float) 0.39226598, (float) -2.7262487,
				(float) -0.5393657, (float) 1.6279038, (float) -0.55897903,
				(float) -1.706357, (float) -1.735777, (float) 0.97085834,
				(float) -1.5984839, (float) 0.65704554, (float) -1.9711366,
				(float) 0.3334261, (float) 1.8828768, (float) -0.57859236,
				(float) -1.0395049, (float) -0.3138128, (float) 1.7750036,
				(float) 1.4415776, (float) 0.69627213, (float) -0.7354987,
				(float) 0.19613299, (float) -1.7848103, (float) 0.19613299,
				(float) 0.14709975, (float) 1.6475172, (float) 0.5982056,
				(float) 0.5491724, (float) 0.93163174, (float) -1.3925443,
				(float) 0.98066497, (float) 0.8629852, (float) -0.0196133,
				(float) -1.2650578, (float) -1.6769371, (float) -0.4707192,
				(float) 1.2650578, (float) -0.51975244, (float) -0.5001391,
				(float) 0.5295591, (float) 1.1964113, (float) 0.55897903,
				(float) 0.4707192, (float) 1.1669914, (float) 0.6472389,
				(float) 0.1372931, (float) -0.06864655, (float) -0.18632634,
				(float) -1.6082906, (float) 0.0392266, (float) 0.6668522,
				(float) 0.0196133, (float) -0.4118793, (float) -1.0395049,
				(float) -0.4511059, (float) 0.42168593, (float) 1.0885382,
				(float) 2.4026291, (float) -0.1176798, (float) -0.55897903,
				(float) 1.4317709, (float) 1.1375713, (float) -0.2549729,
				(float) 0.0588399, (float) -0.8139519, (float) -0.1569064,
				(float) -1.0395049, (float) 0.0784532, (float) -0.51975244,
				(float) -0.30400613, (float) 0.1176798, (float) -0.7649187,
				(float) -0.75511205, (float) -1.4513842, (float) -0.9414384,
				(float) -0.9120184, (float) -0.9610517, (float) 0.2941995,
				(float) 1.1879297, (float) 1.0729688, (float) 1.0729688,
				(float) 1.1496094, (float) 0.613125, (float) 0.68976563,
				(float) 0.8047266, (float) 0.7664063, (float) 0.8047266,
				(float) 0.68976563, (float) 0.68976563, (float) 0.6514453,
				(float) 0.728086, (float) 0.8047266, (float) 0.613125,
				(float) 0.6514453, (float) 0.68976563, (float) 0.613125,
				(float) 0.5748047, (float) 0.5364844, (float) 0.5748047,
				(float) 0.5748047, (float) 0.4981641, (float) 0.45984375,
				(float) 0.5748047, (float) -19.39008, (float) -19.39008,
				(float) -7.6257424, (float) 1.954336, (float) 2.1076174,
				(float) 2.1076174, (float) 2.1076174, (float) 2.1076174,
				(float) 2.0309765, (float) 1.9926564, (float) 2.0309765,
				(float) 1.9926564, (float) 1.8776954, (float) 6.09293,
				(float) 16.707657, (float) 17.12918, (float) 8.123906,
				(float) 2.9506643, (float) 1.954336, (float) 3.5637891,
				(float) 4.9433208, (float) 7.549102, (float) 8.162227,
				(float) 2.3375392, (float) 1.341211, (float) 3.410508,
				(float) 2.8357031 };
		float[] fSampleZ = new float[] { (float) 9.041731, (float) 9.4241905,
				(float) 10.032203, (float) 10.179302, (float) 8.561205,
				(float) 8.551398, (float) 8.345459, (float) 9.787037,
				(float) 10.081236, (float) 9.394771, (float) 10.130269,
				(float) 9.679163, (float) 10.189109, (float) 9.806650,
				(float) 10.561762, (float) 10.159689, (float) 9.698776,
				(float) 9.845877, (float) 11.120741, (float) 8.825985,
				(float) 9.149604,

				(float) 9.07115, (float) 7.44324, (float) 7.58054,
				(float) 7.08040, (float) 8.56120, (float) 10.55195,
				(float) 8.68869, (float) 9.3065, (float) 4.6777,
				(float) 8.6984, (float) 7.55112, (float) 8.6200,
				(float) 12.5132, (float) 8.9829, (float) 7.87473,
				(float) 5.7663, (float) 10.9344, (float) 7.5020,
				(float) 11.52281, (float) 11.8758, (float) 12.846711,
				(float) 7.13924, (float) 8.2277, (float) 13.8273,
				(float) 15.3670, (float) 9.4634,

				(float) 8.855405, (float) 9.296704, (float) 11.41494,
				(float) 11.542427, (float) 10.336209, (float) 14.268676,
				(float) 8.953471, (float) 7.021561, (float) 5.6878567,
				(float) 7.531507, (float) 4.677772, (float) 10.679441,
				(float) 19.956532, (float) 13.621436, (float) 9.679163,
				(float) 6.550842, (float) 7.678607, (float) 11.856239,
				(float) 10.620602, (float) 9.963556, (float) 9.0515375,
				(float) 11.022675, (float) 12.562319, (float) 10.473502,
				(float) 10.169496, (float) 10.247949, (float) 9.92433,
				(float) 9.169217, (float) 10.140076, (float) 10.875574,
				(float) 11.581654, (float) 7.8845463, (float) 10.9442215,
				(float) 13.974476, (float) 10.081236, (float) 8.835792,
				(float) 11.875853, (float) 10.326403, (float) 10.375436,
				(float) 11.689527, (float) 9.698776, (float) 6.335096,
				(float) 10.130269, (float) 14.955141, (float) 6.8450418,
				(float) 6.825428, (float) 7.78648, (float) 11.895466,
				(float) 12.405412, (float) 11.346293, (float) 13.67047,
				(float) 11.885659, (float) 12.248506, (float) 9.129991,
				(float) 4.5208654, (float) 6.560649, (float) 9.747809,
				(float) 9.855683, (float) 7.894353, (float) 10.620602,
				(float) 7.864933, (float) 10.630408, (float) 7.305954,
				(float) 11.571847, (float) 12.483865, (float) 8.159133,
				(float) 12.013146, (float) 11.748366, (float) 10.355823,
				(float) 7.423634, (float) 9.218251, (float) 10.0910425,
				(float) 9.041731, (float) 8.482752, (float) 11.718946,
				(float) 11.052094, (float) 9.92433, (float) 11.287454,
				(float) 11.140354, (float) 9.92433, (float) 8.708305,
				(float) 10.885382, (float) 10.600988, (float) 12.483865,
				(float) 10.8657675, (float) 11.924886, (float) 8.678885,
				(float) 12.630965, (float) 11.179581, (float) 9.855683,
				(float) 9.031924, (float) 9.5320635, (float) 10.767701,
				(float) 9.796844, (float) 10.473502, (float) 10.1989155,
				(float) 10.179302, (float) 10.120462, (float) 9.943943,
				(float) 8.767145, (float) 10.669635, (float) 6.0703163,
				(float) 6.5998755, (float) 8.571012, (float) 9.57129,
				(float) 10.326403, (float) 12.434832, (float) 8.502365,
				(float) 9.169217, (float) 8.512172, (float) 9.767424,
				(float) 11.218807, (float) 11.003061, (float) 10.512729,
				(float) 11.287454, (float) 11.473781, (float) 9.394771,
				(float) 10.434276, (float) 9.649743, (float) 9.388477,
				(float) 8.660391, (float) 8.468789, (float) 7.2808595,
				(float) 2.682422, (float) 0.19160157, (float) 0.19160157,
				(float) 0.22992188, (float) 0.15328126, (float) 0.19160157,
				(float) 0.22992188, (float) 0.11496094, (float) 0.19160157,
				(float) 0.22992188, (float) 0.11496094, (float) 0.22992188,
				(float) 0.22992188, (float) 0.15328126, (float) 0.22992188,
				(float) 0.22992188, (float) 0.15328126, (float) 0.07664063,
				(float) 0.15328126, (float) 0.19160157, (float) 0.15328126,
				(float) 4.981641, (float) 1.456172, (float) 0.15328126,
				(float) 0.07664063, (float) 0.15328126, (float) 0.19160157,
				(float) 0.11496094, (float) 0.15328126, (float) 0.15328126,
				(float) 0.11496094, (float) 0.19160157, (float) 0.19160157,
				(float) 0.9580079, (float) 18.738634, (float) 19.58168,
				(float) 12.913946, (float) 1.4178516, (float) 1.954336,
				(float) 4.598438, (float) 13.45043, (float) 14.408438,
				(float) 8.277188, (float) 13.37379, (float) 6.591094,
				(float) 8.737032, (float) 9.350157, (float) 9.081915 };
		boolean flag = true;
		int iLoop1 = 0;
		// /Single tap and wait for 2 seconds
		Instrumentation instrumentation = getInstrumentation();

		// Register we are interested in the authentication activity...
		Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(
				DemoActivity.class.getName(), null, false);

		TouchUtils.tapView(this, checkbutton);
		// /Check for the visibility of the text
		// assertEquals(View.VISIBLE , check.getVisibility());

		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Start the authentication activity as the first activity...
		// Intent intent = new Intent(Intent.ACTION_MAIN);
		// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// intent.setClassName(instrumentation.getTargetContext(),
		// DemoActivity.class.getName());
		// instrumentation.startActivitySync(intent);

		// Wait for it to start...
		getInstrumentation().waitForIdleSync();
		Activity currentActivity = getInstrumentation()
				.waitForMonitorWithTimeout(monitor, 5);
		// assertThat(currentActivity, is(notNullValue()));
		speedincr = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonspeedincrement);
		gps = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttongps);
		smooth = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonsmoothroad);
		pothole = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonpothole);
		speedbump = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonspeedbump);
		buttonbreak = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonbreak);
		phonedrop = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonphonedrop);
		finish = (Button) currentActivity
				.findViewById(com.example.potholemapper.R.id.buttonfinish);

		TouchUtils.tapView(this, speedincr);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, speedincr);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, speedincr);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, speedincr);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, speedincr);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, speedincr);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, gps);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, gps);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, smooth);
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TouchUtils.tapView(this, pothole);
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, speedbump);
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, buttonbreak);
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, phonedrop);
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TouchUtils.tapView(this, finish);
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getInstrumentation().waitForIdleSync();

		for (iLoop1 = 0; iLoop1 < fSampleX.length; iLoop1++) {
			if ((fSampleX[iLoop1] != mActivity.DemoAccelerometerX.get(iLoop1))
					|| (fSampleZ[iLoop1] != mActivity.DemoAccelerometerZ
							.get(iLoop1))) {
				flag = false;
				break;
			}
		}
		assertEquals(flag, true);

	}

}
