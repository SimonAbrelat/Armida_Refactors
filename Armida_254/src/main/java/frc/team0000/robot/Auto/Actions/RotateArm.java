package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.Action;
import frc.team0000.robot.Subsystems.Arm.Position;

public class RotateArm implements Action {
    
    private Position pos_;
    private double tolerance_;
    
    public RotateArm(Position angle, double tolerance){
        pos_ = angle;
        tolerance_ = tolerance;
    }

    @Override
    public void start(){}

    @Override
    public void update(){ Robot.arm.setPosition(pos_); }

    @Override
    public boolean isFinished(){ return Robot.arm.atPosition(tolerance_); }

    @Override
    public void done(){ Robot.arm.setPosition(Position.OFF); }
}