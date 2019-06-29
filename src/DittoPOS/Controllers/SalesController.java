package DittoPOS.Controllers;

import DittoPOS.administration.User;
import DittoPOS.administration.UserManagement;
import DittoPOS.products.CategoryManagement;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Window;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class SalesController {
    
        @FXML
        private VBox categorybox;
        @FXML
        private Button sidebar;

        @FXML
        private AnchorPane root;
        @FXML
        private Text timenow;
        @FXML
        private StackPane stackroot;
        @FXML
        public void initialize() {

            timenow.setTextAlignment(TextAlignment.RIGHT);
            DateFormat timeFormat = new SimpleDateFormat( "dd/MM/YYYY HH:mm:ss" );
            final Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis( 500 ),
                            event -> {
                                    timenow.setText( timeFormat.format( System.currentTimeMillis() ) );
                            }
                    )
            );
            timeline.setCycleCount( Animation.INDEFINITE );
            timeline.play();

            HBox menubar = new HBox();
            VBox menu = new VBox();
            menu.setStyle("-fx-background-color: blue;");
            menu.setFillWidth(true);

            menubar.setOnMouseClicked((MouseEvent evt) ->
                hideMenu(menubar)
            );

            Button backBtn = new Button("Left Arrow");
            backBtn.setPrefWidth(100);

            backBtn.setOnAction(event ->
                        hideMenu(menubar)
            );


            Button infoBtn = new Button("Info");
            infoBtn.setPrefWidth(100);
            infoBtn.getStyleClass().add("custom-menu-button");
            Button newBtn = new Button("New");
            newBtn.setPrefWidth(100);
            newBtn.getStyleClass().add("custom-menu-button");
            Button openBtn = new Button("Open");
            openBtn.setPrefWidth(100);
            openBtn.getStyleClass().add("custom-menu-button");
            menu.getChildren().addAll(backBtn,infoBtn, newBtn, openBtn);
            VBox.setVgrow(infoBtn, Priority.ALWAYS);
            menubar.getChildren().add(menu);
            sidebar.setOnAction(event ->
                showMenu(menubar)
            );

            CategoryManagement.getInstance().addCategory("Hello");
            CategoryManagement.getInstance().addCategory("this");
            CategoryManagement.getInstance().addCategory("is");
            CategoryManagement.getInstance().addCategory("category do you work lalalalallalaallaaa");
            //
            CategoryManagement.getInstance().allCategory().forEach((o, o2) -> {
                System.out.println(o.toString());
                Button button = new Button((String)o);
                button.setMinHeight(67.00);
                button.setMinWidth(163.00);
               // button.setOnAction(event -> onActionClick(event,i));  // set action of button to the onactionclick button.
                categorybox.getChildren().add(button);

            });

            @FXML
            protected void onActionClick(ActionEvent event, User user){
                usernameField.setText(user.getCategory());
                Button x = (Button)event.getSource();
                System.out.println(x.getWidth());
            }


        }

    private void showMenu(HBox menubar) {
        stackroot.getChildren().add(menubar);
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
        stackroot.getChildren().remove(menubar);
        System.out.println("removed menu");
    }
}