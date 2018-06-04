package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Constants;
import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.Action;
import frc.team0000.robot.Lib.Control.PIDF;

public class Forward implements Action {

    private static PIDF pid = new PIDF(
        Constants.lineP, 
        Constants.lineI, 
        Constants.lineD, 
        Constants.lineF
    );

    private double distance_;
    private double tolerance_;


    public Forward(double distance, double tolerance){
        distance_  = distance;
        tolerance_ = tolerance;
    }

    @Override 
    public void start(){ pid.setSetpoint(distance_); }

    @Override
    public void update(){ 
        double speed = pid.calculate(Robot.drivetrain.getAveDistance());
        Robot.drivetrain.setClosed(speed, speed);
    }

    @Override
    public boolean isFinished(){ return pid.onTarget(tolerance_); }

    @Override
    public void done(){ Robot.drivetrain.zeroEncoders(); }
}