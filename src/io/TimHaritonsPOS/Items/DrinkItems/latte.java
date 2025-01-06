package io.TimHaritonsPOS.Items.DrinkItems;

import io.TimHaritonsPOS.NamePricePair;

import java.util.ArrayList;
import java.util.Arrays;

public class latte extends DrinkItems{
    public latte(int id, char size) {
        super(id, size, "latte", new ArrayList<>(Arrays.asList(new NamePricePair("Milk", 0.00))));
    }

    public double getPrice() {
        double totalPrice = 0;
        switch (size){
            case 's' :
                totalPrice = 2.55;
                break;
            case 'm' :
                totalPrice = 3.90;
                break;
            case 'l' :
                totalPrice = 5.05;
                break;
            default:
                System.out.println("invalid ");
                break;
        }
        totalPrice += super.modifications.stream().mapToDouble(NamePricePair::getPrice).sum();
        return totalPrice;
    }
}
