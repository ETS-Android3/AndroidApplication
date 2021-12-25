package ua.nure.domain.entity;

import java.util.Objects;

public class CarBody extends Entity {
    private String usability;
    private Integer length;
    private Integer height;
    private Integer weight;
    private Integer width;
    private String model;
    private String type;
    private String vCode;
    private String purpose;

    private CarBody(CarBodyBuilder carBodyBuilder) {
        super(carBodyBuilder.getIdentifier());
        setUsability(carBodyBuilder.getUsability());
        setLength(carBodyBuilder.getLength());
        setHeight(carBodyBuilder.getHeight());
        setWeight(carBodyBuilder.getWeight());
        setWidth(carBodyBuilder.getWidth());
        setModel(carBodyBuilder.getModel());
        setType(carBodyBuilder.getType());
        setVCode(carBodyBuilder.getVCode());
        setPurpose(carBodyBuilder.getPurpose());
    }

    public String getUsability() {
        return usability;
    }

    public void setUsability(String usability) {
        this.usability = usability;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof CarBody && ((CarBody) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usability, length, height, weight, width, model, type, vCode, purpose);
    }

    @Override
    public String toString() {
        return "CarBody{" +
                "identifier=" + getIdentifier() +
                ", usability='" + usability + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", vCode='" + vCode + '\'' +
                ", purpose='" + purpose + '\'' +
                ", length=" + length +
                ", height=" + height +
                ", weight=" + weight +
                ", width=" + width +
                '}';
    }

    public static class CarBodyBuilder {
        private Integer identifier;
        private String usability;
        private Integer length;
        private Integer height;
        private Integer weight;
        private Integer width;
        private String model;
        private String type;
        private String vCode;
        private String purpose;

        public CarBodyBuilder() { }

        public CarBody build() {
            return new CarBody(this);
        }

        public Integer getIdentifier() {
            return identifier;
        }

        public CarBodyBuilder setIdentifier(Integer identifier) {
            this.identifier = identifier;
            return this;
        }

        public String getUsability() {
            return usability;
        }

        public CarBodyBuilder setUsability(String usability) {
            this.usability = usability;
            return this;
        }

        public Integer getLength() {
            return length;
        }

        public CarBodyBuilder setLength(Integer length) {
            this.length = length;
            return this;
        }

        public Integer getHeight() {
            return height;
        }

        public CarBodyBuilder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Integer getWeight() {
            return weight;
        }

        public CarBodyBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public Integer getWidth() {
            return width;
        }

        public CarBodyBuilder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        public String getModel() {
            return model;
        }

        public CarBodyBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public String getType() {
            return type;
        }

        public CarBodyBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public String getVCode() {
            return vCode;
        }

        public CarBodyBuilder setVCode(String vCode) {
            this.vCode = vCode;
            return this;
        }

        public String getPurpose() {
            return purpose;
        }

        public CarBodyBuilder setPurpose(String purpose) {
            this.purpose = purpose;
            return this;
        }
    }
}
