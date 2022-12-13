package pl.kurs.models;

import java.util.Objects;

public class Rectangle implements Shape {

    private Type type = Type.RECTANGLE;
    private double width;
    private double height;

    private Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getCircumference() {
        return 2 * width + 2 * height;
    }

    public static Rectangle createInstance(double width, double height) {
        return new Rectangle(width, height);
    }

    public Type getType() {
        return type;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
