package io.TimHaritonsPOS.Items;

public interface Item {
    public double getPrice();
    public String getItemName();
    public String getDisplayString();
    public int getId();
    public String toString();
}
