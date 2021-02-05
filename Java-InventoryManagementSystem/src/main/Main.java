package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;



/**
 * This program is an Inventory Management application for managing an inventory of products
 * that contains associated parts.
 *
 * @author Ron Mercier
 *
 * */
public class Main extends Application {

    /**
     * The first method creates the FXML stage and loads the first scene
     * of the application
     *
     * @param primaryStage Primary Stage of the application.
     * @throws Exception FXMLLoader
     */

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**This is the main method for the Inventory application.
     *
     * This method creates the test data and starts the application.
     *
     * @param args String arguments
     *
     * */
    public static void main(String[] args) {

        int id = Inventory.generatePartId();
        InHouse graphicsCard = new InHouse(id, "Graphics Card", 150.00, 3,1, 5, 55);
        id = Inventory.generatePartId();
        InHouse hardDrive = new InHouse(id, "Hard Drive", 100.00, 6, 1, 7, 50);
        id = Inventory.generatePartId();
        InHouse CPU = new InHouse(id, "CPU", 200.00, 5, 1, 8, 75);

        id = Inventory.generatePartId();
        Outsourced powerSupply = new Outsourced(id, "Power Supply", 50.00, 5, 1, 6, "Corsair");

        Inventory.addPart(graphicsCard);
        Inventory.addPart(hardDrive);
        Inventory.addPart(CPU);
        Inventory.addPart(powerSupply);

        int productId = Inventory.generateProductId();
        Product desktopComputer = new Product(productId, "Desktop Computer", 500.00, 9, 1, 10);

        desktopComputer.addAssociatedPart(graphicsCard);
        desktopComputer.addAssociatedPart(hardDrive);
        desktopComputer.addAssociatedPart(CPU);
        desktopComputer.addAssociatedPart(powerSupply);
        Inventory.addProduct(desktopComputer);



        launch(args);
    }
}
