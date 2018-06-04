package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.OneTimeAction;

public class IntakeClamp extends OneTimeAction {
    private boolean clamp_;

    public IntakeClamp(boolean c){ clamp_ = c;}

    @Override
    public void oneTime(){ Robot.intake.setClamp(clamp_); }
}