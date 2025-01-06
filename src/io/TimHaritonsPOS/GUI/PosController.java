package io.TimHaritonsPOS.GUI;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.Items.ItemLists;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.List;

public class PosController {

    private TreeItem<Item> orderList;

    @FXML
    private TreeView<Item> orderListView;

    @FXML
    private Text totalText;
    private double subTotal = 0;

    @FXML
    private Text subTotalText;

    @FXML
    private Text taxText;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane bakeryPane;

    @FXML
    private Tab bakeryTabPane;

    @FXML
    private Button breakFastPaneButton;

    @FXML
    private Button coldDrinkPaneButton;

    @FXML
    private Button donutPaneButton;

    @FXML
    private AnchorPane drinksPane;

    @FXML
    private Tab drinksTabPane;

    @FXML
    private AnchorPane foodPane;

    @FXML
    private Tab foodTabPane;

    @FXML
    private Button hotDrinkPaneButton;

    @FXML
    private Button lunchPaneButton;

    @FXML
    private Button muffinPaneButton;

    @FXML
    private Button otherDrinksPaneButton;

    @FXML
    private Button pasteryPaneButton;


    @FXML
    private Button payNowButton;

    @FXML
    private Button removeAllItemButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button reviewOrdersButton;

    private final ItemLists itemLists = new ItemLists();
    @FXML
    public void initialize() {

        // Set initial states
        tabPane.getSelectionModel().select(drinksTabPane);
        showHotDrinksPane(); // Display hotDrinksPane by default

        orderList = new TreeItem<>();
        orderListView.setRoot(orderList);
        orderList.setExpanded(true);
        orderListView.setShowRoot(false);

        //Set the CellFactory
        orderListView.setCellFactory(tv -> new TextFieldTreeCell<Item>() {
            @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getItemName());
                    setDisclosureNode(null);
                }
            }
        });
    }

    @FXML
    private void showMuffinPane() {
        System.out.println(itemLists.getMuffinList());
        createButtons(itemLists.getMuffinList(), bakeryPane);
    }

    @FXML
    private void showDonutPane() {
        createButtons(itemLists.getDonutList(), bakeryPane);
    }

    @FXML
    private void showPasteryPane() {
        createButtons(itemLists.getPasteryList(), bakeryPane);
    }

    @FXML
    private void showHotDrinksPane() {
    }

    @FXML
    private void showColdDrinksPane() {
    }

    @FXML
    private void showOtherDrinksPane() {
    }

    @FXML
    public void createButtons(List<Class<?>> itemClassList, AnchorPane itemPane) {
        itemPane.getChildren().clear(); // Clear existing buttons, if any

        // Start Y-coordinate for button placement
        double yOffset = 10.0;
        double xOffSet = 10.0;

        for (Class<?> itemClass :itemClassList) {
            try {
                Item itemInstance = (Item) itemClass.getDeclaredConstructor().newInstance();

                if (xOffSet+150 > itemPane.getPrefWidth()) {
                    xOffSet = 10;
                    yOffset += 60;
                }
                // Create a new button for the muffin
                Button itemButton = new Button((String) itemClass.getMethod("getItemName").invoke(itemInstance));
                itemButton.setLayoutX(xOffSet); // X-coordinate
                itemButton.setLayoutY(yOffset); // Y-coordinate
                itemButton.setPrefWidth(150.0); // Button width
                itemButton.setPrefHeight(50.0); // Button height

                // Add button handler to add muffin to the orderList
                itemButton.setOnAction(e -> addItemToList(itemClass));

                // Add the button to the muffinPane
                itemPane.getChildren().add(itemButton);
                xOffSet+=160;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void addItemToList(Class<?> itemClass) {
        try {
            // Create an instance of the class
            Item item = (Item) itemClass.getDeclaredConstructor(int.class).newInstance(1);

            // Add the object to the item list
            TreeItem<Item> newOrder = new TreeItem<>(item);
            newOrder.setExpanded(true);
            orderList.getChildren().add(newOrder);

            System.out.println(orderList);
            System.out.println(orderList.getChildren());
            subTotal += item.getPrice();
            printTotal(subTotal);
            System.out.println("Added item: " + item);
        } catch (Exception e) {
            System.err.println("Error handling button click for class: " + itemClass.getName());
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePayNow() {
        System.out.println("Payment Process Initiated!");
        // Add payment processing logic here
    }

    @FXML
    private void handleRemoveItem() {
        TreeItem<Item> selectedItem = orderListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            orderList.getChildren().remove(selectedItem);
            subTotal -= selectedItem.getValue().getPrice();
            printTotal(subTotal);
            System.out.println("Removed item: " + selectedItem);
        } else {
            System.out.println("No item selected to remove.");
        }
    }

    private void printTotal(double subTotal){
        subTotalText.setText(String.format ("%.2f", subTotal));
        taxText.setText(String.format ("%.2f", (subTotal*13)/100));
        totalText.setText(String.format ("%.2f", subTotal + (subTotal*13)/100));
    }

    @FXML
    private void handleRemoveAllItems() {
        orderList.getChildren().clear();
        subTotal = 0.00;
        printTotal(subTotal);
        System.out.println("All items removed.");
    }

//    @FXML
//    private void handleReviewOrders() {
//        System.out.println("Reviewing Orders:");
//        orderList.getItems().forEach(System.out::println);
//    }
}
