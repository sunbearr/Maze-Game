/**
 * This is an abstract controller class that provides a common functionality to multiple
 * controller subclasses that control different views.
 */
public abstract class MazeController {


    /**
     * Stores whether the player is stuck with no valid moves, meaning the maze is unsolvable.
     */
    private boolean stuck = false;

    /**
     * The possible moves (RIGHT, DOWN, LEFT and UP) that can be made by the player.
     */
    public static final int[][] POSSIBLE_MOVES = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * The message to be printed if the maze is determined to be unsolvable.
     */
    public static final String UNSOLVABLE_MESSAGE = "The maze is unsolvable";

    /**
     *Computes a single move of an algorithmic automatic solving of the maze.
     *<p>
     * This method checks all possible moves and will move to the first un-traversed
     * tile that can be reached.If no un-traversed tile can be reached with a single move,
     * then the player is moved to a previously traversed tile.
     * If all possible moves lead to a blocking or already backtracked tile,
     * the player is stuck and the maze is unsolvable.
     *</p>
     * @param model The model containing the maze to be navigated.
     * @param ui The implemented UserInterface class used to draw the maze.
     */
    public void autoSolveStep(Model model, UserInterface ui) {
        boolean unTraversedTileFound = false;
        int[] traversedMove = new int[2];

        // assume player is stuck until we find a valid move.
        this.stuck = true;

        for (int[] move : POSSIBLE_MOVES) {

            int[] newPosition = new int[2];
            newPosition[0] = model.getPlayer().getPosition()[0] + move[0];
            newPosition[1] = model.getPlayer().getPosition()[1] + move[1];

            // check if move can be made
            // go through each move option and move to a non-blocking tile
            // go to a traversed tile only if no un-traversed moves exist.
            if (!model.getMaze().getTile(newPosition).isBlocking()
                    && !ListContainsArrayChecker.containsArrayList(newPosition, model.getBacktrackedTilePositions())) {

                // valid move found.
                this.stuck = false;

                // player will move to an un-traversed tile if available.
                if (!ListContainsArrayChecker.containsArrayList(newPosition, model.getTraversedTilePositions())) {
                    model.movePlayer(move);
                    unTraversedTileFound = true;
                } else {
                    traversedMove = move;
                }
            }

        }
        // moves player to traversed tile only if no un-traversed tile is available.
        if (!unTraversedTileFound) {
            model.movePlayer(traversedMove);

        }
        // updates the maze view after moving the player.
        if (model.hasSolved() || this.stuck) {
            ui.draw(model, model.getPlayer().getPosition());
        }
    }

    /**
     * Gets whether the player is stuck in the maze with no valid
     * moves left (maze is unsolvable).
     * @return True if the player is stuck, false otherwise.
     */
    public boolean isStuck() {
        return this.stuck;
    }


}
