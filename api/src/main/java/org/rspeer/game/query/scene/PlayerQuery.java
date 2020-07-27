package org.rspeer.game.query.scene;

import jag.game.scene.entity.RSPlayer;
import org.rspeer.game.Game;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.query.results.SceneNodeQueryResults;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class PlayerQuery extends PathingEntityQuery<RSPlayer, Player, PlayerQuery> {

    private final Supplier<List<Player>> provider;

    public PlayerQuery(Supplier<List<Player>> provider) {
        this.provider = provider;
    }

    @Override
    public Supplier<List<Player>> getDefaultProvider() {
        return provider;
    }

    @Override
    protected SceneNodeQueryResults<Player> createQueryResults(Collection<Player> raw) {
        return new SceneNodeQueryResults<>(raw);
    }

    @Override
    public PlayerQuery self() {
        return this;
    }

    @Override
    public PlayerQuery indexes(int... indexes) {
        indexProvider(Game.getClient().getPlayers(), Player::new);
        return super.indexes(indexes);
    }
}
