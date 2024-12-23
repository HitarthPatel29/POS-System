package io.TimHaritonsPOS.Items.BakeryItems;

public class AppleFritter extends BakeryItem{

    private double price = 1.5;

    public AppleFritter(int id) {
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
        return "AppleFritter{" +
                "price=" + price +
                '}';
    }
}
