package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint gps1 = new GPSPoint(33, 22, 22, 22);
		GPSPoint gps2 = new GPSPoint(33, 22, 22, 22);


		GPSData gpsData1 = new GPSData(2);
		gpsData1.insertGPS(gps1);
		gpsData1.insertGPS(gps2);
		gpsData1.print();
	}
}
