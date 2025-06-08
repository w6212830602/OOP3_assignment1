package shapes;

public class Pyramid extends Shape {
    private double edgeLength;

    public Pyramid(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double calcVolume() {
        return (1.0 / 3.0) * calcBaseArea() * height;
    }

    @Override
    public double calcBaseArea() {
        return Math.pow(edgeLength, 2);
    }
}
