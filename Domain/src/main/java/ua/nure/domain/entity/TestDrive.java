package ua.nure.domain.entity;

import java.util.Objects;

public class TestDrive extends Entity {
    private Integer carSerialNumber;
    private Integer clientIdentifier;
    private Integer score;

    private TestDrive(TestDriveBuilder testDriveBuilder) {
        super(testDriveBuilder.getIdentifier());
        setClientIdentifier(testDriveBuilder.getClientIdentifier());
        setCarSerialNumber(testDriveBuilder.getCarSerialNumber());
        setScore(testDriveBuilder.getScore());
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
        return Objects.hash(super.hashCode(), carSerialNumber, clientIdentifier, score);
    }

    @Override
    public String toString() {
        return "TestDrive{" +
                "identifier=" + getIdentifier() +
                ", carSerialNumber=" + carSerialNumber +
                ", clientIdentifier=" + clientIdentifier +
                ", score=" + score +
                '}';
    }

    public static class TestDriveBuilder {
        private Integer carSerialNumber;
        private Integer clientIdentifier;
        private Integer identifier;
        private Integer score;

        public TestDriveBuilder() { }

        public TestDrive build() {
            return new TestDrive(this);
        }

        public Integer getCarSerialNumber() {
            return carSerialNumber;
        }

        public TestDriveBuilder setCarSerialNumber(Integer carSerialNumber) {
            this.carSerialNumber = carSerialNumber;
            return this;
        }

        public Integer getClientIdentifier() {
            return clientIdentifier;
        }

        public TestDriveBuilder setClientIdentifier(Integer clientIdentifier) {
            this.clientIdentifier = clientIdentifier;
            return this;
        }

        public Integer getIdentifier() {
            return identifier;
        }

        public TestDriveBuilder setIdentifier(Integer identifier) {
            this.identifier = identifier;
            return this;
        }

        public Integer getScore() {
            return score;
        }

        public TestDriveBuilder setScore(Integer score) {
            this.score = score;
            return this;
        }
    }
}