package org.usfirst.frc.team5587.lib;

/**
 * 
 * @author drew
 * @version 6/11/17
 */
public class LogEntry {

	public double time, lEncDist, rEncDist, lVel, rVel,
		heading, xPos, yPos, vBat, totalCurrent;
	/**
	 * A LogEntry holds the state of the robot at a certain time
	 * @param time The FPGA timestamp
	 * @param lEncDist The current distance that the left side of the 
	 * drivetrain has traveled
	 * @param rEncDist The current distance that the right side of the 
	 * drivetrain has traveled
	 * @param lVel speed of the left side of the drivetrain
	 * @param rVel speed of the right side of the drivetrain
	 * @param heading angle that the robot is facing
	 * @param xPos robot position on the x axis (odometry yet to be implemented)
	 * @param yPos robot position on the y axis (odometry yet to be implemented)
	 * 
	 */
	public LogEntry(double time, double lEncDist, double rEncDist, double lVel, 
			double rVel, double heading, double xPos, double yPos, double vBat,
			double totalCurrent){
		this.time = time;
		this.lEncDist = lEncDist;
		this.rEncDist = rEncDist;
		this.lVel = lVel;
		this.rVel = rVel;
		this.heading = heading;
		this.xPos = xPos;
		this.yPos = yPos;
		this.vBat = vBat;
		this.totalCurrent = totalCurrent;
	}
}
