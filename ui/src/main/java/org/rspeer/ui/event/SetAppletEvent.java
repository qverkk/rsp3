package org.rspeer.ui.event;

import java.applet.Applet;
import org.rspeer.event.listener.EventListener;
import org.rspeer.ui.Window;
import org.rspeer.ui.event.listener.UIListener;

public class SetAppletEvent extends UIEvent {

    private final Applet applet;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SetAppletEvent(Window source, Applet applet) {
        super(source);
        this.applet = applet;
    }

    public Applet getApplet() {
        return applet;
    }

    @Override
    public void dispatch(EventListener listener) {
        ((UIListener) listener).notify(this);
    }
}
