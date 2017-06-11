package org.usfirst.frc.team5587.robot.subsystems;


import org.usfirst.frc.team5587.robot.Robot;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5587.robot.RobotMap;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    private CANTalon leftFront, leftBack, rightFront, rightBack;
    private Encoder leftEncoder, rightEncoder;
    private Pneumatics p;
    public RobotDrive drivetrain;

    // 1 / ( Correction constant * Stage 1 * Stage 2 * Wheel Diameter * pi ) 
    private double kDistHighGear = 1 / (40/12D * 44/40D * 6 * Math.PI);
    private double kDistLowGear = 1 / (40/12D * 60/24D * 6 * Math.PI);

    public enum gear {
    	High, Low, Disengaged
    }
    
    gear leftGear, rightGear;
    
    public Drivetrain(){
        leftFront = new CANTalon( RobotMap.LEFT_FRONT );
        leftBack = new CANTalon( RobotMap.LEFT_BACK );
        rightFront = new CANTalon( RobotMap.RIGHT_FRONT );
        rightBack = new CANTalon( RobotMap.RIGHT_BACK );

        leftEncoder = new Encoder( RobotMap.LEFT_ENCODER, RobotMap.LEFT_ENCODER+1 );
        rightEncoder = new Encoder( RobotMap.RIGHT_ENCODER, RobotMap.RIGHT_ENCODER+1 );
        
        p = Robot.pneumatics;
        
        drivetrain = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
    }

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

    public void arcadeDrive( double power, double curve ){
        drivetrain.arcadeDrive( power, curve );
    }

    public void arcadeVelocityControl( double power, double curve){
    	
    }
    
    public void shiftInto(gear g){
    	Value v = (g == gear.High) ? Value.kForward : Value.kReverse;
    	p.setSolenoid( p.shifter, v );
    }
    
    
//    public double getDistance(){
//    	leftEncoder.set
//    }

	public gear getGear() {
		if(p.shifter.get() == Value.kForward){
			return gear.High;
		}
		else if(p.shifter.get() == Value.kReverse){
			return gear.Low;
		}
		else{
			return gear.Disengaged;
		}
	}
}