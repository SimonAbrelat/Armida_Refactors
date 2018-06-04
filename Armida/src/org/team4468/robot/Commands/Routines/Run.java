package org.team4468.robot.Commands.Routines;

import org.team4468.robot.Commands.Drive.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Run extends CommandGroup {

    public Run() {
        addParallel(new LeftDistance(2));
        addSequential(new RightDistance(2));
    }
}
