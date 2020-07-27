package org.rspeer;

import org.rspeer.commons.Configuration;
import org.rspeer.environment.Environment;
import org.rspeer.environment.preferences.BotPreferencesLoader;
import org.rspeer.environment.preferences.JsonBotPreferencesLoader;
import org.rspeer.ui.BotFrame;
import org.rspeer.ui.Window;
import org.rspeer.ui.worker.LoadGameWorker;

import javax.swing.*;

/**
 * Entry point for the application
 */
public class Application {

    private final Environment environment;

    public Application() {
        environment = new Environment();
    }

    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.printf("Loading %s preferences%n", Configuration.getApplicationTitle());

        BotPreferencesLoader preferencesLoader = new JsonBotPreferencesLoader();
        preferencesLoader.load(preferences -> {
            environment.setPreferences(preferences);
            System.out.printf("Successfully loaded %s preferences%n", Configuration.getApplicationTitle());
        });

        SwingUtilities.invokeLater(() -> {
            Window<JFrame> ui = new BotFrame(environment);
            ui.display();

            LoadGameWorker loader = new LoadGameWorker(environment, ui);
            loader.execute();
        });
    }
}
