import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class used to check if a list contains a given array.
 */
public class ListContainsArrayChecker {
    /**
     * Checks whether a given array is in a given ArrayList.
     * @param array The array that may exist in the list
     * @param checkedArrayList The array list of arrays to be checked.
     * @return True if the ArrayList contains the array, false otherwise.
     */
    public static boolean containsArrayList(int[] array, ArrayList<int[]> checkedArrayList) {
        for (int i = 0; i < checkedArrayList.size(); i++) {
            if (Arrays.equals(checkedArrayList.get(i), array)) {
                return true;
            }
        }
        return false;
    }
}
