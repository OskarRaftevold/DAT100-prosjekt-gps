package no.hvl.dat100ptc.oppgave1;

public class GPSPoint {
	private int time;
	private double latitude;
	private double longitude;
	private double altitude;

	public GPSPoint(int time, double latitude, double longitude, double elevation) {
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = elevation;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return altitude;
	}

	public void setElevation(double elevation) {
		this.altitude = elevation;
	}

	@Override
	public String toString() {
		return time + " (" + latitude + "," + longitude + ") " + altitude + "\n";
	}

}
