import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {
    private Model model;
    private final int[] STARTING_POSITION = new int[2];
    private final int[] MOVE_DOWN = new int[2];
    public static final String MAZE1_FILE = System.getProperty("user.dir") + "/test/" + "Maze1";

    @Before
    public void setup(){
        // ~~~ please replace with actual generalised destination
        this.model = new Model(MAZE1_FILE);

        STARTING_POSITION[0] = 1;
        STARTING_POSITION[1] = 1;

        MOVE_DOWN[1] = 1;


    }

    @Test
    public void testGetMaze(){ // ~~~ highly questionable.

        STARTING_POSITION[0] = 1;
        STARTING_POSITION[1] = 1;
        assertEquals(model.getMaze().getStartingPosition()[0], STARTING_POSITION[0]);
        assertEquals(model.getMaze().getStartingPosition()[1], STARTING_POSITION[1]);
    }

    @Test
    public void testGetPlayer(){
        assertEquals(model.getPlayer().getPosition()[0], STARTING_POSITION[0]);
        assertEquals(model.getPlayer().getPosition()[1], STARTING_POSITION[1]);

    }

    @Test
    public void testTraversedTilesEmpty(){
        assertTrue(model.getTraversedTilePositions().isEmpty());
    }

    @Test
    public void testBackTrackedTilesEmpty(){
        assertTrue(model.getBacktrackedTilePositions().isEmpty());
    }

    @Test
    public void testHasSolvedFalse(){
        assertFalse(model.hasSolved());
    }

    @Test
    public void testHasSolvedTrue(){
        int[] endPointPosition = new int[2];
        endPointPosition[0] = 5;
        endPointPosition[1] = 5;

        model.getPlayer().setPosition(endPointPosition);
        assertTrue(model.hasSolved());
    }

    @Test
    public void testMovePlayerBlocking(){
        int[] moveRight = new int[2];
        moveRight[0] = 1;

        model.movePlayer(moveRight);
        assertEquals(model.getPlayer().getPosition()[0], STARTING_POSITION[0]);
        assertEquals(model.getPlayer().getPosition()[1], STARTING_POSITION[1]);
    }

    @Test
    public void testMovePlayerEmptyPath() {
        model.movePlayer(MOVE_DOWN);

        assertEquals(model.getPlayer().getPosition()[0], STARTING_POSITION[0]);
        assertEquals(model.getPlayer().getPosition()[1], STARTING_POSITION[0] + 1);
    }

    @Test
    public void testGetTraversedTilePositions() {
        model.movePlayer(MOVE_DOWN);

        // check starting tile is traversed after moving from it.
        assertEquals(model.getTraversedTilePositions().get(0)[0], STARTING_POSITION[0]);
        assertEquals(model.getTraversedTilePositions().get(0)[1], STARTING_POSITION[1]);
    }

    @Test
    public void testGetBacktrackedTilePositions() {

        final int[] MOVE_UP = new int[2];
        MOVE_UP[1] = -1;
        model.movePlayer(MOVE_DOWN);
        model.movePlayer(MOVE_UP);
        model.movePlayer(MOVE_DOWN);

        // test that the 2nd backtracked tile is the starting tile
        assertEquals(model.getBacktrackedTilePositions().get(1)[0], STARTING_POSITION[0]);
        assertEquals(model.getBacktrackedTilePositions().get(1)[1], STARTING_POSITION[1]);
    }
}