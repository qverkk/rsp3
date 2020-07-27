package org.rspeer.game.movement.pathfinding;

import org.rspeer.game.effect.Direction;
import org.rspeer.game.movement.pathfinding.util.CollisionFlags;
import org.rspeer.game.position.Position;
import org.rspeer.game.scene.Scene;

import java.util.*;

public class Pathing {

    public static int getDistance(Position from, Position to) {
        return getDistance(from, to, false);
    }

    public static int getDistance(Position from, Position to, boolean ignoreStartBlocked) {
        Map<Position, Integer> scoreMap = new HashMap<>();

        Queue<Position> evaluate = new PriorityQueue<>(Comparator.comparingInt(
                position -> scoreMap.getOrDefault(position, Integer.MAX_VALUE)
        ));

        scoreMap.put(from, 0);
        evaluate.add(from);

        while (!evaluate.isEmpty()) {
            Position next = evaluate.remove();
            int nextScore = scoreMap.get(next) + 1;

            for (Position neighbour : getWalkableNeighbours(next, ignoreStartBlocked)) {
                int neighbourScore = scoreMap.getOrDefault(neighbour, Integer.MAX_VALUE);
                if (nextScore >= neighbourScore) {
                    continue;
                }

                scoreMap.put(neighbour, nextScore);
            }
        }


        return scoreMap.getOrDefault(to, Integer.MAX_VALUE);
    }

    public static List<Position> getWalkableNeighbours(Position from, boolean ignoreStartBlocked) {
        List<Position> result = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            Position to = from.translate(dir.getXOffset(), dir.getYOffset());
            if (!to.isInScene()) {
                continue;
            }

            if (CollisionFlags.checkWalkable(dir, Scene.getCollisionFlag(from), Scene.getCollisionFlag(to), ignoreStartBlocked)) {
                result.add(to);
            }
        }

        return result;
    }
}
