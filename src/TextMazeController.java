/**
 * Maze controller is a class that controls and updates the maze model and a view class
 * to facilitate maze navigation and representation.
 *
 * <p>
 *     This class also takes user keyboard inputs and updates the game state in response.
 * </p>
 */
public class TextMazeController extends MazeController {
    /**
     * The model containing the maze to be navigated
     */
    private final Model model;

    /**
     * The userinterface view to draw the state of the maze
     */
    private final UserInterface ui;

    /**
     * A constructor method used to instantiate a new MazeController object.
     * @param gameFile The file location of the maze to be loaded.
     * @param ui The view instance used to display the game state.
     */
    public TextMazeController(String gameFile, UserInterface ui) {
        this.model = new Model(gameFile);
        this.ui = ui;
    }

    /**
     * This method programmatically moves the player until the maze is solved
     * or no valid moves are left (the maze is found to be unsolvable).
     */
    public void solveMaze() {
        while (!this.model.hasSolved()) {
            autoSolveStep(this.model, this.ui);
            if (isStuck()) {
                System.out.println(UNSOLVABLE_MESSAGE);
                break;
            }
        }
    }
}
