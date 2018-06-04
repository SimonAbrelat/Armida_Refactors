package frc.team0000.robot.Subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.team0000.robot.Constants;
import frc.team0000.robot.Lib.Subsystem;
import frc.team0000.robot.Lib.Loop;
import frc.team0000.robot.Lib.Looper;


public class Drivetrain implements Subsystem {

    private SpeedControllerGroup left_ = new SpeedControllerGroup(
        new VictorSP(Constants.leftBot),
        new VictorSP(Constants.leftPair)
    );

    private SpeedControllerGroup right_ = new SpeedControllerGroup(
        new VictorSP(Constants.rightBot),
        new VictorSP(Constants.rightPair)
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

    private DoubleSolenoid shift_ = new DoubleSolenoid(Constants.shifterPort1, Constants.shifterPort2);

    private Drivetrain instance_ = new Drivetrain();

    private AHRS gyro_ = new AHRS(Constants.gyroPort);

    private Loop loop_ = new Loop(){
        @Override public void start(){
            synchronized(Drivetrain.this){
                setOpen(0,0);
                zero();
            }
        }

        @Override public void update(){
            synchronized(Drivetrain.this){
                Gear tmp = (shift_.get() == Value.kForward) ? Gear.HIGH : Gear.LOW;

                if(gear_ != tmp){
                    if(gear_ == Gear.HIGH){ shift_.set(Value.kForward); }
                    else                  { shift_.set(Value.kReverse); }
                }
                if(state_ == State.CLOSED){
                    if(gear_ == Gear.HIGH){ shift_.set(Value.kForward); }
                }

                left_ .set(leftSpeed_);
                right_.set(rightSpeed_);
            }
        }

        @Override public void stop(){ Drivetrain.this.stop(); }
    };

    enum State {
        OPEN,
        CLOSED,
    };

    enum Gear {
        HIGH,
        LOW,
    }
    
    private State state_ = State.OPEN;
    private Gear gear_ = Gear.HIGH;

    private double leftSpeed_ = 0;
    private double rightSpeed_ = 0;

    public Drivetrain(){ left_.setInverted(true); }

    public Drivetrain getInstance() { return instance_; }

    public double getAngPos(){ 
        if(gyro_.isConnected()){ throw new NullPointerException("Gyro is not connected"); }
        return gyro_.getAngle();
    }

    public double getAngVel(){
        if(gyro_.isConnected()){ throw new NullPointerException("Gyro is not connected"); }
        return gyro_.getRate();
    }

    public void zeroGyro(){ gyro_.zeroYaw(); }
    
    public void zeroEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getAveDistance(){
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
    }

    public synchronized void setOpen(double left, double right){
        state_ = State.OPEN;
        leftSpeed_  = left;
        rightSpeed_ = right;
    }

    public synchronized void setClosed(double left, double right){
        state_ = State.CLOSED;
        leftSpeed_  = left;
        rightSpeed_ = right;
    }

    public synchronized void setGear(boolean high){
        if(high) { gear_ = Gear.HIGH; }
        else     { gear_ = Gear.LOW;  }
    }

    @Override
    public void stop(){
        left_ .stopMotor();
        right_.stopMotor();
    }

    @Override
    public void zero(){
        zeroGyro();
        zeroEncoders();
    }

    @Override
    public void registerLoop(Looper looper) { looper.register(loop_); }

    @Override
    public void output() {
        SmartDashboard.putNumber("Left Velocity",  leftEncoder .getRate());
        SmartDashboard.putNumber("Right Velocity", rightEncoder.getRate());
        SmartDashboard.putNumber("Left Distance",  leftEncoder .getDistance());
        SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
        SmartDashboard.putNumber("Angular Position", getAngPos());
        SmartDashboard.putNumber("Angular Velocity", getAngVel());
    }
}