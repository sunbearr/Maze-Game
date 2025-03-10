package tiles;

import java.awt.*;

/**
 * This class is a type of tile representing the end point of the maze,
 * stepping on this tile ends the game.
 */
public class EndPoint extends Tile {
    public static final String COLOUR_ID = "\u001B[32m";

    @Override
    public String toString() {
        return COLOUR_ID;
    } // Green

    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
