package exceptions;

/**
 * A custom exception thrown when trying to load a maze that is improperly formatted.
 */
public class MazeMalformedException extends Exception {
    /**
     * Constructs a new MazeMalformedException with no arguments
     */
    public MazeMalformedException() {
        super();
    }

    /**
     * A secondary constructor that prints a sets the informative message accessible
     * when the exception is thrown.
     * @param message The message to be conveyed when exception is thrown.
     */
    public MazeMalformedException(String message) {
        super(message);
    }
}
