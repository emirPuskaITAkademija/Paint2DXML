package app.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;


public class Circle extends PaintShape{

    public Circle(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public Shape createShape() {
        return new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
    }
}
