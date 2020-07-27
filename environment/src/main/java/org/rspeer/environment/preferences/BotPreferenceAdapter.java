package org.rspeer.environment.preferences;

import com.google.gson.*;
import org.rspeer.environment.preferences.type.BotPreference;

import java.lang.reflect.Type;

public class BotPreferenceAdapter implements JsonDeserializer<BotPreference<?>>, JsonSerializer<BotPreference<?>> {

    @Override
    public BotPreference<?> deserialize(JsonElement elem, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject object = elem.getAsJsonObject();
        String def = object.get("type").getAsString();
        JsonElement element = object.get("value");

        try {
            return ctx.deserialize(element, Class.forName(def));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(BotPreference<?> preference, Type type, JsonSerializationContext ctx) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(preference.getClass().getName()));
        result.add("value", ctx.serialize(preference, preference.getClass()));
        return result;
    }
}
