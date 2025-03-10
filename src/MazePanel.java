import tiles.BacktrackedPath;
import tiles.TraversedPath;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.lang.Math;

/**
 * This class extends JPanel and is responsible for storing and drawing
 * a graphical representation of the maze as a grid of coloured rectangles.
 */
public class MazePanel extends JPanel {
    /**
     * The Model containing the maze to represent graphically.
     */
    private final Model mazeModel;

    /**
     * The desired side length in pixels of the maze to be drawn.
     */
    public static final int MAZE_SIZE = 500;

    /**
     * This is a constructor method used to create a new AutoSolverMazePanel object.
     * @param mazeModel The model containing the maze and player to be drawn.
     */
    public MazePanel(Model mazeModel) {
        this.mazeModel = mazeModel;
    }

    /**
     * This method overrides the paintComponent method of JPanel so that
     * when we repaint this panel, we have a custom drawing method, allowing
     * us to draw and redraw the maze to represent the maze state.
     *
     * @param g the Graphics object used to draw the maze onto.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // necessary for correct painting.
        super.paintComponent(g);
        drawMaze(g, this.mazeModel);
    }

    /**
     * This method is reponsible for drawing the GUI elements to
     * represent the maze.
     * <p>
     *     The maze is represented using a grid of rectangles, each
     *     with a colour unique to the type of tile in the maze.
     * </p>
     * @param g The Graphics object belonging to the JPanel.
     * @param mazeModel The model containing the maze state to be drawn
     */
    private void drawMaze(Graphics g, Model mazeModel) {
        // side length (in pixels) of each square tile to be drawn
        int tileSize = (int) Math.ceil((double)
                                        MAZE_SIZE / Math.max(mazeModel.getMaze().getDimensions()[1],
                                                        mazeModel.getMaze().getDimensions()[0]));

        // Loop through each tile in the maze and draw a coloured rectangle
        for (int row = 0; row < mazeModel.getMaze().getDimensions()[1]; row++) {
            for (int column = 0; column < mazeModel.getMaze().getDimensions()[0]; column++) {
                int[] tilePosition = new int[2];
                tilePosition[0] = column;
                tilePosition[1] = row;

                // The pixel position of the tile to be drawn corresponds
                // to the row and column position of the tile in the maze.
                int verticalPosition = row * tileSize;
                int horizontalPosition = column * tileSize;

                // set the rectangle colour to the colour of the tile type at the
                // current position
                if (Arrays.equals(mazeModel.getPlayer().getPosition(), tilePosition)) {
                    g.setColor(mazeModel.getPlayer().getColor());

                } else if (ListContainsArrayChecker.containsArrayList(tilePosition,
                                                        mazeModel.getBacktrackedTilePositions())) {
                    BacktrackedPath bp = new BacktrackedPath();
                    g.setColor(bp.getColor());

                } else if (ListContainsArrayChecker.containsArrayList(tilePosition,
                                                        mazeModel.getTraversedTilePositions())) {
                    TraversedPath tp = new TraversedPath();
                    g.setColor(tp.getColor());

                } else {
                    g.setColor(mazeModel.getMaze().getTile(tilePosition).getColor());
                }
                // Fills the rectangle with the selected colour.
                g.fillRect(horizontalPosition, verticalPosition, tileSize, tileSize);

            }
        }
    }

}
