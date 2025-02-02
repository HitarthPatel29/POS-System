package io.TimHaritonsPOS.GUI;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.Items.ItemLists;
import io.TimHaritonsPOS.NamePricePair;
import io.TimHaritonsPOS.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class PosController {

    private ArrayList<TreeItem<Item>> orderHistory = new ArrayList<>();
    private ArrayList<Order> orderHistory2 = new ArrayList<>();
    private TreeItem<Item> orderList;
    private double subTotal = 0;
    private char size;
    private int amount = 1;
    private boolean numberPadToggleOnOFF = false;
    private int orderReviewPageNum = 0;

    @FXML
    private TreeView<Item> orderListView;

    @FXML
    private TreeView<Item> orderListView1 = new TreeView<>();

    @FXML
    private TreeView<Item> orderListView2 = new TreeView<>();

    @FXML
    private TreeView<Item> orderListView3 = new TreeView<>();

    @FXML
    private TreeView<Item> orderListView4 = new TreeView<>();

    @FXML
    private TreeView<Item> orderListView5 = new TreeView<>();

    @FXML
    private TreeView<Item> orderListView6 = new TreeView<>();

    @FXML
    private TreeView<Item> orderListView7 = new TreeView<>();

    @FXML
    private Text totalText, subTotalText, taxText;

    @FXML
    private Text totalText1, subTotalText1, taxText1, totalText2, subTotalText2, taxText2, totalText3, subTotalText3, taxText3, totalText4, subTotalText4, taxText4, totalText5, subTotalText5, taxText5, totalText6, subTotalText6, taxText6, totalText7, subTotalText7, taxText7;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab bakeryTabPane, drinksTabPane, foodTabPane;

    @FXML
    private AnchorPane orderReviewPane, orderTakerPane;

    @FXML
    private AnchorPane bakeryPane, drinksPane, foodPane;

    @FXML
    private AnchorPane itemListPane, modifierPane;

    @FXML
    private Button largeSizeButton, mediumSizeButton, smallSizeButton;

    @FXML
    private Button breakFastPaneButton, lunchPaneButton;

    @FXML
    private Button coldDrinkPaneButton, hotDrinkPaneButton, otherDrinksPaneButton;

    @FXML
    private Button muffinPaneButton, donutPaneButton, pasteryPaneButton;

    @FXML
    private Button payNowButton, removeAllItemButton, removeItemButton, reviewOrdersButton;

    @FXML
    private Button exitOrderViewButton, prevPageOrderViewButton, nextPageOrderViewButton;

    private final ItemLists itemLists = new ItemLists();
    @FXML
    public void initialize() {
        orderReviewPane.setVisible(false);
        orderTakerPane.setVisible(true);
        modifierPane.setVisible(false);
        itemListPane.setVisible(true);

        // Set initial states
        tabPane.getSelectionModel().select(drinksTabPane);
        showHotDrinksPane(); // Display hotDrinksPane by default

        orderList = new TreeItem<>();
        orderListView.setRoot(orderList);
        orderList.setExpanded(true);
        orderListView.setShowRoot(false);


        //Set the CellFactory
        orderListView.setCellFactory(tv -> {
            TreeCell<Item> cell = new TextFieldTreeCell<Item>() {
                @Override
                public void updateItem(Item item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getDisplayString());
                        setStyle("-fx-font-family: 'Monospaced'; -fx-text-fill: black; -fx-font-weight: bold;");
                        setDisclosureNode(null);
                        getOnMouseClicked();
                    }
                }
            };

            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {

                    System.out.println(orderList.getChildren());
                    // Ensure the clicked node is either the root or its direct child
                    if (cell.getTreeItem().getParent().equals(orderListView.getRoot())) {
                        Item selectedItem = cell.getItem();
                        itemListPane.setVisible(false);
                        modifierPane.setVisible(true);
                        createButtons(selectedItem.getModifiers());
                    }
                }
                else {
                    modifierPane.setVisible(false);
                    itemListPane.setVisible(true);
                    cell.getTreeView().getSelectionModel().selectLast();
                }
            });
            return cell;
        });

        smallSizeButton.setOnAction((event -> size = 'S'));
        mediumSizeButton.setOnAction((event -> size = 'M'));
        largeSizeButton.setOnAction((event -> size = 'L'));

    }
    private Callback<TreeView<Item>, TreeCell<Item>> createCellFactory() {
        return tv -> {
            tv.setShowRoot(false);
            TreeCell<Item> cell = new TextFieldTreeCell<Item>() {
                @Override
                public void updateItem(Item item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getDisplayString());
                        setStyle("-fx-font-family: 'Monospaced'; -fx-text-fill: black; -fx-font-weight: bold;");
                        setDisclosureNode(null); // Hide disclosure node
                    }
                }
            };
            cell.setDisable(true);
            return cell;
        };
    }


    @FXML
    private void showMuffinPane() {
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
        createButtons(itemLists.getHotDrinkList(), drinksPane);
    }

    @FXML
    private void showColdDrinksPane() {
        createButtons(itemLists.getHotDrinkList(), drinksPane);
    }

    @FXML
    private void showOtherDrinksPane() {
        createButtons(itemLists.getHotDrinkList(), drinksPane);
    }

    @FXML
    private void showBreakFastFoodPane() {
        createButtons(itemLists.getBreakFastFoodList(), foodPane);
    }

    @FXML
    private void showLunchFoodPane() {
        createButtons(itemLists.getLunchFoodList(), foodPane);
    }

    @FXML
    private void numberPadHandler(ActionEvent event){
        // Get the text of the clicked button (number as a string)
        int numberClicked = Integer.parseInt(((Button) event.getSource()).getText());

        if (numberPadToggleOnOFF){
            amount = amount*10 + numberClicked;
        }
        else {
            numberPadToggleOnOFF = true;
            amount = numberClicked;
        }
        // Handle the number (e.g., append to a text field, print to console, etc.)
        System.out.println("Number clicked: " + amount + " numberClicked " + numberClicked);
    }


    @FXML
    public void createButtons(List<Class<?>> itemClassList, AnchorPane itemPane) {
        itemPane.getChildren().clear(); // Clear existing buttons, if any

        // Start Y-coordinate for button placement
        double yOffset = 10.0;
        double xOffSet = 10.0;

        for (Class<?> itemClass :itemClassList) {
            try {

                Item itemInstance;
                if (itemPane.getId().equals("drinksPane")){
                    itemInstance = (Item) itemClass.getDeclaredConstructor(int.class, char.class).newInstance(1, 'S');
                }
                else {
                     itemInstance = (Item) itemClass.getDeclaredConstructor(int.class).newInstance(1);
                }

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
                itemButton.setOnAction(e -> addItemToList(itemClass, itemPane));

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
    private void addItemToList(Class<?> itemClass, AnchorPane itemPane) {
        try {
            // Create an instance of the class
            Item item;
            if (itemPane.getId().equals("drinksPane")){
                if (size != 0 ) {
                    item = (Item) itemClass.getDeclaredConstructor(int.class, char.class).newInstance(1, size);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Size is not Defined! Select the size and then the drink" , ButtonType.OK);
                    alert.show();
                    return;
                }
            }
            else {
                item = (Item) itemClass.getDeclaredConstructor(int.class).newInstance(1);
            }

            // Add the object to the item list
            TreeItem<Item> newOrder = new TreeItem<>(item);
            newOrder.setExpanded(true);

            orderList.getChildren().add(newOrder);

            size = 0;
            subTotal += item.getPrice();
            printTotal(subTotal);
            System.out.println("Added item: " + item);

        } catch (Exception e) {
            System.err.println("Error handling button click for class: " + itemClass.getName());
            e.printStackTrace();
        }
    }

    public void createButtons(ArrayList<NamePricePair> modifierList){
        modifierPane.getChildren().clear(); // Clear existing buttons, if any

        // Start Y-coordinate for button placement
        double yOffset = 20.0;
        double xOffSet = 20.0;

        for (NamePricePair modifier: modifierList) {
            if (xOffSet + 150 > modifierPane.getWidth()) {
                xOffSet = 20;
                yOffset += 60;
            }
            System.out.println(modifierPane.getWidth());
            // Create a new button for the muffin
            Button itemButton = new Button(modifier.getName());
            itemButton.setLayoutX(xOffSet); // X-coordinate
            itemButton.setLayoutY(yOffset); // Y-coordinate
            itemButton.setPrefWidth(150.0); // Button width
            itemButton.setPrefHeight(50.0); // Button height

            // Add button handler to add muffin to the orderList
            itemButton.setOnAction(e -> addModificationToList(modifier));

            // Add the button to the muffinPane
            modifierPane.getChildren().add(itemButton);
            xOffSet += 160;
        }
    }

    /**
     * This method is buttonHandler for modification buttons in the modifier Pane of each item (Pane that opens when an Item is clicked).
     * @param newModification
     */
    private void addModificationToList(Item newModification) {
        try {
            TreeItem<Item> selectedTreeItem = orderListView.getSelectionModel().getSelectedItem();

            // set the amount of modifier
            newModification.setAmount(amount > 0 ? amount : 1);
            addModificationToTreeItem((NamePricePair) newModification, selectedTreeItem);

            //reset variable 'amount' to 1
            amount = 1;
            numberPadToggleOnOFF = false;
        } catch (Exception e) {
            System.err.println("Error handling button click for class: " + newModification);
            e.printStackTrace();
        }
    }

    /**
     * this method is buttonHandler for the quick coffee modification buttons at the bottom of the main screen
     * @param event
     */
    @FXML
    private void addModificationToList(ActionEvent event) {
        Item newModification = null;
        try {
            //Get Selected TreeItem to add the modification tree item under it.
            TreeItem<Item> selectedTreeItem;
            try {
                selectedTreeItem = orderListView.getSelectionModel().getSelectedItem();
                if (selectedTreeItem == null) {
                    // No selection, use the last child of the root
                    System.out.println("entered the unused part at line 427");
                    selectedTreeItem = orderList.getChildren().get(orderList.getChildren().size() - 1);
                }
            } catch (Exception e) {
                System.err.println("Error getting selected item, defaulting to last child: " + e.getMessage());
                selectedTreeItem = orderList.getChildren().get(orderList.getChildren().size() - 1);
            }
            System.out.println(selectedTreeItem);


            //get the Item to select form available modifiers.
            Item item;
            if (selectedTreeItem.getParent() != null && selectedTreeItem.getParent().equals(orderListView.getRoot())) {
                item = selectedTreeItem.getValue();
            } else {
                item = selectedTreeItem.getParent().getValue();
            }

            if (item == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No Item in the Order!",ButtonType.OK);
                alert.show();
                System.err.println("No valid item found in the selected TreeItem or its parent.");
                return;
            }

            //get the button text
            String buttonText = ((Button) event.getSource()).getText();
            System.out.println("Button : " + buttonText);

            //add modification with respect to button text
            if (buttonText.equals("DD w Milk")) {
                addModificationToTreeItem(new NamePricePair("Milk", 0.00, 2*amount), selectedTreeItem);
                addModificationToTreeItem(new NamePricePair("Sugar", 0.00, 2*amount), selectedTreeItem);
            } else if (buttonText.equals("TT w Milk")) {
                addModificationToTreeItem((new NamePricePair("Milk", 0.00, 3*amount)), selectedTreeItem);
                addModificationToTreeItem((new NamePricePair("Sugar", 0.00, 3*amount)), selectedTreeItem);
            } else if (buttonText.equals("Regular w Milk")) {
                addModificationToTreeItem((new NamePricePair("Milk", 0.00, amount)), selectedTreeItem);
                addModificationToTreeItem((new NamePricePair("Sugar", 0.00, amount)), selectedTreeItem);
            } else {
                newModification = item.getModifiers().get(item.getModifiers().indexOf(new NamePricePair(buttonText, 0)));
                // set the amount of modifier
                newModification.setAmount(amount > 0 ? amount : 1);
                addModificationToTreeItem((NamePricePair) newModification, selectedTreeItem);
            }

            //reset variable 'amount' to 1
            numberPadToggleOnOFF = false;
            amount = 1;

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Selection!" + e.getMessage(),ButtonType.OK);
            alert.show();
            System.err.println("Error handling button click for class: " + newModification);
            e.printStackTrace();
        }
    }

    private void addModificationToTreeItem(NamePricePair newModification, TreeItem<Item> selectedTreeItem){
        TreeItem<Item> modification = new TreeItem<>(newModification);
        modification.setExpanded(true);

        if (selectedTreeItem.getParent().equals(orderListView.getRoot())) {
            selectedTreeItem.getChildren().add(modification);
            selectedTreeItem.getValue().setModifications((NamePricePair) modification.getValue());
        } else {
            selectedTreeItem.getParent().getChildren().add(modification);
        }
        System.out.println(selectedTreeItem + "...");

        //Calculate Price
        subTotal += newModification.getPrice();
        printTotal(subTotal);
        System.out.println("Added item: " + newModification);
    }

//    @FXML
//    private void handlePayNow() {
//        System.out.println("Payment Process Initiated!");
//        // Add payment processing logic here
//        if (orderList.getChildren().size() > 0) {
//            orderHistory.add(orderList);
//            orderList = new TreeItem<>();
//            orderListView.setRoot(orderList);
//            subTotal = 0;
//            printTotal(subTotal);
//            size = 0;
//            amount = 1;
//            numberPadToggleOnOFF = false;
//        }
//    }
    @FXML
    private void handlePayNow() {
        System.out.println("Payment Process Initiated!");
        // Add payment processing logic here
        if (orderList.getChildren().size() > 0) {
            Order currentOrder = new Order(orderList, subTotal, "creditCard");
            orderHistory2.add(currentOrder);

            //resetting everything
            orderList = new TreeItem<>();
            orderListView.setRoot(orderList);
            subTotal = 0;
            printTotal(subTotal);
            size = 0;
            amount = 1;
            numberPadToggleOnOFF = false;
        }
    }

    /**
     * orderViewList:
     *      orderList (Root)
     *          orderList.getChildren (1st Layer) -> Items
     *              orderList.getChildren (2nd Layer) -> Modifications
     *          orderList.getChildren (1st Layer) -> Items
     *              orderList.getChildren (2nd Layer) -> Modifications
     *              orderList.getChildren (2nd Layer) -> Modifications
     *              orderList.getChildren (2nd Layer) -> Modifications
     *          orderList.getChildren (1st Layer) -> Items
     *              orderList.getChildren (2nd Layer) -> Modifications
     */

    @FXML
    private void handleRemoveItem() {
        try {
            TreeItem<Item> selectedItem = orderListView.getSelectionModel().getSelectedItem();
            if (selectedItem.getParent().equals(orderListView.getRoot())) {
                orderList.getChildren().remove(selectedItem);
            } else {
                selectedItem.getParent().getChildren().remove(selectedItem);
            }

            subTotal -= selectedItem.getValue().getPrice();
            printTotal(subTotal);
            System.out.println("Removed item: " + selectedItem);
        }
        catch (NullPointerException npe){
            try {
                subTotal-= orderList.getChildren().remove(orderList.getChildren().size() - 1).getValue().getPrice();
                printTotal(subTotal);
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "No items to remove!", ButtonType.OK);
                alert.show();
            }
        }
    }

    /**
     * displays and calculates total values for main Screen
     * (Text sections are default)
     * @param subTotal
     */
    private void printTotal(double subTotal){
        subTotalText.setText(String.format ("%.2f", subTotal));
        taxText.setText(String.format ("%.2f", (subTotal*13)/100));
        totalText.setText(String.format ("%.2f", subTotal + (subTotal*13)/100));
    }

    /**
     * displays and calculates total values for review Screen
     * (Text sections are given in argument)
     * @param subTotal  subtotal amount of the order
     * @param subTotalText  Text section for subTotal
     * @param taxText   Text section for taxTotal
     * @param totalText     Text section for totalTax
     */
    private void printTotal(double subTotal, Text subTotalText, Text taxText, Text totalText){
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

    @FXML
    private void handleReviewOrders() {

        orderListView1.setCellFactory(createCellFactory());
        orderListView2.setCellFactory(createCellFactory());
        orderListView3.setCellFactory(createCellFactory());
        orderListView4.setCellFactory(createCellFactory());
        orderListView5.setCellFactory(createCellFactory());
        orderListView6.setCellFactory(createCellFactory());
        orderListView7.setCellFactory(createCellFactory());

        orderReviewPageNum = 0;
        orderReviewPane.setVisible(true);
        System.out.println("Reviewing Orders: pageNum : " + orderHistory2.size());
        showReviewPage(orderReviewPageNum);         //displaying the 1st Order History Page
        nextPageOrderViewButton.setDisable(orderHistory2.size() <= 7);      //button will be disabled if size <= 7 (true) and will be enabled if size > 6 (false)

        exitOrderViewButton.setOnAction(event-> {
            orderReviewPane.setVisible(false);
            orderTakerPane.setVisible(true);
        });

        prevPageOrderViewButton.setOnAction(event -> {
            if (orderReviewPageNum < (orderHistory2.size()%7 == 0? orderHistory2.size()/7 : (orderHistory2.size()/7)+1)) nextPageOrderViewButton.setDisable(false);
            orderReviewPageNum--;
            showReviewPage(orderReviewPageNum);
            if (orderReviewPageNum == 0) prevPageOrderViewButton.setDisable(true);
        });

        nextPageOrderViewButton.setOnAction(event -> {
            if (orderReviewPageNum >=0) prevPageOrderViewButton.setDisable(false);
            orderReviewPageNum++;
            showReviewPage(orderReviewPageNum);
            if (orderReviewPageNum == (orderHistory2.size()%7 == 0 ? orderHistory2.size()/7 : ((orderHistory2.size()/7)+1))-1) nextPageOrderViewButton.setDisable(true);
        });
    }

    /**
     * displays 7 orders form the orderHistory.
     * if the pageNum = 0 , will display last 7 orders.
     * if pageNum = 1, will display from last 1+(7)th to last 1+(7+6)th order
     * if pageNum = n, will display from last 1+(n*7)th to last 1+(n*7)+6th order
     * @param pageNum
     */
    private void showReviewPage(int pageNum){
        int startIndex = orderHistory2.size()-1-(pageNum*7);
        if (orderHistory2.size() > (pageNum*7)) setReviewSection(orderHistory2.get(startIndex), orderListView1, subTotalText1, taxText1, totalText1);
        if (orderHistory2.size() > (pageNum*7)+1) setReviewSection(orderHistory2.get(startIndex-1), orderListView2, subTotalText2, taxText2, totalText2);
        if (orderHistory2.size() > (pageNum*7)+2) setReviewSection(orderHistory2.get(startIndex-2), orderListView3, subTotalText3, taxText3, totalText3);
        if (orderHistory2.size() > (pageNum*7)+3) setReviewSection(orderHistory2.get(startIndex-3), orderListView4, subTotalText4, taxText4, totalText4);
        if (orderHistory2.size() > (pageNum*7)+4) setReviewSection(orderHistory2.get(startIndex-4), orderListView5, subTotalText5, taxText5, totalText5);
        if (orderHistory2.size() > (pageNum*7)+5) setReviewSection(orderHistory2.get(startIndex-5), orderListView6, subTotalText6, taxText6, totalText6);
        if (orderHistory2.size() > (pageNum*7)+6) setReviewSection(orderHistory2.get(startIndex-6), orderListView7, subTotalText7, taxText7, totalText7);
    }

    private void setReviewSection(Order order, TreeView<Item> listView, Text subTotalText, Text taxText, Text totalText){
        if (order != null) {
            listView.setRoot(order.getOrderList());
            printTotal(order.getSubTotal(), subTotalText, taxText, totalText);
        }
    }
}
