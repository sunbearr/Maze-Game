package tiles;

/**
 * This is a Tiles.PlayerStart class, representing a type of maze Tiles.Tile.
 *
 * <p>
 *      Tiles.PlayerStart is a type of maze tile that is never blocking and marks the
 *      starting point of the player in the maze.
 * </p>
 */
public class PlayerStart extends Tile {
    /**
     * Provides a string representation for the Tiles.PlayerStart tile.
     * @return A string representing the Tiles.PlayerStart tile.
     */
    @Override
    public String toString() {
        return "\u001B[37m"; // White
    }
}
