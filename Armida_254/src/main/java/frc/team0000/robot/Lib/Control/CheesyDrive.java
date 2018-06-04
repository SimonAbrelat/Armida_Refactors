package frc.team0000.robot.Lib.Control;

public class CheesyDrive {
    private static double kThrottleDeadband = 0.02;
    private static double kTurningDeadband  = 0.02;

    private static double kHighTurnNonLinearityFactor = 0.65;
    private static double kLowTurnNonLinearityFactor = 0.5;

    private static double kHighNegInertiaScalar = 4.0;
    private static double kLowNegInertiaThreshold = 0.65;
    private static double kLowNegInertiaTurnScalar = 3.5;
    private static double kLowNegInertiaCloseScalar = 4.0;
    private static double kLowNegInertriaFarScalar = 5.0;

    private static double kHighSensitivity = 0.95;
    private static double kLowSensitivity = 1.3;

    private static double kQuickStopDeadband = 0.2;
    private static double kQuickStopWeight = 0.1;
    private static double kQuickStopScalar = 5.0;

    private double oldTurn_ = 0;
    private double quickStopAcc_ = 0;
    private double negIntertiaAcc_ = 0;

    public class DriveValue{
        public double left;
        public double right;

        public DriveValue(double x, double y){
            left = x;
            right = y;
        }
    }

    public DriveValue cheesyDrive(double throttle, double turn, boolean isQuick, boolean isHigh){
        turn = deadband(turn, kTurningDeadband);
        throttle = deadband(throttle, kThrottleDeadband);

        double negInertia = turn - oldTurn_;
        oldTurn_ = turn;

        // Non-linearity
        double turnNonLinearity;
        if(isHigh){ turnNonLinearity = kHighTurnNonLinearityFactor; }
        else {
            turnNonLinearity = kLowTurnNonLinearityFactor;
            turn = Math.sin(Math.PI / 2.0 * turnNonLinearity * turn) / Math.sin(Math.PI / 2.0 * turnNonLinearity);
        }
        final double denominator = Math.sin(Math.PI / 2.0 * turnNonLinearity);
        // Apply a sin function that's scaled to make it feel better.
        turn = Math.sin(Math.PI / 2.0 * turnNonLinearity * turn) / denominator;
        turn = Math.sin(Math.PI / 2.0 * turnNonLinearity * turn) / denominator;

        double left, right, over;
        double angPower, linPower;

        // Negative Inertia
        double negInertiaScalar;
        double sensitivity;
        if(isHigh){
            negInertiaScalar = kHighNegInertiaScalar;
            sensitivity = kHighSensitivity;
        } else {
            if(turn * negInertia > 0){ negInertiaScalar = kLowNegInertiaTurnScalar; }
            else{
                if(Math.abs(turn) > kLowNegInertiaThreshold){
                    negInertiaScalar = kLowNegInertriaFarScalar;
                } else {
                    negInertiaScalar = kLowNegInertiaCloseScalar;
                }
            }
            sensitivity = kLowSensitivity;
        }
        negIntertiaAcc_ += (negInertia * negInertiaScalar);
        turn += negIntertiaAcc_;
        if(negIntertiaAcc_ > 1) { negIntertiaAcc_ -= 1; }
        if(negIntertiaAcc_ < -1){ negIntertiaAcc_ += 1; }
        else                    { negIntertiaAcc_ = 0;  }
        linPower = throttle;

        // Quickturn
        if(isQuick){
            if(Math.abs(linPower) < kQuickStopDeadband) {
                double aph = kQuickStopWeight;
                quickStopAcc_ = (1-aph)*quickStopAcc_ + aph * limit(turn, 1.0) * kQuickStopScalar;
            }
            over = 1.0;
            angPower = turn;
        } else {
            over = 0.0;
            angPower = Math.abs(throttle) * turn * sensitivity - quickStopAcc_;
            if(quickStopAcc_ > 1) { quickStopAcc_ -= 1; }
            if(quickStopAcc_ < -1){ quickStopAcc_ += 1; }
            else                  { quickStopAcc_ = 0;  }
        }

        right = left = linPower;
        left += angPower;
        right -= angPower;

        if (left > 1.0) {
            right -= over * (left - 1.0);
            left = 1.0;
        } else if (right > 1.0) {
            left -= over * (right - 1.0);
            right = 1.0;
        } else if (left < -1.0) {
            right += over * (-1.0 - left);
            left = -1.0;
        } else if (right < -1.0) {
            left += over * (-1.0 - right);
            right = -1.0;
        }
        return new DriveValue(left, right);
    }

    private double deadband(double x, double d){
        return (Math.abs(x) > Math.abs(d)) ? x : 0.0;
    }

    public static double limit(double v, double maxMagnitude) {
        return limit(v, -maxMagnitude, maxMagnitude);
    }

    public static double limit(double v, double min, double max) {
        return Math.min(max, Math.max(min, v));
    }
}