package io.TimHaritonsPOS.Items.DrinkItems;

import io.TimHaritonsPOS.Items.Item;

public class DrinkItems implements Item {

    private final int id;

    public DrinkItems(int id) {
        this.id = id;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public int getId() {
        return id;
    }
}
