package DittoPOS;

import DittoPOS.helpers.Json;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        //String json = "{\n" +
        // "  \"productJson\": \"[{\\\"product\\\":{\\\"name\\\":\\\"Water\\\",\\\"barcode\\\":\\\"WATER-1\\\",\\\"price\\\":15.0,\\\"canExpire\\\":false},\\\"stock\\\":0,\\\"minStock\\\":0,\\\"maxStock\\\":0},{\\\"product\\\":{\\\"name\\\":\\\"Water Juice\\\",\\\"barcode\\\":\\\"WATER-2\\\",\\\"price\\\":16.0,\\\"canExpire\\\":false},\\\"stock\\\":0,\\\"minStock\\\":0,\\\"maxStock\\\":0}]\",\n" +
        // "  \"receiptJson\": \"{\\\"Admin\\\":{\\\"name\\\":\\\"Admin\\\",\\\"image\\\":\\\"F:\\\\\\\\shy desktop\\\\\\\\Puppet_Auriel_Spray.png\\\",\\\"password_hash\\\":\\\"n7i6M/tjBBA8PfAubweAJbfYpkQ\\\\u003d,hcSHd2ukz73TRj87T54MEHYYlAOPWUSb4QWQOnmmPqM\\\\u003d\\\"}}\",\n" +
        //  "  \"userJson\": \"{\\\"Admin\\\":{\\\"name\\\":\\\"Admin\\\",\\\"image\\\":\\\"F:\\\\\\\\shy desktop\\\\\\\\Puppet_Auriel_Spray.png\\\",\\\"password_hash\\\":\\\"n7i6M/tjBBA8PfAubweAJbfYpkQ\\\\u003d,hcSHd2ukz73TRj87T54MEHYYlAOPWUSb4QWQOnmmPqM\\\\u003d\\\"}}\",\n" +
        //  "  \"categoryJson\": \"{\\\"ALPHA\\\":{\\\"products\\\":[{\\\"product\\\":{\\\"name\\\":\\\"Water Juice\\\",\\\"barcode\\\":\\\"WATER-2\\\",\\\"price\\\":16.0,\\\"canExpire\\\":false},\\\"stock\\\":0,\\\"minStock\\\":0,\\\"maxStock\\\":0},{\\\"product\\\":{\\\"name\\\":\\\"Water\\\",\\\"barcode\\\":\\\"WATER-1\\\",\\\"price\\\":15.0,\\\"canExpire\\\":false},\\\"stock\\\":0,\\\"minStock\\\":0,\\\"maxStock\\\":0}]}}\",\n" +
        //   "  \"cashFlowJson\": \"[13.37]\",\n" +
        //   "  \"cashFlowReJson\": \"[\\\"NEW SALE\\\"]\"\n" +
        //   "}";
        //"{\"productJson\":\"[{\\\"name\\\":\\\"AAA\\\",\\\"price\\\":40.0,\\\"quantity\\\":1,\\\"ingredients\\\":[{\\\"name\\\":\\\"Abb\\\",\\\"price\\\":5.0,\\\"needed\\\":2}]}]\",\"stockJson\":\"{\\\"stocks\\\":[{\\\"left\\\":94,\\\"name\\\":\\\"Abb\\\",\\\"price\\\":5.0,\\\"needed\\\":0}],\\\"daycounter\\\":{\\\"Abb\\\":1},\\\"stockuse\\\":{\\\"Abb\\\":[6.0]}}\",\"orderJson\":\"[{\\\"identifier\\\":1,\\\"orderList\\\":[{\\\"name\\\":\\\"AAA\\\",\\\"price\\\":40.0,\\\"quantity\\\":3,\\\"ingredients\\\":[{\\\"name\\\":\\\"Abb\\\",\\\"price\\\":5.0,\\\"needed\\\":2}]}]}]\"}";
        String json = "{\"productJson\":\"[{\\\"product\\\":{\\\"name\\\":\\\"Water Boii\\\",\\\"barcode\\\":\\\"AAA1\\\",\\\"price\\\":12.0,\\\"canExpire\\\":false},\\\"stock\\\":0,\\\"minStock\\\":0,\\\"maxStock\\\":0}]\",\"receiptJson\":\"{\\\"Admin\\\":{\\\"name\\\":\\\"Admin\\\",\\\"image\\\":\\\"F:\\\\\\\\shy desktop\\\\\\\\Puppet_Auriel_Spray.png\\\",\\\"password_hash\\\":\\\"9k3Zh2jM6zWWj3wTpIkz0cMjgcc\\\\u003d,JsV10ASTqKHiNm2+se4h18OE/h91RXIgjZTZZ+fpVF0\\\\u003d\\\"}}\",\"userJson\":\"{\\\"Admin\\\":{\\\"name\\\":\\\"Admin\\\",\\\"image\\\":\\\"F:\\\\\\\\shy desktop\\\\\\\\Puppet_Auriel_Spray.png\\\",\\\"password_hash\\\":\\\"9k3Zh2jM6zWWj3wTpIkz0cMjgcc\\\\u003d,JsV10ASTqKHiNm2+se4h18OE/h91RXIgjZTZZ+fpVF0\\\\u003d\\\"}}\",\"categoryJson\":\"{\\\"AX\\\":{\\\"products\\\":[{\\\"product\\\":{\\\"name\\\":\\\"Water Boii\\\",\\\"barcode\\\":\\\"AAA1\\\",\\\"price\\\":12.0,\\\"canExpire\\\":false},\\\"stock\\\":0,\\\"minStock\\\":0,\\\"maxStock\\\":0}]}}\",\"cashFlowJson\":\"[13.37]\",\"cashFlowReJson\":\"[\\\"ALPHA BOII\\\"]\"}\n";
        Json.fromString(json).restoreAll();
        //Menu menu = new Menu();
        //menu.mainMenu();
        //UserManagement.getInstance().addUser("Admin","Admin","");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Json.printString();
            }
        });

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        prim = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("DittoPOS");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.setResizable(false);
        System.out.println("[+] Primary Stage initialized, Showing Login");

        primaryStage.setWidth(1280);
        primaryStage.show();
        System.out.println(primaryStage.getWidth() + " " + primaryStage.getHeight());
    }
}
