package DittoPOS.Controllers;

import DittoPOS.Main;
import DittoPOS.administration.UserManagement;
import DittoPOS.products.Category;
import DittoPOS.products.CategoryManagement;
import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;
import DittoPOS.sales.ReceiptManagement;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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

    @FXML
    public void initialize() {

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

    private void refreshReceipt() {
        listReceipt.getChildren().clear();
        AtomicInteger i = new AtomicInteger(1);
        ReceiptManagement.getInstance().getCurrentReceipt().getResit().forEach(
                (saleProduct, integer) ->
                {
                    Button button = new Button(i.getAndIncrement() + " " + saleProduct.getProduct().getName() + " " + (saleProduct.getProduct().getPrice() * integer));
                    button.setMinHeight(80);
                    button.setMinWidth(304);
                    listReceipt.getChildren().add(button);

                });
    }


    private void showProduct() {
        products.getChildren().clear();
        ArrayList<SaleProduct> catProducts = new ArrayList<>();
        if (categoryShow != null)
            catProducts = categoryShow.getProductsInCategory(); // show all product if no category is selected
        if (categoryShow == null)
            catProducts = ProductManagement.getInstance().getProducts();
        System.out.println("Begin");
        catProducts.forEach(saleProduct -> {
            System.out.println(saleProduct.getProduct().getName());
            Button button = new Button(saleProduct.getProduct().getName() + "\n\n" + saleProduct.getProduct().getPrice());
            button.setMinWidth(100);
            button.setMinHeight(100);
            button.setOnMouseClicked(event -> {
                ReceiptManagement.getInstance().getCurrentReceipt().addItem(saleProduct, 1);
                refreshReceipt();
            });
            products.getChildren().add(button);
        });
    }


    private void catClick(ActionEvent event, Category category) {
        if (categorySelected != null)
            categorySelected.setStyle("");
        categorySelected = (Button) event.getSource();
        categorySelected.setStyle("-fx-background-color: grey");
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
        System.out.println("removing menu");
        stackRoot.getChildren().remove(menubar);
        System.out.println("removed menu");
    }

}