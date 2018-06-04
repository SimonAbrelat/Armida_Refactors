package frc.team0000.robot.Subsystems.Loops;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.team0000.robot.Lib.Loop;

public class BrownoutProtection implements Loop {

    private PowerDistributionPanel pdp_ = new PowerDistributionPanel();
    private Compressor compressor_ = new Compressor();

    public BrownoutProtection(){}

    @Override public void start(){ pdp_.clearStickyFaults(); }

    @Override
    public void update(){
        if(pdp_.getVoltage() < 10 || pdp_.getTotalCurrent() > 40){
            compressor_.stop();
        } else {
            compressor_.start();
        }
    }

    @Override
    public void stop(){}
}