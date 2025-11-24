package LAB1.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTests {

    // Doesn't enter the for loop
    @Test
    public void memberOfEmptySetReturnsFalse() {
        Set s = new Set();
        assertFalse(s.member(2));
    }

    // Enters for loop and first if statement
    @Test
    public void memberFalseWhenXSmallerThanFirst() {
        Set s = new Set();
        s.insert(3);
        s.insert(2);

        assertFalse(s.member(1));
    }

    @Test
    public void memberFalseWhenXLargerThanAll() {
        Set s = new Set();
        s.insert(3);
        s.insert(2);

        assertFalse(s.member(4));
    }

    @Test
    public void memberFalseWhenXBetweenTwoElements() {
        Set s = new Set();
        s.insert(4);
        s.insert(2);

        assertFalse(s.member(3));
    }

    // Enters else statement and second if statement
    @Test
    public void memberTrueWhenXEqualsExistingSmallerElement() {
        Set s = new Set();
        s.insert(3);
        s.insert(2);

        assertTrue(s.member(2));
    }

    @Test
    public void memberTrueWhenXEqualsExistingLargerElement() {
        Set s = new Set();
        s.insert(3);
        s.insert(2);

        assertTrue(s.member(3));
    }

    @Test
    public void memberTrueWhenXEqualsExistingNegativeElement() {
        Set s = new Set();
        s.insert(-2);
        s.insert(-3);

        assertTrue(s.member(-2));
    }


}
