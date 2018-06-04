package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.OneTimeAction;
import frc.team0000.robot.Subsystems.Intake.Speed;

public class Expel extends OneTimeAction {

    private Speed speed_;
    private boolean clamp_;

    public Expel(Speed s, boolean c){ 
        speed_ = s;
        clamp_ = c;
     }

    public void oneTime(){ 
        Robot.intake.setClamp(clamp_);
        Robot.intake.setSpeed(speed_);
     }
}