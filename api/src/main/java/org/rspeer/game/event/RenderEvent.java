package org.rspeer.game.event;

import java.awt.Graphics;
import org.rspeer.event.Event;
import org.rspeer.event.listener.EventListener;
import org.rspeer.game.event.listener.RenderListener;

public class RenderEvent extends Event<Graphics, RenderListener> {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RenderEvent(Graphics source) {
        super(source, RenderListener.class);
    }

    @Override
    public void dispatch(EventListener listener) {
        ((RenderListener) listener).notify(this);
    }
}
