/* Author: Dhawal Srivastava
 * Author: Navin Chaganti
 * Graduate Student
 * Software Engineering Concept
 * ECE-573
 * University Of Arizona
 * */

package com.example.potholemapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DemoActivity extends Activity {
	Button Speed_inc;
	Button Speed_dec;
	Button gps;
	Button speedBump;
	Button potHole;
	Button break_event;
	Button smoothRoad;
	Button phoneDrop;
	Button finish_event;

	TextView textSpeed;
	TextView textLat;
	TextView textLong;

	DecimalFormat twoDForm = new DecimalFormat("#.####");

	FileOutputStream fOutputFile;
	int speed = 0, iLoop1, iIndex;
	double dlat = 32.24, dlong = -110.96;

	public ArrayList<Double> gpslat = new ArrayList<Double>();
	public ArrayList<Double> gpslong = new ArrayList<Double>();
	public ArrayList<Float> AccelerometerY = new ArrayList<Float>();
	public ArrayList<Float> AccelerometerX = new ArrayList<Float>();
	public ArrayList<Float> AccelerometerZ = new ArrayList<Float>();
	public ArrayList<Integer> Speed = new ArrayList<Integer>();

	float[] fSamplePotholeFastZ = new float[] { (float) 9.07115,
			(float) 7.44324, (float) 7.58054, (float) 7.08040, (float) 8.56120,
			(float) 10.55195, (float) 8.68869, (float) 9.3065, (float) 4.6777,
			(float) 8.6984, (float) 7.55112, (float) 8.6200, (float) 12.5132,
			(float) 8.9829, (float) 7.87473, (float) 5.7663, (float) 10.9344,
			(float) 7.5020, (float) 11.52281, (float) 11.8758,
			(float) 12.846711, (float) 7.13924, (float) 8.2277,
			(float) 13.8273, (float) 15.3670, (float) 9.4634 };
	float[] fSamplePotholeFastX = new float[] { (float) 0.71588,
			(float) 2.1280, (float) 0.049033, (float) 0.4314926,
			(float) 2.10842, (float) -1.392544, (float) 0.0294199,
			(float) -0.35303, (float) -1.971136, (float) 0.843371,
			(float) 0.20593, (float) -1.6671305, (float) 0.588399,
			(float) -1.10815, (float) 1.37293, (float) 0.245166,
			(float) -0.56878, (float) -3.94227, (float) -1.098344,
			(float) -0.20593, (float) -0.52955, (float) 1.461190,
			(float) -0.912018, (float) -2.12804, (float) -0.57859,
			(float) -0.5792 };
	float[] fSamplePotholeFastY = new float[] { (float) -1.39254,
			(float) -2.07900, (float) -0.80414, (float) -1.60829,
			(float) -2.12804, (float) -0.951245, (float) -2.794895,
			(float) -0.7354987, (float) -2.471275, (float) -1.8240368,
			(float) -1.627903, (float) 0.1569064, (float) -1.147378,
			(float) -0.990471, (float) -2.461469, (float) 0.83356,
			(float) -2.02016, (float) -1.94171, (float) -1.94171,
			(float) -0.46091, (float) -0.54917, (float) 0.245166,
			(float) -0.83356, (float) 0.686465, (float) -3.128321,
			(float) -1.03950 };

	float[] fSamplePhoneDropY = new float[] { (float) -0.9196875,
			(float) -0.8813672, (float) -1.1879297, (float) -2.069297,
			(float) -1.4178516, (float) -0.15328126, (float) -0.19160157,
			(float) -0.15328126, (float) -0.07664063, (float) -0.15328126,
			(float) -0.15328126, (float) -0.11496094, (float) -0.07664063,
			(float) -0.038320314, (float) -0.19160157, (float) -0.0,
			(float) -0.11496094, (float) -0.038320314, (float) -0.07664063,
			(float) -0.0, (float) -0.0, (float) -0.11496094,
			(float) -0.07664063, (float) -0.0, (float) -0.0, (float) 19.62,
			(float) 19.006876, (float) 4.176914, (float) -0.68976563,
			(float) -0.68976563, (float) -0.7664063, (float) -0.7664063,
			(float) -1.0346485, (float) -1.1112891, (float) -1.1496094,
			(float) -1.2645704, (float) -1.456172, (float) -1.954336,
			(float) -15.78797, (float) -19.313438, (float) -19.313438,
			(float) 11.726016, (float) 1.0729688, (float) -1.0346485,
			(float) -0.613125, (float) -1.7244141, (float) -0.728086,
			(float) 3.027305, (float) 0.19160157, (float) -1.456172,
			(float) 0.38320315, (float) -0.19160157 };
	float[] fSamplePhoneDropX = new float[] { (float) 1.1879297,
			(float) 1.0729688, (float) 1.0729688, (float) 1.1496094,
			(float) 0.613125, (float) 0.68976563, (float) 0.8047266,
			(float) 0.7664063, (float) 0.8047266, (float) 0.68976563,
			(float) 0.68976563, (float) 0.6514453, (float) 0.728086,
			(float) 0.8047266, (float) 0.613125, (float) 0.6514453,
			(float) 0.68976563, (float) 0.613125, (float) 0.5748047,
			(float) 0.5364844, (float) 0.5748047, (float) 0.5748047,
			(float) 0.4981641, (float) 0.45984375, (float) 0.5748047,
			(float) -19.39008, (float) -19.39008, (float) -7.6257424,
			(float) 1.954336, (float) 2.1076174, (float) 2.1076174,
			(float) 2.1076174, (float) 2.1076174, (float) 2.0309765,
			(float) 1.9926564, (float) 2.0309765, (float) 1.9926564,
			(float) 1.8776954, (float) 6.09293, (float) 16.707657,
			(float) 17.12918, (float) 8.123906, (float) 2.9506643,
			(float) 1.954336, (float) 3.5637891, (float) 4.9433208,
			(float) 7.549102, (float) 8.162227, (float) 2.3375392,
			(float) 1.341211, (float) 3.410508, (float) 2.8357031 };
	float[] fSamplePhoneDropZ = new float[] { (float) 9.388477,
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

	float[] fSampleSpeedBumpY = new float[] { (float) -1.4611908,
			(float) -2.128043, (float) -1.8828768, (float) -0.9610517,
			(float) -0.951245, (float) 0.0392266, (float) -1.7161638,
			(float) -3.304841, (float) -2.0397832, (float) -1.4611908,
			(float) -0.2941995, (float) 0.36284605, (float) 1.93191,
			(float) 1.5886773, (float) -4.118793, (float) -3.000835,
			(float) -1.6867437, (float) 0.38245934, (float) -0.7256921,
			(float) 1.4513842 };
	float[] fSampleSpeedBumpX = new float[] { (float) -0.98066497,
			(float) 0.2549729, (float) -0.92182505, (float) -2.1966896,
			(float) -2.0299766, (float) -0.84337187, (float) 0.2745862,
			(float) 0.57859236, (float) 0.49033248, (float) -0.2745862,
			(float) 0.4707192, (float) 0.24516624, (float) -0.98066497,
			(float) -1.343511, (float) 0.098066494, (float) -0.0392266,
			(float) -1.4219642, (float) -2.2064962, (float) -1.6867437,
			(float) -0.4314926 };
	float[] fSampleSpeedBumpZ = new float[] { (float) 8.855405,
			(float) 9.296704, (float) 11.41494, (float) 11.542427,
			(float) 10.336209, (float) 14.268676, (float) 8.953471,
			(float) 7.021561, (float) 5.6878567, (float) 7.531507,
			(float) 4.677772, (float) 10.679441, (float) 19.956532,
			(float) 13.621436, (float) 9.679163, (float) 6.550842,
			(float) 7.678607, (float) 11.856239, (float) 10.620602,
			(float) 9.963556 };

	float[] fSampleSmoothRoadY = new float[] { (float) -1.3140911,
			(float) -1.4219642, (float) -2.7164419, (float) -2.775282,
			(float) -1.9809432, (float) -1.9809432, (float) -1.99075,
			(float) -2.4810824, (float) -1.6867437, (float) -1.372931,
			(float) -1.7848103, (float) -2.3045628, (float) -3.0302548,
			(float) -1.8240368, (float) -2.6379888, (float) -2.3143694,
			(float) -2.5006957, (float) -2.2849493, (float) -2.0888164,
			(float) -2.5301156, (float) -1.7750036 };
	float[] fSampleSmoothRoadX = new float[] { (float) 0.78453195,
			(float) 0.84337187, (float) -0.0392266, (float) 0.6472389,
			(float) -0.049033247, (float) 0.71588546, (float) 0.9120184,
			(float) -0.1372931, (float) 0.4314926, (float) 0.7256921,
			(float) -0.1372931, (float) 0.4511059, (float) 0.24516624,
			(float) 0.4314926, (float) 0.74530536, (float) -0.30400613,
			(float) 0.0784532, (float) 0.3334261, (float) -0.35303938,
			(float) 0.70607877, (float) 0.49033248 };
	float[] fSampleSmoothRoadZ = new float[] { (float) 9.041731,
			(float) 9.4241905, (float) 10.032203, (float) 10.179302,
			(float) 8.561205, (float) 8.551398, (float) 8.345459,
			(float) 9.787037, (float) 10.081236, (float) 9.394771,
			(float) 10.130269, (float) 9.679163, (float) 10.189109,
			(float) 9.806650, (float) 10.561762, (float) 10.159689,
			(float) 9.698776, (float) 9.845877, (float) 11.120741,
			(float) 8.825985, (float) 9.149604 };

	float[] fSampleBreakY = new float[] { (float) -0.39226598,
			(float) -2.0790098, (float) 0.8041453, (float) -0.56878567,
			(float) -0.8139519, (float) -1.3140911, (float) -1.9711366,
			(float) -0.37265268, (float) 0.0196133, (float) 0.5982056,
			(float) -2.1084297, (float) -1.8534569, (float) 0.2353596,
			(float) -4.981778, (float) 0.5001391, (float) 1.8044236,
			(float) -1.9809432, (float) -1.539644, (float) -0.4118793,
			(float) -0.56878567, (float) -0.049033247, (float) 0.83356524,
			(float) 1.6279038, (float) -0.5982056, (float) -0.1372931,
			(float) -0.1569064, (float) 0.14709975, (float) -2.098623,
			(float) -0.8139519, (float) -0.44129923, (float) -0.70607877,
			(float) -0.89240515, (float) -2.3045628, (float) 1.4513842,
			(float) 1.2944778, (float) -0.28439283, (float) -2.0299766,
			(float) -3.4813607, (float) -1.2356379, (float) -0.51975244,
			(float) -1.4317709, (float) -3.000835, (float) 2.1084297,
			(float) -0.8139519, (float) -1.6671305, (float) 0.6374322,
			(float) -1.2454445, (float) 0.0784532, (float) -0.9120184,
			(float) -0.0588399, (float) -2.3045628, (float) -1.4709975,
			(float) 0.8531785, (float) 0.5491724, (float) -2.3928225,
			(float) -1.4219642, (float) -0.40207264, (float) -1.3533176,
			(float) 0.88259846, (float) -1.1964113, (float) -0.2941995,
			(float) -0.8727918, (float) -1.3631244, (float) -0.2745862,
			(float) -0.7256921, (float) -1.2748644, (float) 0.16671304,
			(float) 0.6864655, (float) -0.4707192, (float) -1.0591182,
			(float) -1.2650578, (float) 0.049033247, (float) -0.0196133,
			(float) 0.19613299, (float) -0.26477954, (float) 0.83356524,
			(float) -0.6276256, (float) 0.6766588, (float) -1.0002782,
			(float) 0.51975244, (float) -0.97085834, (float) -0.6766588,
			(float) 0.71588546, (float) -1.2944778, (float) 1.4415776,
			(float) 0.17651969, (float) -0.78453195, (float) -0.10787315,
			(float) -0.2745862, (float) -1.1571847, (float) -0.71588546,
			(float) -0.18632634, (float) -0.19613299, (float) -0.2941995,
			(float) -1.0493115, (float) -2.0692031, (float) -0.1569064,
			(float) 0.0196133, (float) -0.00980665 };
	float[] fSampleBreakX = new float[] { (float) 0.37265268,
			(float) -1.1179581, (float) -0.8139519, (float) -0.5295591,
			(float) -0.8531785, (float) -1.9613299, (float) -1.0493115,
			(float) 0.0, (float) 0.16671304, (float) 0.00980665,
			(float) -3.8147867, (float) -0.32361946, (float) 0.40207264,
			(float) -1.6082906, (float) -1.4906107, (float) -0.0588399,
			(float) -1.7651969, (float) -2.9027684, (float) -0.0392266,
			(float) -0.37265268, (float) -0.4118793, (float) 0.5099458,
			(float) 0.39226598, (float) -2.7262487, (float) -0.5393657,
			(float) 1.6279038, (float) -0.55897903, (float) -1.706357,
			(float) -1.735777, (float) 0.97085834, (float) -1.5984839,
			(float) 0.65704554, (float) -1.9711366, (float) 0.3334261,
			(float) 1.8828768, (float) -0.57859236, (float) -1.0395049,
			(float) -0.3138128, (float) 1.7750036, (float) 1.4415776,
			(float) 0.69627213, (float) -0.7354987, (float) 0.19613299,
			(float) -1.7848103, (float) 0.19613299, (float) 0.14709975,
			(float) 1.6475172, (float) 0.5982056, (float) 0.5491724,
			(float) 0.93163174, (float) -1.3925443, (float) 0.98066497,
			(float) 0.8629852, (float) -0.0196133, (float) -1.2650578,
			(float) -1.6769371, (float) -0.4707192, (float) 1.2650578,
			(float) -0.51975244, (float) -0.5001391, (float) 0.5295591,
			(float) 1.1964113, (float) 0.55897903, (float) 0.4707192,
			(float) 1.1669914, (float) 0.6472389, (float) 0.1372931,
			(float) -0.06864655, (float) -0.18632634, (float) -1.6082906,
			(float) 0.0392266, (float) 0.6668522, (float) 0.0196133,
			(float) -0.4118793, (float) -1.0395049, (float) -0.4511059,
			(float) 0.42168593, (float) 1.0885382, (float) 2.4026291,
			(float) -0.1176798, (float) -0.55897903, (float) 1.4317709,
			(float) 1.1375713, (float) -0.2549729, (float) 0.0588399,
			(float) -0.8139519, (float) -0.1569064, (float) -1.0395049,
			(float) 0.0784532, (float) -0.51975244, (float) -0.30400613,
			(float) 0.1176798, (float) -0.7649187, (float) -0.75511205,
			(float) -1.4513842, (float) -0.9414384, (float) -0.9120184,
			(float) -0.9610517, (float) 0.2941995 };
	float[] fSampleBreakZ = new float[] { (float) 9.0515375, (float) 11.022675,
			(float) 12.562319, (float) 10.473502, (float) 10.169496,
			(float) 10.247949, (float) 9.92433, (float) 9.169217,
			(float) 10.140076, (float) 10.875574, (float) 11.581654,
			(float) 7.8845463, (float) 10.9442215, (float) 13.974476,
			(float) 10.081236, (float) 8.835792, (float) 11.875853,
			(float) 10.326403, (float) 10.375436, (float) 11.689527,
			(float) 9.698776, (float) 6.335096, (float) 10.130269,
			(float) 14.955141, (float) 6.8450418, (float) 6.825428,
			(float) 7.78648, (float) 11.895466, (float) 12.405412,
			(float) 11.346293, (float) 13.67047, (float) 11.885659,
			(float) 12.248506, (float) 9.129991, (float) 4.5208654,
			(float) 6.560649, (float) 9.747809, (float) 9.855683,
			(float) 7.894353, (float) 10.620602, (float) 7.864933,
			(float) 10.630408, (float) 7.305954, (float) 11.571847,
			(float) 12.483865, (float) 8.159133, (float) 12.013146,
			(float) 11.748366, (float) 10.355823, (float) 7.423634,
			(float) 9.218251, (float) 10.0910425, (float) 9.041731,
			(float) 8.482752, (float) 11.718946, (float) 11.052094,
			(float) 9.92433, (float) 11.287454, (float) 11.140354,
			(float) 9.92433, (float) 8.708305, (float) 10.885382,
			(float) 10.600988, (float) 12.483865, (float) 10.8657675,
			(float) 11.924886, (float) 8.678885, (float) 12.630965,
			(float) 11.179581, (float) 9.855683, (float) 9.031924,
			(float) 9.5320635, (float) 10.767701, (float) 9.796844,
			(float) 10.473502, (float) 10.1989155, (float) 10.179302,
			(float) 10.120462, (float) 9.943943, (float) 8.767145,
			(float) 10.669635, (float) 6.0703163, (float) 6.5998755,
			(float) 8.571012, (float) 9.57129, (float) 10.326403,
			(float) 12.434832, (float) 8.502365, (float) 9.169217,
			(float) 8.512172, (float) 9.767424, (float) 11.218807,
			(float) 11.003061, (float) 10.512729, (float) 11.287454,
			(float) 11.473781, (float) 9.394771, (float) 10.434276,
			(float) 9.649743 };

	float[] fSamplePotholeSlowY = new float[] { (float) -0.74530536,
			(float) -0.32361946, (float) -0.20593965, (float) 1.8632635,
			(float) 0.89240515, (float) 0.8727918, (float) 1.7651969,
			(float) 0.8139519, (float) 2.4712758, (float) -1.0885382,
			(float) 2.010363, (float) 1.1277647, (float) 2.4026291,
			(float) 0.98066497, (float) -1.2944778, (float) 0.0784532,
			(float) -0.2157463, (float) 0.2157463, (float) -0.19613299,
			(float) -0.0196133, (float) 0.9610517, (float) 0.6766588,
			(float) -1.5200307, (float) 0.1372931, (float) 1.1179581,
			(float) 1.6279038, (float) 1.2160245 };
	float[] fSamplePotholeSlowX = new float[] { (float) 0.4511059,
			(float) -0.8531785, (float) 1.6082906, (float) 0.7649187,
			(float) -1.147378, (float) -0.70607877, (float) 0.1176798,
			(float) 0.2353596, (float) -2.0299766, (float) 1.0100849,
			(float) -1.1179581, (float) 0.37265268, (float) -0.7649187,
			(float) 0.2157463, (float) 0.0784532, (float) 1.2160245,
			(float) -0.20593965, (float) -0.35303938, (float) -0.89240515,
			(float) 0.16671304, (float) 1.0100849, (float) 0.32361946,
			(float) -0.39226598, (float) -1.1375713, (float) 0.5491724,
			(float) -0.588399, (float) -1.3925443 };
	float[] fSamplePotholeSlowZ = new float[] { (float) 9.855683,
			(float) 9.826263, (float) 11.375713, (float) 9.77723,
			(float) 8.296426, (float) 5.3838506, (float) 9.365351,
			(float) 9.669356, (float) 9.228058, (float) 10.718668,
			(float) 8.355266, (float) 10.375436, (float) 6.5998755,
			(float) 6.237029, (float) 7.78648, (float) 6.913688,
			(float) 8.433719, (float) 10.914802, (float) 9.002504,
			(float) 9.179024, (float) 10.9442215, (float) 11.26784,
			(float) 13.033037, (float) 10.610795, (float) 10.110656,
			(float) 9.100571, (float) 9.610517 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		textSpeed = (TextView) findViewById(R.id.speed);
		textLat = (TextView) findViewById(R.id.lat);
		textLong = (TextView) findViewById(R.id.longt);
	}

	public void Speedincrement(View view) {

		speed = speed + 5;
		textSpeed.setText(speed + " m/s");
	}

	public void Speeddecrement(View view) {
		if (speed != 0) {
			speed = speed - 5;
			textSpeed.setText(" " + speed + " m/s");
		}
	}

	public void GPS(View view) {
		dlat = Double.valueOf(twoDForm.format(dlat));
		dlong = Double.valueOf(twoDForm.format(dlong));
		dlat = dlat + 0.005;
		dlong = dlong + 0.05;

		textLat.setText("Lat: " + String.format("%.2f", (dlat)));
		textLong.setText("Long: " + String.format("%.2f", (dlong)));

	}

	public void SmoothRoad(View view) {
		for (iLoop1 = 0; iLoop1 < fSampleSmoothRoadY.length; iLoop1++) {
			AccelerometerY.add(fSampleSmoothRoadY[iLoop1]);
			AccelerometerX.add(fSampleSmoothRoadX[iLoop1]);
			AccelerometerZ.add(fSampleSmoothRoadZ[iLoop1]);
			gpslat.add(dlat);
			gpslong.add(dlong);
			Speed.add(speed);

		}
		Toast.makeText(this,
				"Driving on Smooth Road",
				Toast.LENGTH_SHORT).show();
	}

	public void Pothole(View view) {
		if (speed > 13) {
			for (iLoop1 = 0; iLoop1 < fSamplePotholeFastY.length; iLoop1++) {
				AccelerometerY.add(fSamplePotholeFastY[iLoop1]);
				AccelerometerX.add(fSamplePotholeFastX[iLoop1]);
				AccelerometerZ.add(fSamplePotholeFastZ[iLoop1]);
				gpslat.add(dlat);
				gpslong.add(dlong);
				Speed.add(speed);
			}
		} else {
			for (iLoop1 = 0; iLoop1 < fSamplePotholeSlowY.length; iLoop1++) {
				AccelerometerY.add(fSamplePotholeSlowY[iLoop1]);
				AccelerometerX.add(fSamplePotholeSlowX[iLoop1]);
				AccelerometerZ.add(fSamplePotholeSlowZ[iLoop1]);
				gpslat.add(dlat);
				gpslong.add(dlong);
				Speed.add(speed);
			}

		}

		Toast.makeText(this,
				" Oh! you hit a pot hole",
				Toast.LENGTH_SHORT).show();
	}

	public void SpeedBump(View view) {

		for (iLoop1 = 0; iLoop1 < fSampleSpeedBumpY.length; iLoop1++) {
			AccelerometerY.add(fSampleSpeedBumpY[iLoop1]);
			AccelerometerX.add(fSampleSpeedBumpX[iLoop1]);
			AccelerometerZ.add(fSampleSpeedBumpZ[iLoop1]);
			gpslat.add(dlat);
			gpslong.add(dlong);
			Speed.add(speed);
		}

		Toast.makeText(this,
				"You hit a speed bump",
				Toast.LENGTH_SHORT).show();
	}

	public void Break(View view) {

		for (iLoop1 = 0; iLoop1 < fSampleBreakY.length; iLoop1++) {
			AccelerometerY.add(fSampleBreakY[iLoop1]);
			AccelerometerX.add(fSampleBreakX[iLoop1]);
			AccelerometerZ.add(fSampleBreakZ[iLoop1]);
			gpslat.add(dlat);
			gpslong.add(dlong);
			Speed.add(speed);
		}

		Toast.makeText(this,
				" That was a sudden break",
				Toast.LENGTH_SHORT).show();
	}

	public void PhoneDrop(View view) {

		for (iLoop1 = 0; iLoop1 < fSamplePhoneDropY.length; iLoop1++) {
			AccelerometerY.add(fSamplePhoneDropY[iLoop1]);
			AccelerometerX.add(fSamplePhoneDropX[iLoop1]);
			AccelerometerZ.add(fSamplePhoneDropZ[iLoop1]);
			gpslat.add(dlat);
			gpslong.add(dlong);
			Speed.add(speed);
		}

		Toast.makeText(this,
"Ouch. You just dropped your phone",
				Toast.LENGTH_SHORT).show();
	}

	public void Finish(View view) {

		// get the path to sdcard
		File sdcard = Environment.getExternalStorageDirectory();
		// to this path add a new directory path
		File dir = new File(sdcard.getAbsolutePath() + "/PotholeMapper/");
		// create this directory if not already created
		dir.mkdir();
		// create the file in which we will write the contents
		File file = new File(dir, "Accelerometer_0.txt");

		try {
			fOutputFile = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(
					fOutputFile);
			iIndex = AccelerometerX.size();
			/*
			 * Writing the time stamp inside the file displaying the time the
			 * file is created
			 */
			// String format = "MM/dd/yyyy @ HH:mm:ss";
			// SimpleDateFormat dateFormat = new SimpleDateFormat(format,
			// Locale.US);

			// outStreamWriter.write("% Generated on "
			// + dateFormat.format(new Date()) + "\n");
			// /* Writing the Name of the Student */
			// outStreamWriter.write("% By Dhawal Srivastava - PotHole - Smooth Road - CurbHit - PhoneDrop - SpeedBump - Speed\n");

			for (iLoop1 = 0; iLoop1 < iIndex; iLoop1++) {

				outStreamWriter.write(AccelerometerY.get(iLoop1).toString()
						+ "\t" + AccelerometerX.get(iLoop1).toString() + "\t"
						+ AccelerometerZ.get(iLoop1).toString() + "\t"
						+ gpslat.get(iLoop1).toString() + "\t"
						+ gpslong.get(iLoop1).toString() + "\t"
						+ Speed.get(iLoop1).toString() + "\n");
			}

			
			
			
			outStreamWriter.flush();
			outStreamWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fOutputFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent();
		ArrayList<String> AccelerometerStringZ = new ArrayList<String>();
		for (iLoop1 = 0; iLoop1 < AccelerometerZ.size(); iLoop1++) {
			AccelerometerStringZ
					.add(Float.toString(AccelerometerZ.get(iLoop1)));
		}
		ArrayList<String> AccelerometerStringY = new ArrayList<String>();
		for (Float item : AccelerometerY) {
			AccelerometerStringY.add(Float.toString(item));
		}
		ArrayList<String> AccelerometerStringX = new ArrayList<String>();
		for (Float item : AccelerometerX) {
			AccelerometerStringX.add(Float.toString(item));
		}
		intent.putExtra("string", "activitystring");
		intent.putStringArrayListExtra("AccelerometerX", AccelerometerStringX);
		intent.putStringArrayListExtra("AccelerometerY", AccelerometerStringY);
		intent.putStringArrayListExtra("AccelerometerZ", AccelerometerStringZ);
		setResult(RESULT_OK, intent);
		DemoActivity.this.finish();

	}
}
