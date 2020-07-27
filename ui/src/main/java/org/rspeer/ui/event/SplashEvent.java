package org.rspeer.ui.event;

import org.rspeer.event.listener.EventListener;
import org.rspeer.ui.Window;
import org.rspeer.ui.event.listener.UIListener;

public class SplashEvent extends UIEvent {

    private final String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SplashEvent(Window source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void dispatch(EventListener listener) {
        ((UIListener) listener).notify(this);
    }
}
