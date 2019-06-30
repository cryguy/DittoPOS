package DittoPOS.Controllers;

import DittoPOS.Main;
import DittoPOS.administration.UserManagement;
import DittoPOS.products.Category;
import DittoPOS.products.CategoryManagement;
import DittoPOS.products.ProductManagement;
import DittoPOS.products.SaleProduct;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CategoryController {
    @FXML
    private TextField catname;

    @FXML
    private Button add;

    @FXML
    private Button del;

    @FXML
    private Button save;


    private Button lastclicked;
    private Category categoryselected;
    private String nameOfCatSel;

    @FXML
    private VBox catlist;

    @FXML
    private FlowPane incat;

    @FXML
    private FlowPane notincat;


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
    private Button usrBtn;

    @FXML
    private Button outBtn;

    @FXML
    private AnchorPane root;


    @FXML
    private Button cashFlowBtn;


    private void onActionClick(ActionEvent event, Category category, String name) {
        if (lastclicked != null)
            lastclicked.setStyle("");
        lastclicked = (Button) event.getSource();
        lastclicked.setStyle("-fx-background-color: grey");

        categoryselected = category;
        nameOfCatSel = name;
        this.catname.setText(name);
        loadProducts();
    }


    private void loadProducts() {
        ArrayList<SaleProduct> toshow = new ArrayList<>(ProductManagement.getInstance().getProducts());
        toshow.removeAll(categoryselected.getProductsInCategory());
        notincat.getChildren().clear();
        incat.getChildren().clear();
        for (SaleProduct i : categoryselected.getProductsInCategory()) {
            Button button = new Button(i.getProduct().getName());
            button.setMinHeight(100);
            button.setMinWidth(100);
            button.setOnMouseClicked(event -> {
                categoryselected.removeProductFromCategory(i);
                loadProducts();
            });
            incat.getChildren().add(button);
        }

        for (SaleProduct i : toshow) {
            Button button = new Button(i.getProduct().getName());
            button.setMinHeight(100);
            button.setMinWidth(100);
            button.setOnMouseClicked(event -> {
                categoryselected.addProductToCategory(i);
                loadProducts();
            });
            notincat.getChildren().add(button);
        }
    }


    @FXML
    public void initialize() {
        del.setOnMouseClicked(event -> {
            CategoryManagement.getInstance().deleteCategory(nameOfCatSel);
            populateCatList();
        });
        save.setOnMouseClicked(event -> {
            Category temp = CategoryManagement.getInstance().getCategory(nameOfCatSel);
            CategoryManagement.getInstance().deleteCategory(nameOfCatSel);
            CategoryManagement.getInstance().addCategory(catname.getText());
            CategoryManagement.getInstance().getCategory(catname.getText()).setProducts(temp.getProductsInCategory());
            populateCatList();
        });
        username.setText(UserManagement.getInstance().loggedin.getName());
        timeNow.setTextAlignment(TextAlignment.RIGHT);
        DateFormat timeFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> {
                            timeNow.setText(timeFormat.format(System.currentTimeMillis()));
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

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
        hideMenu(sidebarMenu);

        populateCatList();
        add.setOnMouseClicked(event -> {
                    if (CategoryManagement.getInstance().getCategory(catname.getText()) != null)
                        AlertHelper.showAlert(Alert.AlertType.ERROR, Main.prim.getOwner(), "DittoPOS - Category ERROR", "ERROR - A Category with the Chosen name is already Present");

                    else {
                        CategoryManagement.getInstance().addCategory(catname.getText());
                        populateCatList();
                        catname.setText("");
                    }
                }
        );
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

    private void populateCatList() {
        catlist.getChildren().clear();
        CategoryManagement.getInstance().allCategory().forEach((string, category) -> {
            Button button = new Button(string);
            button.setMinWidth(235);
            button.setMinHeight(88);
            button.setOnAction(event -> onActionClick(event, category, string));  // set action of button to the onactionclick button.
            catlist.getChildren().add(button);
        });
    }
}
