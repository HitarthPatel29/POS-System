package io.TimHaritonsPOS.Items.BakeryItems;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.NamePricePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BakeryItem implements Item {
    protected final int id;
    protected final String itemName;
    protected final double price;
    private LinkedList<NamePricePair> modifications = new LinkedList<>();

    private final ArrayList<NamePricePair> modifiers = new ArrayList<>(Arrays.asList(
            new NamePricePair("Heated", 0.00),
            new NamePricePair("Cut", 0.00),
            new NamePricePair("Butter", 0.00)
    ));

    public BakeryItem(int id, String itemName, double price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public ArrayList<NamePricePair> getModifiers() {
        return modifiers;
    }

    @Override
    public void setModifications(NamePricePair newModification){
        modifications.add(newModification);
    }

    @Override
    public void removeModification(NamePricePair modificationToBeRemoved) {
        modifications.remove(modificationToBeRemoved);
    }

    @Override
    public int getAmount() {
        return 0;
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
    public void setAmount(int i) {

    }

    @Override
    public String toString() {
        return itemName + "{" +
                "price=" + price +
                '}';
    }
}
