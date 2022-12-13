package pl.kurs.app;

import pl.kurs.models.*;
import pl.kurs.services.IShapeFactory;
import pl.kurs.services.ShapeFactory;
import pl.kurs.services.ShapeService;

import java.io.IOException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {


        IShapeFactory shapeFactory = new ShapeFactory();
        ShapeService shapeService = new ShapeService(shapeFactory);
        Square sq1 = shapeFactory.createSquare(10); // nie ma jeszcze kwadratu o boku 10 wiec tworzy nowa instancje
        Square sq2 = shapeFactory.createSquare(12);
        Square sq3 = shapeFactory.createSquare(11);
        Circle circle1 = shapeFactory.createCircle(11);
        Circle circle2 = shapeFactory.createCircle(10);
        Rectangle rectangle1 = shapeFactory.createRectangle(11, 19);
        Rectangle rectangle2 = shapeFactory.createRectangle(10, 19);
        System.out.println(rectangle1 == rectangle2);
        System.out.println(circle1 == circle2);
        System.out.println(sq1);
        System.out.println(sq2);
        System.out.println(sq3);
        System.out.println(sq1 == sq2);
        List<Shape> shapes = List.of(sq1, sq2, sq3, circle1, circle2, rectangle1, rectangle2);


        System.out.println("shapeService.findBiggestArea(shapes) = " + shapeService.findBiggestArea(shapes));
        System.out.println("shapeService.findBiggestCircumferenceByShape(shapes, Shapes.CIRCLE) = " + shapeService.findBiggestCircumferenceByShape(shapes, Type.RECTANGLE));

        shapeService.exportShapes(shapes, "src/main/resources/shapes.json");
        System.out.println("shapeService.importShapes(\"src/main/resources/shapes.json\") = " + shapeService.importShapes("src/main/resources/shapes.json"));


    }
}
