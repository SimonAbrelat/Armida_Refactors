package frc.team0000.robot.Auto.Routines;

import frc.team0000.robot.Auto.Actions.Forward;
import frc.team0000.robot.Lib.Actions.AutoEndedException;
import frc.team0000.robot.Lib.Actions.Routine;

public class BaseLine extends Routine {
    public BaseLine(){}

    @Override
    public void routine() throws AutoEndedException {
        addAction(new Forward(-3.5, 0.05));
    }
}