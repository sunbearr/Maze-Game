import tiles.*;
import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.FileNotFoundException;

/**
 * Maze is a class that stores the structure of tiles in the maze.
 */
public class Maze {
    /**
     * The starting coordinate position of the player in the maze.
     */
    private final int[] startingPosition = new int[2];

    /**
     * The 2d array of characters representing tiles in the maze.
     */
    private char[][] mazeStructure;

    /**
     * The string uniquely representing a Wall tile.
     */
    public static final char WALL = '#';

    /**
     * The string uniquely representing an EmptyPath tile.
     */
    public static final char EMPTY_PATH = ' ';

    /**
     * The string uniquely representing an EndPoint tile.
     */
    public static final char END_POINT = 'E';

    /**
     * The string uniquely representing a PlayerStart tile.
     */
    public static final char PLAYER_START = 'S';

    /**
     * The message to be printed when a maze malformed exception is found.
     */
    public static final String MAZE_MALFORMED_MSG = "The maze is malformed.";


    /**
     * The message to be printed when a maze size missmatch exception is found.
     */
    public static final String MAZE_SIZE_MISSMATCH_MSG = "There was a maze size mismatch";

    /**
     * The message to be printed when a file not found exception is found.
     */
    public static final String FILE_NOT_FOUND_MSG = "The provided file was not found.";

    /**
     * The message to be printed when an illegal argument exception is found.
     */
    public static final String ILLEGAL_ARGUMENT_MSG = "Illegal argument, check for invalid"
                                                        + " characters or even maze dimensions";

    /**
     * Constructs a new Maze object given a filename leading to a text containing
     * information needed to construct a maze.
     * @param filename the name of the maze text file to construct the maze structure from.
     */
    public Maze(String filename) {
        this.mazeStructure = loadMaze(filename);
        addPlayerStart(); // initializes the starting position of the player from the given maze.
    }

    /**
     * Loads the structure of a maze from a text file using the MazeLoader class.
     *
     * <p>
     *     This method loads a provided maze and catches any errors arising from attempting
     *     to load a maze provided in a text file.
     * </p>
     * @param filename The name of the file containing the maze structure and dimensions.
     * @return the 2d character array mazeStructure of the loaded maze.
     */
    public char[][] loadMaze(String filename) {
        FileLoader loader = new FileLoader();

        try {
            mazeStructure =  loader.load(filename);

        } catch (MazeMalformedException e1) {
            System.out.println(MAZE_MALFORMED_MSG);
            System.exit(1);

        } catch (MazeSizeMissmatchException e2) {
            System.out.println(MAZE_SIZE_MISSMATCH_MSG);
            System.exit(1);

        } catch (FileNotFoundException e3) {
            System.out.println(FILE_NOT_FOUND_MSG);
            System.exit(1);

        } catch (IllegalArgumentException e4) {

            System.out.println(ILLEGAL_ARGUMENT_MSG);
            System.exit(1);
        }
        return mazeStructure;
    }

    /**
     * Gets the dimensions (number of rows/columns) of the maze
     * @return returns the dimensions (number of rows/columns) of the maze
     */
    public int[] getDimensions() {
        int rows = mazeStructure.length;
        // we validated that all rows are same length when loading.
        int columns = mazeStructure[0].length;
        int[] mazeDimensions = new int[2];
        mazeDimensions[0] = columns;
        mazeDimensions[1] = rows;
        return mazeDimensions;
    }

    /**
     * Gets the tile object at a certain position in the maze.
     * @param position The position of the object to be returned.
     * @return The tile in the maze at the position provided.
     */
    public Tile getTile(int[] position) {
        switch (mazeStructure[position[1]][position[0]]) {
            case WALL:
                return new Wall();
            case EMPTY_PATH:
                return new EmptyPath();
            case PLAYER_START:
                return new PlayerStart();
            case END_POINT:
                return new EndPoint();
        }
        // we validated the maze to ensure no illegal characters, this should never be returned.
        return null;
    }

    /**
     * Sets the starting position of the player.
     */
    public void addPlayerStart() {
        for (int i = 0; i < getDimensions()[1]; i++) {
            for (int j = 0; j < getDimensions()[0]; j++) {
                if (mazeStructure[i][j] == PLAYER_START) {
                    this.startingPosition[0] = j;
                    this.startingPosition[1] = i;
                }
            }
        }
    }

    /**
     * Gets the player starting position of the maze.
     * @return The player's starting point position in the maze.
     */
    public int[] getStartingPosition() {
        return this.startingPosition;
    }

}
