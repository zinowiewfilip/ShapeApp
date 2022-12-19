package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Square implements Shape{

    private Type type = Type.SQUARE;
    private double side;

    @JsonCreator
    private Square(
            @JsonProperty("side") double side
    ) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public double getCircumference() {
        return side * 4;
    }

    public static Square createInstance(double side) {
        return new Square(side);
    }

    public Type getType() {
        return type;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
