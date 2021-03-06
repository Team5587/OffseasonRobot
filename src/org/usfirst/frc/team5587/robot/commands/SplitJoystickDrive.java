package org.usfirst.frc.team5587.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5587.robot.OI;
import org.usfirst.frc.team5587.robot.Robot;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5587.robot.subsystems.Drivetrain.Gear;
import org.usfirst.frc.team5587.robot.subsystems.Pneumatics;


public class SplitJoystickDrive extends Command {
	OI oi;
	private Drivetrain drivetrain;
	private Pneumatics p;
	private double mSense = .75;
	public SplitJoystickDrive() {
		// Use requires() here to declare subsystem dependencies
		//requires(Robot.exampleSubsystem);
		requires(Robot.drivetrain);
		drivetrain = Robot.drivetrain;
		p = Robot.pneumatics;
		oi = Robot.oi;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		SmartDashboard.putNumber("kSense", 1);
		System.out.println("Starting Teleop");
		SmartDashboard.putData("Reset Encoders", new ResetEncoders());
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		mSense = SmartDashboard.getNumber("Sensitivity", 1.0);
		drivetrain.curvatureDrive( -oi.stick.getRawAxis(1)*mSense, oi.stick.getRawAxis(4)*mSense, false);
		
		if( oi.stick.getRawButton(5) && oi.stick.getRawButton(6) ){
			drivetrain.shiftInto( Gear.Disengaged );
			System.out.println("Shifted to Disengaged");
		}
		else if( oi.stick.getRawButton(5) ){
			drivetrain.shiftInto( Gear.Low );
			System.out.println("Shifted to Low");
		}
		else if( oi.stick.getRawButton(6) ){
			drivetrain.shiftInto( Gear.High );
			System.out.println("Shifted to High");
		}
		else{}

		drivetrain.graphEncoders();

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
