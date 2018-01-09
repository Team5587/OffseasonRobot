package org.usfirst.frc.team5587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5587.robot.OI;
import org.usfirst.frc.team5587.robot.Robot;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain.gear;


public class SplitJoystickDrive extends Command {
	OI oi;
	Drivetrain drivetrain;
	private double kSense = .75;
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
		SmartDashboard.putNumber("kSense", .3);
		System.out.println("Starting Teleop");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		kSense = SmartDashboard.getNumber("kSense", 0.3);
		drivetrain.arcadeDrive( -oi.stick.getRawAxis(1)*kSense, -oi.stick.getRawAxis(0)*(kSense*1.3), false);
		
		if( oi.stick.getRawButton(5) && oi.stick.getRawButton(6) ){
			drivetrain.shiftInto( gear.Disengaged );
			System.out.println("Shifted to Disengaged");
		}
		else if( oi.stick.getRawButton(5) ){
			drivetrain.shiftInto( gear.Low );
			System.out.println("Shifted to Low");
		}
		else if( oi.stick.getRawButton(6) ){
			drivetrain.shiftInto( gear.High );
			System.out.println("Shifted to High");
		}
		else{}

		//drivetrain.showCurrents();
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
