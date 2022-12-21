package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
        @JsonSubTypes.Type(value = Square.class, name = "Square"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "Rectangle")
})
public interface Shape {

    @JsonProperty("type")
    default String getClassName() {
        return this.getClass().getSimpleName();
    }


    @JsonIgnore
    double getArea();
    @JsonIgnore
    double getCircumference();
}
