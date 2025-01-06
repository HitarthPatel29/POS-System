package io.TimHaritonsPOS.Items;

import io.TimHaritonsPOS.Items.BakeryItems.*;

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

//    private ArrayList<Item> BreakFast;

    public ArrayList<Class<?>> getMuffinList() {
        return muffinList;
    }

    public ArrayList<Class<?>> getDonutList() {
        return donutList;
    }

    public ArrayList<Class<?>> getPasteryList() {
        return pasteryList;
    }
}
