package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat= GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double ystep = MAPYSIZE / (Math.abs(maxlat-minlat));
	
		return ystep;
	}

	public void showRouteMap(int ybase) {
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		for (int x = 0; x < gpspoints.length-1; x++)  {
			setColor(0, 255, 0);
			drawLine(
					(int)Math.round(MARGIN+(gpspoints[x].getLongitude()-minlon)*xstep()),
					(int)Math.round(ybase-(gpspoints[x].getLatitude()-minlat)*ystep()),
					(int)Math.round(MARGIN+(gpspoints[x+1].getLongitude()-minlon)*xstep()),
					(int)Math.round(ybase-(gpspoints[x+1].getLatitude()-minlat)*ystep()));
			fillCircle(
					(int)Math.round(MARGIN+(gpspoints[x].getLongitude()-minlon)*xstep()),
					(int)Math.round(ybase-(gpspoints[x].getLatitude()-minlat)*ystep()),
					4);

		}
		setColor(0, 0, 255);
		fillCircle((int)Math.round(MARGIN+(gpspoints[gpspoints.length-1].getLongitude()-minlon)*xstep()),
				(int)Math.round(ybase-(gpspoints[gpspoints.length-1].getLatitude()-minlat)*ystep()), 10);
	}

	public void showStatistics() {
		
		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Arial",12);
	
		drawString("Total Time:", MARGIN, TEXTDISTANCE);
		drawString("Total Distance:", MARGIN, TEXTDISTANCE*2);
		drawString("Total Elevation:", MARGIN, TEXTDISTANCE*3);
		drawString("Max Speed:", MARGIN, TEXTDISTANCE*4);
		drawString("Average Speed:", MARGIN, TEXTDISTANCE*5);
		drawString("Energy:", MARGIN, TEXTDISTANCE*6);
		drawString(GPSUtils.formatTime(gpscomputer.totalTime()), 170, TEXTDISTANCE);
		drawString(GPSUtils.formatDouble((gpscomputer.totalDistance() / 1000)) + " km", 170, TEXTDISTANCE * 2);
		drawString(GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", 170, TEXTDISTANCE * 3);
		drawString(GPSUtils.formatDouble(gpscomputer.maxSpeed()) + (" km/t"), 170, TEXTDISTANCE * 4);
		drawString(GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", 170, TEXTDISTANCE * 5);
		drawString(GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal", 170, TEXTDISTANCE * 6);
	}

}
