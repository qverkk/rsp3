package org.rspeer.ui.component.menu;

import java.util.function.Consumer;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.rspeer.commons.Pair;
import org.rspeer.environment.Environment;
import org.rspeer.environment.preferences.type.AlwaysOnTopPreference;
import org.rspeer.environment.preferences.type.SceneRenderPreference;
import org.rspeer.game.Game;
import org.rspeer.ui.debug.Debug;
import org.rspeer.ui.debug.GameDebug;
import org.rspeer.ui.debug.explorer.itf.InterfaceExplorer;
import org.rspeer.ui.locale.Message;

public class BotMenuBar extends JMenuBar {

    private static final DebugEntry[] DEBUG_ENTRIES = new DebugEntry[]{
        new DebugEntry(new JCheckBoxMenuItem("Game"), new GameDebug())
    };

    private final Environment environment;

    private JCheckBoxMenuItem renderScene;

    public BotMenuBar(Environment environment) {
        this.environment = environment;
        add(createFileMenu());
        add(createDebugMenu());
        add(createWindowMenu());
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu(Message.FILE.getActive(environment.getPreferences()));
        //TODO: stuff
        return file;
    }

    private JMenu createWindowMenu() {
        JMenu window = new JMenu(Message.WINDOW.getActive(environment.getPreferences()));

        JMenuItem onTop = new JCheckBoxMenuItem(Message.ALWAYS_ON_TOP.getActive(environment.getPreferences()),
                                                environment.getPreferences().valueOf(AlwaysOnTopPreference.class));
        onTop.addItemListener(act -> {
            environment.getBotContext().getFrame().setAlwaysOnTop(onTop.isSelected());
            environment.getPreferences().set(AlwaysOnTopPreference.class, onTop.isSelected());
        });

        window.add(onTop);
        return window;
    }

    private JMenu createDebugMenu() {
        JMenu debug = new JMenu(Message.DEBUG.getActive(environment.getPreferences()));

        JMenuItem itfs = new JMenuItem("Interfaces");
        itfs.addActionListener(act -> new InterfaceExplorer(environment));

        renderScene = new JCheckBoxMenuItem("Render Scene", true);
        renderScene.addItemListener(evt -> {
            Game.getClient().setSceneRenderingDisabled(!renderScene.isSelected());
            environment.getPreferences().set(SceneRenderPreference.class, renderScene.isSelected());
        });

        for (DebugEntry entry : DEBUG_ENTRIES) {
            JMenuItem option = entry.getLeft();
            Debug debugger = entry.getRight();
            option.addItemListener(evt -> {
                if (option.isSelected()) {
                    Game.getClient().getEventDispatcher().subscribe(debugger);
                } else {
                    Game.getClient().getEventDispatcher().unsubscribe(debugger);
                }
                if (entry.onSelect != null) {
                    entry.onSelect.accept(option.isSelected());
                }
            });
            debug.add(option);
        }

        debug.add(renderScene);
        debug.add(itfs);

        return debug;
    }

    private static class DebugEntry extends Pair<JMenuItem, Debug> {

        private final Consumer<Boolean> onSelect;

        private DebugEntry(JMenuItem option, Debug debug) {
            this(option, debug, null);
        }

        private DebugEntry(JMenuItem option, Debug debug, Consumer<Boolean> onSelect) {
            super(option, debug);
            this.onSelect = onSelect;
        }
    }

    public JCheckBoxMenuItem getRenderScene() {
        return renderScene;
    }
}
