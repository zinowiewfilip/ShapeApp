package pl.kurs.services;

import com.fasterxml.jackson.annotation.JsonTypeName;
import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Shape;
import pl.kurs.models.Square;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory implements IShapeFactory{

    private static Map<String, Shape> squareCache = new HashMap<>();
    private static Map<String, Shape> circleCache = new HashMap<>();
    private static Map<String, Shape> rectangleCache = new HashMap<>();

    public ShapeFactory() {
    }

    public Square createSquare(double side) {
        if (side <= 0)
            throw new IllegalArgumentException("Podano nieprawidlowe dane");
        if (!squareCache.containsKey(String.valueOf(side)))
            squareCache.put(String.valueOf(side), Square.createInstance(side));

        return (Square) squareCache.get(String.valueOf(side));

    }


    public Circle createCircle(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Podano nieprawidlowe dane");
        if (!circleCache.containsKey(String.valueOf(radius)))
            circleCache.put(String.valueOf(radius), Circle.createInstance(radius));

        return (Circle) circleCache.get(String.valueOf(radius));
    }


    public Rectangle createRectangle(double width, double height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Podano nieprawidlowe dane");
        if (!rectangleCache.containsKey(width + " " + height))
            rectangleCache.put(width + " " + height, Rectangle.createInstance(width, height));

        return (Rectangle) rectangleCache.get(width + " " + height);
    }


}
