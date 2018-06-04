package frc.team0000.robot.Lib.Actions;

import frc.team0000.robot.Lib.Actions.Action;
import frc.team0000.robot.Lib.Actions.AutoEndedException;

public abstract class Routine {
    private final long kPeriod = 20;
    private boolean enabled_ = false;

    protected abstract void routine() throws AutoEndedException;

    public void run(){
        enabled_ = true;
        try {
            routine();
        } catch (AutoEndedException e) {
            System.out.print("Auto ended early");
            return;
        }
        System.out.print("Auto ended");
    }

    public void stop(){ enabled_ = false; }

    public boolean isRunning(){ return enabled_; } 

    public boolean isRunningThrow() throws AutoEndedException{
        if(!enabled_){ throw new AutoEndedException(); }
        return true;
    }

    public void addAction(Action act) throws AutoEndedException {
        isRunningThrow();
        act.start();

        while(isRunningThrow() && !act.isFinished()){
            act.update();
            try {
                Thread.sleep(kPeriod);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}