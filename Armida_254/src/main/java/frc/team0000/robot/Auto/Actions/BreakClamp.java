package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.OneTimeAction;

public class BreakClamp extends OneTimeAction {
    private boolean break_;
    
    public BreakClamp(boolean breaking){ break_ = breaking; }

    @Override
    public void oneTime(){ Robot.arm.setBreak(break_); }
}