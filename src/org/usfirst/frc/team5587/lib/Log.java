package org.usfirst.frc.team5587.lib;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;

public class Log {
	ArrayList<LogEntry> log = new ArrayList<LogEntry>();
	PowerDistributionPanel pdp = new PowerDistributionPanel();

	/**
	 * A LogEntry holds the state of the robot at a certain time
	 * 
	 * @param time
	 *            The FPGA timestamp
	 * @param lEncDist
	 *            The current distance that the left side of the drivetrain has
	 *            traveled
	 * @param rEncDist
	 *            The current distance that the right side of the drivetrain has
	 *            traveled
	 * @param lVel
	 *            speed of the left side of the drivetrain
	 * @param rVel
	 *            speed of the right side of the drivetrain
	 * @param heading
	 *            angle that the robot is facing
	 * @param xPos
	 *            robot position on the x axis (odometry yet to be implemented)
	 * @param yPos
	 *            robot position on the y axis (odometry yet to be implemented)
	 */
	public void addEntry(double lEncDist, double rEncDist, double lVel, double rVel, double heading, double xPos,
			double yPos) {
		double time = Timer.getFPGATimestamp();
		double vBat = pdp.getVoltage();
		double totalCurrent = pdp.getTotalCurrent();
		log.add(new LogEntry(time, lEncDist, rEncDist, lVel, rVel, heading, xPos, yPos, vBat, totalCurrent));
	}

	/**
	 * 
	 * @param time
	 *            the timestamp of the desired LogEntry
	 * @return the closest entry to the time provided
	 */
	public LogEntry getEntry(double time) {
		LogEntry desired = null;
		for (LogEntry e : log) {
			if (e.time <= time) {
				e = desired;
			}
		}
		if (desired == null) {
			desired = log.get(0);
		}
		return desired;
	}

	/**
	 * 
	 * @return the most recent entry in the log
	 */
	public LogEntry getLastEntry() {
		return log.get(log.size() - 1);
	}

	/**
	 * 
	 * @param indexAgo
	 *            How many entries ago to look at
	 * @return LogEntry at index
	 */
	public LogEntry getEntryIndexAgo(int indexAgo) {
		return log.get(log.size() - indexAgo - 1);
	}
}
