package org.rspeer.game.script.event.listener;

import org.rspeer.event.Event;
import org.rspeer.event.listener.EventListener;
import org.rspeer.game.script.Script;
import org.rspeer.game.script.loader.ScriptSource;

public class ScriptChangeEvent extends Event<ScriptSource, ScriptChangeListener> {

    private final Script.State state;
    private final Script.State previousState;

    public ScriptChangeEvent(ScriptSource source, Script.State state, Script.State previousState) {
        super(source, ScriptChangeListener.class);
        this.state = state;
        this.previousState = previousState;
    }

    public Script.State getPreviousState() {
        return previousState;
    }

    public Script.State getState() {
        return state;
    }

    @Override
    public void dispatch(EventListener listener) {
        if (listener instanceof ScriptChangeListener) {
            ((ScriptChangeListener) listener).notify(this);
        }
    }
}
