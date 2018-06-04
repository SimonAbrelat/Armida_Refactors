package frc.team0000.robot.Auto.Actions;

import frc.team0000.robot.Robot;
import frc.team0000.robot.Lib.Actions.OneTimeAction;
import frc.team0000.robot.Subsystems.Intake.Speed;

public class IntakeSpeed extends OneTimeAction {
    private Speed speed_;
    
    public IntakeSpeed(Speed speed){ speed_ = speed; }

    @Override
    public void oneTime(){ Robot.intake.setSpeed(speed_); }
}