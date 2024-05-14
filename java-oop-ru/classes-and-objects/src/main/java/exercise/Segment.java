package exercise;

// BEGIN
public record Segment(Point beginPoint, Point endPoint) {

    public Point getMidPoint() {
        var midX = (beginPoint.x() + endPoint.x()) / 2;
        var midY = (beginPoint.y() + endPoint.y()) / 2;
        return new Point(midX, midY);
    }

}
// END
