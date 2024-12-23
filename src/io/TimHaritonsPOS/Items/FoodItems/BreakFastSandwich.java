package io.TimHaritonsPOS.Items.FoodItems;

public class BreakFastSandwich extends FoodItem {

    private double price = 3.50;

    public BreakFastSandwich(int id) {
        super(id);
    }

    public double getPrice() {
        return price + (0.5*modifications.size()) ;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BreakFastSandwich{" +
                "price=" + getPrice() + printModification()+'}';
    }
}
