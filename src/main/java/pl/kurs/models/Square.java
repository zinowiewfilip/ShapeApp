package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonCreator;


public class Square implements Shape {

    private double side;

    @JsonCreator
    private Square() {
    }

    private Square(
            double side
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
