package org.usfirst.frc.team5587.lib;

import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;

public class Shifting {

	Drivetrain d;

	public Shifting(Drivetrain d) {
		this.d = d;
	}

	Drivetrain.gear g = d.getGear();
}
