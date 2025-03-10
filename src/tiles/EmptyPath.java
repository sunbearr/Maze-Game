package tiles;

/**
 * This class is a type of tile representing an empty path that the
 */
public class EmptyPath extends Tile {
    @Override
    public String toString() {
        return "\u001B[36m";
    }

    // get color is white by default from super class
}
