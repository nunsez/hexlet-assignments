package exercise;

// BEGIN
public class Circle {

    private final int radius;

    public Circle(Point center, int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        var radius = getRadius();

        if (radius < 0) {
            throw new NegativeRadiusException();
        }

        return Math.PI * radius * radius;
    }

}
// END
