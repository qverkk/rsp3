package org.rspeer.environment.preferences;

import com.google.gson.stream.JsonReader;
import org.rspeer.commons.Configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonBotPreferencesLoader extends BotPreferencesLoader {

    @Override
    protected BotPreferences load() {
        File preferencesFile = Configuration.Paths.PREFERENCES_LOCATION.toFile();
        if (preferencesFile.exists()) {
            try (JsonReader reader = new JsonReader(new FileReader(preferencesFile))) {
                BotPreferences preferences = BotPreferences.GSON.fromJson(reader, BotPreferences.class);
                if (preferences != null) {
                    return preferences;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new BotPreferences();
    }
}
