import javax.swing.*;

/**
 * This is a GUI view class implementing the UserInterface interface.
 *
 * <p>
 *     This class is responsible for displaying the maze using Java Swing
 *     GUI elements.
 * </p>
 */
public class GraphicalView implements UserInterface {
    /**
     * The JPanel used to contain the grid of coloured rectangles representing
     * the maze state.
     */
    private JPanel mazePanel;

    /**
     * The JFrame containing the mazePanel representing the maze.
     */
    private JFrame frame;

    /**
     * The width in pixels of the frame to be drawn.
     */
    public static final int FRAME_WIDTH = 1200;

    /**
     * The height in pixels of the frame to be drawn.
     */
    public static final int FRAME_HEIGHT = 600;

    /**
     * This method draws/redraws the maze based on the maze model.
     * @param mazeModel The maze model whose tiles and player status are used to draw the maze.
     * @param playerPosition The position of the player in the maze.
     */
    public void draw(Model mazeModel, int[] playerPosition) {
        this.mazePanel.repaint();
    }

    /**
     * Initialises the frame on which to draw maze elements / file selection
     */
    public void initialiseFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * Initialises a mazePanel used to store the coloured rectangle grid representing the maze.
     * @param mazeModel The maze model whose tile and player information is used by mazePanel tp
     *                  draw the maze.
     */
    public void initialiseMaze(Model mazeModel) {
        initialiseFrame();
        this.mazePanel = new MazePanel(mazeModel);
        this.frame.add(this.mazePanel);
        this.frame.setVisible(true);
    }

    /**
     * Gets the frame belonging to the instance of GraphicalView
     * @return The frame belonging to the instance of GraphicalView
     */
    public JFrame getFrame() {
        return this.frame;
    }
}