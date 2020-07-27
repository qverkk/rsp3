package org.rspeer.game.script;

import java.nio.file.Path;
import org.rspeer.commons.Configuration;
import org.rspeer.commons.Executor;
import org.rspeer.commons.Time;
import org.rspeer.event.EventDispatcher;
import org.rspeer.game.Game;
import org.rspeer.game.script.event.listener.ScriptChangeEvent;
import org.rspeer.game.script.loader.ScriptSource;

//TODO make this better, add random handling (login screen, welcome screen etc)
public abstract class Script implements Runnable {

    private EventDispatcher internalDispatcher;
    private ScriptSource source;

    private State state = State.STOPPED;

    public abstract int loop();

    public static Path getDataDirectory() {
        return Configuration.Paths.DATA_LOCATION;
    }

    public void onStart() {

    }

    public void onFinish() {

    }

    public final void setState(State state) {
        ScriptChangeEvent event = new ScriptChangeEvent(source, state, this.state);
        internalDispatcher.dispatch(event);
        this.state = state;
        if (state == State.STOPPED) {
            Executor.execute(this::onFinish);
        } else if (state == State.STARTING) {
            onStart();
        }
    }

    public final void setInternalDispatcher(EventDispatcher internalDispatcher) {
        this.internalDispatcher = internalDispatcher;
    }

    public final void setSource(ScriptSource source) {
        this.source = source;
    }

    public State getState() {
        return state;
    }

    public final ScriptMeta getMeta() {
        return getClass().getAnnotation(ScriptMeta.class);
    }

    @Override
    public final void run() {
        while (true) {
            if (state == State.PAUSED) {
                Time.sleep(100);
                continue;
            }

            if (state == State.STOPPED) {
                break;
            }

            Game.getClient().setMouseIdleTime(0);

            int sleep = loop();
            if (sleep < 0) {
                setState(State.STOPPED);
                return;
            }

            Time.sleep(sleep);
        }
    }

    public enum State {
        STARTING,
        RUNNING,
        PAUSED,
        STOPPED
    }
}
