package exercise;

// BEGIN
public interface Home extends Comparable<Home> {

    double getArea();

    default int compareTo(Home another) {
        return Double.compare(this.getArea(), another.getArea());
    }

}
// END
