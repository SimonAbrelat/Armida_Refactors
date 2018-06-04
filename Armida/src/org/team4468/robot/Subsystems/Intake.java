package org.team4468.robot.Subsystems;

import org.team4468.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
    
    private VictorSP left  = new VictorSP(Constants.intakePort1);
    private VictorSP right = new VictorSP(Constants.intakePort2);
    
    public DoubleSolenoid clamp = new DoubleSolenoid(Constants.intakeClampPort1, Constants.intakePort2);

    public Intake() { left.setInverted(true);}

    public void initDefaultCommand() {}
    
    public void setSpeed(double s) {
        left .set(s);
        right.set(s);
    }
    
    public void stop() {
        left .stopMotor();
        right.stopMotor();
    }
    
    public void setClamp(Value v) { clamp.set(v); }
}