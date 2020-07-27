package jag.game.scene;

import jag.RSProvider;

public interface RSCollisionMap extends RSProvider {

    int WIDTH = 104;
    int LENGTH = 104;

    default int getFlag(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= LENGTH) {
            return -1;
        }
        return getFlags()[x][y];
    }

    int[][] getFlags();

    int getWidth();

    int getInsetX();

    int getInsetY();

    int getHeight();

}