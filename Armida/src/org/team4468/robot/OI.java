/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team4468.robot;

import org.team4468.robot.Commands.Manipulators.*;
import org.team4468.robot.Commands.Drive.Shift;
import org.team4468.robot.Commands.Manipulators.IntakeClamp;
import org.team4468.robot.Commands.Manipulators.IntakeSpeed;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public XboxController drvr = new XboxController(Constants.driveController);
	public XboxController ctrl = new XboxController(Constants.operatorController);
	
	public OI() {
        JoystickButton A = new JoystickButton(ctrl, 1); 
        JoystickButton Y = new JoystickButton(ctrl, 4); 
        JoystickButton X = new JoystickButton(ctrl, 3);
        JoystickButton B = new JoystickButton(ctrl, 2);
        JoystickButton LB = new JoystickButton(ctrl, 5);
        JoystickButton RB = new JoystickButton(ctrl, 6);
        
        A.whenPressed(new Rotate(0.0));
        Y.whenPressed(new Rotate(-140.0));
        X.whenPressed(new Rotate(-120.0));
        B.whenPressed(new Rotate(-60.0));  
        LB.whenPressed(new IntakeClamp(Value.kReverse));
        RB.whenPressed(new IntakeClamp(Value.kForward));
        
        if (drvr.getTriggerAxis(Hand.kRight) == 1) {
            new Shift(Value.kForward).start();
        } else if (drvr.getTriggerAxis(Hand.kLeft) == 1) {
            new Shift(Value.kReverse).start();
        }
    
        if (ctrl.getTriggerAxis(Hand.kRight) == 5) {
            new IntakeSpeed(1).start();
        } else if (ctrl.getTriggerAxis(Hand.kLeft) == -5 ) {
            new IntakeSpeed(-1).start();
        }
	}
}
