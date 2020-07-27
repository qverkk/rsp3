package org.rspeer.game.script;

import org.rspeer.event.EventDispatcher;
import org.rspeer.game.script.loader.ScriptProvider;
import org.rspeer.game.script.loader.ScriptSource;

public class ScriptController {

    private final EventDispatcher internalDispatcher;

    private Script active;
    private ScriptSource source;
    private Thread scriptThread;

    public ScriptController(EventDispatcher internalDispatcher) {
        this.internalDispatcher = internalDispatcher;
    }

    public void start(ScriptProvider provider, ScriptSource source) {
        if (active != null) {
            throw new IllegalStateException("A script is already running");
        }

        this.source = source;
        active = provider.define(source);
        active.setInternalDispatcher(internalDispatcher);
        active.setSource(source);
        active.setState(Script.State.STARTING);
        active.setState(Script.State.RUNNING);

        scriptThread = new Thread(active);
        scriptThread.start();
    }

    public void stop() {
        if (active != null) {
            active.setState(Script.State.STOPPED);
            active = null;
        }

        source = null;
        scriptThread = null;
    }

    public ScriptSource getSource() {
        return source;
    }

    public Script getActive() {
        return active;
    }
}
