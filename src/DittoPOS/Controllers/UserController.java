package DittoPOS.Controllers;

import DittoPOS.Main;
import DittoPOS.administration.UserManagement;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserController {


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
    private VBox userList;

    @FXML
    private TextField usernameTxt;

    @FXML
    private ImageView usrImg;

    @FXML
    private Button chooseImage;

    private File newFile;

    @FXML
    private Button newUser;

    @FXML
    private Button saveUser;

    @FXML
    private Button delUser;

    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button cashFlowBtn;

    @FXML
    public void initialize() {
        hideMenu(sidebarMenu);
        username.setText(UserManagement.getInstance().loggedin.getName());
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

        newUser.setOnMouseClicked(event -> {
            //System.out.println(newFile.getAbsolutePath());
            if (newFile != null)
                UserManagement.getInstance().addUser(this.usernameTxt.getText(), this.passwordTxt.getText(), newFile.getAbsolutePath());
            else
                UserManagement.getInstance().addUser(this.usernameTxt.getText(), this.passwordTxt.getText(), "");
            populateUsers();
        });


        saveUser.setOnMouseClicked(event -> {

            if (UserManagement.getInstance().users.get(usernameTxt.getText()) == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Main.prim, "DittoPOS - Administration", "User with name doesnt exists, creating new user instead");
                if (newFile != null)
                    UserManagement.getInstance().addUser(this.usernameTxt.getText(), this.passwordTxt.getText(), newFile.getAbsolutePath());
                else
                    UserManagement.getInstance().addUser(this.usernameTxt.getText(), this.passwordTxt.getText(), "");


            } else {
                UserManagement.getInstance().users.get(usernameTxt.getText()).setPassword(this.passwordTxt.getText(), false);
                if (this.newFile != null)
                    UserManagement.getInstance().users.get(usernameTxt.getText()).setImage(this.newFile.getAbsolutePath());
            }
            populateUsers();

        });
        delUser.setOnMouseClicked(event -> {
            if (UserManagement.getInstance().users.size() == 1)
                AlertHelper.showAlert(Alert.AlertType.ERROR, Main.prim, "DittoPOS - Administration", "Current Amount of users is 1, refusing to delete User");
            else
                UserManagement.getInstance().users.remove(usernameTxt.getText());
            populateUsers();
        });

        // Choose Image to save
        chooseImage.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG", "*.png")
                    , new FileChooser.ExtensionFilter("JPG", "*.jpg")
            );

            newFile = fileChooser.showOpenDialog(Main.prim);
            try {
                usrImg.setImage(new Image(newFile.toURI().toString()));
            } catch (Exception e) {
                System.out.println("Image not found!");
            }
        });

        // Handlers for sidebar
        sidebarMenu.setOnMouseClicked(event -> hideMenu(sidebarMenu));

        backBtn.setOnAction(event ->
                hideMenu(sidebarMenu)
        );

        sideBar.setOnAction(event ->
                showMenu(sidebarMenu)
        );


        // sidebar navigation

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
        populateUsers();
        hideMenu(sidebarMenu);


    }


    private void populateUsers() {
        userList.getChildren().clear();
        UserManagement.getInstance().users.forEach((s, user) -> {
            Button button = new Button(user.toString());
            button.setMinWidth(400);
            button.setMinHeight(70);
            button.setOnMouseClicked(event -> {
                this.usernameTxt.setText(user.getName());
                this.newFile = new File(user.getImage());
                try {
                    usrImg.setImage(new Image((new File(user.getImage())).toURI().toString()));
                } catch (Exception e) {
                    System.out.println("Image not found!");
                }
            });
            userList.getChildren().add(button);
        });

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
