public class PointTest {

    public double x;
    public double y;


    public static double distance(PointTest p1, PointTest p2) {

        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;

        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
