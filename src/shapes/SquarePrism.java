package shapes;

public class SquarePrism extends Shape {
    private double edgeLength;

    public SquarePrism(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double getBaseArea() {
        return Math.pow(edgeLength, 2);
    }

    @Override
    public double getVolume() {
        return getBaseArea() * height;
    }
}
