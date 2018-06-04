package org.team4468.robot.Commands.Manipulators;

import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeSpeed extends Command {

    private double speed;
    
    private Intake in = Robot.intake;
    
    public IntakeSpeed(double s) {
        requires(in);
        
        speed = s;
    }

    protected void execute() { in.setSpeed(speed); }

    protected boolean isFinished() { return false; }

    protected void end()         { in.stop(); }
    protected void interrupted() { in.stop(); }
}
