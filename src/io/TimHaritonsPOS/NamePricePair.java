package io.TimHaritonsPOS;

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
    public String toString() {
        return name + "' -> $" + price;
    }
}
