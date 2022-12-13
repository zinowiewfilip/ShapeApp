package pl.kurs.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.models.Shape;
import pl.kurs.models.Type;

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
        Optional<Shape> result = shapes.stream()
                .max(Comparator.comparingDouble(x -> x.getArea()));
        return result.orElseThrow();
    }

    public Shape findBiggestCircumferenceByShape(List<Shape> shapes, Enum shape) {
        Optional<Shape> result = shapes.stream()
                .filter(x -> x.getType().equals(shape))
                .max(Comparator.comparingDouble(x -> x.getCircumference()));
        return result.orElseThrow();
    }

    public void exportShapes(List<Shape> shapes, String path) throws IOException {
        objectMapper.writeValue(new File(path), shapes);
    }

    public List<Shape> importShapes(String path) throws IOException {
        List<Shape> shapes = new ArrayList<>();
        JsonNode shapeJson = objectMapper.readTree(new File(path));
        for (int i = 0; i < shapeJson.size(); i++) {
            if(shapeJson.get(i) == null) {
                continue;
            }
            if(shapeJson.get(i).get("type") == null) {
                continue;
            }
            if (shapeJson.get(i)
                    .get("type")
                    .asText()
                    .equals(Type.SQUARE.toString()))
                shapes.add(shapeFactory.createSquare(shapeJson.get(i)
                        .get("side")
                        .asDouble()));
            else if (shapeJson.get(i)
                    .get("type")
                    .asText()
                    .equals(Type.CIRCLE.toString())) {
                shapes.add(shapeFactory.createCircle(shapeJson.get(i)
                        .get("radius")
                        .asDouble()));
            } else if (shapeJson
                    .get(i)
                    .get("type")
                    .asText()
                    .equals(Type.RECTANGLE.toString())) {
                shapes.add(shapeFactory.createRectangle(shapeJson.get(i)
                                .get("width")
                                .asDouble(),
                        shapeJson.get(i)
                                .get("height")
                                .asDouble()));
            }
        }
        return shapes;
    }
}
