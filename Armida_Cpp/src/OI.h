#ifndef OI_H
#define OI_H

#include <Buttons/JoystickButton.h>
#include <XboxController.h>
#include <Joystick.h>
#include <Constants.h>


class OI {
public:
	OI();
	double getLeftJoy();
	double getRightJoy();
	bool test();

private:
	frc::XboxController m_drvr{Constants::driveController};
	frc::Joystick m_optr{Constants::operatorController};

	frc::JoystickButton m_dUp{&m_optr, 5};
	frc::JoystickButton m_dRight{&m_optr, 6};
	frc::JoystickButton m_dDown{&m_optr, 7};
	frc::JoystickButton m_dLeft{&m_optr, 8};
	frc::JoystickButton m_l2{&m_optr, 9};
	frc::JoystickButton m_r2{&m_optr, 10};
	frc::JoystickButton m_l1{&m_optr, 11};
	frc::JoystickButton m_r1{&m_optr, 12};
};

#endif  // OI_H
