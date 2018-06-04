package org.team4468.robot.Subsystems;

import org.team4468.robot.Constants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RotatingLift extends Subsystem {
    
    private VictorSP lift = new VictorSP(Constants.lift);
    
    public AnalogPotentiometer pot = new AnalogPotentiometer(
            Constants.potPort,
            Constants.potRange,
            Constants.potOff
    ); 
    
    public void initDefaultCommand() {}
    
    public void set(double s) { lift.set(s); }
    
    public void stop() { lift.stopMotor(); }
}

