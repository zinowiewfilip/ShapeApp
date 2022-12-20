package pl.kurs.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.models.Circle;
import pl.kurs.models.Shape;
import pl.kurs.models.Square;
import pl.kurs.models.Type;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeServiceTest {
    List<Shape> shapes;

    ShapeService shapeService;

    @Mock
    IShapeFactory shapeFactoryMock;
    ShapeFactory shapeFactory;

    ObjectMapper objectMapper;
    String path;



    @Before
    public void init() {
        shapes = new ArrayList<>();
        shapeFactory = new ShapeFactory();
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        shapeService = new ShapeService(shapeFactoryMock);

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
        Shape result = shapeService.findBiggestArea(listForTest);

        //then
        assertEquals(circleForTest, result);
    }
    @Test
    public void shouldReturnSquareWithSideEquals14AsBiggestCircumferenceFromChosen() {
        //given
        List<Shape> listForTest = shapes;
        Square squareForTest = shapeFactory.createSquare(14);

        //when
        Shape result = shapeService.findBiggestCircumferenceByShape(listForTest, Type.SQUARE);

        //then
        assertEquals(squareForTest, result);
    }

    @Test
    public void whenCorrectWriteShouldEquals() throws IOException {
        //given
        List<Shape> listForTest = shapes;
        path = "src/test/resources/shapesForTest.json";
        Square squareForTest = shapeFactory.createSquare(12);

        //when
        shapeService.exportShapes(listForTest, path);
        JsonNode jsonFromFile = objectMapper.readTree(new File(path));

        //then
        assertEquals(squareForTest.getType().name(), jsonFromFile.get(1).get("type").asText());
    }
    @Test
    public void whenCorrectReadShouldEquals() throws IOException {
        //given
        path = "src/test/resources/shapesForTestReading.json";
        Circle c1 = shapeFactory.createCircle(10);
        List<Shape> listForTest = List.of(c1);

        //when
        objectMapper.writeValue(new File(path), c1);

        List<Shape> result = shapeService.importShapes(path);
        System.out.println(result);

        //then
        assertEquals(listForTest, result);

    }
}