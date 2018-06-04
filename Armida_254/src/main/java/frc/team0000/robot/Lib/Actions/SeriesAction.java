package frc.team0000.robot.Lib.Actions;

import java.util.ArrayDeque;
import java.util.Deque;

public class SeriesAction implements Action {

    private Action current_ = null;
    private Deque<Action> actions_ = new ArrayDeque<Action>();

    public SeriesAction(Action... acts){
        for(Action a: acts) { actions_.add(a); }
        current_ = null;
    }

    @Override
    public boolean isFinished(){ return (actions_.isEmpty() && current_ == null); }

    @Override
    public void start(){}

    @Override
    public void update(){
        if(current_ == null) {
            if(actions_.isEmpty()) { return; }
            current_ = actions_.pop();
            current_.update();
        }
        current_.update();
        if(current_.isFinished()) {
            current_.done();
            current_ = null;
        }
    }

    @Override
    public void done(){}
}