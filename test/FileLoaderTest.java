import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileLoaderTest {
    private FileLoader fileLoader;

    public static final String MAZE1_SIZE_MISS_MATCH = System.getProperty("user.dir")
                                                        + "/test/"
                                                        + "Maze1SizeMissMatch";
    public static final String MAZE1_UNEQUAL_ROW_LENGTHS = System.getProperty("user.dir")
                                                            + "/test/"
                                                            + "Maze1UnequalRowLength";
    public static final String MAZE1_EVEN_MAZE_DIMENSION = System.getProperty("user.dir")
                                                            + "/test/"
                                                            + "Maze1EvenMazeDimension";
    public static final String MAZE1_INVALID_TILE = System.getProperty("user.dir")
                                                     + "/test/"
                                                     + "Maze1InvalidTile";
    public static final String MAZE1_MISSING_ENDPOINT = System.getProperty("user.dir")
                                                     + "/test/"
                                                     + "Maze1MissingEndPoint";
    public static final String MAZE1_MISSING_PLAYER_START = System.getProperty("user.dir")
                                                            + "/test/"
                                                            + "Maze1MissingPlayerStart";
    public static final String MAZE1_MULTIPLE_PLAYER_STARTS = System.getProperty("user.dir")
                                                                + "/test/"
                                                                + "Maze1MultiplePlayerStarts";

    @Before
    public void setup() {
        this.fileLoader = new FileLoader();
    }

    @Test
    public void testLoadedMazeRows() throws Exception {
        char[][] mazeStructure = fileLoader.load(ModelTest.MAZE1_FILE);
        // check for proper number of rows
        assertEquals(mazeStructure.length, 7);
    }

    @Test
    public void testLoadedMazeColumns() throws Exception {
        char[][] mazeStructure = fileLoader.load(ModelTest.MAZE1_FILE);
        // check for proper number of columns in all rows.
        for (int i = 0; i < mazeStructure.length; i++) {
            assertEquals(mazeStructure[i].length, 7);
        }

    }

    @Test (expected = MazeSizeMissmatchException.class)
    public void testMazeSizeMissMatch() throws Exception {
        fileLoader.load(MAZE1_SIZE_MISS_MATCH);
    }

    @Test (expected = MazeMalformedException.class)
    public void testMazeMalformed() throws Exception {
        fileLoader.load(MAZE1_UNEQUAL_ROW_LENGTHS);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEvenMazeDimension() throws Exception {
        fileLoader.load(MAZE1_EVEN_MAZE_DIMENSION);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidTile() throws Exception {
        fileLoader.load(MAZE1_INVALID_TILE);
    }

    @Test (expected = MazeMalformedException.class)
    public void testMissingEndPoint() throws Exception {
        fileLoader.load(MAZE1_MISSING_ENDPOINT);
    }

    @Test (expected = MazeMalformedException.class)
    public void testMissingPlayerStart() throws Exception {
        fileLoader.load(MAZE1_MISSING_PLAYER_START);
    }

    @Test (expected = MazeMalformedException.class)
    public void testMultiplePlayerStarts() throws Exception {
        fileLoader.load(MAZE1_MULTIPLE_PLAYER_STARTS);
    }




}