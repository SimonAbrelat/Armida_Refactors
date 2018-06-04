package frc.team0000.robot.Lib;

import frc.team0000.robot.Lib.Looper;

public interface Subsystem {

    public static Loop loop_ = new Loop(){
        @Override public void start(){}
        @Override public void update(){}
        @Override public void stop(){}
    };

    public abstract void stop();
    public abstract void zero();
    public abstract void output();
    public abstract void registerLoop(Looper looper);
}