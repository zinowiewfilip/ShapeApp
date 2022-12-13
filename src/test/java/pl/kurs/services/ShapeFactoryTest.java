package pl.kurs.services;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.models.Circle;
import pl.kurs.models.Shape;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeFactoryTest {
    ShapeFactory shapeFactory;

    @Before
    public void init() {
        shapeFactory = new ShapeFactory();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingSquare() {
        //given
        double doubleForTest = 0;

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> shapeFactory.createSquare(doubleForTest));

        //then
        assertEquals(IllegalArgumentException.class, e.getClass());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingCircle() {
        //given
        double doubleForTest = 0;

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> shapeFactory.createCircle(doubleForTest));

        //then
        assertEquals(IllegalArgumentException.class, e.getClass());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCreatingRectangle() {
        //given
        double doubleForTest = 0;

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> shapeFactory.createRectangle(doubleForTest, doubleForTest));

        //then
        assertEquals(IllegalArgumentException.class, e.getClass());
    }
    @Test
    public void shouldHaveTheSameReference() {
        //given
        Circle cirleForTest1;
        Circle cirleForTest2;

        //when
        cirleForTest1 = shapeFactory.createCircle(10);
        cirleForTest2 = shapeFactory.createCircle(10);

        //then
        assertTrue(cirleForTest1 == cirleForTest2);

    }

}