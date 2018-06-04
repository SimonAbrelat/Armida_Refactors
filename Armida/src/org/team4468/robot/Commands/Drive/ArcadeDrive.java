package org.team4468.robot.Commands.Drive;

import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.Drivetrain;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {

    private XboxController drvr = Robot.oi.drvr;
    private Drivetrain dt = Robot.drive;
    
    public ArcadeDrive() {
        requires(dt);
    }

    protected void execute() { dt.set(drvr.getX(Hand.kLeft), drvr.getY(Hand.kRight)); }

    protected boolean isFinished() { return false; }
}