package frc.team0000.robot.Lib.Actions;

public abstract class OneTimeAction implements Action {
    @Override public boolean isFinished(){ return true; }
    @Override public void start (){ oneTime(); }
    @Override public void update(){}
    @Override public void done  (){}

    public abstract void oneTime();
}