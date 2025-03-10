package tiles;

import java.awt.*;

/**
 * TraversedPath is a type of tile representing a tile that has been stepped on
 * exactly once by the player.
 *
 */
public class TraversedPath extends Tile{
    @Override
    public String toString() {
        return "\u001B[35m";
    } //purple square

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }
}
