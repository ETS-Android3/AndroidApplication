package ua.nure.domain.entity;

import java.util.Objects;

public class Engine extends Entity {
    private Integer volume;
    private String model;
    private String brand;
    private String type;
    private String vCode;

    private Engine(EngineBuilder engineBuilder) {
        super(engineBuilder.getIdentifier());
        setVolume(engineBuilder.getVolume());
        setModel(engineBuilder.getModel());
        setBrand(engineBuilder.getBrand());
        setType(engineBuilder.getType());
        setVCode(engineBuilder.getVCode());
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

    public String getVCode() {
        return vCode;
    }

    public void setVCode(String vCode) {
        this.vCode = vCode;
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
        return Objects.hash(super.hashCode(), volume, model, brand, type, vCode);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "identifier=" + getIdentifier() +
                ", volume=" + volume +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", code='" + vCode + '\'' +
                '}';
    }

    public static class EngineBuilder {
        private Integer identifier;
        private Integer volume;
        private String model;
        private String brand;
        private String type;
        private String vCode;

        public EngineBuilder() {}

        public Engine build(){
            return new Engine(this);
        }

        public Integer getIdentifier() {
            return identifier;
        }

        public Integer getVolume() {
            return volume;
        }

        public String getModel() {
            return model;
        }

        public String getBrand() {
            return brand;
        }

        public String getType() {
            return type;
        }

        public String getVCode() {
            return vCode;
        }

        public EngineBuilder setIdentifier(Integer identifier) {
            this.identifier = identifier;
            return this;
        }

        public EngineBuilder setVolume(Integer volume) {
            this.volume = volume;
            return this;
        }

        public EngineBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public EngineBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public EngineBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public EngineBuilder setVCode(String vCode) {
            this.vCode = vCode;
            return this;
        }
    }
}
