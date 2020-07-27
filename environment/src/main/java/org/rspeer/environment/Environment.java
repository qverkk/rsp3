package org.rspeer.environment;

import org.rspeer.environment.preferences.BotPreferences;
import org.rspeer.event.EventDispatcher;
import org.rspeer.game.script.ScriptController;

/**
 * Bot environment
 */
public class Environment {

    private final EventDispatcher internalDispatcher;
    private final ScriptController scriptController;
    private final BotContext botContext;
    private BotPreferences preferences;

    public Environment() {
        internalDispatcher = new EventDispatcher();
        scriptController = new ScriptController(internalDispatcher);
        botContext = new BotContext();
    }

    public ScriptController getScriptController() {
        return scriptController;
    }

    public BotContext getBotContext() {
        return botContext;
    }

    public BotPreferences getPreferences() {
        return preferences;
    }

    public EventDispatcher getInternalDispatcher() {
        return internalDispatcher;
    }

    public void setPreferences(BotPreferences preferences) {
        this.preferences = preferences;
    }
}
