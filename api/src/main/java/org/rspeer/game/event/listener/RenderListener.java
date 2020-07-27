package org.rspeer.game.event.listener;

import org.rspeer.event.listener.EventListener;
import org.rspeer.game.event.RenderEvent;

public interface RenderListener extends EventListener {
    void notify(RenderEvent e);
}
