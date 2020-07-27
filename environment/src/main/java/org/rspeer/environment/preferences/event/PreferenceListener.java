package org.rspeer.environment.preferences.event;

import org.rspeer.event.listener.EventListener;

public interface PreferenceListener extends EventListener {

    void notify(PreferenceEvent e);
}
