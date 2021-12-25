package ua.nure.domain.entity;

import java.util.Objects;

public class Contract extends Entity {
    private Integer carSerialNumber;
    private Integer clientIdentifier;
    private String date;

    private Contract(ContractBuilder contractBuilder) {
        super(contractBuilder.getIdentifier());
        setClientIdentifier(contractBuilder.getClientIdentifier());
        setCarSerialNumber(contractBuilder.getCarSerialNumber());
        setDate(contractBuilder.getDate());
    }

    public Integer getCarSerialNumber() {
        return carSerialNumber;
    }

    public void setCarSerialNumber(Integer carSerialNumber) {
        this.carSerialNumber = carSerialNumber;
    }

    public Integer getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(Integer clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Contract && ((Contract) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carSerialNumber, clientIdentifier, date);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "identifier=" + getIdentifier() +
                ", carSerialNumber=" + carSerialNumber +
                ", clientIdentifier='" + clientIdentifier + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public static class ContractBuilder {
        private Integer carSerialNumber;
        private Integer clientIdentifier;
        private Integer identifier;
        private String date;

        public ContractBuilder() { }

        public Contract build() {
            return new Contract(this);
        }

        public Integer getCarSerialNumber() {
            return carSerialNumber;
        }

        public ContractBuilder setCarSerialNumber(Integer carSerialNumber) {
            this.carSerialNumber = carSerialNumber;
            return this;
        }

        public Integer getClientIdentifier() {
            return clientIdentifier;
        }

        public ContractBuilder setClientIdentifier(Integer clientIdentifier) {
            this.clientIdentifier = clientIdentifier;
            return this;
        }

        public Integer getIdentifier() {
            return identifier;
        }

        public ContractBuilder setIdentifier(Integer identifier) {
            this.identifier = identifier;
            return this;
        }

        public String getDate() {
            return date;
        }

        public ContractBuilder setDate(String date) {
            this.date = date;
            return this;
        }
    }
}