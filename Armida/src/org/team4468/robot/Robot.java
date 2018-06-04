/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team4468.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team4468.robot.Subsystems.*;
import org.team4468.robot.Commands.Manipulators.Rotate;
import org.team4468.robot.Commands.Routines.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
    
    public static RotatingLift lift;
    public static Drivetrain drive;
    public static Intake intake;
	public static OI oi;

	public static Rotate rot;
	
	Command autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	@Override public void robotInit() {
	    lift   = new RotatingLift();
	    drive  = new Drivetrain();
	    intake = new Intake();
		oi     = new OI();
		
		autoChooser.addDefault("Default Auto", new Run());
		
		SmartDashboard.putData("Auto mode", autoChooser);
	}

	@Override public void disabledPeriodic()   { Scheduler.getInstance().run(); }
	@Override public void autonomousPeriodic() { Scheduler.getInstance().run(); }
	@Override public void teleopPeriodic()     { Scheduler.getInstance().run(); }

	@Override public void disabledInit() {}
	@Override public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();
		
		if (autonomousCommand != null) { autonomousCommand.start(); }
	}
	@Override public void teleopInit() {
		if (autonomousCommand != null) { autonomousCommand.cancel(); }
	}
}
