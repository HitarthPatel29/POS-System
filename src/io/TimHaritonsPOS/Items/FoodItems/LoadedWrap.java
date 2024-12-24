package io.TimHaritonsPOS.Items.FoodItems;

import io.TimHaritonsPOS.NamePricePair;

import java.util.Arrays;
import java.util.LinkedList;

public class LoadedWrap extends FoodItem{
    public LoadedWrap(int id) {super(id, "Loaded Wrap", 7.5,  new LinkedList<>(
            Arrays.asList(
                    new NamePricePair("lettuce", 0.50),
                    new NamePricePair("Tomato", 0.50),
                    new NamePricePair("Onions", 0.50),
                    new NamePricePair("Chipotle", 0.75),
                    new NamePricePair("Sausage", 1.50)
            )));
    }
}
