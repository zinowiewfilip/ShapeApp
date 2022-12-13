package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Shape {

    @JsonIgnore
    double getArea();
    @JsonIgnore
    double getCircumference();
    Type getType();
}
