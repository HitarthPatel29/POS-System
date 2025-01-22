package io.TimHaritonsPOS.Items.FoodItems;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.NamePricePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class FoodItem implements Item {
    private final int id;
    private final String itemName;
    private final double price;
    private LinkedList<NamePricePair> ingredients;
    private LinkedList<NamePricePair> modifications = new LinkedList<>();

    private final ArrayList<NamePricePair> modifiers = new ArrayList<>(Arrays.asList(
            new NamePricePair("Cheese", 1.00),
            new NamePricePair("lettuce", 0.50),
            new NamePricePair("Tomato", 0.50),
            new NamePricePair("Onions", 0.50),
            new NamePricePair("Chipotle", 0.75),
            new NamePricePair("Ketchup", 0),
            new NamePricePair("Bacon", 1.50),
            new NamePricePair("Sausage", 1.50)
    ));

    public FoodItem(int id, String itemName, double price, LinkedList<NamePricePair> ingredients ) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.ingredients = ingredients;
    }
    public double getPrice() {
        double totalPrice = price;
        for (NamePricePair modifier: modifications) {
            totalPrice+= modifier.getPrice();
        }
        return totalPrice;
    }
    @Override
    public int getId() {
        return id;
    }
    public LinkedList<NamePricePair> getIngredients(){
        return ingredients;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    public String getDisplayString(){
        return String.format("%-24s $%4.2f",itemName, getPrice());
    }


    @Override
    public ArrayList<NamePricePair> getModifiers() {
        return modifiers;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public void setAmount(int i) {

    }

    private void listOfModifiers(){
        System.out.println("Pick Modifications for " + itemName + " or else ENTER 9");
        int i = 1;
        for (NamePricePair modifier: modifiers){
            System.out.println(i + ". " + modifier.toString());
            i++;
        }
        System.out.println((i++) + ". To Remove Item!!!");
        System.out.println(i+". No more Addition!!!");
    }

    @Override
    public void setModifications(NamePricePair newModification){
        modifications.add(newModification);
    }

    @Override
    public void removeModification(NamePricePair modificationToBeRemoved) {
        modifications.remove(modificationToBeRemoved);
    }

    private String printModification(){
        if (modifications.size() == 0) return "";
        else {
            StringBuilder returnString = new StringBuilder("{");
            for (NamePricePair modifier: modifications) {
                returnString.append(modifier).append(", ");
            }
            returnString.append("}");
            return returnString.toString();
        }
    }

    private void printIngredients(){
        for (int i = 0; i< ingredients.size(); i++){
            System.out.println( (i+1) +". "+  ingredients.get(i).toString() );
        }
    }

    public void removeIngredients(int id){
        ingredients.remove(id-1);
    }


    @Override
    public String toString() {
        return "BreakFastSandwich{" +
                "price=" + getPrice() + printModification()+'}';
    }

}
