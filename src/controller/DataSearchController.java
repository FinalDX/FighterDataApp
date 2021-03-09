package controller;

import fighterdataapplication.FighterDataApplication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Fight;

/**
 * FXML Controller class
 *
 * @author Sean Fenner
 */
public class DataSearchController implements Initializable {
    // Table view and its columns
    @FXML private TableView<Fight> fightTable;
    @FXML private TableColumn<Fight, String> redFighterColumn;
    @FXML private TableColumn<Fight, String> blueFighterColumn;
    @FXML private TableColumn<Fight, String> dateColumn;
    @FXML private TableColumn<Fight, String> winnerColumn;
    @FXML private TableColumn<Fight, String> weightClassColumn;
    @FXML private TableColumn<Fight, String> numOfRoundsColumn;
    @FXML private TableColumn<Fight, String> bStanceColumn;
    @FXML private TableColumn<Fight, String> bHeightColumn;
    @FXML private TableColumn<Fight, String> bReachColumn;
    @FXML private TableColumn<Fight, String> rStanceColumn;
    @FXML private TableColumn<Fight, String> rHeightColumn;
    @FXML private TableColumn<Fight, String> rReachColumn;                                                      
    // Text field
    @FXML private TextField searchTextField;
    
    // =========================================================================
    // MEHTODS
    // =========================================================================
    
    @FXML public void dashboardButtonPressed(ActionEvent event) throws IOException {
        Parent changeParent = FXMLLoader.load(getClass().getResource("/view/DashboardScreen.fxml"));
        Scene changeScene = new Scene(changeParent);
        changeScene.getStylesheets().add(getClass().getResource("/files/chartStyle.css").toExternalForm());

        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.setScene(changeScene);
        stage.show();
    }
    // -------------------------------------------------------------------------
    
    @FXML public void enterPressedSearchTextField(ActionEvent event) {
        String searchedText = searchTextField.getText();
        FilteredList<Fight> filteredData = new FilteredList<>(FighterDataApplication.allFights);
        // Lambda expression used to filter the table view by the selected month
        filteredData.setPredicate(row -> {
            return searchedText.equals(row.getRed_fighter()) || 
                    searchedText.equals(row.getBlue_fighter()) ||
                    searchedText.equals(row.getDate()) ||
                    searchedText.equals(row.getWinner()) ||
                    searchedText.equals(row.getWeight_class()) ||
                    searchedText.equals(row.getB_stance()) ||
                    searchedText.equals(row.getR_stance());
        });
        fightTable.setItems(filteredData);
    }
    // -------------------------------------------------------------------------
    
    @FXML public void clearButtonPushed(ActionEvent event) {
        fightTable.setItems(FighterDataApplication.allFights);
    }
    // -------------------------------------------------------------------------
    
    @FXML public void logOutButtonPressed(ActionEvent event) throws IOException {
        Parent changeParent = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene changeScene = new Scene(changeParent);

        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.setScene(changeScene);
        stage.show();
    }
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // INITIALIZE METHOD
    // =========================================================================
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set cells values in customerTable table view
        redFighterColumn.setCellValueFactory(new PropertyValueFactory<>
        ("red_fighter"));
        blueFighterColumn.setCellValueFactory(new PropertyValueFactory<>
        ("blue_fighter"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>
        ("date"));
        winnerColumn.setCellValueFactory(new PropertyValueFactory<>
        ("winner"));
        weightClassColumn.setCellValueFactory(new PropertyValueFactory<>
        ("weight_class"));
        numOfRoundsColumn.setCellValueFactory(new PropertyValueFactory<>
        ("num_of_rounds"));
        bStanceColumn.setCellValueFactory(new PropertyValueFactory<>
        ("b_stance"));
        bHeightColumn.setCellValueFactory(new PropertyValueFactory<>
        ("b_height_cm"));
        bReachColumn.setCellValueFactory(new PropertyValueFactory<>
        ("b_reach_cm"));
        rStanceColumn.setCellValueFactory(new PropertyValueFactory<>
        ("r_stance"));
        rHeightColumn.setCellValueFactory(new PropertyValueFactory<>
        ("r_height_cm"));
        rReachColumn.setCellValueFactory(new PropertyValueFactory<>
        ("r_reach_cm"));
        // Set items in table view using the allCustomers observable list
        fightTable.setItems(FighterDataApplication.allFights);
    }    
    
}
