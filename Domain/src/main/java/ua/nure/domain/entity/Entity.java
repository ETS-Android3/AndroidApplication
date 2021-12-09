package ua.nure.domain.entity;

public class Entity {
    private Object identifier;

    private Entity() {
        identifier = -1;
    }

    public Entity(Object identifier) {
        this.identifier = identifier;
    }

    public Object getIdentifier() {
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