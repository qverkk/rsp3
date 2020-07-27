package org.rspeer.game.script.event.listener;

import org.rspeer.event.listener.EventListener;

public interface ScriptChangeListener extends EventListener {
    void notify(ScriptChangeEvent e);
}
