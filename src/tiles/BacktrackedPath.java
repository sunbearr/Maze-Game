package tiles;

import java.awt.*;

/**
 * This class is a type of tile representing a tile that has been backtracked
 * on by the player (the player has stepped on them more than once).
 */
public class BacktrackedPath extends Tile {
    @Override
    public String toString() {
        return "\u001B[33m";
    } //orange square

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
}
