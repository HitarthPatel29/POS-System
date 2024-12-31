package io.TimHaritonsPOS.Items.DrinkItems;

import io.TimHaritonsPOS.NamePricePair;

public class Coffee extends DrinkItems{

    public Coffee(int id, char size, String type) {
        super(id, size, type+"Coffee");
    }

    public double getPrice() {
        double totalPrice = 0;
        switch (size){
            case 's' :
                totalPrice = 1.55;
                break;
            case 'm' :
                totalPrice = 1.90;
                break;
            case 'l' :
                totalPrice = 2.05;
                break;
            default:
                System.out.println("invalid ");
                break;
        }
        for (NamePricePair modifier: modifications) {
            totalPrice+=modifier.getPrice();
        }
        return totalPrice;
    }
}
