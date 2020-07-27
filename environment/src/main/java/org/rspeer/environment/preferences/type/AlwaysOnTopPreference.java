package org.rspeer.environment.preferences.type;

public class AlwaysOnTopPreference extends BotPreference<Boolean> {

    @Override
    public Boolean getDefault() {
        return false;
    }

    @Override
    public boolean isSupported(Boolean value) {
        return value != null;
    }
}
