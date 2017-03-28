package javaTest;

import org.testng.annotations.Test;

public class PointTests {

    @Test

    public void testDistance () {
        Point p1 = new Point(4, 6);
        Point p2 = new Point(12, 18);
        assert p1.distance(p2) == 14.422205101855956;
    }
}
