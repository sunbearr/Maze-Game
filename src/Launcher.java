import tiles.*;

/**
 * This class facilitates launching the text or GUI maze solver
 */
public class Launcher {

    /**
     * The user's current directory and location of playable maze files.
     */
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir").replace("\\",
            "/");

    /**
     * The main method used to start solving the maze. Takes user input on whether to
     * use a text or graphical view. User also selects the maze to solve view command
     * line argument or through a dropdown menu.
     * @param args Either the name of maze to be displayed with text or GUI to use a graphical view.
     */
    public static void main(String[] args) {
        if (args[0].equals("GUI")) {
            GraphicalView gui = new GraphicalView();
            GraphicalMazeController controller = new GraphicalMazeController(gui);
            controller.solveMazeFile();
        } else {
            String gamefile = CURRENT_DIRECTORY + "/" + args[0];
            TextView ui = new TextView();
            TextMazeController textMazeController = new TextMazeController(gamefile, ui);
            textMazeController.solveMaze();
        }
    }
}
