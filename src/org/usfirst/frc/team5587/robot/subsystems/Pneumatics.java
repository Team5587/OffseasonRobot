package org.usfirst.frc.team5587.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team5587.robot.commands.PneumaticsOverhead;
import org.usfirst.frc.team5587.robot.RobotMap;

/**
 *
 */
public class Pneumatics extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    private Compressor comp;
    public DoubleSolenoid shifter;

    public Pneumatics(){
        comp = new Compressor(0);
        shifter = new DoubleSolenoid(RobotMap.LEFT_SHIFTER_A, RobotMap.LEFT_SHIFTER_B);
    }

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
        setDefaultCommand( new PneumaticsOverhead() );
	}

    public void enableClosedLoop(boolean enabled){
        comp.setClosedLoopControl( enabled );
    }

    public void setSolenoid(Solenoid s, boolean value){
        s.set( value );
    }
    public void setSolenoid(DoubleSolenoid s, Value value){
        s.set( value );
    }
    public void setSolenoid(DoubleSolenoid s, int value){
        switch(value){
            case -1: 
                s.set(Value.kReverse);
                break;
            case 0: 
                s.set(Value.kOff);
                break;
            case 1: 
                s.set(Value.kForward);
                break;
        }
    }
}
