package org.rspeer.environment.preferences.event;

import org.rspeer.environment.preferences.type.BotPreference;
import org.rspeer.event.Event;
import org.rspeer.event.listener.EventListener;

public class PreferenceEvent extends Event<BotPreference<?>, PreferenceListener> {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PreferenceEvent(BotPreference<?> source) {
        super(source, PreferenceListener.class);
    }

    @Override
    public void dispatch(EventListener listener) {
        ((PreferenceListener) listener).notify(this);
    }
}
