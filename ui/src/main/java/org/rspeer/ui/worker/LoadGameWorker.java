package org.rspeer.ui.worker;

/*
    Author: zScorpio
    Time: 22:18 (UTC+2)
    Date: Sunday - 05/31/2020
*/

import jag.game.RSClient;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import javax.swing.JFrame;
import org.rspeer.environment.Environment;
import org.rspeer.environment.preferences.event.PreferenceEvent;
import org.rspeer.environment.preferences.type.BotPreference;
import org.rspeer.environment.preferences.type.SceneRenderPreference;
import org.rspeer.game.Game;
import org.rspeer.game.loader.GameLoader;
import org.rspeer.ui.Window;
import org.rspeer.ui.event.SetAppletEvent;
import org.rspeer.ui.event.SplashEvent;

public class LoadGameWorker extends BotWorker<RSClient, String> {

    public LoadGameWorker(Environment environment, Window<JFrame> window) {
        super(environment, window);
    }

    @Override
    protected RSClient work() throws IOException {
        return GameLoader.load(true, new Callback());
    }

    @Override
    protected void notify(String message) {
        SplashEvent event = new SplashEvent(window, message);
        environment.getInternalDispatcher().dispatch(event);
    }

    @Override
    protected void onFinish() {
        try {
            RSClient client = get();
            SetAppletEvent event = new SetAppletEvent(window, client.asApplet());
            environment.getInternalDispatcher().dispatch(event);
            if (!environment.getPreferences().valueOf(SceneRenderPreference.class)) {
                BotPreference<Boolean> preference = environment.getPreferences().get(SceneRenderPreference.class);
                PreferenceEvent preferenceEvent = new PreferenceEvent(preference);
                environment.getInternalDispatcher().dispatch(preferenceEvent);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class Callback implements Consumer<RSClient> {

        @Override
        public void accept(RSClient client) {
            publish("Loading RuneScape");
            Game.setClient(client);
            publish("Successfully loaded RuneScape");
        }
    }
}
