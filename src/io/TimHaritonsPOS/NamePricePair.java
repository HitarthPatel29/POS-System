package io.TimHaritonsPOS;

import java.util.Objects;

public class NamePricePair {
    private final String name;
    private final double price;

    public NamePricePair(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        //NamePricePair that = (NamePricePair) o;
        return Objects.equals(name, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + "' -> $" + price;
    }
}
