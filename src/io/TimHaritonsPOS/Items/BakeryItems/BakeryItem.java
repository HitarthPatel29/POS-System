package io.TimHaritonsPOS.Items.BakeryItems;

import io.TimHaritonsPOS.Items.Item;

public abstract class BakeryItem implements Item {
    protected final int id;

    public BakeryItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
