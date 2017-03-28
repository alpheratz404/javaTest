package javaTest;

import org.testng.annotations.Test;

public class PointTests {

    @Test

    public void testDistance () {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(9, 12);
        assert p1.distance(p2) == 10.63014581273465;
    }
}
