package io.TimHaritonsPOS.Items.FoodItems;

import io.TimHaritonsPOS.Items.Item;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class FoodItem implements Item {
    protected final int id;
    protected LinkedList<String> ingredients = new LinkedList<>();
    protected LinkedList<String> modifications = new LinkedList<>();
    protected LinkedList<String> modifiers = new LinkedList<>(Arrays.asList("cheese", "lettuce", "tomato", "onions", "chipotle", "ketchup", "Bacon", "Sausage"));

    public FoodItem(int id) {
        this.id = id;
        listOfModifiers();
        setModifications();
    }
    protected void setModifications(){
        Scanner sc = new Scanner(System.in);
        int modifierNum = 0;
        while (true) {
            System.out.println("Enter the number to add Modifiers : ");
            modifierNum = sc.nextInt();
            if (modifierNum == 9) break;
            modifications.add(modifiers.get(modifierNum-1));
        }
    }

    @Override
    public int getId() {
        return id;
    }

    protected void listOfModifiers(){
        System.out.printf("%-" + 30 + "s %s\n", "1. cheese", "2. lettuce");
        System.out.printf("%-" + 30 + "s %s\n", "3. Tomato", "4. Onions");
        System.out.printf("%-" + 30 + "s %s\n", "5. Chipotle", "6. Ketchup");
        System.out.printf("%-" + 30 + "s %s\n", "7. Bacon", "8. Sausage");
        System.out.println("9. No more Addition!!!");
    }

    protected String printModification(){
        if (modifications.size() == 0) return "";
        else {
            String returnString = "{";
            for (String modifier: modifications) {
                returnString+= modifier + ", ";
            }
            returnString+= "}";
            return  returnString;
        }
    }

}
