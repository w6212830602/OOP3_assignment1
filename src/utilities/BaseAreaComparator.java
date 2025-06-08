package utilities;

import java.util.Comparator;
import shapes.Shape;

public class BaseAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s2.calcBaseArea(), s1.calcBaseArea());
    }
}
