package frc.team0000.robot.Subsystems;

import frc.team0000.robot.Lib.Subsystem;
import frc.team0000.robot.Lib.Control.PIDF;
import frc.team0000.robot.Lib.Loop;
import frc.team0000.robot.Lib.Looper;
import frc.team0000.robot.Constants;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Arm implements Subsystem {

    private double zero_ = 0;

    private static double kIntakeAng = 10;
    private static double kPortalAng = 50;
    private static double kOutputAng = 140;

    private VictorSP motor_ = new VictorSP(Constants.armPort1);
	
	private AnalogPotentiometer pot_ = new AnalogPotentiometer(
	        Constants.potPort, 
	        Constants.potRange, 
	        Constants.potOff
	);
	
    private DoubleSolenoid break_ = new DoubleSolenoid(Constants.BreakClampPort1, Constants.BreakClampPort2);

    private PIDF pid = new PIDF(
        Constants.armP, 
        Constants.armI, 
        Constants.armD, 
        Constants.armF
    );

    private Loop loop_ = new Loop(){
        @Override public void start(){
            synchronized(Arm.this){ setBreak(true); }
        }

        @Override public void update(){
            synchronized(Arm.this){
                Break tmp = (break_.get() == Value.kForward) ? Break.BREAKED : Break.FREE;
                if(tmp != clamp_){
                    if(clamp_ == Break.BREAKED){ break_.set(Value.kForward); }
                    else                       { break_.set(Value.kReverse); }
                }

                switch(target_){
                case OFF:
                    motor_.set(0);
                    break;
                case ZERO  : pid.setSetpoint(0);
                case INTAKE: pid.setSetpoint(kIntakeAng);
                case PORTAL: pid.setSetpoint(kPortalAng);
                case OUTPUT: pid.setSetpoint(kOutputAng);
                default    : motor_.set(pid.calculate(getAngle()));
                }
            }
        }

        @Override public void stop(){ Arm.this.stop(); }
    };

    enum Break {
        FREE,
        BREAKED,       
    }

    public enum Position {
        OFF,
        ZERO,
        INTAKE,
        PORTAL,
        OUTPUT,
    }

    private Break clamp_ = Break.FREE;
    private Position target_ = Position.OFF;

    public Arm(){}

    public synchronized void setBreak(boolean target){ clamp_ = target ? Break.BREAKED : Break.FREE; }

    public synchronized void setPosition(Position target){ target_ = target; }

    public synchronized boolean atPosition(double tolerance) { return pid.onTarget(tolerance); }

    private double getAngle(){ return pot_.get() - zero_; }

    public void zeroPot(){ zero_ = pot_.get(); }

    @Override
    public void zero(){
        //zero_ = pot_.get();
    }

    @Override
    public void stop(){
        target_ = Position.OFF;
        motor_.stopMotor();
    }

    @Override
    public void registerLoop(Looper looper){ looper.register(loop_); }

    @Override
    public void output(){
        SmartDashboard.putNumber("Angular Position", getAngle());
        SmartDashboard.putBoolean("Breaked?", break_.get() == Value.kForward);
    }
}