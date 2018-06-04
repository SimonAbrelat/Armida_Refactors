package frc.team0000.robot.Auto.Routines;

import frc.team0000.robot.Auto.Actions.Forward;
import frc.team0000.robot.Auto.Actions.HoldArm;
import frc.team0000.robot.Auto.Actions.IntakeClamp;
import frc.team0000.robot.Auto.Actions.IntakeSpeed;
import frc.team0000.robot.Auto.Actions.RotateArm;
import frc.team0000.robot.Lib.Actions.AutoEndedException;
import frc.team0000.robot.Lib.Actions.ParallelAction;
import frc.team0000.robot.Lib.Actions.Routine;
import frc.team0000.robot.Lib.Actions.SeriesAction;
import frc.team0000.robot.Subsystems.Arm.Position;
import frc.team0000.robot.Subsystems.Intake.Speed;

public class LineScore extends Routine {
    public LineScore(){}

    @Override
    public void routine() throws AutoEndedException{
        addAction(new IntakeClamp(true));

        addAction(new SeriesAction(new Forward(-2.5, 0.5),
                                   new RotateArm(Position.OUTPUT, 20.0)));

        addAction(new ParallelAction(new HoldArm(Position.OUTPUT),
                  new SeriesAction  (new IntakeSpeed(Speed.LOW_OUT),
                                     new IntakeClamp(false))));
    }
}