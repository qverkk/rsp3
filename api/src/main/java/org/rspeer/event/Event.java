package org.rspeer.event;

import java.util.EventObject;
import org.rspeer.event.listener.EventListener;

public abstract class Event<T, V extends EventListener> extends EventObject {

    private final Class<V> listenerClass;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public Event(T source, Class<V> listenerClass) {
        super(source);
        this.listenerClass = listenerClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getSource() {
        return (T) super.getSource();
    }

    public Class<V> getListenerClass() {
        return listenerClass;
    }

    public abstract void dispatch(EventListener listener);
}
