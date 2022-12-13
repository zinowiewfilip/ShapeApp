package pl.kurs.models;

import java.util.Objects;

public class Circle implements Shape{

    private Type type = Type.CIRCLE;
    private double radius;

    private Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    public static Circle createInstance(double radius) {
        return new Circle(radius);
    }

    public Type getType() {
        return type;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
