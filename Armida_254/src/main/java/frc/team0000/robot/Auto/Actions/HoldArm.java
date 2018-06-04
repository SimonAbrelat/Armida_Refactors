package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.OneTimeAction;
import frc.team0000.robot.Subsystems.Arm.Position;

public class HoldArm extends OneTimeAction {
    
    private Position pos_;

    public HoldArm(Position pos){ pos_ = pos; }

    @Override
    public void oneTime(){ Robot.arm.setPosition(pos_); }
}