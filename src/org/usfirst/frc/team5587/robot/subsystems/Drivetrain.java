package org.usfirst.frc.team5587.robot.subsystems;

import org.usfirst.frc.team5587.robot.Robot;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team5587.robot.RobotMap;
import org.usfirst.frc.team5587.robot.subsystems.Pneumatics;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private WPI_TalonSRX leftFront, leftBack, rightFront, rightBack;
	private Pneumatics p;
	public RobotDrive drivetrain;

	// 1 / ( Correction constant * Stage 1 * Stage 2 * Wheel Diameter * pi )
	private double kDistHighGear = 1 / (40 / 12D * 44 / 40D * 6 * Math.PI);
	private double kDistLowGear = 1 / (40 / 12D * 60 / 24D * 6 * Math.PI);

	private double kMaxVelocity = 0;

	public enum gear {
		High, Low, Disengaged
	}

	public Drivetrain() {
		leftFront = new WPI_TalonSRX(RobotMap.LEFT_FRONT);
		leftBack = new WPI_TalonSRX(RobotMap.LEFT_BACK);

		rightFront = new WPI_TalonSRX(RobotMap.RIGHT_FRONT);
		rightBack = new WPI_TalonSRX(RobotMap.RIGHT_BACK);

		leftFront.configSelectedFeedbackSensor( FeedbackDevice.QuadEncoder, 0, 0);
		rightFront.configSelectedFeedbackSensor( FeedbackDevice.QuadEncoder, 0, 0);

		leftBack.set(ControlMode.Follower, leftFront.getDeviceID());
		rightBack.set(ControlMode.Follower, rightFront.getDeviceID());

		p = Robot.pneumatics;

		drivetrain = new RobotDrive(leftFront, rightFront);
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void arcadeDrive(double power, double curve) {
		drivetrain.arcadeDrive(power, curve);
	}

	public void arcadeDrive(double power, double curve, boolean squaredInputs) {
		drivetrain.arcadeDrive(power, curve, squaredInputs);
	}

	public void arcadeVelocityControl(double power, double curve) {
		
	}

	public void shiftInto(gear g) {
		Value v = Value.kOff;
	       	switch(g){
		case High:
			v = Value.kForward;
			break;
		case Low:
			v = Value.kReverse;
			break;
		case Disengaged:
			v = Value.kOff;
			break;
		}
		p.setSolenoid(p.shifter, v);
	}

	// public double getDistance(){
	// leftEncoder.set
	// }

	public gear getGear() {
		if (p.shifter.get() == Value.kForward) {
			return gear.High;
		} else if (p.shifter.get() == Value.kReverse) {
			return gear.Low;
		} else {
			return gear.Disengaged;
		}
	}

	public void printEncoders(){
		System.out.println("Left Encoder Pos:" + leftFront.getSelectedSensorPosition(0) + " Vel: " + leftFront.getSelectedSensorVelocity(0));
		System.out.println("Right Encoder Pos:" + rightFront.getSelectedSensorPosition(0) + " Vel: " + rightFront.getSelectedSensorVelocity(0));
	}

	public void showCurrents(){
		SmartDashboard.putNumber("PDP 0: ", Robot.pdp.getCurrent(0));
		SmartDashboard.putNumber("PDP 1: ", Robot.pdp.getCurrent(1));
		SmartDashboard.putNumber("PDP 2: ", Robot.pdp.getCurrent(2));
		SmartDashboard.putNumber("PDP 3: ", Robot.pdp.getCurrent(3));
	}
}
