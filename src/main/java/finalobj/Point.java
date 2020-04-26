package finalobj;

public class Point {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
}


enum  Color{
    RED, BLUE
}

 class ColorPoint extends Point {
    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

     @Override
     public boolean equals(Object o) {
         if (!(o instanceof Point))
             return false;
// If o is a normal Point, do a color-blind comparison
         if (!(o instanceof ColorPoint))
             return o.equals(this);
// o is a ColorPoint; do a full comparison
         return super.equals(o) && ((ColorPoint) o).color == color;
     }
}