package org.team4468.robot.Commands.Manipulators;

import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.RotatingLift;

import edu.wpi.first.wpilibj.command.Command;

public class ManualRotate extends Command {

    private double speed;
    
    private RotatingLift rl = Robot.lift;
    
    public ManualRotate(double s) {
        requires(rl);
        
        speed = s;
    }

    protected void execute() { rl.set(speed); }

    protected boolean isFinished() { return !(Robot.oi.ctrl.getAButton() || Robot.oi.ctrl.getYButton()); }

    protected void end()         { rl.stop(); }
    protected void interrupted() { rl.stop(); }
}
