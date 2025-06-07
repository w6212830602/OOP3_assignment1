package shapes;

public class Pyramid extends Shape {
    private double edgeLength;

    public Pyramid(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double getBaseArea() {
        return Math.pow(edgeLength, 2);
    }

    @Override
    public double getVolume() {
        return (1.0 / 3.0) * getBaseArea() * height;
    }
}
