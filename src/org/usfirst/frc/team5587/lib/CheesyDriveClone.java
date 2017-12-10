package org.usfirst.frc.team5587.lib;


public class CheesyDriveClone{

    double powerDeadband = 0.0;
    double curveDeadband = 0.0;
    double sensitivity = 1.0;
    
    public DriveSignal cheesyDrive( double power, double curve, boolean quickturn) {
        double angularPower;
        double leftOut, rightOut;
        power = handleDeadband(power, powerDeadband);
        curve = handleDeadband(curve, curveDeadband);

        if(quickturn){
            leftOut = rightOut = power;
            leftOut += curve;
            rightOut -= curve;

            if (leftOut > 1.0) {
                rightOut -= leftOut - 1.0;
                leftOut = 1.0;
            } else if (rightOut > 1.0) {
                leftOut -= rightOut - 1.0;
                rightOut = 1.0;
            } else if (leftOut < -1.0) {
                rightOut += -1.0 - leftOut;
                leftOut = -1.0;
            } else if (rightOut < -1.0) {
                leftOut += -1.0 - rightOut;
                rightOut = -1.0;
            }
        }
        else{
            angularPower = Math.abs(power) * curve * sensitivity;
            leftOut = rightOut = power;
            leftOut += angularPower;
            rightOut -= angularPower;
        }

        return DriveSignal(leftOut, rightOut);
    }

    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}