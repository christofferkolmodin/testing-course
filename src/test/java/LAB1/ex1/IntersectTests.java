package LAB1.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntersectTests {



    // For loop is not entered when at least one set is empty
    @Test
    public void intersectFirstSetEmpty() {
        int[] testValues = {1, 3, 2};
        Set s1 = new Set();
        Set s2 = createSet(testValues);

        // transform s1 into the intersection of s1 and s2
        s1.intersect(s2);

        int[] expectedResult = {};
        // Nothing is removed or added to s1 which is empty, so it should still be empty
        assertArrayEquals(expectedResult, s1.toArray());
    }

    // For loop not entered, but elements in s1 has to be removed
    // First bug detected: In the original code the elements of s1 are not removed when second set is empty
    @Test
    public void intersectSecondSetEmpty() {
        int[] testValues = {1, 3, 2};
        Set s1 = createSet(testValues);
        Set s2 = new Set();

        // transform s1 into the intersection of s1 and s2
        s1.intersect(s2);

        int[] expectedResult = {};
        assertArrayEquals(expectedResult, s1.toArray());
    }

    @Test
    public void intersectSetsWithCommonElementsSecondSetLarger() {
        int[] testValues1 = {1, 2};
        Set s1 = createSet(testValues1);

        int[] testValues2 = {1, 2, 3};
        Set s2 = createSet(testValues2);

        s1.intersect(s2);

        int[] expectedResult = {1, 2};
        assertArrayEquals(expectedResult, s1.toArray());
    }

    // Second bug detected: In the original code, after Set 2 is exhausted,
    // the remaining unchecked elements of Set 1 were not appropriately removed
    @Test
    public void intersectSetsWithCommonElementsFirstSetLarger() {
        int[] testValues1 = {1, 2, 3};
        Set s1 = createSet(testValues1);

        int[] testValues2 = {1, 2};
        Set s2 = createSet(testValues2);

        s1.intersect(s2);

        int[] expectedResult = {1, 2};
        assertArrayEquals(expectedResult, s1.toArray());
    }

    // This test enters all conditions inside and outside the for loop
    // Third bug detected: When an element is removed, the variable i should not iterate
    @Test
    public void intersectWithInnerElementsRemoved() {

        int[] testValues1 = {1, 5, 3, 4, 8};
        int[] testValues2 = {1, 2, 4, 7, 10, 12};
        Set s1 = createSet(testValues1);
        Set s2 = createSet(testValues2);

        s1.intersect(s2);

        assertArrayEquals(new int[]{1, 4}, s1.toArray());
    }

    private Set createSet(int[] values) {
        Set s = new Set();
        for (int v : values) {
            s.insert(v);
        }
        return s;
    }
}
