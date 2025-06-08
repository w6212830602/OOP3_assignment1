package shapes;

public class SquarePrism extends Shape {
    private double edgeLength;

    public SquarePrism(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double calcVolume() {
        return calcBaseArea() * height;
    }

    @Override
    public double calcBaseArea() {
        return Math.pow(edgeLength, 2);
    }
}
