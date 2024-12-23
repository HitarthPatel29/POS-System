package io.TimHaritonsPOS.Items.FoodItems;

public class LoadedWrap extends FoodItem{
    private double price = 7.5;
    public LoadedWrap(int id) {
        super(id);
    }

    @Override
    public double getPrice() {
        return price + (0.5*modifications.size());
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LoadedWrap{" +
                "price=" + price + printModification()+
                '}';
    }
}
