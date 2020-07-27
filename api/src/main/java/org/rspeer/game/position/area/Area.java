package org.rspeer.game.position.area;

import org.rspeer.game.adapter.type.SceneNode;
import org.rspeer.game.position.Position;

import java.util.List;

public interface Area {

    int getFloorLevel();

    List<Position> getTiles();

    boolean contains(SceneNode entity);

    static Area rectangular(Position a, Position b) {
        return new RectangularArea(a.getFloorLevel(), a, b);
    }
}