package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Constants;
import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.Action;
import frc.team0000.robot.Lib.Control.PIDF;

public class Turn implements Action {
    
    private PIDF pid = new PIDF(
        Constants.angleP, 
        Constants.angleI, 
        Constants.angleD, 
        Constants.angleF
    );
    
    private double angle_;
    private double tolerance_;

    public Turn(double angle, double tolerance){
        angle_ = angle;
        tolerance_ = tolerance;
    }

    @Override
    public void start(){ pid.setSetpoint(angle_); }

    @Override
    public void update(){
        double speed = pid.calculate(Robot.drivetrain.getAngPos());
        Robot.drivetrain.setClosed(speed, -speed);
    }

    @Override
    public boolean isFinished(){ return pid.onTarget(tolerance_); }

    @Override
    public void done(){}
}