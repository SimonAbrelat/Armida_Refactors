package org.team4468.robot.Subsystems;

import org.team4468.robot.Constants;
import org.team4468.robot.Commands.Drive.ArcadeDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
    
    private SpeedControllerGroup left = new SpeedControllerGroup(
            new VictorSP(Constants.leftPair),
            new VictorSP(Constants.leftBot)
    );
    
    private SpeedControllerGroup right = new SpeedControllerGroup(
            new VictorSP(Constants.rightPair),
            new VictorSP(Constants.rightBot)
    );
    
    public Encoder leftEncoder = new Encoder(
            Constants.leftEnc1,
            Constants.leftEnc2,
            Constants.leftEncInverted,
            EncodingType.k4X
    );
    
    public Encoder rightEncoder = new Encoder(
            Constants.rightEnc1,
            Constants.rightEnc2,
            Constants.rightEncInverted,
            EncodingType.k4X
    );
    
    public DoubleSolenoid shift = new DoubleSolenoid(Constants.shifterPort1, Constants.shifterPort2);

    public Drivetrain() { left.setInverted(true); }

    public void initDefaultCommand() { setDefaultCommand(new ArcadeDrive()); }
    
    public void set(double angle, double speed) {
        if(angle < Constants.deadband) { angle = 0; }
        if(speed < Constants.deadband) { speed = 0; }
        angle = curve(angle);
        speed = curve(speed);
        double maxInput = Math.copySign(Math.max(Math.abs(speed), Math.abs(angle)), speed);

        if (speed >= 0.0) {
            if (angle >= 0.0) {
                left.set(maxInput);
                right.set(speed-angle);
            } else {
                left.set(speed+angle);
                right.set(maxInput);
            }
        } else {
            if (angle >= 0.0) {
                left.set(speed+angle);
                right.set(maxInput);
            } else {
                left.set(maxInput);
                right.set(speed-angle);
            }
        }
    }
    
    private double curve(double x) { return Math.copySign(x*x, x); }
    public void setLeft (double s) { left. set(s); }
    public void setRight(double s) { right.set(s); }
    
    public void stop() {
        left .stopMotor();
        right.stopMotor();
    }
    
    public void shift(Value v) { shift.set(v); }
}