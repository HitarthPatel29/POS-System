package io.TimHaritonsPOS.Items.BakeryItems;

public class ChocoMuffin extends BakeryItem{
    private double price = 2.50;

    public ChocoMuffin(int id) {
        super(id);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ChocoMuffin{" +
                "price=" + price +
                '}';
    }
}
