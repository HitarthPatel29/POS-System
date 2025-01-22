package io.TimHaritonsPOS.Items;

import io.TimHaritonsPOS.NamePricePair;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Item {
    public double getPrice();
    public String getItemName();
    public String getDisplayString();
    public int getId();
    public ArrayList<NamePricePair> getModifiers();

    public void setModifications(NamePricePair modifier);

    public void removeModification(NamePricePair modifier);
    public int getAmount();

    public void setAmount(int i);
    public String toString();

}
