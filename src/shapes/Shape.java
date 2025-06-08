package shapes;

public abstract class Shape implements Comparable<Shape> {
    protected double height;

    public Shape(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public abstract double calcVolume();
    public abstract double calcBaseArea();

    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.calcVolume(), other.calcVolume());
    }

}
