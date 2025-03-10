import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;

/**
 * This is a MazeLoader class that implements the FileInterface interface and
 * which loads the structure of a maze from a text file.
 *
 *
 */
public class FileLoader implements FileInterface {
    /**
     * The set of valid characters found in the maze file
     */
    public static final Set<Character> VALID_TILES = new HashSet<>(Arrays.asList('#',
                                                                                ' ',
                                                                                'P', 'E', 'S'));

    /**
     * Loads the structure of a maze provided through a text file to a
     * 2d character array storing the rows of tiles of the maze.
     *
     * <p>
     *     This method takes a text file containing the dimensions of the
     *     maze on the top line and a character representation of the tiles
     *     of the maze in the subsequent lines and stores the maze tiles to
     *     2d array of chars.
     * </p>
     *
     * @param filename The file path to the maze file to be loaded.
     * @return A 2d of characters representing a collection of rows containing a number of tiles.
     * @throws MazeMalformedException If the maze is improperly formatted.
     * @throws MazeSizeMissmatchException If the maze dimensions provided in the top line
     * do not conform with the maze dimensions of the provided maze.
     * @throws IllegalArgumentException If there is a validation error, such as illegal characters
     * in the provided maze structure.
     * @throws FileNotFoundException If the filename provided does not lead to a proper file.
     */
    public char[][] load(String filename) throws MazeMalformedException,
                                                 MazeSizeMissmatchException,
                                                 IllegalArgumentException,
                                                 FileNotFoundException {
        String mazeTiles = "";
        File file;
        Scanner mazeScanner;
        // set of valid tile inputs
        file = new File(filename);
        mazeScanner = new Scanner(file);
        int rowsCounted = 0;
        int playerStartFlag = 0; // flag used to check the maze contains a starting position
        int endPointFlag = 0; // flag used to check the maze contains an end point.

        // stores the maze dimension info from the top line of the file.
        String mazeDimInfo = mazeScanner.nextLine();
        // loops through each line of the file and adds it to a string fileMaze.
        while (mazeScanner.hasNextLine()) {
            mazeTiles = mazeTiles + mazeScanner.nextLine();
            rowsCounted = rowsCounted + 1;
            // throws an exception if all rows being added are not the same length
            if (mazeTiles.length() % rowsCounted != 0) {
                throw new MazeMalformedException();
            }
        }

        int[] mazeDimensions = new int[2];
        String[] dimensions;
        dimensions = mazeDimInfo.trim().split("\\s"); // removes all whitespace

        // set mazedimension variables
        mazeDimensions[1] = Integer.parseInt(dimensions[0]);
        mazeDimensions[0] = Integer.parseInt(dimensions[1]);

        // validate dimensions of the maze by checking both the
        // number of tiles in the given maze and avoiding the case where the rows
        // and columns are flipped in the given dimensions by checking the number of counted rows.
        if (mazeTiles.length() != mazeDimensions[0] * mazeDimensions[1]
                || rowsCounted != mazeDimensions[1]) { // recent change from 1 to 0 ~~~
            throw new MazeSizeMissmatchException();
        }
        // validate dimensions by checking neither dimension is even.
        if (mazeDimensions[0] % 2 == 0 || mazeDimensions[1] % 2 == 0) {
            throw new IllegalArgumentException();
        }

        // split the fileMaze into an array of rows
        // set number of rows equal to the provided vertical dimensions
        String[] mazeRows = new String[mazeDimensions[1]];
        for (int i = 0; i < mazeDimensions[1]; i++) {
            //extracts maze rows from the fileMaze string.
            mazeRows[i] = mazeTiles.substring(i * mazeDimensions[0], (i + 1) * mazeDimensions[0]);
        }

        // Initialize 2d char array of maze elements.
        char[][] mazeStructure = new char[mazeDimensions[1]][mazeDimensions[0]];
        for (int i = 0; i < mazeRows.length; i++) {

            // populate 2d character array mazeStructure
            mazeStructure[i] = mazeRows[i].toCharArray();

            // check for invalid tiles and check if the maze contains a start and end point.
            for (int j = 0; j < mazeDimensions[0]; j++) {
                if (!VALID_TILES.contains(mazeRows[i].charAt(j))) {
                    throw new IllegalArgumentException();
                }
                if (mazeRows[i].charAt(j) == 'S') {
                    // if more than one player starts are found, playStartFlag will be more than 1.
                    playerStartFlag = playerStartFlag + 1;
                }
                if (mazeRows[i].charAt(j) == 'E') {
                    endPointFlag = 1;
                }
            }
        }
        // throws maze malformed exception if the maze either doesn't contain a start
        // or finish point.
        if (playerStartFlag != 1 || endPointFlag != 1) {
            throw new MazeMalformedException();
        }

        mazeScanner.close();
        return mazeStructure;
    }
}
