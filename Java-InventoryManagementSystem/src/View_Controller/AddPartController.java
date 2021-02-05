package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that controls the Add Part screen of the Inventory Application
 *
 * @author Ron Mercier
 * */

public class AddPartController implements Initializable {
    /**
     * The InHouse Radio button.
     * */
    @FXML
    private RadioButton InHouseBtn;
    /**
     * The Outsourced Radio button.
     * */
    @FXML
    private RadioButton OutsourcedBtn;
    /**
     * The toggle group for the radio buttons.
     * */
    @FXML
    private ToggleGroup PartToggleGroup;
    /**
     * The text field for the Part ID.
     * */
    @FXML
    private TextField PartIDTxt;
    /**
     * The text field for the Part Name.
     * */
    @FXML
    private TextField PartNameTxt;
    /**
     * The text field for the Part Stock.
     * */
    @FXML
    private TextField PartInvTxt;
    /**
     * The text field for the Part Price.
     * */
    @FXML
    private TextField PartPriceTxt;
    /**
     * The text field for Part Max.
     * */
    @FXML
    private TextField PartMaxTxt;
    /**
     * The text field for Part Min.
     * */
    @FXML
    private TextField PartMinTxt;
    /**
     * The text field for the Part Location.
     * */
    @FXML
    private TextField PartLocationTxt;
    /**
     * The Label for the Part Location.
     * */
    @FXML
    private Label PartLocationLbl;
    /**
     * The button to save the part.
     * */
    @FXML
    private Button SaveBtn;
    /**
     * The button to cancel and go back to main screen.
     * */
    @FXML
    private Button CancelBtn;

    private boolean PartsOutsourced;
    /**
     * Event that sets the Part location label to
     * Machine ID when InHouse radio is selected
     *
     * @param event Event for InHouse Radio button.
     * */
    @FXML
    void AddPartInHouse(MouseEvent event) {
        PartsOutsourced = false;
        PartLocationLbl.setText("Machine ID:");
    }
    /**
     * Event that sets the Part location label to
     * Company Name when the Outsourced radio is
     * selected
     *
     * @param event Event for Outsourced Radio button.
     * */
    @FXML
    void AddPartOutSourced(MouseEvent event) {
        PartsOutsourced = true;
        PartLocationLbl.setText("Company Name:");
    }

    /**
     * Action event that adds new part to the inventory and loads
     * the main screen once part is saved.
     *
     * Fields are validated with error messages that display empty fields
     * and/or invalid values.
     *
     * @param event Save button event.
     * @throws IOException from FXMLLoader
     *
     * */

    @FXML
    void SavePart(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = PartNameTxt.getText();
            int stock = Integer.parseInt(PartInvTxt.getText());
            double price = Double.parseDouble(PartPriceTxt.getText());
            int min = Integer.parseInt(PartMinTxt.getText());
            int max = Integer.parseInt(PartMaxTxt.getText());
            int machineId;
            String companyName;
            boolean partAddedToInventory = false;

            if (partIsValid(name, price, stock, min, max)) {

                        if (InHouseBtn.isSelected()) {
                            try {
                                machineId = Integer.parseInt(PartLocationTxt.getText());
                                InHouse InHousePt = new InHouse(id, name, price, stock, min, max, machineId);
                                InHousePt.setId(Inventory.generatePartId());
                                Inventory.addPart(InHousePt);
                                partAddedToInventory = true;
                            } catch (Exception e) {
                                formError(1);
                            }
                        }

                        if (OutsourcedBtn.isSelected()) {
                            companyName = PartLocationTxt.getText();
                            Outsourced outPt = new Outsourced(id, name, price, stock, min, max, companyName);
                            outPt.setId(Inventory.generatePartId());
                            Inventory.addPart(outPt);
                            partAddedToInventory = true;
                        }
                        if (partAddedToInventory) {
                            backToMain(event);
                        }
                    }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Input Error");
            alert.setHeaderText("Error saving part");
            alert.setContentText("Form contains invalid values or empty fields!");
            alert.showAndWait();
        }

    }
    /**
     * Shows various error messages used as part of the validation process.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The inventory level for the part.
     * @param price The price of the part.
     * @param name The name of the part.
     * @return Boolean Indicates if part is valid.
     *
     * */
    public Boolean partIsValid(String name, double price, int stock, int min, int max) {
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

        try {
            if (this.PartToggleGroup.getSelectedToggle().equals(this.InHouseBtn) || this.PartToggleGroup.getSelectedToggle()
                .equals(this.OutsourcedBtn)) {


                if (PartLocationTxt.getText().isEmpty()) {
                    errorMessage += ("Machine ID or Company Name is required!\n");
                }
            }
        } catch(Exception e) {
            errorMessage += ("Part type of In-House or Outsourced must be selected\n");
        }

        if (errorMessage.length() > 0) {
            errorMessage += ("\nFix the listed errors and save again");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Validation Error");
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
     * Action event that displays a confirmation message before
     * loading the main screen.
     *
     * @param event Cancel button event.
     * @throws IOException from FXMLLoader.
     *
     * */
    @FXML
    private void CancelSave(ActionEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Confirm Cancel");
            alert.setHeaderText("Are you sure you want to cancel?");
            alert.setContentText("Click ok to exit. \nClick Cancel to continue.");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            else {
                alert.close();
            }
        }
        catch (IOException ignored) {}
    }

    /**
     * Error message for the MachineID.
     *
     * @param alertType Alert type selector
     *
     * */
    private void formError(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (alertType == 1) {
            alert.setTitle("Error");
            alert.setHeaderText("Error Adding Part");
            alert.setContentText("Machine ID can only contain numbers.");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the AddPartController, generates a partId for new parts added and
     * disables the text field for the part ID.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle Resource bundles contain locale specific objects.
     *
     * */

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        PartIDTxt.setText("Auto ID");
        PartIDTxt.setDisable(true);
    }

}
