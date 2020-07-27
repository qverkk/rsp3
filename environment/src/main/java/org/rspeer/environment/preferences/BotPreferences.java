package org.rspeer.environment.preferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.rspeer.commons.Configuration;
import org.rspeer.environment.preferences.type.AlwaysOnTopPreference;
import org.rspeer.environment.preferences.type.BotPreference;
import org.rspeer.environment.preferences.type.LocalePreference;
import org.rspeer.environment.preferences.type.SceneRenderPreference;

@SuppressWarnings(value = "all")
public class BotPreferences {

    static final Gson GSON;

    static {
        GSON = new GsonBuilder().registerTypeAdapter(BotPreference.class, new BotPreferenceAdapter()).create();
    }

    private final Map<String, BotPreference<?>> preferences;

    public BotPreferences() {
        preferences = new HashMap<>();
        map(new LocalePreference());
        map(new SceneRenderPreference());
        map(new AlwaysOnTopPreference());

        for (BotPreference preference : preferences.values()) {
            preference.set(preference.getDefault());
        }
    }

    private void map(BotPreference<?> preference) {
        preferences.put(preference.getClass().getName(), preference);
    }

    public synchronized void save() {
        try (JsonWriter writer = new JsonWriter(new FileWriter(Configuration.Paths.PREFERENCES_LOCATION.toFile()))) {
            GSON.toJson(GSON.toJsonTree(this), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T valueOf(Class<? extends BotPreference<T>> clazz) {
        BotPreference<T> preference = (BotPreference<T>) preferences.get(clazz.getName());
        if (preference == null) {
            throw new IllegalArgumentException();
        }

        T value = preference.get();
        return value != null ? value : preference.getDefault();
    }

    public <T> void set(Class<? extends BotPreference<T>> clazz, T value) {
        BotPreference<T> preference = (BotPreference<T>) preferences.get(clazz.getName());
        if (preference == null) {
            throw new IllegalArgumentException();
        }

        preference.set(value);
        preference.notify(this);
    }

    public <T> BotPreference<T> get(Class<? extends BotPreference<T>> clazz) {
        BotPreference<T> preference = (BotPreference<T>) preferences.get(clazz.getName());
        if (preference == null) {
            throw new IllegalArgumentException();
        }
        return preference;
    }
}
