package io.TimHaritonsPOS.Items.BakeryItems;

public class BostonCream extends BakeryItem {
    private double price = 1.50;

    public BostonCream(int id) {
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
        return "BostonCream{" +
                "price=" + price +
                '}';
    }
}
