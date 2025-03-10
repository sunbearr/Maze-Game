package exceptions;

/**
 * A custom exception thrown when attempting to load a file whose stated dimensions
 * differ from the actual dimensions of provided maze.
 */
public class MazeSizeMissmatchException extends Exception {
    /**
     * A no-argument constructor used to construct a new MazeSizeMissMatch exception.
     */
    public MazeSizeMissmatchException() {
        super();
    }

    /**
     * A secondary constructor that prints a sets the informative message accessible
     * when a MazeSizeMissMatch exception is thrown.
     * @param message The message to be conveyed when exception is thrown.
     */
    public MazeSizeMissmatchException(String message) {
        super(message);
    }
}
