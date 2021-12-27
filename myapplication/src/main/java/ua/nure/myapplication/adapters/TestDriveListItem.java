package ua.nure.myapplication.adapters;

import utility.CommandsList;

public class TestDriveListItem {
    private Integer resource;
    private Integer score;
    private String carLine;
    private String brand;

    public TestDriveListItem(String carLine, String brand, Integer resource, Integer score) {
        this.resource = resource;
        this.carLine = carLine;
        this.brand = brand;
        this.score = score;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
