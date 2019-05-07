package DittoPOS;

import DittoPOS.helpers.*;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        Button button = new Button();
        button.setOnAction((e) -> {
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        String json = "{\"productJson\":\"[{\\\"name\\\":\\\"AAA\\\",\\\"price\\\":40.0,\\\"quantity\\\":1,\\\"ingredients\\\":[{\\\"name\\\":\\\"Abb\\\",\\\"price\\\":5.0,\\\"needed\\\":2}]}]\",\"stockJson\":\"{\\\"stocks\\\":[{\\\"left\\\":94,\\\"name\\\":\\\"Abb\\\",\\\"price\\\":5.0,\\\"needed\\\":0}],\\\"daycounter\\\":{\\\"Abb\\\":1},\\\"stockuse\\\":{\\\"Abb\\\":[6.0]}}\",\"orderJson\":\"[{\\\"identifier\\\":1,\\\"orderList\\\":[{\\\"name\\\":\\\"AAA\\\",\\\"price\\\":40.0,\\\"quantity\\\":3,\\\"ingredients\\\":[{\\\"name\\\":\\\"Abb\\\",\\\"price\\\":5.0,\\\"needed\\\":2}]}]}]\"}";
        Json.fromString(json).restoreAll();
        //Menu menu = new Menu();
        //menu.mainMenu();
        // launch(args);
    }
}
