package io.TimHaritonsPOS.GUI;

import io.TimHaritonsPOS.Items.Item;
import io.TimHaritonsPOS.Items.ItemLists;
import io.TimHaritonsPOS.NamePricePair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class PosController {

    private ArrayList<TreeItem<Item>> orderHistory = new ArrayList<>();
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
    private GridPane orderHistoryViewsGrid;

    private final TreeView<Item>[] listOfOrderHistoryViews = new TreeView[]{orderListView1, orderListView2, orderListView3, orderListView4, orderListView5};

    @FXML
    private Text totalText;

    @FXML
    private Text subTotalText;

    @FXML
    private Text taxText;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab bakeryTabPane;

    @FXML
    private Tab drinksTabPane;

    @FXML
    private Tab foodTabPane;

    @FXML
    private AnchorPane orderReviewPane;

    @FXML
    private AnchorPane orderTakerPane;

    @FXML
    private AnchorPane bakeryPane;

    @FXML
    private AnchorPane drinksPane;

    @FXML
    private AnchorPane foodPane;

    @FXML
    private AnchorPane modifierPane;

    @FXML
    private AnchorPane itemListPane;

    @FXML
    private Button breakFastPaneButton;

    @FXML
    private Button coldDrinkPaneButton;

    @FXML
    private Button donutPaneButton;

    @FXML
    private Button largeSizeButton;

    @FXML
    private Button mediumSizeButton;

    @FXML
    private Button smallSizeButton;

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

    @FXML
    private Button exitOrderViewButton ;

    @FXML
    private Button prevPageOrderViewButton;

    @FXML
    private Button nextPageOrderViewButton;

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




    @FXML
    private void handlePayNow() {
        System.out.println("Payment Process Initiated!");
        // Add payment processing logic here
        if (orderList.getChildren().size() > 0) {
            orderHistory.add(orderList);
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

    @FXML
    private void handleReviewOrders() {
        orderReviewPageNum = 0;
        orderReviewPane.setVisible(true);
        System.out.println("Reviewing Orders:");
        showReviewPage(orderReviewPageNum);
        if (orderHistory.size() <= 5) nextPageOrderViewButton.setDisable(true);

        exitOrderViewButton.setOnAction(event-> {
            orderReviewPane.setVisible(false);
            orderTakerPane.setVisible(true);
        });
        prevPageOrderViewButton.setOnAction(event -> {
            if (orderReviewPageNum < (orderHistory.size()%5 == 0? orderHistory.size()/5 : (orderHistory.size()/5)+1)) nextPageOrderViewButton.setDisable(false);
            orderReviewPageNum--;
            showReviewPage(orderReviewPageNum);
            if (orderReviewPageNum == 0) prevPageOrderViewButton.setDisable(true);
        });
        nextPageOrderViewButton.setOnAction(event -> {
            if (orderReviewPageNum >=0) prevPageOrderViewButton.setDisable(false);
            orderReviewPageNum++;
            showReviewPage(orderReviewPageNum);
            if (orderReviewPageNum == (orderHistory.size()%5 == 0 ? orderHistory.size()/5 : ((orderHistory.size()/5)+1))-1) nextPageOrderViewButton.setDisable(true);
        });
    }

    private void showReviewPage(int pageNum){
        int startIndex = orderHistory.size()-1-(pageNum*5);
        orderListView1.setRoot(orderHistory.size() > (pageNum*5) ? orderHistory.get(startIndex) : null);
        orderListView2.setRoot(orderHistory.size() > (pageNum*5)+1 ? orderHistory.get(startIndex-1) : null);
        orderListView3.setRoot(orderHistory.size() > (pageNum*5)+2 ? orderHistory.get(startIndex-2) : null);
        orderListView4.setRoot(orderHistory.size() > (pageNum*5)+3 ? orderHistory.get(startIndex-3) : null);
        orderListView5.setRoot(orderHistory.size() > (pageNum*5)+4 ? orderHistory.get(startIndex-4) : null);
    }
}
