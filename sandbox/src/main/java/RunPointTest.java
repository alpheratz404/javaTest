public class RunPointTest {

    public static void main(String[] args) {

        PointTest p1 = new PointTest();
        p1.x = 4;
        p1.y = 6;
        System.out.println("Координты точки p1.x = " + p1.x + ", p1.y =" + p1.y);

        PointTest p2 = new PointTest();
        p2.x = 12;
        p2.y = 18;
        System.out.println("Координты точки p2.x = " + p2.x + ", p2.y = " + p2.y);

        System.out.println("Расстояние между p1 и p2 = " + PointTest.distance(p1, p2));

    }
}
