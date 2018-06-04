#ifndef ROBOTMAP_H
#define ROBOTMAP_H

#include <cstdint>
#include <SPI.h>
#include <math.h>

#define _USE_MATH_DEFINES

class Constants {
public:
	//// System Units
    // General
	constexpr double angleOffset = 1082.0;
	constexpr double weight = 59.0; // Placeholder
    // Wheel
	constexpr double distanceBetweenWheels = 27.0;
	constexpr double wheelDiameter = 0.1524;
	constexpr double wheelCircumference = wheelDiameter * M_PI;
    // Gears
	constexpr double highUnstagedGearRatio = 12.0/125.0;
	constexpr double lowUnstagedGearRatio = 6.0/17.0;
	constexpr double stageRatio = 64.0/20.0;
	constexpr double encoderRatio = 1.0/3.0;
	constexpr double maxVelocity = 6.0;
    // Driver Input
	constexpr double deadband = 0.1;
	constexpr double turnMultiplier = 0.9;

    ///// Drivetrain
    // Left Side
	constexpr double leftPair = 9;
	constexpr double leftBot  = 8;
	constexpr double leftP = 9.999999;
	constexpr double leftI = 0.0;
	constexpr double leftD = 0.0;
	// Right Side
	constexpr char rightPair = 7;
	constexpr char rightBot  = 6;
	constexpr double rightP = 9.999999;
	constexpr double rightI = 0.0;
	constexpr double rightD = 0.0;
	// Both Sides
	constexpr double lineP = .95;
	constexpr double lineI = 0.0;
	constexpr double lineD = 0.0;


	// IO
	constexpr int driveController = 0;
	constexpr int operatorController = 1;

	//// Encoders
	// Constants
	constexpr double pulsesPerRev = 128.0;
	constexpr double distancePerPulse = 0.000396745; //wheelCircumference / (pulsesPerRev * (stageRatio * encoderRatio));
	// Left Encoder
	constexpr bool leftEncInverted = true;
	constexpr int leftEnc2 = 3;
	constexpr int leftEnc1 = 2;
	// Right Encoder
    constexpr bool rightEncInverted = false;
    constexpr int rightEnc2 = 1;
    constexpr int rightEnc1 = 0;

    // Gyro
    constexpr frc::SPI::Port gyroPort = frc::SPI::Port::kMXP;
    constexpr double angleP = 0.012;
    constexpr double angleI = 0.0;
    constexpr double angleD = 0.00;

    // Shifter
    constexpr int shifterPort1 = 0;
    constexpr int shifterPort2 = 4;

    // Intake
    constexpr int intakePort1 = 5;
    constexpr int intakePort2 = 4;
    constexpr int intakeClampPort1 = 5;
    constexpr int intakeClampPort2 = 3;
    constexpr int photoGatePort = 5;

    // Lifter
    constexpr int lifterPort1 = 3;
    constexpr double lifterP  = .05;
    constexpr double lifterI  = 0.005;
    constexpr double lifterD  = 0.0;
    constexpr int BreakClampPort1 = 6;
    constexpr int BreakClampPort2 = 7;

    // Potentiometer
    constexpr int potPort = 3; // Placeholder
    constexpr double potRange = 1125.0; // Placeholder
    constexpr double potOff = 0.0; // Placeholder


    //// System functions
    const double dt() {
    		uint64_t time = frc::GetFPGATime();
        uint64_t delta = time - prevTime;
        prevTime = time;
        return delta/1000.0;
    }

private:
    uint64_t prevTime = 0;
};
#endif  // ROBOTMAP_H
