package frc.team0000.robot.Lib.Actions;

import frc.team0000.robot.Lib.CrashTrackingRunnable;

public class RoutineExecutor {
    
    private Thread thread_ = null;

    private Routine routine_;

    public RoutineExecutor(Routine routine){ routine_ = routine; }

    public void start(){
        if(thread_ == null){
            thread_ = new Thread(new CrashTrackingRunnable() {
                @Override public void runCrashTracked(){
                    if(routine_ != null){ routine_.run(); }
                }
            });

            thread_.start();
        }
    }

    public void stop(){
        if(routine_ != null){ routine_.stop(); }
        thread_ = null;
    }
}