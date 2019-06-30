package DittoPOS.Controllers;

import DittoPOS.Main;
import DittoPOS.administration.UserManagement;
import DittoPOS.products.CategoryManagement;
import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProductController {
    @FXML
    private Text username;

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
    private Button salesBtn;
    @FXML
    private Button prodBtn;

    @FXML
    private Button catBtn;

    @FXML
    private Button setBtn;

    @FXML
    private VBox productList;

    @FXML
    private Button usrBtn;

    @FXML
    private Button outBtn;

    @FXML
    private AnchorPane root;


    @FXML
    private Button add;

    @FXML
    private Button del;

    @FXML
    private Button save;

    @FXML
    private TextField prodName;

    @FXML
    private TextField barcode;

    @FXML
    private TextField price;

    @FXML
    private Button cashFlowBtn;

    private void refreshProduct() {
        productList.getChildren().clear();
        ProductManagement.getInstance().getProducts().forEach(saleProduct -> {
            Button button = new Button(saleProduct.getProduct().getName());
            button.setMinHeight(60);
            button.setMinWidth(400);


            // event handlers for the button
            button.setOnMouseClicked(event -> {
                this.barcode.setText(saleProduct.getProduct().getBarcode());
                this.prodName.setText(saleProduct.getProduct().getName());
                this.price.setText(Double.toString(saleProduct.getProduct().getPrice()));
            });
            productList.getChildren().add(button);
        });
    }

    public void initialize() {

        username.setText(UserManagement.getInstance().loggedin.getName());
        hideMenu(sidebarMenu);
        timeNow.setTextAlignment(TextAlignment.RIGHT);
        DateFormat timeFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss"); // format the date in dd/mm/yy hh:mm:ss
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> timeNow.setText(timeFormat.format(System.currentTimeMillis()))
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        refreshProduct();

        sidebarMenu.setOnMouseClicked(event -> hideMenu(sidebarMenu));

        backBtn.setOnAction(event ->
                hideMenu(sidebarMenu)
        );

        sideBar.setOnAction(event ->
                showMenu(sidebarMenu)
        );
        salesBtn.setOnMouseClicked(event -> Main.changeScene("SalesScreen.fxml"));
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
        add.setOnMouseClicked(event -> {
            try {
                if (ProductManagement.getInstance().getProduct(prodName.getText()) != null) {
                    throw new NullPointerException("");
                }

                ProductManagement.getInstance().addProduct(prodName.getText(), Double.valueOf(price.getText()), barcode.getText());
            } catch (Exception e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, ((Button) event.getSource()).getScene().getWindow(), "ERROR", "Value not allowed OR product with same name already exists");
            }
            refreshProduct();
        });
        del.setOnMouseClicked(event -> {
            SaleProduct product = ProductManagement.getInstance().getProduct(prodName.getText());
            if (product == null)
                AlertHelper.showAlert(Alert.AlertType.ERROR, ((Button) event.getSource()).getScene().getWindow(), "ERROR", "Invalid Product to delete");
            else {
                ProductManagement.getInstance().deleteProduct(product);
                CategoryManagement.getInstance().allCategory().forEach((s, category) -> category.removeProductFromCategory(product));
            }
            refreshProduct();
        });
        save.setOnMouseClicked(event -> {
            SaleProduct product = ProductManagement.getInstance().getProduct(prodName.getText());
            if (product == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, ((Button) event.getSource()).getScene().getWindow(), "ERROR", "Product Does Not exist, proceeding to add new product");
                try {
                    ProductManagement.getInstance().addProduct(prodName.getText(), Double.valueOf(price.getText()), barcode.getText());
                } catch (Exception e) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, ((Button) event.getSource()).getScene().getWindow(), "ERROR", "Value not allowed");
                }
            } else {
                try {
                    ProductManagement.getInstance().saveProduct(product, prodName.getText(), Double.valueOf(price.getText()), barcode.getText());
                } catch (Exception e) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, ((Button) event.getSource()).getScene().getWindow(), "ERROR", "Value not allowed");
                }
            }
            refreshProduct();
        });
    }

    /**
     * Show and Hide the side bar with animations, when showing sidebar, set original page to go to 70% fade to have the effect of being in the background.
     */

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
