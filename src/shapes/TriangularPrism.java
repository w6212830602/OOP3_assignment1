
package shapes;

public class TriangularPrism extends Shape {
    private double edgeLength;

    public TriangularPrism(double height, double edgeLength) {
        super(height);
        this.edgeLength = edgeLength;
    }

    @Override
    public double calcVolume() {
        return calcBaseArea() * height;
    }

    @Override
    public double calcBaseArea() {
        return (Math.pow(edgeLength, 2) * Math.sqrt(3)) / 4.0;
    }
}
