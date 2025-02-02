package io.TimHaritonsPOS;

import io.TimHaritonsPOS.Items.Item;
import javafx.scene.control.TreeItem;


public class Order {

    private TreeItem<Item> orderList;
    private double subTotal = 0;
    private String paymentDetails;

    public Order(TreeItem<Item> orderList, double subTotal, String paymentDetails) {
        this.orderList = orderList;
        this.subTotal = subTotal;
        this.paymentDetails = paymentDetails;
    }

    public TreeItem<Item> getOrderList() {
        return orderList;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }
}
