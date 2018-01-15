package org.usfirst.frc.team5587.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private I2C arduino;

	public LEDSubsystem(){
		arduino = new I2C(Port.kMXP, 8);
	}

	public void sendRGB(int r, int g, int b){
		byte[] color = new byte[3];

		//These bytes are SIGNED (-127,127) they are converted to unsigned later by the arduino
		color[1] = (byte)r;
		color[2] = (byte)g;
		color[3] = (byte)b;
		byte[] received = {(byte)0}; //declared and initailized but never used, as null can not be passed as a parameter. This value is never used.
		arduino.transaction(color, 3, received, 0);
	}

	public void initDefaultCommand() {
	}
}
