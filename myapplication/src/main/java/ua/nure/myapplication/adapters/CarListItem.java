package ua.nure.myapplication.adapters;

import utility.CommandsList;

public class CarListItem {
    private Integer resource;
    private String carLine;
    private String brand;

    public CarListItem(String carLine, String brand, Integer resource) {
        this.carLine = carLine;
        this.brand = brand;
        this.resource = resource;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
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

    @Override
    public String toString() {
        return brand + CommandsList.GAP_STRING + carLine;
    }
}
