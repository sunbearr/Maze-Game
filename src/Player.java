import java.awt.*;

/**
 * This is a Player class which represents the player and their position in the maze.
 *
 *
 */
public class Player {
    /** An integer array representing the player's position.
     *
     */
    private int[] position;

    /**
     * Constructs a new Player object.
     * <p>
     *     Constructs a player object with a provided starting x and y position.
     *     The provided position should be within the bounds of the maze, i.e. between
     *     0 and the row/column length.
     * </p>
     * @param position The initial position that the player starts at in the maze.
     */
    public Player(int[] position) {
        setPosition(position);
    }

    /**
     * Provides a string representation of the player.
     * @return A string representation of the player.
     */
    public String toString() {
        return "\u001B[34m";
    } // Blue

    /**
     * Sets the current position of the player.
     * <p>
     *    The updated position should be within the bounds of the maze, i.e. between
     *    0 and the row/column length.
     * </p>
     *
     * @param position The maze position to set the player to.
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * Gets the current position of the player
     * @return The current position of the player in the maze.
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Gets the colour representing the player. Used when displaying the player in a GUI view.
     * @return The colour representing the player in the maze.
     */
    public Color getColor() {
        return Color.BLUE;
    }

}
