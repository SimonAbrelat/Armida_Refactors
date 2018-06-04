package frc.team0000.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team0000.robot.Constants;
import frc.team0000.robot.Lib.Loop;
import frc.team0000.robot.Lib.Looper;
import frc.team0000.robot.Lib.Subsystem;

public class Intake implements Subsystem {

    private static double kHI_IN = 1;
    private static double kHI_OUT = -1;
    private static double kLOW_IN = 0.5;
    private static double kLOW_OUT = -0.5;

    private VictorSP left_  = new VictorSP(Constants.intakePort1);
    private VictorSP right_ = new VictorSP(Constants.intakePort2);

    private DoubleSolenoid clamp_ = new DoubleSolenoid(Constants.intakeClampPort1, Constants.intakeClampPort2);

    public enum Speed {
        OFF,
        HI_IN,
        HI_OUT,
        LOW_IN,
        LOW_OUT,
    }

    enum Clamp {
        CLAMPED,
        UNCLAMPED,
    }

    private Speed state_ = Speed.OFF;
    private Clamp target_ = Clamp.UNCLAMPED;

    private Loop loop_ = new Loop() {
        @Override public void start(){
            synchronized(Intake.this){
                clamp_.set(Value.kReverse);
                state_ = Speed.OFF;
            }
        }

        @Override public void update(){
            synchronized(Intake.this){
                Clamp tmp = (clamp_.get() == Value.kForward) ? Clamp.CLAMPED : Clamp.UNCLAMPED;
                if(target_ != tmp){
                    if(target_ == Clamp.CLAMPED){ clamp_.set(Value.kForward); }
                    else                        { clamp_.set(Value.kReverse); }
                }

                switch(state_){
                case OFF    : setMotors(0);
                case HI_IN  : setMotors(kHI_IN);
                case HI_OUT : setMotors(kHI_OUT);
                case LOW_IN : setMotors(kLOW_IN);
                case LOW_OUT: setMotors(kLOW_OUT);
                }
            }
        }

        @Override public void stop(){ Intake.this.stop(); }
    };

    public Intake(){ left_.setInverted(true); }

    public synchronized void setClamp(boolean target){ target_ = target ? Clamp.CLAMPED : Clamp.UNCLAMPED; }
    public synchronized void setSpeed(Speed target)  { state_  = target; }

    private void setMotors(double speed){
        left_ .set(speed);
        right_.set(speed);
    }

    @Override
    public void zero(){}

    @Override
    public void stop(){
        clamp_.set(Value.kReverse);
        left_ .stopMotor();
        right_.stopMotor();
    }

    @Override 
    public void registerLoop(Looper looper){ looper.register(loop_); }

    @Override
    public void output(){
        SmartDashboard.putNumber("Left Motor Speed" , left_ .getSpeed());
        SmartDashboard.putNumber("Right Motor Speed", right_.getSpeed());
        SmartDashboard.putBoolean("Clamped?", clamp_.get() == Value.kForward);
    }
}