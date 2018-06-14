/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#pragma once

#include <Commands/Subsystem.h>
#include <SpeedControllerGroup.h>
#include <Drive/DifferentialDrive.h>
#include <VictorSP.h>
#include <Encoder.h>
#include <AHRS.h>
#include <../Constants.h>

class Drivetrain : public frc::Subsystem {
private:
	frc::VictorSP l1 {Constants::leftPair};
	frc::VictorSP l2 {Constants::leftBot};
	frc::SpeedControllerGroup m_left {l1, l2};

	frc::VictorSP r1 {Constants::rightPair};
	frc::VictorSP r2 {Constants::rightBot};
	frc::SpeedControllerGroup m_right {r1, r2};

	frc::DifferentialDrive m_drive {m_left, m_right};

	frc::Encoder m_leftEncoder {
        Constants::leftEnc1,
        Constants::leftEnc2,
        Constants::leftEncInverted,
        frc::Encoder::EncodingType::k4X
	};

	frc::Encoder m_rightEncoder {
		Constants::rightEnc1,
		Constants::rightEnc2,
		Constants::rightEncInverted,
		frc::Encoder::EncodingType::k4X
	};

	frc::AHRS gyro {Constants.gyroPort};

public:
	Drivetrain();
	void InitDefaultCommand() override;
};

