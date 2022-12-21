package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Circle implements Shape {

    private double radius;

    @JsonCreator
    private Circle() {
    }

    private Circle(
            double radius
    ) {
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
