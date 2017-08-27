package org.usfirst.frc.team5587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5587.robot.Robot;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain.gear;

/**
 *
 */
public class ShiftInto extends Command {
	Drivetrain d;
	gear g;

	public ShiftInto(gear g) {
		// Use requires() here to declare subsystem dependencies
		// requires(Robot.exampleSubsystem);
		d = Robot.drivetrain;
		this.g = g;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		d.shiftInto(g);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
