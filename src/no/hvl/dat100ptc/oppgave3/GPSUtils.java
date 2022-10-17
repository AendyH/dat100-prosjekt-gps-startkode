package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min; 
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		
		for (int x = 0; x < gpspoints.length; x++) {
			latitudes[x] = gpspoints[x].getLatitude();
		}
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		
		for (int x = 0; x < gpspoints.length; x++) {
			longitudes[x] = gpspoints[x].getLongitude();
		}
		return longitudes;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double a, c, d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		double deltaLat = (latitude2 - latitude1);
		
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		double deltaLong = (longitude2 - longitude1);
	
		a = Math.pow(Math.sin(deltaLat/2),2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLong/2),2);
		
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		d = R * c;
		
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = distance(gpspoint1, gpspoint2) / secs;

		return speed*3.6;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		String hr = String.format("%02d",secs/3600)+TIMESEP;
		String min = String.format("%02d",secs/60%60)+TIMESEP;
		String sec = String.format("%02d",secs%60);
		
		timestr = hr + min + sec;
		
		return "  " + timestr;
		
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str = String.format("%.2f", d);
		
		while (str.length() != TEXTWIDTH) {
			str = " " + str;
		}
		str = str.replace(",", ".");
		
		return str;
	
		
	}
}
