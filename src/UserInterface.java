/**
 * This interface provides a template for view classes used to draw the maze state.
 */
public interface UserInterface {
    /**
     * Template method, classes implementing this method will draw the maze state
     * using various visual elements like text for graphical shapes.
     * @param model The model containing the maze state to draw.
     * @param playerPosition The current position of the player in the maze.
     */
    void draw(Model model, int[] playerPosition);
}
