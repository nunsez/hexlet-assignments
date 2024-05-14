package exercise;

import java.text.DecimalFormat;

// BEGIN
public final class Flat implements Home {

    private final double area;

    private final double balconyArea;

    private final int floor;

    private final DecimalFormat areaFormatter = new DecimalFormat("#.0#");

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        var area = areaFormatter.format(getArea());
        return "Квартира площадью %s метров на %d этаже".formatted(area, floor);
    }

}
// END
