package org.usfirst.frc.team5587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5587.robot.OI;
import org.usfirst.frc.team5587.robot.Robot;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;


/**
 *
 */

public class SplitJoystickDrive extends Command {
	OI oi;
	Drivetrain drivetrain;
	public SplitJoystickDrive() {
		// Use requires() here to declare subsystem dependencies
		//requires(Robot.exampleSubsystem);
		requires(Robot.drivetrain);
		drivetrain = Robot.drivetrain;
		oi = Robot.oi;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		drivetrain.arcadeDrive( -oi.stick.getRawAxis(1), -oi.stick.getRawAxis(4));
		if( oi.stick.getRawButton(4) && oi.stick.getRawButton(5) ){
			drivetrain.shiftInto( drivetrain.gear.Disengaged );
		}
		else if( oi.stick.getRawButton(4) ){
			drivetrain.shiftInto( drivetrain.gear.Low );
		}
		else if( oi.stick.getRawButton(5) ){
			drivetrain.shiftInto( drivetrain.gear.High );
		}
		else{}
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
