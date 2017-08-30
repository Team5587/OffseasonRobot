package org.usfirst.frc.team5587.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5587.robot.Robot;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;

/**
 *
 */
public class DriveStraightForever extends Command {
	
	Drivetrain drivetrain;
	
	public DriveStraightForever() {
		// Use requires() here to declare subsystem dependencies
		// requires(Robot.exampleSubsystem);
		drivetrain = Robot.drivetrain;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		drivetrain.arcadeDrive(0.5, 0.0);
		drivetrain.printEncoders();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
