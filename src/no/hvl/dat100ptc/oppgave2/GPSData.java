package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		this.antall = 0;
		gpspoints = new GPSPoint[antall];


	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return true;
		}
		return false;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpsPoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		return insertGPS(gpsPoint);

	}

	public void print() {
		System.out.println("====== GPS Data - START ======");
        for (GPSPoint gpspoint : gpspoints) {
			System.out.print(gpspoint+"");
		}
		System.out.println("====== GPS Data - SLUTT ======");

	}
}
