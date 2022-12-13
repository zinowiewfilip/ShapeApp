package pl.kurs.services;

import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;

public interface IShapeFactory {
    public Square createSquare(double side);
    public Circle createCircle(double radius);
    public Rectangle createRectangle(double width, double height);
}
