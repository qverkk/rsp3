package org.rspeer.game.effect;

public enum Direction {

    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1);

    private final int xOffset;
    private final int yOffset;

    Direction(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public int getXOffset() {
        return xOffset;
    }
}
