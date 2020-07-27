package org.rspeer.game.event;

import org.rspeer.commons.StringCommons;
import org.rspeer.event.Event;
import org.rspeer.event.listener.EventListener;
import org.rspeer.game.event.listener.ChatMessageListener;

public class ChatMessageEvent extends Event<String, ChatMessageListener> {

    private final int type;
    private final String contents;
    private final String channel;

    public ChatMessageEvent(int type, String source, String contents, String channel) {
        super(StringCommons.sanitize(source), ChatMessageListener.class);
        this.type = type;
        this.contents = StringCommons.sanitize(contents);
        this.channel = channel != null ? StringCommons.sanitize(channel) : null;
    }

    @Override
    public void dispatch(EventListener listener) {
        ((ChatMessageListener) listener).notify(this);
    }

    //TODO maybe enum or constants
    public int getType() {
        return type;
    }

    public String getContents() {
        return contents;
    }

    public String getChannel() {
        return channel;
    }
}
