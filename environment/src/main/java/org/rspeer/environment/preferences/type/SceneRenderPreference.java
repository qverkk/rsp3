package org.rspeer.environment.preferences.type;

public class SceneRenderPreference extends BotPreference<Boolean> {

    @Override
    public Boolean getDefault() {
        return true;
    }

    @Override
    public boolean isSupported(Boolean value) {
        return value != null;
    }
}
