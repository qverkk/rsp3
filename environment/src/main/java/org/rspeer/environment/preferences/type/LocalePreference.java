package org.rspeer.environment.preferences.type;

import java.util.Locale;

public class LocalePreference extends BotPreference<Locale> {

    @Override
    public Locale getDefault() {
        return Locale.ENGLISH;
    }

    @Override
    public boolean isSupported(Locale value) {
        return value == Locale.ENGLISH;
    }
}
