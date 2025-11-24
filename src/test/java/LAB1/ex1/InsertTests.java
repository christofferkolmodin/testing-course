package LAB1.ex1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertTests {

    /*
     * Statement coverage tests: Ensure every line of code is covered in a test
     * 1: Enter for loop, don't enter for loop
     * 2: Enter both if statements, enter the else statement
     */

    /*
     * Branch coverage tests: Ensure both outcomes of any decision is covered in a test
     * 3: Test when i < a.size is true and when it's false
     * 4: Test when a.get(i) > x is true and when it's false
     * 5: Test when a.get(i) == x is true and when it's false
     */

    // First element doesn't enter the for loop
    @Test
    public void insertIntoEmptySet() {
        Set s = new Set();
        s.insert(2);

        int[] expectedResult = {2};
        assertArrayEquals(expectedResult, s.toArray());
    }

    // Adding a second smaller element will enter the for loop and the first if statement
    // Tests when (a.get(i) > x) is true and when (a.get(i) == x) is false
    // Also covers
    // First bug: Original code inserted the smaller element once inside the if statement and again outside the for loop
    @Test
    public void insertSecondSmallerElement() {
        Set s = new Set();

        s.insert(2);
        s.insert(1);

        int[] expectedResult = {1, 2};
        assertArrayEquals(expectedResult, s.toArray());
    }

    // Adding a duplicate element will enter the for loop and the else statement, as well as the second if statement
    // Second bug: Original code added the duplicate element outside the for loop when it should skip it
    @Test
    public void insertDuplicateElement() {
        Set s = new Set();

        s.insert(1);
        s.insert(1);

        int[] expectedResult = {1};
        assertArrayEquals(expectedResult, s.toArray());
    }

    // Tests when a.get(i) > x is false
    @Test
    public void insertSecondLargerElementAtBeginning() {
        Set s = new Set();

        s.insert(2);
        s.insert(1);

        int[] expectedResult = {1, 2};
        assertArrayEquals(expectedResult, s.toArray());
    }

    // Tests the ordering of the elements
    @Test
    public void insertSecondLargerElementAtEnd() {
        Set s = new Set();

        s.insert(1);
        s.insert(2);

        int[]  expectedResult = {1, 2};
        assertArrayEquals(expectedResult, s.toArray());
    }

    // Test negative values and their ordering
    @Test
    public void insertNegativeValues() {
        Set s = new Set();
        s.insert(-1);
        s.insert(-2);

        int[] expectedResult = {-2, -1};
        assertArrayEquals(expectedResult, s.toArray());
    }

}
