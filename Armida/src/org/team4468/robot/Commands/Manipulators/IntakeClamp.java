package org.team4468.robot.Commands.Manipulators;

import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.Intake;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeClamp extends Command {

    private Intake in = Robot.intake;
    private Value target;
    
    public IntakeClamp(Value v) {
        requires(in);
        
        target = v;
    }

    protected void execute() { in.setClamp(target); }
    
    protected boolean isFinished() { return target == in.clamp.get(); }
}
