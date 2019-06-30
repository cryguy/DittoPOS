package DittoPOS.Controllers;

import DittoPOS.Main;
import DittoPOS.administration.UserManagement;
import DittoPOS.products.Category;
import DittoPOS.products.CategoryManagement;
import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;
import DittoPOS.reports.CashFlow;
import DittoPOS.sales.ReceiptManagement;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SalesController {
    private static Button categorySelected = null;
    private static Category categoryShow = null;
    @FXML
    private VBox categoryBox;


    @FXML
    private VBox listReceipt;


    @FXML
    private AnchorPane root;
    @FXML
    private Text timeNow;


    @FXML
    private Button sideBar;

    @FXML
    private StackPane stackRoot;

    @FXML
    private HBox sidebarMenu;

    @FXML
    private Button backBtn;

    @FXML
    private Button prodBtn;

    @FXML
    private Button catBtn;

    @FXML
    private Button setBtn;

    @FXML
    private Button usrBtn;

    @FXML
    private Button outBtn;

    @FXML
    private Text username;

    @FXML
    private FlowPane products;

    @FXML
    private Button cashFlowBtn;


    private AtomicBoolean notDone = new AtomicBoolean(true);
    private AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
    @FXML
    private Button enter;
    @FXML
    private Button add;
    @FXML
    private Button minus;
    @FXML
    private TextField priceTot;
    private SaleProduct currentPressed;

    @FXML
    public void initialize() {

        refreshReceipt();
        username.setText(UserManagement.getInstance().loggedin.getName());
        hideMenu(sidebarMenu);
        timeNow.setTextAlignment(TextAlignment.RIGHT);
        DateFormat timeFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> timeNow.setText(timeFormat.format(System.currentTimeMillis()))
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        showProduct();

/*
        Main.prim.getScene().addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.ENTER) {
                notDone.set(true);
                while (notDone.get()) {
                    TextInputDialog dialog = new TextInputDialog(totalPrice.get() + "");

                    dialog.setTitle("Enter Amount Paid");
                    dialog.setHeaderText("Paid");
                    dialog.setContentText("Amount:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        if (Double.valueOf(result.get()) >= totalPrice.get()) {
                            AlertHelper.showAlert(Alert.AlertType.INFORMATION, Main.prim.getOwner(), "Ditto POS", "CHANGE = " + (Double.valueOf(result.get()) - totalPrice.get()));
                            notDone.set(false);

                        }
                        else {
                            AlertHelper.showAlert(Alert.AlertType.ERROR, Main.prim.getOwner(), "Ditto POS", "Amount Paid is lower than total price");

                        }
                    }
                    else {
                        notDone.set(false);
                    }
                }
                resetReceiptAndStart();
                ke.consume(); // <-- stops passing the event to next node
            }
            if (ke.getCode() == KeyCode.ADD) {
                // fire event here
                ke.consume(); // <-- stops passing the event to next node
                ReceiptManagement.getInstance().getCurrentReceipt().addItem(currentPressed, 1);
                refreshReceipt();
            }
            if (ke.getCode() == KeyCode.SUBTRACT) {
                // fire event here
                ke.consume(); // <-- stops passing the event to next node
                ReceiptManagement.getInstance().getCurrentReceipt().addItem(currentPressed, -1);
                refreshReceipt();
            }
        });
*/

        enter.setOnMouseClicked(event -> {

            notDone.set(true);
            while (notDone.get()) {
                TextInputDialog dialog = new TextInputDialog(totalPrice.get() + "");

                dialog.setTitle("Enter Amount Paid");
                dialog.setHeaderText("Paid");
                dialog.setContentText("Amount:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    if (Double.valueOf(result.get()) >= totalPrice.get()) {
                        AlertHelper.showAlert(Alert.AlertType.INFORMATION, Main.prim.getOwner(), "Ditto POS", "CHANGE = " + (Double.valueOf(result.get()) - totalPrice.get()));
                        notDone.set(false);

                    } else {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, Main.prim.getOwner(), "Ditto POS", "Amount Paid is lower than total price");

                    }
                } else {
                    notDone.set(false);
                }
            }
            resetReceiptAndStart();
            // tabulate and show alert
        });
        add.setOnMouseClicked(event -> {
            if (currentPressed != null)
                ReceiptManagement.getInstance().getCurrentReceipt().addItem(currentPressed, 1);
            refreshReceipt();
        });
        minus.setOnMouseClicked(event -> {

            if (currentPressed != null)
                ReceiptManagement.getInstance().getCurrentReceipt().addItem(currentPressed, -1);
            refreshReceipt();
        });

        sidebarMenu.setOnMouseClicked(event -> hideMenu(sidebarMenu));

        backBtn.setOnAction(event ->
                hideMenu(sidebarMenu)
        );

        sideBar.setOnAction(event ->
                showMenu(sidebarMenu)
        );

        Button x = new Button("ALL");
        x.setMinHeight(67.00);
        x.setMinWidth(163.00);
        x.setOnAction(event -> catClick(event, null));  // set action of button to the onactionclick button.
        categoryBox.getChildren().add(x);

        CategoryManagement.getInstance().allCategory().forEach((name, category) -> {
            Button button = new Button(name);
            button.setMinHeight(67.00);
            button.setMinWidth(163.00);
            button.setOnAction(event -> catClick(event, category));  // set action of button to the onactionclick button.
            categoryBox.getChildren().add(button);
        });
        refreshReceipt();


//        Stage stage = (Stage) stackRoot.getScene().getWindow();

        prodBtn.setOnMouseClicked(event ->
                Main.changeScene("ProductManagement.fxml")
        );
        catBtn.setOnMouseClicked(event ->
                Main.changeScene("categoryManagement.fxml")
        );
        usrBtn.setOnMouseClicked(event ->
                Main.changeScene("UserManagement.fxml")
        );
        outBtn.setOnMouseClicked(event ->
                Main.changeScene("LoginPage.fxml")
        );

        cashFlowBtn.setOnMouseClicked(event -> Main.changeScene("CashFlow.fxml"));


    }


    private void resetReceiptAndStart() {
        ReceiptManagement.getInstance().addReceiptAndClear(ReceiptManagement.getInstance().getCurrentReceipt());
        CashFlow.getInstance().addToList("SALES", totalPrice.get());
        totalPrice.set(0.0);
        refreshReceipt();
    }

    private void refreshReceipt() {
        listReceipt.getChildren().clear();
        AtomicInteger i = new AtomicInteger(1);
        totalPrice.set((double) 0);
        try {
            ReceiptManagement.getInstance().getCurrentReceipt().getResit().forEach(
                    (saleProduct, integer) ->
                    {

                        Button button = new Button(i.getAndIncrement() + "          " + integer + " x " + saleProduct.getProduct().getName() + "            " + (saleProduct.getProduct().getPrice() * integer));
                        button.setMinHeight(80);
                        button.setMinWidth(304);

                        button.setOnMouseClicked(event -> {
                            currentPressed = saleProduct;
                        });


                        listReceipt.getChildren().add(button);
                        totalPrice.updateAndGet(v -> v + (saleProduct.getProduct().getPrice() * integer));
                    });
        } catch (Exception e) {
            resetReceiptAndStart();
        }
        priceTot.setText(totalPrice.get() + "$");
    }


    private void showProduct() {
        products.getChildren().clear();
        ArrayList<SaleProduct> catProducts = new ArrayList<>();
        if (categoryShow != null)
            catProducts = categoryShow.getProductsInCategory(); // show all product if no category is selected
        if (categoryShow == null)
            catProducts = ProductManagement.getInstance().getProducts();
        catProducts.forEach(saleProduct -> {
            Button button = new Button(saleProduct.getProduct().getName() + "\n\n" + saleProduct.getProduct().getPrice());
            button.setMinWidth(100);
            button.setMinHeight(100);
            button.setOnMouseClicked(event -> {
                ReceiptManagement.getInstance().getCurrentReceipt().addItem(saleProduct, 1);
                refreshReceipt();
                currentPressed = saleProduct;
            });
            products.getChildren().add(button);
        });
    }


    private void catClick(ActionEvent event, Category category) {
        if (categorySelected != null)
            categorySelected.setStyle("");
        categorySelected = (Button) event.getSource();
        categorySelected.setStyle("-fx-background-color: grey"); // set selected button to some other color, makes it more "visible" to user
        categoryShow = category;
        showProduct();
        //System.out.println(x.getWidth());
    }

    private void showMenu(HBox menubar) {
        stackRoot.getChildren().add(menubar);
        FadeTransition hideEditorRootTransition = new FadeTransition(Duration.millis(500), root);
        hideEditorRootTransition.setFromValue(1.0);
        hideEditorRootTransition.setToValue(0.3);

        FadeTransition showFileRootTransition = new FadeTransition(Duration.millis(500), menubar);
        showFileRootTransition.setFromValue(0.0);
        showFileRootTransition.setToValue(1.0);
        hideEditorRootTransition.play();
        showFileRootTransition.play();
    }

    private void hideMenu(HBox menubar) {
        FadeTransition hideFileRootTransition = new FadeTransition(Duration.millis(500), menubar);
        hideFileRootTransition.setFromValue(1.0);
        hideFileRootTransition.setToValue(0.0);

        FadeTransition showEditorRootTransition = new FadeTransition(Duration.millis(500), root);
        showEditorRootTransition.setFromValue(0.3);
        showEditorRootTransition.setToValue(1.0);

        showEditorRootTransition.play();
        hideFileRootTransition.play();
        stackRoot.getChildren().remove(menubar);
    }

}