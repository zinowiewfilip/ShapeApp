package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Circle implements Shape{

    @JsonProperty("type") private Type type = Type.CIRCLE;
    private double radius;

    @JsonCreator
    private Circle(
            @JsonProperty("radius") double radius
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
