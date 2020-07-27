package org.rspeer.environment.preferences;

/*
    Author: zScorpio
    Time: 20:36 (UTC+2)
    Date: Sunday - 05/31/2020
*/

import java.util.function.Consumer;

public abstract class BotPreferencesLoader {

    protected abstract BotPreferences load();

    public final void load(Consumer<BotPreferences> later) {
        BotPreferences result = load();
        later.accept(result);
    }
}
