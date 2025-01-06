package io.TimHaritonsPOS.Items.DrinkItems;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.NamePricePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class DrinkItems implements Item {

    private final int id;
    protected final char size;
    private final String itemName;
    private LinkedList<NamePricePair> ingredients;
    protected LinkedList<NamePricePair> modifications = new LinkedList<>();
    private final ArrayList<NamePricePair> modifiers = new ArrayList<>(Arrays.asList(
            new NamePricePair("Regular", 0.00),
            new NamePricePair("DoubleDouble", 0.00),
            new NamePricePair("TripleTriple", 0.00),
            new NamePricePair("Cream", 0.00),
            new NamePricePair("Milk", 0.00),
            new NamePricePair("Sugar", 0.00),
            new NamePricePair("Sweetner", 0.00),
            new NamePricePair("Almond Milk", 0.50),
            new NamePricePair("Oat Milk", 0.50),
            new NamePricePair("whip topping", 1.00),
            new NamePricePair("Cane Syrup", 0.75)
    ));

    public DrinkItems(int id, char size, String itemName ) {
        this.id = id;
        this.size = size;
        this.itemName = itemName;
//        listOfModifiers();
//        setModifications();
    }
    public DrinkItems(int id, char size, String itemName , ArrayList<NamePricePair> modifications) {
        this.id = id;
        this.size = size;
        this.itemName = itemName;
        this.modifications.addAll(modifications);
//        listOfModifiers();
//        setModifications();
    }

    public abstract double getPrice();
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    public String getDisplayString(){
        return String.format("%-2s %-21s $%4.2f", size, itemName, getPrice());
    }

    public LinkedList<NamePricePair> getingredients(){
        return ingredients;
    }

    private void listOfModifiers(){
        System.out.println("Pick Modifications for " + itemName + " or else ENTER 9");
        int i = 1;
        for (NamePricePair modifer: modifiers) {
            System.out.println(i +". " +modifer.toString());
            i++;
        }
        System.out.println((i) +". No more Addition!!!");
    }

    private void setModifications(){
        Scanner sc = new Scanner(System.in);
        int modifierNum = 0;
        int modifierAmount = 1;
        while (true) {
            System.out.println("Enter the number to add Modifiers : ");
            modifierNum = sc.nextInt();
            if (modifierNum == modifiers.size()+1) {
                break;
            }
            NamePricePair mod = modifiers.get(modifierNum-1);
            mod.setAmount(modifierAmount);
            modifications.add(mod);
        }
    }
    private void setModifications(String modifier, int amount){
        if (modifier.equals("DoubleDoubleMilk")){
             modifications.add(new NamePricePair("Milk", 0.00, 2));
             modifications.add(new NamePricePair("Sugar", 0.00, 2));
        } else if (modifier.equals("TripleTripleMilk")) {
            modifications.add(new NamePricePair("Milk",0.00, 3));
            modifications.add(new NamePricePair("sugar",0.00, 3));
        } else if (modifier.equals("RegularMilk")) {
            modifications.add(new NamePricePair("Milk",0.00));
            modifications.add(new NamePricePair("sugar",0.00));
        } else {
            NamePricePair mod = modifiers.get(modifiers.indexOf(modifier));
            mod.setAmount(amount);
            modifications.add(mod) ;
        }
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
        return itemName+"{" +
                "price=" + getPrice() + printModification()+'}';
    }
}
