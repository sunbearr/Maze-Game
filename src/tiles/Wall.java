package tiles;

import java.awt.Color;

/**
 * This is a wall class representing a type of tile in the maze.
 *
 * <p>
 *     A wall is a type of maze tile that is always blocking.
 * </p>
 *
 */
public class Wall extends Tile {

    /**
     * Sets the blocking state of a maze tile.
     *
     * @return True as walls are always blocking.
     */
    @Override
    public boolean isBlocking() {
        return true;
    }

    /**
     * Provides the string representation of the wall class.
     * @return A string representing the wall class.
     */
    @Override
    public String toString() {
        return "\u001B[31m";
    } // REd

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
