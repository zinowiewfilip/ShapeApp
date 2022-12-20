package pl.kurs.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import pl.kurs.models.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShapeService {
    private ObjectMapper objectMapper = new ObjectMapper();
    private IShapeFactory shapeFactory;

    public ShapeService(IShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Shape findBiggestArea(List<Shape> shapes) {
        return shapes.stream()
                .max(Comparator.comparingDouble(x -> x.getArea()))
                .orElseThrow();

    }

    public Shape findBiggestCircumferenceByShape(List<Shape> shapes, Enum shape) {
        return shapes.stream()
                .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(shape.name()))
                .max(Comparator.comparingDouble(x -> x.getCircumference()))
                .orElseThrow();
    }

    public void exportShapes(List<Shape> shapes, String path) throws IOException {
        objectMapper.writeValue(new File(path), shapes);
    }

    public List<Shape> importShapes(String path) throws IOException {
        List<Shape> shapes = new ArrayList<>();
        JsonNode shapeJson = objectMapper.readTree(new File(path));
            for (int i = 0; i < shapeJson.size(); i++) {

                if (shapeJson.get(i) == null) {
                    continue;
                }
                if (shapeJson.get(i).get("type") == null) {
                    continue;
                }
                if (shapeJson.get(i)
                        .get("type")
                        .asText()
                        .equals(Type.SQUARE.toString()))
                    shapes.add(shapeFactory.createSquare(objectMapper.treeToValue(shapeJson.get(i), Square.class).getSide()));
                else if (shapeJson.get(i)
                        .get("type")
                        .asText()
                        .equals(Type.CIRCLE.toString())) {
                    shapes.add(shapeFactory.createCircle(objectMapper.treeToValue(shapeJson.get(i), Circle.class).getRadius()));
                } else {
                    Rectangle rectangle = objectMapper.treeToValue(shapeJson.get(i), Rectangle.class);
                    shapes.add(shapeFactory.createRectangle(rectangle.getWidth(), rectangle.getHeight()));
                }
            }



        return shapes;
    }
}
