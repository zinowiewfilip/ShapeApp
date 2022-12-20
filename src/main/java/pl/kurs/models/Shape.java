package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public interface Shape {



    @JsonIgnore
    double getArea();
    @JsonIgnore
    double getCircumference();
}
