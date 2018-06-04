package org.team4468.robot.Commands.Drive;

import org.team4468.robot.Constants;
import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.Drivetrain;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class RightDistance extends Command {

    private double distance;
    
    private Drivetrain dt = Robot.drive;
    private PIDController pid;
    private PIDOutput out;
    
    public RightDistance(double d) {
        requires(dt);
        
        distance = d;
        
        out = new PIDOutput() {
            @Override public void pidWrite(double d) { dt.setRight(d); }
        };
          
        pid = new PIDController(
                Constants.rightP, 
                Constants.rightI, 
                Constants.rightD, 
                dt.rightEncoder,
                out
        );
        
        pid.setPercentTolerance(1);
        pid.setOutputRange(-1, 1);
        pid.setContinuous();
    }

    protected void initialize() { pid.enable(); }

    protected void execute() { pid.setSetpoint(distance); }

    protected boolean isFinished() { return pid.onTarget(); }

    protected void end()         { pid.disable(); } 
    protected void interrupted() { pid.disable(); }
}
