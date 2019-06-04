package DittoPOS.Controllers;

import DittoPOS.administration.User;
import DittoPOS.administration.UserManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Window;


public class LoginController {
@FXML
    private VBox userList;
@FXML
    private SplitPane splitPane;
@FXML
    private TextField usernameField;
@FXML
    private PasswordField passwordField;

@FXML
    protected void callLogin(){
        User user;
        Window owner = userList.getScene().getWindow();
        if (!(UserManagement.getInstance().users.containsKey(usernameField.getText())))
        {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Button Pressed!",
                    ("Error Occurred!")); // user not found
        }
        else {
            user = UserManagement.getInstance().users.get(usernameField.getText());
            if (user.checkPassword(passwordField.getText())) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Button Pressed!",
                        ("Hello " + user.getName())); // correct password given, pass the user to the current session.
            }
            else{
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login",
                        ("Login Failed!")); // wrong password
            }
        }

}

@FXML
    protected void onActionClick(ActionEvent event, User user){
    usernameField.setText(user.getName());
}
    @FXML
    public void initialize() {

        SplitPane.Divider divider = splitPane.getDividers().get(0);
        double splitpanepos = divider.getPosition();
        divider.positionProperty().addListener((observable, oldvalue, newvalue) -> divider.setPosition(splitpanepos));

        System.out.println("Initializing");
        for (User i: UserManagement.getInstance().users.values()) {
            Button button = new Button(i.toString());
            button.setMinHeight(67.00);
            button.setMinWidth(334.00);
            button.setOnAction(event -> onActionClick(event,i));  // set action of button to the onactionclick button.
            userList.getChildren().add(button);
        }

        System.out.println("Finished init");
    }
}
