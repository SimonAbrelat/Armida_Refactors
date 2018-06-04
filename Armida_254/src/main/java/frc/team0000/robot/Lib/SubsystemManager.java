package frc.team0000.robot.Lib;

import java.util.Deque;
import java.util.ArrayDeque;

public class SubsystemManager implements Subsystem {

    private Deque<Subsystem> subs_ = new ArrayDeque<Subsystem>();

    public SubsystemManager(Subsystem... subs){
        for(Subsystem sub: subs) { subs_.add(sub); }
    }

    @Override public void zero()  { subs_.forEach(a -> a.zero());   }
    @Override public void stop()  { subs_.forEach(a -> a.stop());   }
    @Override public void output(){ subs_.forEach(a -> a.output()); }

    @Override public void registerLoop(Looper looper_){ subs_.forEach(a -> a.registerLoop(looper_)); }
}