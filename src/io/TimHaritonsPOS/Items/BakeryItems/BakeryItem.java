package io.TimHaritonsPOS.Items.BakeryItems;

import io.TimHaritonsPOS.Items.Item;

public class BakeryItem implements Item {
    protected final int id;
    protected final String itemName;
    protected final double price;

    public BakeryItem(int id, String itemName, double price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }
    public String getDisplayString(){
        return String.format("%-24s $%4.2f",itemName, getPrice());
    }

    @Override
    public String toString() {
        return itemName + "{" +
                "price=" + price +
                '}';
    }
}
