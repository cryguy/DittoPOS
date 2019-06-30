package DittoPOS;

import DittoPOS.Controllers.AlertHelper;
import DittoPOS.administration.UserManagement;
import DittoPOS.helpers.Json;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class Main extends Application {
    public static Stage prim = null;

    public static void changeScene(String fxml) {
        try {
            prim.getScene().setRoot(FXMLLoader.load(
                    Main.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            String base64 = Base64.getEncoder().encodeToString(Json.printString().getBytes());
            try {
                FileWriter fw = new FileWriter("important.data");
                fw.write(base64);
                fw.close();
                System.out.println("[+] SAVED TO FILE, GOODBYE");
            } catch (Exception e) {
                System.out.println("[-] UNABLE TO WRITE TO FILE!!!");
            }
        }));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        prim = primaryStage;

        try {
            FileReader reader = new FileReader("important.data");
            BufferedReader bufferedReader = new BufferedReader(reader);
            byte[] valueDecoded = Base64.getDecoder().decode(bufferedReader.readLine());
            String json = new String(valueDecoded);
            reader.close();
            System.out.println("[+] LOADING DATA!!!");
            Json.fromString(json).restoreAll();
            System.out.println("[+] LOADED DATA!!!");
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, prim.getOwner(), "Welcome to DittoPOS", "The default login is enabled, Login with Account Admin with password - \"Admin\".\nSomething bad happened if you do not expect to see this! ");
            System.out.println("[+] DATA NOT FOUND, STARTING FROM SCRATCH!!!");
            UserManagement.getInstance().addUser("Admin", "Admin", "");
        }



        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("DittoPOS");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.setResizable(false);
        System.out.println("[+] Primary Stage initialized, Showing Login");


        primaryStage.setWidth(1280);
        primaryStage.show();
    }
}
