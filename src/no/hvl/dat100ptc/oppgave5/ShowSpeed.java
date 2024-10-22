package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

import java.awt.*;

public class ShowSpeed extends EasyGraphics {

	private static int MARGIN = 50;
	private static int BARHEIGHT = 100;

	private GPSComputer gpscomputer;

	public ShowSpeed() {
		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		makeWindow("Speed profile",
				2 * MARGIN +
						2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);

		showSpeedProfile(MARGIN + BARHEIGHT);
	}

	public void showSpeedProfile(int ybase) {
		int x = MARGIN;
		double[] speeds = gpscomputer.speeds();
		double averageSpeed = gpscomputer.averageSpeed() * 3.6;


		for (int i = 0; i < speeds.length; i++) {
			int speedHeight = (int) (speeds[i] * 3.6);
			setColor(0,0,255);
			drawLine(x, ybase, x, ybase - speedHeight);
			x += 2;
		}


		setColor(0,255,0);
		drawLine(x, ybase - (int) averageSpeed,
				x + 2 * speeds.length, ybase - (int) averageSpeed);
	}
}