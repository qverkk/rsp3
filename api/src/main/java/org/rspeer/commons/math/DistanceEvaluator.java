package org.rspeer.commons.math;

import org.rspeer.game.adapter.type.SceneNode;
import org.rspeer.game.position.Position;

/**
 * Interface for distance calculations
 */
public interface DistanceEvaluator {

    double evaluate(int x1, int y1, int x2, int y2);

    default double evaluate(SceneNode src, SceneNode dst) {
        Position p1 = src.getPosition();
        Position p2 = dst.getPosition();
        return evaluate(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
}

