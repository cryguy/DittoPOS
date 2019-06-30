package DittoPOS.Controllers;


import DittoPOS.Main;
import DittoPOS.administration.UserManagement;
import DittoPOS.reports.CashFlow;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicReference;

public class CashFlowController {

    @FXML
    private Button add;

    @FXML
    private TextArea summary;

    @FXML
    private TextField reason;

    @FXML
    private TextField amount;

    @FXML
    private TextField total;

    @FXML
    private Text timeNow;

    @FXML
    private ComboBox<String> comboInOut;

    @FXML
    private Text username;
    @FXML
    private Button sideBar;

    @FXML
    private StackPane stackRoot;

    @FXML
    private Button backBtn;

    @FXML
    private Button setBtn;

    @FXML
    private Button usrBtn;
    @FXML
    private Button prodBtn;
    @FXML
    private HBox sidebarMenu;
    @FXML
    private Button salesBtn;
    @FXML
    private Button catBtn;
    @FXML
    private AnchorPane root;
    @FXML
    private Button outBtn;

    @FXML
    private Button cashFlowBtn;

    @FXML
    void initialize() {
        updateAll();
        comboInOut.getItems().addAll("IN", "OUT");
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

        add.setOnMouseClicked(event -> {
            try {
                double moneytoadd = Math.abs(Double.valueOf(amount.getText()));

                if (reason.getText().equals("") || moneytoadd == 0.0 || comboInOut.getSelectionModel().isEmpty()) {
                    throw new NullPointerException();
                } else {
                    if (comboInOut.getSelectionModel().getSelectedItem().equals("OUT"))
                        moneytoadd *= -1;
                    CashFlow.getInstance().addToList(this.reason.getText(), moneytoadd);

                    updateAll();
                }

            } catch (Exception e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Main.prim.getOwner(), "Error", "Invalid Value entered");
            }
        });
        hideMenu(sidebarMenu);
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

    void updateAll() {
        this.summary.setText("");
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        CashFlow.getInstance().getMoneylog().forEach(aDouble -> total.updateAndGet(v -> (double) (v + aDouble)));

        this.total.setText(total + "$");

        int indexto = CashFlow.getInstance().getLog().size();

        for (int i = 0; i < indexto; i++) {
            this.summary.setText(this.summary.getText() + CashFlow.getInstance().getLog().get(i) + "    " + CashFlow.getInstance().getMoneylog().get(i) + "\n");
        }
    }
}
