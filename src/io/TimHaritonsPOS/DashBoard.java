package io.TimHaritonsPOS;

import io.TimHaritonsPOS.Items.BakeryItems.AppleFritter;
import io.TimHaritonsPOS.Items.BakeryItems.BostonCream;
import io.TimHaritonsPOS.Items.BakeryItems.ChocoMuffin;
import io.TimHaritonsPOS.Items.FoodItems.BreakFastSandwich;
import io.TimHaritonsPOS.Items.Item;

import java.util.LinkedList;

public class DashBoard {
    public static void main(String[] args) {
        LinkedList<Item> order = new LinkedList<>();
        int id = 0;
        order.add(new AppleFritter(id++));
        order.add(new BostonCream(id++));
        order.add(new ChocoMuffin(id++));
        System.out.println(order.toString());

        order.add(new BreakFastSandwich(id++));
        System.out.println(order.toString());
    }
}
