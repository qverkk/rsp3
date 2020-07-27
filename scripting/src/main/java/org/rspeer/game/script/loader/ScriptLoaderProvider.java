package org.rspeer.game.script.loader;

import org.rspeer.commons.Configuration;
import org.rspeer.game.script.loader.local.LocalScriptLoader;

import java.util.function.Supplier;

public class ScriptLoaderProvider implements Supplier<ScriptProvider> {

    @Override
    public ScriptProvider get() {
        // tood add remote loader
        return new LocalScriptLoader(Configuration.Paths.SCRIPTS_LOCATION);
    }
}
