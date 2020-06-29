package app.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Square extends PaintShape {

    public Square(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public Shape createShape() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }
}
