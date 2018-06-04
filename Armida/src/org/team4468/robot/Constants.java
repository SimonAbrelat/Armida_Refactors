package org.team4468.robot;

import edu.wpi.first.wpilibj.I2C;

public class Constants {
   ///// Drivetrain
   // Left Side
   public static int leftPair = 9;
   public static int leftBot  = 8;
   public static double leftP = 0;
   public static double leftI = 0;
   public static double leftD = 0;
   // Right Side
   public static int rightPair = 7;
   public static int rightBot  = 6;
   public static double rightP = 0;
   public static double rightI = 0;
   public static double rightD = 0;

   // IO
   public static int driveController = 0;
   public static int operatorController = 1;
   public static double deadband = 0.1;
   
   //// Encoders
   // Constants
   public static double pulsesPerRev = 128;
   public static double distancePerPulse = 0.000396745; //wheelCircumference / (pulsesPerRev * (stageRatio * encoderRatio));
   // Left Encoder
   public static boolean leftEncInverted = true;
   public static int leftEnc2 = 1;
   public static int leftEnc1 = 0;
   // Right Encoder
   public static boolean rightEncInverted = false;
   public static int rightEnc2 = 3;
   public static int rightEnc1 = 2;
   
   // Gyro
   public static edu.wpi.first.wpilibj.I2C.Port gyroPort = I2C.Port.kOnboard;
   public static double angleP = 0;
   public static double angleI = 0;
   public static double angleD = 0;
   
   // Shifter
   public static int shifterPort1 = 1;
   public static int shifterPort2 = 2;
   
   // Intake
   public static int intakePort1 = 5;
   public static int intakePort2 = 4;
   public static int intakeClampPort1 = 3;
   public static int intakeClampPort2 = 4;
   
   // Lifter
   public static int lift     = 3;
   public static double liftP = 0;
   public static double liftI = 0;
   public static double liftD = 0;
   
   // Potentiometer
   public static int potPort = 0;
   public static double potRange = 1125;
   public static double potOff = 1066.6;
}
