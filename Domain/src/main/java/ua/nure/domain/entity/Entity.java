package ua.nure.domain.entity;

public class Entity {
    private Integer identifier;

    private Entity() {
        identifier = -1;
    }

    public Entity(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = this.identifier.equals(-1) ? identifier : this.identifier;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Entity && ((Entity) object).identifier == identifier;
    }

    @Override
    public String toString() {
        return "Entity{" + "identifier=" + identifier + '}';
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}