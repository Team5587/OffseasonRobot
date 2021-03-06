 package org.usfirst.frc.team5587.robot.subsystems;

import org.usfirst.frc.team5587.robot.Robot;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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
	public DifferentialDrive drivetrain;

	// Output Shaft to Encoder * Wheel Diameter * pi )
	//For each inch moved, multiply by the constant to get rotations of cim
	private double kDistLeft = -1 * 512d * 3d;
	private double kDistRight = -1 * 128d * 3d;	

	private double kPLow = 0;
	private double kILow = 0;
	private double kDLow = 0;
	private double kFLow = 0;

	private double kPHigh = 0;
	private double kIHigh = 0;
	private double kDHigh = 0;
	private double kFHigh = 0;

	public enum Gear {
		High(Value.kReverse), 
		Low(Value.kForward), 
		Disengaged(Value.kOff);

		public Value value;

		Gear(Value value){
			this.value = value;
		}
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

		drivetrain = new DifferentialDrive(leftFront, rightFront);
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

	public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
		drivetrain.curvatureDrive(xSpeed, Math.signum(xSpeed)*zRotation, isQuickTurn);
	}

	public void arcadeVelocityControl(double power, double curve) {
		
	}

	public void shiftInto(Gear g) {
		p.setSolenoid(p.shifter, g.value);
	}

	// public double getDistance(){
	// leftEncoder.set
	// }

	public Gear getGear() {
		if (p.shifter.get() == Value.kForward) {
			return Gear.High;
		} else if (p.shifter.get() == Value.kReverse) {
			return Gear.Low;
		} else {
			return Gear.Disengaged;
		}
	}

	public String getGearString() {
		if (p.shifter.get() == Value.kForward) {
			return "High";
		} else if (p.shifter.get() == Value.kReverse) {
			return "Low";
		} else {
			return "Disengaged";
		}
	}

	public void printEncoders(){
		System.out.println("Left Encoder Pos:" + leftFront.getSelectedSensorPosition(0) + " Vel: " + leftFront.getSelectedSensorVelocity(0));
		System.out.println("Right Encoder Pos:" + rightFront.getSelectedSensorPosition(0) + " Vel: " + rightFront.getSelectedSensorVelocity(0));
	}

	public void graphEncoders(){
		SmartDashboard.putNumber("Left Encoder Pos: ", leftFront.getSelectedSensorPosition(0)/kDistLeft);
		SmartDashboard.putNumber("Left Encoder Vel: ", leftFront.getSelectedSensorVelocity(0)/kDistLeft);
		SmartDashboard.putNumber("Right Encoder Pos: ", rightFront.getSelectedSensorPosition(0)/kDistRight);
		SmartDashboard.putNumber("Right Encoder Vel: ", rightFront.getSelectedSensorVelocity(0)/kDistRight);
	}

	public void showCurrents(){
		SmartDashboard.putNumber("PDP 0: ", Robot.pdp.getCurrent(0));
		SmartDashboard.putNumber("PDP 1: ", Robot.pdp.getCurrent(1));
		SmartDashboard.putNumber("PDP 2: ", Robot.pdp.getCurrent(2));
		SmartDashboard.putNumber("PDP 3: ", Robot.pdp.getCurrent(3));
	}

	public void setEncoders(int pos) {
		setEncoders(pos, pos);
	}

	public void setEncoders(int left, int right) {
		leftFront.setSelectedSensorPosition(left, 0, 0);
		rightFront.setSelectedSensorPosition(right, 0, 0);
	}

	/**
	public void startCalibration(double calDist){
		leftStartDist = leftFront.getEncPosition();
		rightStartDist = rightFront.getEncPosition();
		this.calDist = calDist;
		this.calGear = getGear();
	}
	public void stopCalibration(){
		leftDiff = leftFront.getEncPosition()-leftStartDist;
		rightDiff = rightFront.getEncPosition()-rightStartDist;
		System.out.println( "Gear calibrated: " + calGear.toString() + " Left: " + leftDiff/calDist + "Right: " + rightDiff/calDist );
	}
	*/
}
