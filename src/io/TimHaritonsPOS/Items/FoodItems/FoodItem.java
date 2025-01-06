package io.TimHaritonsPOS.Items.FoodItems;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.NamePricePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class FoodItem implements Item {
    private final int id;
    private final String ItemName;
    private final double price;
    private final LinkedList<NamePricePair> ingredients;
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
        this.ItemName = itemName;
        this.price = price;
        this.ingredients = ingredients;
        listOfModifiers();
        setModifications();
    }
    private void setModifications(){
        Scanner sc = new Scanner(System.in);
        int modifierNum = 0;
        while (true) {
            System.out.println("Enter the number to add Modifiers : ");
            modifierNum = sc.nextInt();
            if (modifierNum == 9) break;
            modifications.add(modifiers.get(modifierNum-1));
        }
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

    @Override
    public String getItemName() {
        return null;
    }


    private void listOfModifiers(){
        System.out.println("Pick Modifications for " + ItemName + " or else ENTER 9");
        for (int i = 0; i< modifiers.size(); i+=2){
            System.out.printf("%-" + 30 + "s %s\n", (i+1) +". "+  modifiers.get(i).toString(), (i+2) +". "+ modifiers.get(i+1).toString());
        }
        System.out.println("9. No more Addition!!!");
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



    @Override
    public String toString() {
        return "BreakFastSandwich{" +
                "price=" + getPrice() + printModification()+'}';
    }

}
