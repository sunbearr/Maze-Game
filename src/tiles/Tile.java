package tiles;

import java.awt.Color;

/**
 * This is an abstract class for Maze tiles designed to provide base functionality
 * for the tiles in the maze.
 */
public abstract class Tile {
    /**
     * Sets the blocking state of a maze tile.
     *
     * @return True if the tile is blocking (prevents player movement) and false otherwise.
     */
    public boolean isBlocking() {
        return false;
    }

    /**
     * Provides a string representation of the maze tile.
     * @return The string representation of the maze tile.
     */
    public abstract String toString();

    /**
     * Gets the colour representing the tile
     * @return The colour representing the tile.
     */
    public Color getColor() {
        return Color.WHITE;
    }
}
