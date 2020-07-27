package org.rspeer.ui.event.listener;

import org.rspeer.event.listener.EventListener;
import org.rspeer.ui.event.UIEvent;

public interface UIListener extends EventListener {

    void notify(UIEvent e);
}
