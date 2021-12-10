package ua.nure.domain.entity;

import java.util.Objects;

public class CarBody extends Entity {
    String usability;
    String bodyType;
    Integer length;
    Integer height;
    Integer weight;
    Integer width;
    String model;

    public CarBody(Integer identifier,
                   String usability,
                   String bodyType,
                   Integer length,
                   Integer height,
                   Integer weight,
                   Integer width,
                   String model) {
        super(identifier);
        this.usability = usability;
        this.bodyType = bodyType;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.width = width;
        this.model = model;
    }

    public String getUsability() {
        return usability;
    }

    public void setUsability(String usability) {
        this.usability = usability;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof CarBody && ((CarBody) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usability, bodyType, length, height, weight, width, model);
    }

    @Override
    public String toString() {
        return "CarBody{" +
                "identifier=" + getIdentifier() +
                ", usability='" + usability + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", model='" + model + '\'' +
                ", length=" + length +
                ", height=" + height +
                ", weight=" + weight +
                ", width=" + width +
                '}';
    }
}
