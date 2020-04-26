package finalobj;

public class Test01 {
    public static void main(String[] args) {



        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

        System.out.println(p1.equals(p3));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p2));

    }

}
