package ua.nure.domain.entity;

import java.util.Objects;

public class Car extends Entity {
    private Engine engine;
    private CarBody body;
    private String manufacturerCountry;
    private Integer serialNumber;
    private String manufactureDate;
    private String photoPath;
    private String carLine;
    private String brand;
    private Long price;

    private Car(CarBuilder carBuilder) {
        super(carBuilder.getIdentifier());
        setEngine(carBuilder.getEngine());
        setBody(carBuilder.getBody());
        setManufacturerCountry(carBuilder.getManufacturerCountry());
        setSerialNumber(carBuilder.getSerialNumber());
        setManufactureDate(carBuilder.getManufactureDate());
        setPhotoPath(carBuilder.getPhotoPath());
        setCarLine(carBuilder.getCarLine());
        setBrand(carBuilder.getBrand());
        setPrice(carBuilder.getPrice());
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarBody getBody() {
        return body;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCarLine() {
        return carLine;
    }

    public void setCarLine(String carLine) {
        this.carLine = carLine;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Car && ((Car) object).getSerialNumber().equals(serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manufacturerCountry, serialNumber, manufactureDate, photoPath, carLine, brand, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "identifier=" + getIdentifier() +
                ", manufacturerCountry='" + manufacturerCountry + '\'' +
                ", manufactureDate='" + manufactureDate + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", carLine='" + carLine + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", serialNumber=" + serialNumber +
                ", engine=" + engine + "\n" +
                ", bodyType=" + body + "\n" +
                '}';
    }

    public static class CarBuilder {
        private Engine engine;
        private CarBody body;
        private Integer identifier;
        private String manufacturerCountry;
        private Integer serialNumber;
        private String manufactureDate;
        private String photoPath;
        private String carLine;
        private String brand;
        private Long price;

        public CarBuilder() { }

        public Car build() {
            return new Car(this);
        }

        public Engine getEngine() {
            return engine;
        }

        public CarBuilder setEngine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public CarBody getBody() {
            return body;
        }

        public CarBuilder setBody(CarBody body) {
            this.body = body;
            return this;
        }

        public Integer getIdentifier() {
            return identifier;
        }

        public CarBuilder setIdentifier(Integer identifier) {
            this.identifier = identifier;
            return this;
        }

        public String getManufacturerCountry() {
            return manufacturerCountry;
        }

        public CarBuilder setManufacturerCountry(String manufacturerCountry) {
            this.manufacturerCountry = manufacturerCountry;
            return this;
        }

        public Integer getSerialNumber() {
            return serialNumber;
        }

        public CarBuilder setSerialNumber(Integer serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public String getManufactureDate() {
            return manufactureDate;
        }

        public CarBuilder setManufactureDate(String manufactureDate) {
            this.manufactureDate = manufactureDate;
            return this;
        }

        public String getPhotoPath() {
            return photoPath;
        }

        public CarBuilder setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
            return this;
        }

        public String getCarLine() {
            return carLine;
        }

        public CarBuilder setCarLine(String carLine) {
            this.carLine = carLine;
            return this;
        }

        public String getBrand() {
            return brand;
        }

        public CarBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Long getPrice() {
            return price;
        }

        public CarBuilder setPrice(Long price) {
            this.price = price;
            return this;
        }
    }
}
