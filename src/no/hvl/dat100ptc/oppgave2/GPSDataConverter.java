package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {


	private static int TIME_STARTINDEX = 11;

	public static int toSeconds(String timestr) {

		int secs;
		int hr, min, sec;
		secs = Integer.parseInt(timestr.substring(17, 19));
		min = Integer.parseInt(timestr.substring(14, 16))*60;
		hr = Integer.parseInt(timestr.substring(11, 13))*3600;

		return secs + min + hr;

	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		GPSPoint gpspoint;
		int timeInt = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);

		return new GPSPoint(timeInt, latitude, longitude, elevation);
	}

}
