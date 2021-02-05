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
 * Controller class that controls the Add Product screen of the Inventory Application
 *
 * @author Ron Mercier
 *
 * */
public class AddProductController implements Initializable {
    /**
     * Contains a list of parts that is associated with products
     *
     * //@param addedParts Parts that are associated with a product
     *
     * */
    private final ObservableList<Part> addedParts = FXCollections.observableArrayList();
    /**
     * The text field for the product ID.
     * */
    @FXML
    private TextField AddProdIDTxt;
    /**
     * The text field for the product name.
     * */
    @FXML
    private TextField AddProdNameTxt;
    /**
     * The text field for the product inventory.
     * */
    @FXML
    private TextField AddProdInvTxt;
    /**
     * The text field for the product price.
     * */
    @FXML
    private TextField AddProdPriceTxt;
    /**
     * The text field for the Maximum product level.
     * */
    @FXML
    private TextField AddProdMaxTxt;
    /**
     * The text field for the Minimum product level.
     * */
    @FXML
    private TextField AddProdMinTxt;
    /**
     * The text field to search for a part.
     * */
    @FXML
    private TextField SearchPartTxt;
    /**
     * Table view of all the parts.
     * */
    @FXML
    private TableView<Part> CurrentPartsTable;
    /**
     * The part ID column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, Integer> PartIDCol;
    /**
     * The part name column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, String> PartNameCol;
    /**
     * The part stock column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, Integer> PartStockCol;
    /**
     * The part price column for all parts in the table.
     * */
    @FXML
    private TableColumn<Part, Double> PartPriceCol;
    /**
     * Table view of all the associated parts.
     * */
    @FXML
    private TableView<Part> AssociatedPartsTable;
    /**
     * The part ID column of all the associated parts.
     * */
    @FXML
    private TableColumn<Part, Integer> AssociatedPartIDCol;
    /**
     * The part name column of all the associated parts.
     * */
    @FXML
    private TableColumn<Part, String> AssociatedPartNameCol;
    /**
     * The part stock column of all the associated parts.
     * */
    @FXML
    private TableColumn<Part, Integer> AssociatedPartStockCol;
    /**
     * The part price column of all the associated parts.
     * */
    @FXML
    private TableColumn<Part, Double> AssociatedPartPriceCol;


    /**
     * Action event that starts a search for a part based on the name or ID in the search
     * text field, and displays the results in the table.
     *
     * @param event Search button event.
     *
     * */
    @FXML
    void SearchPart(ActionEvent event) {
        ObservableList<Part> allParts = Inventory.getAllParts() ;
        ObservableList<Part> partSearch = FXCollections.observableArrayList();
        String searchString = SearchPartTxt.getText();
        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partSearch.add(part);
            }
        }
        CurrentPartsTable.setItems(partSearch);
        if (SearchPartTxt.getText().isEmpty() || partSearch.isEmpty()) {
            formError(2);
        }
    }
    /**
     * Adds a selected part object to the associated part table from the all parts table.
     *
     * If there is no part selected, an error message is displayed.
     *
     * @param event Add button event.
     * */
    @FXML
    void AddProdAddPart(ActionEvent event) {
        boolean found = false;
        Part part = CurrentPartsTable.getSelectionModel().getSelectedItem();
        if(part != null) {
            for (int i = 0; i < addedParts.size(); i++) {
                if(addedParts.get(i).equals(part)) {
                    found = true;
                }
            }

            if(found) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Part Duplication");
                alert.setHeaderText("Part already in Product");
                alert.setContentText("This part is already linked to the product");
                alert.showAndWait();
            } else {
                addedParts.add(part);
                AssociatedPartsTable.setItems(addedParts);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Part selected");
            alert.setHeaderText("Please select a part from the existing list to add to the product");
            alert.showAndWait();
        }
    }
    /**
     * Removes a selected part from the associated parts table.
     *
     * Displays a confirmation message before part is removed or if no
     * part is selected.
     *
     * @param event Remove button event.
     * */
    @FXML
    void RemoveAssociatedPart(ActionEvent event) {
        Part part = AssociatedPartsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            formError(3);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to remove the part?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                addedParts.remove(part);
                AssociatedPartsTable.setItems(addedParts);
            }

        }
    }
    /**
     * Action event that displays a confirmation message before loading the main screen.
     *
     * @param event Cancel button event.
     * @throws IOException from FXMLLoader.
     * */
    @FXML
    private void CancelSave(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("Click ok to exit. \nClick Cancel to continue.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
           backToMain(event);
        }
    }
    /**
     * Action event that adds a new product to the inventory and loads
     * the main screen once the product is saved.
     *
     * @param event Save button event.
     * @throws IOException from FXMLLoader.
     * */
    @FXML
    void SaveProduct(ActionEvent event) throws IOException {
        try {
            int id = 0;
            String name = AddProdNameTxt.getText();
            int stock = Integer.parseInt(AddProdInvTxt.getText());
            double price = Double.parseDouble(AddProdPriceTxt.getText());
            int min = Integer.parseInt(AddProdMinTxt.getText());
            int max = Integer.parseInt(AddProdMaxTxt.getText());

                if (productIsValid(name, price, stock, min, max)) {
                            Product newProduct = new Product(id, name, price, stock, min, max);
                            for (Part part : addedParts) {
                                newProduct.addAssociatedPart(part);
                            }
                            newProduct.setId(Inventory.generateProductId());
                            Inventory.addProduct(newProduct);
                            backToMain(event);

                    }


        } catch (Exception e) {
            formError(1);
        }
        }

    /**
     * Shows various error messages used as part of the validation process.
     *
     * @param min The minimum value for the product.
     * @param max The maximum value for the product.
     * @param stock The inventory level for the product.
     * @param price The price of the product.
     * @param name The name of the product.
     * @return Boolean Indicates if product is valid.
     *
     * */
    public Boolean productIsValid(String name, double price, int stock, int min, int max) {
        String errorMessage = "";
        boolean valid;

        if (name.isEmpty()) {
            errorMessage += ("Name field cannot be empty.\n");
        }

        if (min <= 0) {
            errorMessage += ("Min must be more than 0.\n");
        }

        if (min > max) {
            errorMessage += ("Min must be less than max.\n");
        }

        if (stock < min || stock > max) {
            errorMessage += ("Inventory must be between minimum and maximum.\n");
        }

        if (price <= 0) {
            errorMessage += "Price must be greater than 0.\n";
        }

        if(addedParts.isEmpty()) {
            errorMessage += ("Product must have at least one part\n");
        } else {
            //loop through all the products and add up the cost
            //price of product cannot be less than the sum of the parts
            double partCost = 0.0;
            for (int i = 0; i < addedParts.size(); i++) {
                partCost += addedParts.get(i).getPrice();
            }
            if(partCost > price) {
                errorMessage += ("Product price must be greater than the sum of the parts.");
            }
        }

        if (errorMessage.length() > 0) {
            errorMessage += ("\nFix the listed errors and save again");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Validation Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    /**
     * Action event that loads the Main Screen
     *
     * @param event Passed from parent method.
     * @throws IOException from FXMLLoader.
     * */
    private void backToMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Shows various error messages used as part of the validation process.
     *
     * @param alertType Alert type selector
     * */
    private void formError(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("ERROR");
                alert.setHeaderText("Error Adding Product");
                alert.setContentText("Form contains invalid values or blank fields.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("ERROR");
                alert.setHeaderText("Part not found!");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("ERROR");
                alert.setHeaderText("Part was not or selected!");
                alert.showAndWait();
                break;
        }
    }

    /**
     *
     * Initializes the Add Product Controller and populates the tables.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     *
     * //@param resourceBundle Resource bundles contain locale specific objects.
     *
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        CurrentPartsTable.setItems(Inventory.getAllParts());


        AssociatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        /**
         * Generates a new product ID and disables the product ID text field.
         *
         **/
        AddProdIDTxt.setText("Auto ID");
        AddProdIDTxt.setDisable(true);
    }



}

