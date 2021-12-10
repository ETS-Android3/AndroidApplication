package ua.nure.domain.entity;

import java.util.Date;
import java.util.Objects;

public class Car extends Entity {
    String manufacturerCountry;
    Integer serialNumber;
    Date manufactureDate;
    String engineModel;
    String bodyModel;
    String photoPath;
    String carLine;
    String brand;
    Long price;

    public Car(Integer identifier,
               Integer serialNumber,
               String manufacturerCountry,
               Date manufactureDate,
               String engineModel,
               String bodyModel,
               String photoPath,
               String carLine,
               String brand,
               Long price) {
        super(identifier);
        this.manufacturerCountry = manufacturerCountry;
        this.serialNumber = serialNumber;
        this.manufactureDate = manufactureDate;
        this.engineModel = engineModel;
        this.bodyModel = bodyModel;
        this.photoPath = photoPath;
        this.carLine = carLine;
        this.brand = brand;
        this.price = price;
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

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getBodyModel() {
        return bodyModel;
    }

    public void setBodyModel(String bodyModel) {
        this.bodyModel = bodyModel;
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
        return object instanceof Car && ((Car) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manufacturerCountry, serialNumber, manufactureDate, engineModel, bodyModel, photoPath, carLine, brand, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "identifier=" + getIdentifier() +
                ", manufacturerCountry='" + manufacturerCountry + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", engineModel='" + engineModel + '\'' +
                ", bodyModel='" + bodyModel + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", carLine='" + carLine + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
