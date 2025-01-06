package io.TimHaritonsPOS.Items;

import io.TimHaritonsPOS.Items.BakeryItems.*;
import io.TimHaritonsPOS.Items.DrinkItems.*;
import io.TimHaritonsPOS.Items.FoodItems.BreakFastSandwich;
import io.TimHaritonsPOS.Items.FoodItems.LoadedWrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLists {
    private final ArrayList<Class<?>> muffinList = new ArrayList<>(
            List.of(
                    ChocoMuffin.class
            )
    );
    private final ArrayList<Class<?>> donutList = new ArrayList<>(
            Arrays.asList(
                AppleFritter.class,
                BostonCream.class
            )
    );

    private final ArrayList<Class<?>> pasteryList = new ArrayList<>(
            Arrays.asList(
                    HerbGarlicPastery.class,
                    ChocoCroissant.class
            )
    );

    private final ArrayList<Class<?>> hotDrinkList = new ArrayList<>(
            Arrays.asList(
                    OriginalBlendCoffee.class,
                    DecafCoffee.class,
                    DarkRoastCoffee.class,
                    latte.class
            )
    );

    private final ArrayList<Class<?>> breakFastFoodList= new ArrayList<>(
            Arrays.asList(
                    BreakFastSandwich.class
            )
    );

    private final ArrayList<Class<?>> lunchFoodList = new ArrayList<>(
            Arrays.asList(
                    LoadedWrap.class
            )
    );



    public ArrayList<Class<?>> getMuffinList() {
        return muffinList;
    }

    public ArrayList<Class<?>> getDonutList() {
        return donutList;
    }

    public ArrayList<Class<?>> getPasteryList() {
        return pasteryList;
    }

    public ArrayList<Class<?>> getHotDrinkList() {
        return hotDrinkList;
    }

    public ArrayList<Class<?>> getBreakFastFoodList() {
        return breakFastFoodList;
    }

    public ArrayList<Class<?>> getLunchFoodList() {
        return lunchFoodList;
    }
}
