package controller;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BasketballPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    public Button addB;
    public Button removeB;
    //Note - These widgets are with out explicit types
    //Casting on selection will be needed
    //*
    public TableColumn allJerseyCol;
    public TableColumn allNameCol;
    public TableColumn allPointsCol;
    public TableColumn allTeamCol;
    public TableView allTable;
    public TableColumn fewJerseyCol;
    public TableColumn fewNameCol;
    public TableColumn fewPointsCol;
    public TableColumn fewTeamCol;
    public TableView fewTable;
    //*/
    //Note - These widgets have types defined - casting on selection will not be needed
    /*
    public TableColumn<BasketballPlayer, Integer> allJerseyCol;
    public TableColumn<BasketballPlayer, String> allNameCol;
    public TableColumn<BasketballPlayer, Integer> allPointsCol;
    public TableColumn<BasketballPlayer, String> allTeamCol;
    public TableView<BasketballPlayer> allTable;
    public TableColumn<BasketballPlayer, Integer> fewJerseyCol;
    public TableColumn<BasketballPlayer, String> fewNameCol;
    public TableColumn<BasketballPlayer, Integer> fewPointsCol;
    public TableColumn<BasketballPlayer, String> fewTeamCol;
    public TableView<BasketballPlayer> fewTable;
    */
    private ObservableList<BasketballPlayer> allPlayers = FXCollections.observableArrayList();
    private ObservableList<BasketballPlayer> fewPlayers = FXCollections.observableArrayList();


    public void onAddB(ActionEvent actionEvent) {
        System.out.println("On Add Clicked");

        BasketballPlayer SP = (BasketballPlayer) allTable.getSelectionModel().getSelectedItem();
        if(SP == null)
            return;

        allPlayers.remove(SP);
        fewPlayers.add(SP);
    }

    public void onRemoveB(ActionEvent actionEvent) {
        System.out.println("On Remove Clicked");

        BasketballPlayer SP = (BasketballPlayer) fewTable.getSelectionModel().getSelectedItem();
        if(SP == null)
            return;

        fewPlayers.remove(SP);
        allPlayers.add(SP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allPlayers.add(new BasketballPlayer(11, "Yao Ming", 1234, "Rockets"));
        allPlayers.add(new BasketballPlayer(22, "Clyde Drexler", 10000, "Rockets"));
        allPlayers.add(new BasketballPlayer(34, "Hakeem Olajuwon", 2, "Rockets"));
        allPlayers.add(new BasketballPlayer(45, "Rudy Tomjanovich", 450, "Rockets"));

        allPlayers.add(new BasketballPlayer(8, "Kobe Bryant", 867, "Lakers"));
        allPlayers.add(new BasketballPlayer(13, "Wilt Chamberlin", 1541, "Lakers"));
        allPlayers.add(new BasketballPlayer(34, "Shaquille O'Neal", 45, "Lakers"));
        allPlayers.add(new BasketballPlayer(32, "Magic Johnson", 9786, "Lakers"));

        allJerseyCol.setCellValueFactory(new PropertyValueFactory<>("jersey"));
        allNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        allTeamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        allTable.setItems(allPlayers);

        fewJerseyCol.setCellValueFactory(new PropertyValueFactory<>("jersey"));
        fewNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fewPointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        fewTeamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        fewTable.setItems(fewPlayers);


    }
}
