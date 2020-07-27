package org.rspeer.game.position.area;

import org.rspeer.commons.Range;
import org.rspeer.game.adapter.type.SceneNode;
import org.rspeer.game.position.Position;

import java.util.ArrayList;
import java.util.List;

class RectangularArea implements Area {

    private final int floorLevel;

    private final Range xs;
    private final Range ys;
    private final List<Position> ps;

    RectangularArea(int floorLevel, Position a, Position b) {
        this.floorLevel = floorLevel;
        this.xs = Range.ordered(a.getX(), b.getX());
        this.ys = Range.ordered(a.getY(), b.getY());
        this.ps = new ArrayList<>();

        for (int x = xs.minimum(); x <= xs.maximum(); x++) {
            for (int y = ys.maximum(); y >= ys.minimum(); y--) {
                ps.add(new Position(x, y));
            }
        }
    }

    @Override
    public int getFloorLevel() {
        return floorLevel;
    }

    @Override
    public List<Position> getTiles() {
        return ps;
    }

    @Override
    public boolean contains(SceneNode entity) {
        return entity != null
                && entity.getFloorLevel() == floorLevel
                && xs.within(entity.getX())
                && ys.within(entity.getY());
    }
}
