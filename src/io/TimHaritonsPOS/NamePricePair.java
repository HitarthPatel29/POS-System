package io.TimHaritonsPOS;

import io.TimHaritonsPOS.Items.Item;

import java.util.ArrayList;
import java.util.Objects;

public class NamePricePair implements Item {
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

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public String getDisplayString() {
        if (getPrice() > 0) return String.format("%-2s %-19s $%4.2f", amount, name, getPrice());
        else return String.format("%-2s %-19s", amount, name);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public ArrayList<NamePricePair> getModifiers() {
        return null;
    }

    @Override
    public void setModifications(NamePricePair modifier) {
    }

    @Override
    public void removeModification(NamePricePair modificationToBeRemoved) {
    }

    @Override
    public int getAmount() {
        return 0;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Comparing with: " + o);
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof String) {
            return name.equalsIgnoreCase((String) o);
        }
        if (o instanceof NamePricePair other) {
            return name.equalsIgnoreCase(other.name);
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return amount+" "+name + " -> $" + price;
    }
}
