package org.rspeer.game.script.loader;

import java.util.HashSet;

public class ScriptBundle extends HashSet<ScriptSource> {

    public ScriptSource findShallow(ScriptSource shallow) {
        for (ScriptSource source : this) {
            if (source.shallowEquals(shallow)) {
                return source;
            }
        }
        return null;
    }
}
