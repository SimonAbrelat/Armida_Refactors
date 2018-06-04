package org.team4468.robot.Commands.Drive;

import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.Drivetrain;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class Shift extends Command {

    private Drivetrain dt = Robot.drive;
    private Value target;
    
    public Shift(Value v) {
        requires(dt);
        
        target = v;
    }

    protected void execute() { dt.shift(target); }
    
    protected boolean isFinished() { return target == dt.shift.get(); }
}