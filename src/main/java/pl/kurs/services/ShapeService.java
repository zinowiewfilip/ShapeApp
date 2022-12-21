package pl.kurs.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.models.Shape;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ShapeService {
    private ObjectMapper objectMapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    private IShapeFactory shapeFactory;


    public ShapeService(IShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Shape findBiggestArea(List<Shape> shapes) {
        return shapes.stream()
                .max(Comparator.comparingDouble(x -> x.getArea()))
                .orElseThrow();

    }

    public Shape findBiggestCircumferenceByShape(List<Shape> shapes, String name) {
        return shapes.stream()
                .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(name))
                .max(Comparator.comparingDouble(x -> x.getCircumference()))
                .orElseThrow();
    }

    public void exportShapes(List<Shape> shapes, String path) throws IOException {
        objectMapper.writeValue(new File(path), shapes);
    }

    public List<Shape> importShapes(String path) throws IOException {
        List<Shape> shapes = objectMapper.readValue(new File(path), new TypeReference<List<Shape>>() {
        });

        return shapes;
    }
}
