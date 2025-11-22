package LAB1.ex1;

import LAB1.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DistinctClosedTests {

    // Does not enter any for loops
    @Test
    public void distinctClosedEmptySet() {
        Set s = new Set();
        boolean result = s.distinctClosed((a, b) -> a + b);
        assertTrue(result);
    }

    @Test
    public void distinctClosedSingleElement() {
        int[] testValues = {1};
        Set s = createSet(testValues);

        boolean result = s.distinctClosed((a, b) -> a + b);

        // a + b not existing in the Set is okay if a == b
        assertTrue(result);
    }

    // Enters the innermost if condition which returns false
    @Test
    public void distinctClosedMultipleElementsExpectedFalse() {
        int[] testValues = {1, 2, 3};
        Set s = createSet(testValues);

        boolean result = s.distinctClosed((a, b) -> a + b);

        // 2 + 3 = 5, and 5 is not in the set. So this should be false
        assertFalse(result);
    }

    @Test
    public void distinctClosedMultipleElementsExpectedTrue() {
        int[] testValues = {1, 0, 0};
        Set s = createSet(testValues);

        boolean result = s.distinctClosed((a, b) -> a + b);

        // 1 + 0 = 1, and 1 is not in the set. So this should be true
        assertTrue(result);
    }

    @Test
    public void distinctClosedMultipleElementsSubtraction() {
        int[] testValues = {1, 0};
        Set s = createSet(testValues);

        boolean result = s.distinctClosed((a, b) -> a - b);

        // 0 - 1 = -1, and -1 is not in the set. So this should be false
        assertFalse(result);
    }

    // If the function is simply 0, and 0 is in the set, this should be asserted as true
    @Test
    public void distinctClosed_constantZeroOperationOnSetContainingZero_returnsTrue() {
        int[] testValues = {0, 1, 2};
        Set s = createSet(testValues);

        boolean result = s.distinctClosed((a, b) -> 0);

        assertTrue(result);
    }

    private Set createSet(int[] values) {
        Set s = new Set();
        for (int v : values) {
            s.insert(v);
        }
        return s;
    }
}
