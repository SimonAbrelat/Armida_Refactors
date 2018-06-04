package frc.team0000.robot.Lib.Actions;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParallelAction implements Action {
    
    private Deque<Action> actions_ = new ArrayDeque<Action>();
    
    public ParallelAction(Action... acts){
        for (Action a: acts) { actions_.add(a); }
    }

    @Override 
    public boolean isFinished(){ return actions_.stream().allMatch(a -> a.isFinished()); }
    
    @Override public void start() { actions_.forEach(a -> a.start());  }
    @Override public void update(){ actions_.forEach(a -> a.update()); }
    @Override public void done()  { actions_.forEach(a -> a.done());   }
}