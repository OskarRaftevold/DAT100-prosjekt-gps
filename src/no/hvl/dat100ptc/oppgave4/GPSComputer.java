package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

    private GPSPoint[] gpspoints;

    public GPSComputer(String filename) {

        GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
        gpspoints = gpsdata.getGPSPoints();

    }

    public GPSComputer(GPSPoint[] gpspoints) {
        this.gpspoints = gpspoints;
    }

    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;
    }



    public double totalDistance() {
        double distance = 0;

        for (int i = 0; i < gpspoints.length - 1; i++) {
            distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
        }

        return distance;
    }


    public double totalElevation() {
        double elevation = 0;

        for (int i = 0; i < gpspoints.length - 1; i++) {
            double elevationDiff = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
            if (elevationDiff > 0) {
                elevation += elevationDiff;
            }
        }

        return elevation;
    }


    public int totalTime() {
        int startTime = gpspoints[0].getTime();
        int endTime = gpspoints[gpspoints.length - 1].getTime();

        return endTime - startTime;
    }



    public double[] speeds() {
        double[] speeds = new double[gpspoints.length - 1];

        for (int i = 0; i < gpspoints.length - 1; i++) {
            speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
        }

        return speeds;
    }


    public double maxSpeed() {
        double[] speeds = speeds();

        return GPSUtils.findMax(speeds);
    }


    public double averageSpeed() {
        double totalDistance = 0;
        int totalTime = 0;

        // Regn ut total distanse (bruker metoden distance fra GPSUtils)
        for (int i = 0; i < gpspoints.length - 1; i++) {
            totalDistance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
        }

        // Regn ut total tid
        totalTime = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();

        // Returner gjennomsnittshastigheten i m/s
        return totalDistance / totalTime;
    }




    // conversion factor m/s to miles per hour (mps)
    public static final double MS = 2.23;

    public double kcal(double weight, int secs, double speed) {
        double speedmph = speed * MS; // Convert speed to mph
        double met = 0;

        if (speedmph < 10) {
            met = 4.0;
        } else if (speedmph < 12) {
            met = 6.0;
        } else if (speedmph < 14) {
            met = 8.0;
        } else if (speedmph < 16) {
            met = 10.0;
        } else if (speedmph < 20) {
            met = 12.0;
        } else {
            met = 16.0;
        }

        double hours = secs / 3600.0;
        return met * weight * hours;
    }


    public double totalKcal(double weight) {
        double totalKcal = 0;
        double[] speeds = speeds();
        int totalTime = totalTime();

        for (int i = 0; i < speeds.length; i++) {
            int secs = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
            totalKcal += kcal(weight, secs, speeds[i]);
        }

        return totalKcal;
    }


    private static double WEIGHT = 80.0;

    public void displayStatistics() {
        int totalTime = totalTime();
        double totalDistance = totalDistance() / 1000; // Convert to km
        double totalElevation = totalElevation();
        double maxSpeed = maxSpeed() * 3.6; // Convert to km/h
        double averageSpeed = averageSpeed();
        double totalKcal = totalKcal(WEIGHT);

        System.out.println("==============================================");
        System.out.printf("Total Time     :   %02d:%02d:%02d\n", totalTime / 3600, (totalTime % 3600) / 60, totalTime % 60);
        System.out.printf("Total distance :   %10.2f km\n", totalDistance);
        System.out.printf("Total elevation:   %10.2f m\n", totalElevation);
        System.out.printf("Max speed      :   %10.2f km/h\n", maxSpeed);
        System.out.printf("Average speed  :   %10.2f km/h\n", averageSpeed);
        System.out.printf("Energy         :   %10.2f kcal\n", totalKcal);
        System.out.println("==============================================");
    }


}
