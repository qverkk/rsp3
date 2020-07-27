package org.rspeer.environment.preferences.type;

import org.rspeer.environment.preferences.BotPreferences;

public abstract class BotPreference<T> {

    private T value;

    public final T get() {
        return value;
    }

    public final void set(T value) {
        if (isSupported(value)) {
            this.value = value;
        }
    }

    public final void notify(BotPreferences preferences) {
        preferences.save();
    }

    public abstract T getDefault();

    public abstract boolean isSupported(T value);
}
