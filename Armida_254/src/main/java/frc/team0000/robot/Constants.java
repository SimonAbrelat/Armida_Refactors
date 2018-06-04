package frc.team0000.robot;

import edu.wpi.first.wpilibj.I2C;

public class Constants {
    ///// Drivetrain
    // Left Side
    public static int leftPair = 9;
    public static int leftBot  = 8;
    // Right Side
    public static int rightPair = 7;
    public static int rightBot  = 6;
    // Drive PIDS
    public static double lineP = 45.0;
    public static double lineI = 0.01;
    public static double lineD = 0.01;
    public static double lineF = 0.0;
    public static double velP = 0.0;
    public static double velI = 0.0;
    public static double velD = 0.0;
    public static double velF = 0.0;
    
    // IO
    public static int driveController = 0;
    public static int operatorController = 1;

    //// Encoders
    // Constants
    public static double pulsesPerRev = 128.0;
    public static double distancePerPulse = 0.000396745; //wheelCircumference / (pulsesPerRev * (stageRatio * encoderRatio));
    // Left Encoder
    public static boolean leftEncInverted = true;
    public static int leftEnc2 = 3;
    public static int leftEnc1 = 2;
    // Right Encoder
    public static boolean rightEncInverted = false;
    public static int rightEnc2 = 1;
    public static int rightEnc1 = 0;

    // Gyro
    public static I2C.Port gyroPort  = I2C.Port.kOnboard;
    public static double angleP = .96;
    public static double angleI = 0.004;
    public static double angleD = 0.0;
    public static double angleF = 0.0;

    // Shifter
    public static int shifterPort1 = 0;
    public static int shifterPort2 = 4;

    // Intake
    public static int intakePort1 = 5;
    public static int intakePort2 = 4;
    public static int intakeClampPort1 = 5;
    public static int intakeClampPort2 = 3;
    public static int photoGatePort = 5;
    
    // Lifter
    public static int armPort1 = 3;
    public static double armP  = 5.0;
    public static double armI  = 0.0;
    public static double armD  = .12;
    public static double armF = 0;
    public static int BreakClampPort1 = 6;
    public static int BreakClampPort2 = 7;
    
    // Potentiometer
    public static int potPort = 3; // Placeholder
    public static double potRange = 1125.0; // Placeholder
    public static double potOff = 0.0; // Placeholder

    // Looper Rates
    public static double subsystemRate = 0.05;
    public static double fastRate      = 0.005;
}