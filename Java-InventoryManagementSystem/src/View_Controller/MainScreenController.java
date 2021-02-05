package View_Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Controller class that controls the main screen of the Inventory application.
 *
 * FUTURE FEATURE
 * A future feature that could be implemented to this application is a security
 * feature such as a login screen requiring a user name and password.
 *
 * RUNTIME ERROR
 * A Runtime Error occurs when no part is selected when the user clicks the modify
 * button.  The error occurs due to a null value being passed to the initialize method
 * on the ModifyProductController. An example of the runtime being corrected/prevented,
 * can bee seen below in the prodModifyButtonClicked() method.
 *
 *
 * @author Ron Mercier
 * */


public class MainScreenController implements Initializable {
    /**
     * Table view of all the parts.
     * */
    @FXML
    private TableView<Part> MainPartsTable;
    /**
     * The part ID column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    /**
     * The part name column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, String> partNameCol;
    /**
     * The part stock column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, Integer> partStockCol;
    /**
     * The part price column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    /**
     * Table view of all the products.
     * */
    @FXML
    private TableView<Product> MainProductsTable;
    /**
     * The product ID column for all products in the table.
     * */
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    /**
     * The product name column for all products in the table.
     * */
    @FXML
    private TableColumn<Product, String> productNameCol;
    /**
     * The product stock column for all products in the table.
     * */
    @FXML
    private TableColumn<Product, Integer> productStockCol;
    /**
     * The product price column for all products in the table.
     * */
    @FXML
    private TableColumn<Product,Double> productPriceCol;
    /**
     * The text field to search for a part.
     * */
    @FXML
    private TextField MainPartsSearch;
    /**
     * The text field to search for a product.
     * */
    @FXML
    private TextField MainProductsSearch;
    /**
     * Button to exit the application.
     * */
    @FXML
    private Button ExitBtn;
    /**
     * Button to delete a part from the parts table.
     * */
    @FXML
    private Button partDeleteBtn;
    /**
     * Button to delete a product from the products table.
     * */
    @FXML
    private Button productDeleteBtn;


    /**
     * Part that is selected from the parts table.
     * */
    private static Part modPart;

    /**
     * Method to retrieve the selected part in the inventory.
     *
     * @return Returns the selected part.
     * */
    public static Part getModPart() {
        return modPart;
    }

    /**
     * Product that is selected from the products table.
     * */
    private static Product modProduct;

    /**
     * Method to retrieve the selected product in the inventory.
     *
     * @return Returns the selected product.
     * */
    public static Product getModProduct() {
        return modProduct;
    }

    /**
     * Exits the application.
     *
     * @param event Exit button event.
     */
    @FXML
    void exitButtonClicked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    /**
     * Action event that starts a search for a part based on the name or ID in the search
     * text field, and displays the results in the table.
     *
     * @param event Search button event.
     *
     * */
    @FXML
    private void searchPartTable(ActionEvent event) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partTableSearch = FXCollections.observableArrayList();
        String searchItem = MainPartsSearch.getText();
        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchItem) ||
                    part.getName().contains(searchItem)) {
                partTableSearch.add(part);
            }
        }
        MainPartsTable.setItems(partTableSearch);
        if (MainPartsSearch.getText().isEmpty() || partTableSearch.isEmpty()) {
            errorMessage(1);
        }
    }
    /**
     * Loads the Add Part Controller of the application.
     *
     * @param event Add button event
     * @throws IOException from the FXMLLoader
     * */
    @FXML
    private void addPartButtonClicked(ActionEvent event) throws IOException
    {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }
    /**
     * Event that loads the Modify Part Controller and displays an error when no part is selected.
     *
     * @param event Modify Part event */
    @FXML
    private void modPartButtonClicked(ActionEvent event) throws IOException {
        modPart = MainPartsTable.getSelectionModel().getSelectedItem();

        if (modPart == null) {
            errorMessage(2);
        } else {
                Parent parent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPart.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        }
    }
    /**
     * Deletes the selected part from the parts table and shows an error when no part is selected.
     *
     * @param event Delete Part button event
     * */
    @FXML
    private void deletePartButtonClicked(ActionEvent event) {
        Part selectedPart = MainPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            errorMessage(2);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the part?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }
    /**
     * Action event that starts a search for a product based on the name or ID in the search
     * text field, and displays the results in the table.
     *
     * @param event Search button event.
     *
     * */
    @FXML
    private void searchProductTable(ActionEvent event) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productTableSearch = FXCollections.observableArrayList();
        String searchItem = MainProductsSearch.getText();
        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchItem) || product.getName().contains(searchItem)) {
                productTableSearch.add(product);
            }
        }
        MainProductsTable.setItems(productTableSearch);
        if (MainProductsSearch.getText().isEmpty() || productTableSearch.isEmpty()) {
            errorMessage(3);
        }
    }

    /**
     * Loads the Add Product Controller of the application.
     *
     * @param event Add button event
     * @throws IOException from the FXMLLoader
     * */
    @FXML
    private void addProductButtonClicked(ActionEvent event) throws IOException
    {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        Stage addProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addProductStage.setScene(addProductScene);
        addProductStage.show();
    }
    /**
     * Event that loads the Modify Product Controller and displays an error when no product is selected.
     *
     * @param event Modify Part event
     * */
    @FXML
    private void modProductButtonClicked(ActionEvent event) throws IOException {
        modProduct = MainProductsTable.getSelectionModel().getSelectedItem();
        /**
         * From this if statement, when a null value is passed to the initialize method of the Modify
         * Product Controller it causes a runtime error.  So an if else statement is implemented
         * to prevent the error.*/
        if (modProduct == null) {
            errorMessage(4);
        } else {
                Parent parent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        }
    }
    /**
     * Deletes the selected product from the products table and shows an error when no product is selected.
     *
     * @param event Delete Product button event
     * */
    @FXML
    private void deleteProductButtonClicked(ActionEvent event) {
        Product selectedProduct = MainProductsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            errorMessage(4);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the product?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ObservableList<Part> addedParts = selectedProduct.getAllAssociatedParts();

                if (addedParts.size() >= 1) {
                    errorMessage(5);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }
    /**
     * Shows various error messages used as part of the validation process.
     *
     * @param alertType Alert type selector
     * */
    private void errorMessage(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("ERROR");
                alert.setHeaderText("Part not found!");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("ERROR");
                alert.setHeaderText("Please select a part!");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("ERROR");
                alert.setHeaderText("Product not found!");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("ERROR");
                alert.setHeaderText("Please select a product!");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("ERROR");
                alert.setHeaderText("Associated Parts");
                alert.setContentText("Associated parts must be removed from product before deletion!");
                alert.showAndWait();
                break;
        }
    }

    /**
     *
     * Initializes the Main Screen Controller and populates the tables.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Resource bundles contain locale specific objects.
     *
     * */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        MainPartsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        MainProductsTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}

