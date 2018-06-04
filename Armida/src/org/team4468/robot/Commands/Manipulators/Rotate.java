package org.team4468.robot.Commands.Manipulators;

import org.team4468.robot.Constants;
import org.team4468.robot.Robot;
import org.team4468.robot.Subsystems.RotatingLift;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command {
    
    private double theta;
    
    private RotatingLift rl = Robot.lift;
    private PIDController pid;
    private PIDOutput out;
    
    public Rotate(double t) {
        requires(rl);
        
        theta = t;
        
        out = new PIDOutput() {
            @Override public void pidWrite(double d) { rl.set(d); }
        };
          
        pid = new PIDController(
                Constants.liftP, 
                Constants.liftI, 
                Constants.liftD, 
                rl.pot, 
                out
        );
        
        pid.setPercentTolerance(1);
        pid.setOutputRange(-1, 1);
        pid.setContinuous();
    }

    public void set(double t) { theta = t; }
    
    protected void initialize() { pid.enable(); }

    protected void execute() { pid.setSetpoint(theta); }

    protected boolean isFinished() { return !(Robot.oi.ctrl.getAButton() || 
                                              Robot.oi.ctrl.getBButton() ||
                                              Robot.oi.ctrl.getXButton() || 
                                              Robot.oi.ctrl.getYButton()); }

    protected void end()         { pid.disable(); } 
    protected void interrupted() { pid.disable(); }
}