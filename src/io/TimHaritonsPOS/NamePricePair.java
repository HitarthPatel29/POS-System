package io.TimHaritonsPOS;

import java.util.Objects;

public class NamePricePair {
    private final String name;
    private final double price;
    private int amount;

    public NamePricePair(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public NamePricePair(String name, double price) {
        this.name = name;
        this.price = price;
        this.amount = 1;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return (price*amount);
    }

    public void setAmount(int amount){
        this.amount = amount;
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
        return amount+" "+name + "' -> $" + price;
    }
}
