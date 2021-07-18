package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
        AnchorPane rootNode = loader.load();


        MenuBar menuBar = createMenu();
        Pane menuPane = (Pane) rootNode.getChildren().get(0);
        menuPane.getChildren().addAll(menuBar);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        primaryStage.setTitle("Inventory App");
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }

    private MenuBar createMenu() {
        //FILE MENU
        Menu fileMenu = new Menu("File");
        MenuItem exitGame = new MenuItem("Exit");
        exitGame.setOnAction(event -> {
            exitGame();
        });

        //HELP MENU
        Menu helpMenu = new Menu("Help");
        MenuItem aboutInventory = new MenuItem("About Inventory Management");
        aboutInventory.setOnAction(event -> {
            aboutInven();
        });
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> {
            aboutMe();
        });

        fileMenu.getItems().addAll(exitGame);
        helpMenu.getItems().addAll(aboutInventory,aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private void exitGame(){
        Platform.exit();
        System.exit(0);
    }

    private void aboutInven(){
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About Inventory Management Application");
        about.setHeaderText("Inventory Details");
        about.setContentText("This Inventory Management Application is designed to help manage, store and keep a track of different products/items along with their Price and Quantity. " +
                "You can add Items into the list by entering the required details and hitting the 'Add' button. " +
                "Similarly Items can be shipped by clicking on the 'Ship' button after which the Profit/Loss will be calculated.");
        about.show();
    }

    private void aboutMe(){
        Alert meAlert = new Alert(Alert.AlertType.INFORMATION);
        meAlert.setTitle("About The Developer");
        meAlert.setHeaderText("Aryan Hamine");
        meAlert.setContentText("I am a Java Application Developer and love experimenting with various functionalities of the same." +
                "This application is a sincere attempt to add functionalities to the code which would be required by an Inventory. ");
        meAlert.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
