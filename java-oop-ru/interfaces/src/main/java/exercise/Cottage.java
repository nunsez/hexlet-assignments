package exercise;

import java.text.DecimalFormat;

// BEGIN
public final class Cottage implements Home {

    private final double area;

    private final int floorCount;

    private final DecimalFormat areaFormatter = new DecimalFormat("#.0#");

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        var area = areaFormatter.format(getArea());
        return "%d этажный коттедж площадью %s метров".formatted(floorCount, area);
    }

}
// END
