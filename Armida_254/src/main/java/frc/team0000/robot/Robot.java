package frc.team0000.robot;

dimport edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team0000.robot.Auto.Actions.Expel;
import frc.team0000.robot.Auto.Routines.BaseLine;
import frc.team0000.robot.Auto.Routines.LineScore;
import frc.team0000.robot.Lib.Looper;
import frc.team0000.robot.Lib.SubsystemManager;
import frc.team0000.robot.Lib.Actions.Routine;
import frc.team0000.robot.Lib.Actions.RoutineExecutor;
import frc.team0000.robot.Subsystems.*;
import frc.team0000.robot.Subsystems.Arm.Position;
import frc.team0000.robot.Subsystems.Intake.Speed;

public class Robot extends IterativeRobot {

    public static Looper subsystemLoop;

    public static SubsystemManager subsystemManager;
    public static Drivetrain drivetrain;
    public static Intake intake;
    public static Arm arm;

    private static XboxController driver;
    private static Joystick operator;

    private SendableChooser<Routine> chooser_;
    private RoutineExecutor executor_ = null;
    private Routine routine_;

    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        intake     = new Intake();
        arm        = new Arm();

        subsystemManager = new SubsystemManager(
            drivetrain,
            intake,
            arm
        );

        subsystemLoop = new Looper(Constants.subsystemRate);
        subsystemManager.registerLoop(subsystemLoop);

        chooser_.addDefault("Base line",  new BaseLine());
        chooser_.addObject ("Line Score", new LineScore());
        SmartDashboard.putData("Autonomous Chooser", chooser_);
    }

    @Override
    public void disabledInit() {
        subsystemManager.stop();
     }

    @Override
    public void autonomousInit() { 
        routine_ = chooser_.getSelected();
        
        subsystemManager.zero();
        subsystemLoop.start();

        executor_ = new RoutineExecutor(routine_);
        executor_.start();
    }

    @Override
    public void teleopInit() {
        if(executor_ != null){ executor_.stop(); }

        subsystemLoop.start();

        driver   = new XboxController(Constants.driveController);
        operator = new Joystick      (Constants.operatorController);
    }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() {
        subsystemManager.output(); 
    }
    
    @Override
    public void autonomousPeriodic() {
        subsystemManager.output();
     }

    @Override
    public void teleopPeriodic() {
        /// OPERATOR
        // ARM
        if(operator.getRawButton(2)){ arm.setPosition(Position.INTAKE); }
        if(operator.getRawButton(3)){ arm.setPosition(Position.OUTPUT); }
        if(operator.getRawButton(4)){ arm.setPosition(Position.PORTAL); }

        if(operator.getRawButton(10)){ arm.setBreak(false); }
        if(operator.getRawButton(11)){ arm.setBreak(true);  }
        // INTAKLE
        if(operator.getRawButton(8)){ intake.setSpeed(Speed.HI_IN); }
        if(operator.getRawButton(1)){ new Expel(Speed.LOW_OUT, false); }
        
        if(operator.getRawButton(7)){ intake.setClamp(true);  }
        if(operator.getRawButton(6)){ intake.setClamp(false); }

        /// Driver
        // Gear
        if(driver.getBumper(Hand.kRight)){ drivetrain.setGear(true);  }
        if(driver.getBumper(Hand.kLeft )){ drivetrain.setGear(false); }

        subsystemManager.output();
    }

    @Override
    public void testPeriodic() { }
}