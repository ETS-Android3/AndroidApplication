package ua.nure.domain.entity;

import java.util.Objects;

public class Engine extends Entity {
    Integer volume;
    String model;
    String brand;
    String type;
    String code;

    public Engine(Integer identifier, Integer volume, String model, String brand, String type, String code) {
        super(identifier);
        this.volume = volume;
        this.brand = brand;
        this.type = type;
        this.code = code;
        this.model = model;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Engine && ((Engine) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume, model, brand, type, code);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "identifier=" + getIdentifier() +
                ", volume=" + volume +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
