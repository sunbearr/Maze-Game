import tiles.*;

import java.util.Arrays;
import java.util.List;

/**
 * This class is a view class representing the maze with text-based coloured rectangles.
 */
public class TextView implements UserInterface {

    /**
     * Defines a rectangle character to be coloured and resets the colour.
     * When placed after a colour definition, this allows a single coloured
     * rectangle to be created.
     */
    private static final String RECTANGLE_BUILDER = "█" + "\u001B[0m";

    /**
     * This method draws the maze state, including tile positions, player position and
     * traversed/backtracked tiles as a string of coloured rectangle characters.
     * @param mazeModel The model containing the maze state to draw.
     * @param playerPosition The position of the player in the maze.
     */
    public void draw(Model mazeModel, int[] playerPosition) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mazeModel.getMaze().getDimensions()[1]; i++) {
            for (int j = 0; j < mazeModel.getMaze().getDimensions()[0]; j++) {

                int[] tilePosition = new int[2];
                tilePosition[0] = j;
                tilePosition[1] = i;

                // add the tile to the string representation unless the player is on it.
                if (Arrays.equals(tilePosition, playerPosition)) {
                    String tileRepresentation = mazeModel.getPlayer().toString()
                                                    + RECTANGLE_BUILDER;
                    sb.append(tileRepresentation);
                    // check if an empty tile to be drawn has been traversed or backtracked.
                } else if (ListContainsArrayChecker.containsArrayList(tilePosition,
                                                        mazeModel.getBacktrackedTilePositions())) {

                    BacktrackedPath bp = new BacktrackedPath();
                    String tileRepresentation = bp.toString() + RECTANGLE_BUILDER;
                    sb.append(tileRepresentation);
                } else if (ListContainsArrayChecker.containsArrayList(tilePosition,
                                                        mazeModel.getTraversedTilePositions())) {
                    TraversedPath tp = new TraversedPath();
                    String tileRepresentation = tp.toString() + RECTANGLE_BUILDER;
                    sb.append(tileRepresentation);

                } else {
                    String tile = mazeModel.getMaze().getTile(tilePosition).toString();
                    String tileRepresentation = tile + RECTANGLE_BUILDER;
                    sb.append(tileRepresentation);
                }

            }
            sb.append("\n");
        }
        System.out.println(sb); // draw maze representation to system out.

    }

}
