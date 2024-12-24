package io.TimHaritonsPOS.Items.FoodItems;

import io.TimHaritonsPOS.NamePricePair;

import java.util.Arrays;
import java.util.LinkedList;

public class BreakFastSandwich extends FoodItem {
    public BreakFastSandwich(int id) {
        super(id, "Breakfast Sandwich", 4.0 , new LinkedList<>(
            Arrays.asList(
                new NamePricePair("Cheese", 1.00),
                new NamePricePair("Chipotle", 0.75),
                new NamePricePair("Bacon", 1.50)
            )));
    }

}
