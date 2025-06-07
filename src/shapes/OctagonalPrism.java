package shapes;

public class OctagonalPrism extends Shape {
    private double edgeLength;

    public OctagonalPrism(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double getBaseArea() {
        return 2 * (1 + Math.sqrt(2)) * Math.pow(edgeLength, 2);
    }

    @Override
    public double getVolume() {
        return getBaseArea() * height;
    }
}
