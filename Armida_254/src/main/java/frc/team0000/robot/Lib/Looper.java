package frc.team0000.robot.Lib;

import java.util.ArrayDeque;
import java.util.Deque;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Looper {
    public static double kPeriod;

    private boolean running_;
    private Notifier notifier_;
    private Deque<Loop> loops_;
    private Object loopLock_ = new Object();
    private double timestamp_;
    private double dt_;

    private final CrashTrackingRunnable runnable_ = new CrashTrackingRunnable(){
        @Override public void runCrashTracked(){
            synchronized(loopLock_){
                if(running_){
                    double now = Timer.getFPGATimestamp();
                    for(Loop l: loops_) { l.update(); }
                    dt_ = now - timestamp_;
                    timestamp_ = now;
                }
            }
        }
    };

    public Looper(double period){
        kPeriod   = period;
        running_  = false;
        loops_    = new ArrayDeque<Loop>();
        notifier_ = new Notifier(runnable_);
    }

    public synchronized void register(Loop loop){
        synchronized(loopLock_){ loops_.add(loop); }
    }

    public synchronized void start(){
        if(!running_){
            System.out.println("Starting loops");
            synchronized(loopLock_){
                timestamp_ = Timer.getFPGATimestamp();
                for(Loop l: loops_) { l.update(); }
                running_ = true;
            }
            notifier_.startPeriodic(kPeriod);
        }
    }

    public synchronized void stop(){
        if(running_){
            notifier_.stop();
            synchronized(loopLock_){
                running_   = false;
                timestamp_ = Timer.getFPGATimestamp();
                for(Loop l: loops_) {
                    System.out.print("Stopping " + l);
                    l.stop();
                }
            }
        }
    }

    public void toDash(){ SmartDashboard.putNumber("Loop dt", dt_); }
}