import java.util.ArrayList;
import tiles.*;

/**
 * Model is a class that stores game information on the player and the maze to be navigated.
 * <p>
 *     This class also facilitates updating the game state in response to player actions.
 * </p>
 */
public class Model {
    /**
     * The maze to be navigated by the player.
     */
    private final Maze maze;
    /**
     * The player object that moves through the maze.
     */

    private final Player player;

    /**
     * The array list of tiles traversed (stepped on once) by the player.
     */
    private final ArrayList<int[]> traversedTilePositions = new ArrayList<>();

    /**
     * The array list of tiles backtracked (stepped on more than once) by the player.
     */
    private final ArrayList<int[]> backtrackedTilePositions = new ArrayList<>();

    /**
     * A constuctor method used to instantiate a new Model object, including its maze
     * and player objects.
     * @param filename The name of the file from which to load the maze
     *                 of the model.
     */
    public Model(String filename) {
        this.maze = new Maze(filename);

        this.player = new Player(this.maze.getStartingPosition());
    }

    /**
     * Gets the maze object of the model
     * @return The maze object of the model.
     */
    public Maze getMaze() {
        return this.maze;
    }

    /**
     * Gets the player object of the model.
     * @return The player object of the model.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the array list of tile positions that have been traversed by the player.
     * @return The array list of tile positions that have been traversed by the player.
     */
    public ArrayList<int[]> getTraversedTilePositions() {
        return this.traversedTilePositions;
    }

    /**
     * Gets the array list of tile positions that have been backtracked by the player.
     * @return The array list of tile positions that have been backtracked by the player
     */
    public ArrayList<int[]> getBacktrackedTilePositions() {
        return this.backtrackedTilePositions;
    }

    /**
     * Finds whether the maze has been solved by the player or not.
     * @return True if the maze has been solved and false if it has not.
     *         A maze is considered solved if the player's position is
     *         on the endpoint of the maze.
     */
    public boolean hasSolved() {
        // return true only if the player's position is on the endpoint.
        return this.maze.getTile(this.player.getPosition()).toString().equals(EndPoint.COLOUR_ID);
    }

    /**
     * Provides functionality for moving the player in the maze.
     * <p>
     *     As well as updating the player's position in the maze, this method also validates
     *     whether the player can move depending on whether the tile to be moved to is
     *     blocking or not. Additionally, upon moving this method updates the lists of
     *     traversed and backtracked tiles depending on whether the current tile and tile to
     *     be moved to have already been traversed.
     * </p>
     * @param movement An array containing the x and y movement a player takes in a single
     *                 step through the maze.
     *
     */
    public void movePlayer(int[] movement) {
        if (hasSolved()) {
            return;
        }

        //check if the tile to be moved to is blocking or not.
        int[] updatedPosition = new int[2];
        updatedPosition[0] = this.player.getPosition()[0] + movement[0];
        updatedPosition[1] = this.player.getPosition()[1] + movement[1];
        if (this.maze.getTile(updatedPosition).isBlocking()) {
            return;
        }
        // update list of traversed tiles
        updateOccupiedTile(this.player.getPosition(), updatedPosition);

        // update player position
        this.player.setPosition(updatedPosition);
    }

    /**
     * Updates the traversedTilePositions and backtrackedTilePositions in the maze
     * according to the player's current position and position upon moving once.
     * @param currentPosition The current player position prior to moving
     * @param futurePosition The updated player position after moving.
     */
    private void updateOccupiedTile(int[] currentPosition, int[] futurePosition) {

        // Current tile is only set to be backtracked if the next tile a player
        // steps on to is not traversed, otherwise it is set to be traversed.
        if (!ListContainsArrayChecker.containsArrayList(futurePosition, this.traversedTilePositions)
                && !ListContainsArrayChecker.containsArrayList(futurePosition,
                                                                this.backtrackedTilePositions)) {
            this.traversedTilePositions.add(currentPosition);
        } else {
            this.backtrackedTilePositions.add(currentPosition);
        }
    }
}
