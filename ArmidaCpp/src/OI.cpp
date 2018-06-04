#include "OI.h"

#include <WPILib.h>
#include "GenericHID.h"

OI::OI() {
	// Process operator interface input here.
}

double OI::getLeftJoy(){
	return m_drvr.GetX(frc::GenericHID::kLeftHand);
}

double OI::getRightJoy(){
	return m_drvr.GetX(frc::GenericHID::kRightHand);
}

bool OI::test(){
	return m_drvr.GetAButton();
}
