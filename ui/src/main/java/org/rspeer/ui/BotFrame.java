package org.rspeer.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import org.rspeer.commons.Configuration;
import org.rspeer.environment.Environment;
import org.rspeer.environment.preferences.event.PreferenceEvent;
import org.rspeer.environment.preferences.event.PreferenceListener;
import org.rspeer.environment.preferences.type.AlwaysOnTopPreference;
import org.rspeer.environment.preferences.type.SceneRenderPreference;
import org.rspeer.ui.component.menu.BotMenuBar;
import org.rspeer.ui.component.menu.BotToolBar;
import org.rspeer.ui.component.splash.Splash;
import org.rspeer.ui.event.SetAppletEvent;
import org.rspeer.ui.event.SplashEvent;
import org.rspeer.ui.event.UIEvent;
import org.rspeer.ui.event.listener.UIListener;

public class BotFrame extends Window<JFrame> implements UIListener, PreferenceListener {

    private final Environment environment;

    private BotMenuBar menu;
    private Splash splash;

    public BotFrame(Environment environment) {
        super(new JFrame(Configuration.getApplicationTitle()));
        this.environment = environment;
        applyDefaults();
        applyComponents();
        applyListeners();
    }

    private void applyDefaults() {
        System.setProperty("sun.awt.noerasebackground", "true");
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void applyComponents() {
        try {
            frame.setIconImage(ImageIO.read(getClass().getResource("/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setAlwaysOnTop(environment.getPreferences().valueOf(AlwaysOnTopPreference.class));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        splash = new Splash();
        frame.add(splash, BorderLayout.CENTER);

        menu = new BotMenuBar(environment);
        frame.setJMenuBar(menu);
        frame.add(new BotToolBar(environment), BorderLayout.NORTH);

        environment.getBotContext().setFrame(frame);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(frame.getSize());
        frame.validate();
        //TODO: Implement logger & save its show/hide state in the preferences file
    }

    private void applyListeners() {
        environment.getInternalDispatcher().subscribe(this);
        /*
                Typically, we should add a windowClosing callback for the frame
                but we're not doing that here since the setDefaultCloseOperation
                method is taking an EXIT_ON_CLOSE value
         */
    }

    @Override
    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void dispose() {
        frame.dispose();
    }

    @Override
    public void notify(UIEvent e) {
        if (e.getSource() == this) {
            if (e instanceof SplashEvent) {
                SplashEvent event = (SplashEvent) e;
                splash.setMessage(event.getMessage());
            } else if (e instanceof SetAppletEvent) {
                SetAppletEvent event = (SetAppletEvent) e;
                if (splash != null) {
                    splash = null;
                }
                BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
                Component previousComp = layout.getLayoutComponent(BorderLayout.CENTER);
                frame.remove(previousComp);
                frame.add(event.getApplet(), BorderLayout.CENTER);
            }
        }
    }

    @Override
    public void notify(PreferenceEvent e) {
        if (e.getSource() instanceof SceneRenderPreference) {
            menu.getRenderScene().setSelected(false);
        }
    }
}
