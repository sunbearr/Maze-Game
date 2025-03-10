import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This is a controller class that controls and updates a Model and GUI view class
 * to facilitate automated navigation through a maze represented using a GUI.
 */
public class GraphicalMazeController extends MazeController {

    /**
     * The graphical user-interface view class object used to draw the maze.
     */
    private final GraphicalView gui;

    /**
     * Maze1 playable maze file location
     */
    public static final String MAZE_1 = Launcher.CURRENT_DIRECTORY  + "/" + "Maze1";

    /**
     * Maze2 playable maze file location
     */
    public static final String MAZE_2 = Launcher.CURRENT_DIRECTORY  + "/" + "Maze2";

    /**
     * Maze3 playable maze file location
     */
    public static final String MAZE_3 = Launcher.CURRENT_DIRECTORY  + "/" + "Maze3";

    public static final String SELECT_MAZE_BUTTON_TEXT = "Select Maze File";

    /**
     * Constructor method used to create a new AutoSolverMazeController instance.
     * @param ui The user interface class used to draw the maze.
     */
    public GraphicalMazeController(UserInterface ui) {
        this.gui = (GraphicalView) ui;
    }

    /**
     * This method programmatically moves the player until the maze is solved
     * or no valid moves are left (the maze is found to be unsolvable).
     * @param model The model containing the maze to be solved.
     * @param ui The user-interface view class used to draw the maze.
     */
    public void solve(Model model, UserInterface ui) {
        this.gui.initialiseMaze(model);
        while (!model.hasSolved()) {
            autoSolveStep(model, ui);
            if (isStuck()) {
                System.out.println(UNSOLVABLE_MESSAGE);
                break;
            }
        }
    }

    /**
     * Creates a file selector from which the user can select a maze to be
     * programmatically solved.
     *
     * <p>
     *     This method also facilitates the automatic solving of the maze chosen,
     *     with the solving of the maze being an actionEvent triggered by selecting
     *     the maze.
     * </p>
     */
    public void solveMazeFile() {
        // initialize the frame and file selection drop-down menu.
        this.gui.initialiseFrame();
        JComboBox<String> fileSelector = new JComboBox<>();
        fileSelector.addItem(MAZE_1);
        fileSelector.addItem(MAZE_2);
        fileSelector.addItem(MAZE_3);

        // create a button to select the chosen maze
        JButton selectMaze = new JButton(SELECT_MAZE_BUTTON_TEXT);

        // initialize the frame properties and add the drop-down menu and selection button.
        this.gui.getFrame().setLayout(new FlowLayout());
        this.gui.getFrame().setVisible(true);
        this.gui.getFrame().add(fileSelector);
        this.gui.getFrame().add(selectMaze);

        // Set automatically solving the maze chosen as an actionEvent triggered
        // by hitting the maze select button.
        selectMaze.addActionListener((ActionEvent solveMaze) -> {
            String chosenFile = (String) fileSelector.getSelectedItem();
            Model model = new Model(chosenFile);
            solve(model, gui);
        });
    }
}
