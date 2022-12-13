package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.models.Circle;
import pl.kurs.models.Shape;
import pl.kurs.models.Square;
import pl.kurs.models.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeServiceTest {
    List<Shape> shapes;

    ShapeService shapeService;

    @Mock
    IShapeFactory shapeFactoryMock;
    ShapeFactory shapeFactory;

    @Before
    public void init() {
        shapes = new ArrayList<>();
        shapeFactory = new ShapeFactory();
        MockitoAnnotations.openMocks(this);
        shapeService = new ShapeService(shapeFactory);
        shapes.add(shapeFactory.createSquare(10));
        shapes.add(shapeFactory.createSquare(12));
        shapes.add(shapeFactory.createSquare(14));
        shapes.add(shapeFactory.createSquare(10));
        shapes.add(shapeFactory.createCircle(10));
        shapes.add(shapeFactory.createCircle(12));
        shapes.add(shapeFactory.createCircle(14));
        shapes.add(shapeFactory.createCircle(10));
        shapes.add(shapeFactory.createRectangle(10, 12));
        shapes.add(shapeFactory.createRectangle(10, 12));
        shapes.add(shapeFactory.createRectangle(12, 14));
        shapes.add(shapeFactory.createRectangle(15, 12));

    }

    @Test
    public void shouldReturnCircleWithRadiusEquals14AsBiggestArea() {
        //given
        List<Shape> listForTest = shapes;
        Circle circleForTest = shapeFactory.createCircle(14);

        //when
        Shape result = shapeService.findBiggestArea(shapes);

        //then
        assertEquals(circleForTest, result);
    }
    @Test
    public void shouldReturnSquareWithSideEquals14AsBiggestCircumferenceFromChosen() {
        //given
        List<Shape> listForTest = shapes;
        Square squareForTest = shapeFactory.createSquare(14);

        //when
        Shape result = shapeService.findBiggestCircumferenceByShape(shapes, Type.SQUARE);

        //then
        assertEquals(squareForTest, result);
    }

    //

}