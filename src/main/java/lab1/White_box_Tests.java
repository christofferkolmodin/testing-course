package lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class White_box_Tests {
    
    @Test
    public void test_A(){
        Set s = new Set();
        s.insert(3);
        s.insert(1);
        s.insert(2);
        s.insert(3);
        System.out.println(Arrays.toString(s.toArray()));
    }

    @Test
    public void test_B(){
        Set s = new Set();
        s.insert(3);
        s.insert(1);
        s.insert(2);
        System.out.println(s.member(1));
        System.out.println(s.member(2));
        System.out.println(s.member(4));
    }

    
}
