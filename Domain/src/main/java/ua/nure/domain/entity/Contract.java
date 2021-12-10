package ua.nure.domain.entity;

import java.util.Date;
import java.util.Objects;

public class Contract extends Entity {
    Integer carSerialNumber;
    String clientEmail;
    Date date;

    public Contract(Integer identifier, Integer carSerialNumber, String clientEmail, Date date) {
        super(identifier);
        this.carSerialNumber = carSerialNumber;
        this.clientEmail = clientEmail;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Contract && ((Contract) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carSerialNumber, clientEmail, date);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "identifier=" + getIdentifier() +
                ", carSerialNumber=" + carSerialNumber +
                ", clientEmail='" + clientEmail + '\'' +
                ", date=" + date +
                '}';
    }
}
