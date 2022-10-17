package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		this.gpspoints = new GPSPoint[n];
		antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		if (antall < gpspoints.length) {
		gpspoints[antall] = gpspoint;
		antall++;
		inserted = true;
	}
		
		return inserted;
		
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		
		boolean inserted = false;
		GPSPoint gpspoint = new GPSPoint(GPSDataConverter.toSeconds(time), Double.valueOf(latitude),
				Double.valueOf(longitude), Double.valueOf(elevation));
		if (insertGPS(gpspoint) == true)
			inserted = true;
		
		return inserted;
		
		
		
	}

	public void print() {
		System.out.println("====== Konvertert GPS Data - START ======");
		for (int x = 0; x < gpspoints.length; x++) {
			System.out.println(gpspoints[x].toString());
		}
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
