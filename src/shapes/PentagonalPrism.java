package shapes;

public class PentagonalPrism extends Shape {
    private double edgeLength;

    public PentagonalPrism(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double getBaseArea() {
        return (5.0 * Math.pow(edgeLength, 2) * Math.tan(Math.toRadians(54))) / 4.0;
    }

    @Override
    public double getVolume() {
        return getBaseArea() * height;
    }
}
