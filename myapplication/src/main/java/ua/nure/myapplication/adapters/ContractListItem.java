package ua.nure.myapplication.adapters;

import utility.CommandsList;

public class ContractListItem {
    private Integer resource;
    private Integer price;
    private String carLine;
    private String brand;

    public ContractListItem(String carLine, String brand, Integer resource, Integer price) {
        this.carLine = carLine;
        this.brand = brand;
        this.price = price;
        this.resource = resource;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
