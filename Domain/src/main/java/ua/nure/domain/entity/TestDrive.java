package ua.nure.domain.entity;

import java.util.Objects;

public class TestDrive extends Entity {
    Integer carSerialNumber;
    String clientEmail;
    Integer score;

    public TestDrive(Integer identifier, Integer carSerialNumber, String clientEmail, Integer score) {
        super(identifier);
        this.carSerialNumber = carSerialNumber;
        this.clientEmail = clientEmail;
        this.score = score;
    }

    public Integer getCarSerialNumber() {
        return carSerialNumber;
    }

    public void setCarSerialNumber(Integer carSerialNumber) {
        this.carSerialNumber = carSerialNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof TestDrive && ((TestDrive) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carSerialNumber, clientEmail, score);
    }

    @Override
    public String toString() {
        return "TestDrive{" +
                "identifier='" + getIdentifier() + '\'' +
                ", carSerialNumber=," + carSerialNumber + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", score=" + score +
                '}';
    }
}
