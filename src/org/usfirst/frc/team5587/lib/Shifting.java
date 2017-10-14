package org.usfirst.frc.team5587.lib;

import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain.Gear;

public class Shifting {

	Drivetrain d;

	public Shifting(Drivetrain d) {
		this.d = d;
	}

	Gear g = d.getGear();
}
