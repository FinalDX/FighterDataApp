package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import fighterdataapplication.FighterDataApplication;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Fight;
/**
 * FXML Controller class
 *
 * @author Sean Fenner
 */
public class DashboardController implements Initializable {
    // Observalbe lists
    private final ObservableList<String> weightClassList = 
            FXCollections.observableArrayList("Women Strawweight", "Women Flyweight",
                    "Women Bantamweight", "Women Featherweight", "Flyweight", 
                    "Bantamweight", "Featherweight", "Lightweight",
                    "Welterweight", "Middleweight", "Light Heavyweight",
                    "Heavyweight", "Catch Weight", "Open Weight");
    private final ObservableList<String> redFighterList = FXCollections.observableArrayList();;
    private final ObservableList<String> blueFighterList = FXCollections.observableArrayList();;
    private final ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList();
    // Fight object
    private Fight generatedFight;
    // Analyze flag
    private boolean analyzeComplete = false;
    // Charts
    @FXML private BarChart<?, ?> strikeBarChart;
    @FXML private BarChart<?, ?> statBarChart;
    @FXML private LineChart<Date, Number> lineChart;
    @FXML private PieChart pieChart;
    // Combo boxes
    @FXML private ComboBox<String> weightClassComboBox;
    @FXML private ComboBox<String> redFighterComboBox;
    @FXML private ComboBox<String> blueFighterComboBox;
    
    // =========================================================================
    // MEHTODS
    // =========================================================================
    
    public void clearTables() {
        strikeBarChart.getData().clear();
        statBarChart.getData().clear();
        lineChart.getData().clear();
        pieChart.getData().clear();
    }
    // -------------------------------------------------------------------------
    
    @FXML public void dataSearchButtonPressed(ActionEvent event) throws IOException {
        Parent changeParent = FXMLLoader.load(getClass().getResource("/view/DataSearchScreen.fxml"));
        Scene changeScene = new Scene(changeParent);

        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.setScene(changeScene);
        stage.show();
    }
    // -------------------------------------------------------------------------
    
    @FXML public void clearButtonPushed(ActionEvent event) {
        clearTables();
        analyzeComplete = false;
    }
    // -------------------------------------------------------------------------
        
    @FXML public void analyzeButtonPressed(ActionEvent event) throws ParseException {
        // Combo boxes must have values in order for this mehtod to execute
        if (redFighterComboBox.getValue() != null && 
                blueFighterComboBox.getValue() != null &&
                weightClassComboBox.getValue() != null) {
            // Figher names
            String selectedRedFighter;
            String selectedBlueFighter;
            // Red sets
            XYChart.Series redStrikeBarSet = new XYChart.Series<>();
            XYChart.Series redStatBarSet = new XYChart.Series<>();
            XYChart.Series redLineSet = new XYChart.Series<>();
            // Blue sets
            XYChart.Series blueStrikeBarSet = new XYChart.Series<>();
            XYChart.Series blueStatBarSet = new XYChart.Series<>();
            XYChart.Series blueLineSet = new XYChart.Series<>();
            // Dictionaries
            HashMap<Date, Double> redDateStrikeTable = new HashMap<>();
            HashMap<Date, Double> blueDateStrikeTable = new HashMap<>();
            // Date lists
            List<Date> redDateList = new ArrayList<>();
            List<Date> blueDateList = new ArrayList<>();
            List<Date> allDateList = new ArrayList<>();
            // Hidden line set
            XYChart.Series differenceLineSet = new XYChart.Series<>();
            // Red fighter data
            double redBodyStrikes = 0;
            double redHeadStrikes = 0;
            double redKnockDowns = 0;
            double redLegStrikes = 0;
            double redPasses = 0;
            double redReversals = 0;
            double redSubmissionAttempts = 0;
            double redTakedowns = 0;
            String redLosses = "?";
            String redTitleBouts = "?";
            int redWinDecMajority = 0;
            int redWinDecSplit = 0;
            int redWinDecUnanimous = 0;
            int redWinKOTKO = 0;
            int redWinSubmission = 0;
            int redWinDocStop = 0;
            String redReach = "?";
            String redAge = "?";
            int redTotalWins = 0;
            double redTotalStrikes;
            Date redLastFight = new Date();
            int redCount = 0;
            // Blue fighter data
            double blueBodyStrikes = 0;
            double blueHeadStrikes = 0;
            double blueKnockDowns = 0;
            double blueLegStrikes = 0;
            double bluePasses = 0;
            double blueReversals = 0;
            double blueSubmissionAttempts = 0;
            double blueTakedowns = 0;
            String blueLosses = "?";
            String blueTitleBouts = "?";
            int blueWinDecMajority = 0;
            int blueWinDecSplit = 0;
            int blueWinDecUnanimous = 0;
            int blueWinKOTKO = 0;
            int blueWinSubmission = 0;
            int blueWinDocStop = 0;
            String blueReach = "?";
            String blueAge = "?";
            int blueTotalWins = 0;
            double blueTotalStrikes;
            Date blueLastFight = new Date();
            int blueCount = 0;
            // Initialze dates
            Date redDate = new Date();
            Date blueDate = new Date();
            // Clear tables of previous values
            clearTables();
            // Get the names of both figheters for the purpose of searching for
            // all of their data
            selectedRedFighter = redFighterComboBox.getValue();
            selectedBlueFighter = blueFighterComboBox.getValue();
            // Set dates to earliest possible so that the latest fight date can be 
            // found
            redLastFight.setTime(0);
            redDate.setTime(0);
            // Find every record for the selected fighter
            for (int i = 0; i < FighterDataApplication.allFights.size(); i++) {
                // If the selected red fighter name matches a name in the red
                // fighter property of allFights then gather data
                if (selectedRedFighter.equals(FighterDataApplication.allFights.get(i).getRed_fighter())) {
                    // Count occurence
                    redCount += 1;
                    // Reset total strikes
                    redTotalStrikes = 0;
                    // Update strike bar values
                    if (!FighterDataApplication.allFights.get(i).getR_avg_body_landed().equals("?")) {
                        redBodyStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_body_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_head_landed().equals("?")) {
                        redHeadStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_head_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_leg_landed().equals("?")) {
                        redLegStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_leg_landed());
                    }
                    // Update stat bar values
                    if (!FighterDataApplication.allFights.get(i).getR_avg_kd().equals("?")) {
                        redKnockDowns += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_kd());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_pass().equals("?")) {
                        redPasses += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_pass());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_rev().equals("?")) {
                        redReversals += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_rev());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_sub_att().equals("?")) {
                        redSubmissionAttempts += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_sub_att());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_td_landed().equals("?")) {
                        redTakedowns += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_td_landed());
                    }
                    // Update line chart values
                    if (!FighterDataApplication.allFights.get(i).getDate().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getR_avg_body_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getR_avg_head_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getR_avg_leg_landed().equals("?")) {
                        redDate = new SimpleDateFormat("dd/MM/yyyy").parse(FighterDataApplication.allFights.get(i).getDate());
                        redTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_body_landed());
                        redTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_head_landed());
                        redTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_leg_landed());
                        // Save date and totalStrike pair so that total Strikes can be
                        // retrieved after sorting the dates.
                        redDateStrikeTable.put(redDate, redTotalStrikes);
                        redDateList.add(redDate);
                        allDateList.add(redDate);
                    }
                    // Collect fight data from the fighter's last fight
                    if (redDate.compareTo(redLastFight) > 0) {
                        // Update last fight
                        redLastFight = redDate;
                        // Update pie chart values
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_decision_majority().equals("?")) {
                            redWinDecMajority = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_decision_majority());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_decision_split().equals("?")) {
                            redWinDecSplit = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_decision_split());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_decision_unanimous().equals("?")) {
                            redWinDecUnanimous = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_decision_unanimous());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_doc_stop().equals("?")) {
                            redWinDocStop = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_doc_stop());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_ko_tko().equals("?")) {
                            redWinKOTKO = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_ko_tko());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_submisision().equals("?")) {
                            redWinSubmission = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_submisision());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_losses().equals("?")) {
                            redLosses = FighterDataApplication.allFights.get(i).getR_losses();
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_reach_cm().equals("?")) {
                            redReach = FighterDataApplication.allFights.get(i).getR_reach_cm();
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_age().equals("?")) {
                            redAge = FighterDataApplication.allFights.get(i).getR_age();
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_total_title_bouts().equals("?")) {
                            redTitleBouts = FighterDataApplication.allFights.get(i).getR_total_title_bouts();
                        }
                        redTotalWins = redWinDecMajority + redWinDecSplit + redWinDecUnanimous + 
                                redWinDocStop + redWinKOTKO + redWinSubmission;
                    }

                } 
                // If the selected red fighter name matches a name in the blue
                // fighter property of allFights then gather all data
                else if (selectedRedFighter.equals(FighterDataApplication.allFights.get(i).getBlue_fighter())) {
                    // Count occurence
                    redCount += 1;
                    // Reset total strikes to calculate new total strikes based on 
                    // the date
                    redTotalStrikes = 0;
                    // Update strike bar values
                    if (!FighterDataApplication.allFights.get(i).getB_avg_body_landed().equals("?")) {
                        redBodyStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_body_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_head_landed().equals("?")) {
                        redHeadStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_head_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_leg_landed().equals("?")) {
                        redLegStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_leg_landed());
                    }
                    // Update stat bar values
                    if (!FighterDataApplication.allFights.get(i).getB_avg_kd().equals("?")) {
                        redKnockDowns += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_kd());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_pass().equals("?")) {
                        redPasses += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_pass());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_rev().equals("?")) {
                        redReversals += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_rev());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_sub_att().equals("?")) {
                        redSubmissionAttempts += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_sub_att());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_td_landed().equals("?")) {
                        redTakedowns += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_td_landed());
                    }
                    // Update line chart values
                    if (!FighterDataApplication.allFights.get(i).getDate().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getB_avg_body_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getB_avg_head_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getB_avg_leg_landed().equals("?")) {
                        redDate = new SimpleDateFormat("dd/MM/yyyy").parse(FighterDataApplication.allFights.get(i).getDate());
                        redTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_body_landed());
                        redTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_head_landed());
                        redTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_leg_landed());
                        // Save date and totalStrike pair so that total Strikes can be
                        // retrieved after sorting the dates.
                        redDateStrikeTable.put(redDate, redTotalStrikes);
                        redDateList.add(redDate);
                        allDateList.add(redDate);
                    }
                    // Collect pie chart values
                    if (redDate.compareTo(redLastFight) > 0) {
                        // Update last fight
                        redLastFight = redDate;
                        // Update pie chart values
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_decision_majority().equals("?")) {
                            redWinDecMajority = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_decision_majority());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_decision_split().equals("?")) {
                            redWinDecSplit = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_decision_split());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_decision_unanimous().equals("?")) {
                            redWinDecUnanimous = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_decision_unanimous());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_doc_stop().equals("?")) {
                            redWinDocStop = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_doc_stop());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_ko_tko().equals("?")) {
                            redWinKOTKO = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_ko_tko());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_submisision().equals("?")) {
                            redWinSubmission = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_submisision());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_losses().equals("?")) {
                            redLosses = FighterDataApplication.allFights.get(i).getB_losses();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_reach_cm().equals("?")) {
                            redReach = FighterDataApplication.allFights.get(i).getB_reach_cm();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_age().equals("?")) {
                            redAge = FighterDataApplication.allFights.get(i).getB_age();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_total_title_bouts().equals("?")) {
                            redAge = FighterDataApplication.allFights.get(i).getB_total_title_bouts();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_total_title_bouts().equals("?")) {
                            redTitleBouts = FighterDataApplication.allFights.get(i).getB_total_title_bouts();
                        }
                        redTotalWins = redWinDecMajority + redWinDecSplit + redWinDecUnanimous + 
                                redWinDocStop + redWinKOTKO + redWinSubmission;
                    }   
                } 
                // If the selected blue fighter name matches a name in the red
                // fighter property of allFights then gather all data
                if (selectedBlueFighter.equals(FighterDataApplication.allFights.get(i).getRed_fighter())) {
                    // Count occurence
                    blueCount += 1;
                    // Reset total strikes
                    blueTotalStrikes = 0;
                    // Update strike bar values
                    if (!FighterDataApplication.allFights.get(i).getR_avg_body_landed().equals("?")) {
                        blueBodyStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_body_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_head_landed().equals("?")) {
                        blueHeadStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_head_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_leg_landed().equals("?")) {
                        blueLegStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_leg_landed());
                    }
                    // Update stat bar values
                    if (!FighterDataApplication.allFights.get(i).getR_avg_kd().equals("?")) {
                        blueKnockDowns += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_kd());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_pass().equals("?")) {
                        bluePasses += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_pass());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_rev().equals("?")) {
                        blueReversals += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_rev());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_sub_att().equals("?")) {
                        blueSubmissionAttempts += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_sub_att());
                    }
                    if (!FighterDataApplication.allFights.get(i).getR_avg_td_landed().equals("?")) {
                        blueTakedowns += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_td_landed());
                    }
                    // Update line chart values
                    if (!FighterDataApplication.allFights.get(i).getDate().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getR_avg_body_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getR_avg_head_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getR_avg_leg_landed().equals("?")) {
                        blueDate = new SimpleDateFormat("dd/MM/yyyy").parse(FighterDataApplication.allFights.get(i).getDate());
                        blueTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_body_landed());
                        blueTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_head_landed());
                        blueTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getR_avg_leg_landed());
                        // Save date and totalStrike pair so that total Strikes can be
                        // retrieved after sorting the dates.
                        blueDateStrikeTable.put(blueDate, blueTotalStrikes);
                        blueDateList.add(blueDate);
                        allDateList.add(blueDate);
                    }
                    // Collect fight data from the fighter's last fight
                    if (blueDate.compareTo(blueLastFight) > 0) {
                        // Update last fight
                        blueLastFight = blueDate;
                        // Update pie chart values
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_decision_majority().equals("?")) {
                            blueWinDecMajority = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_decision_majority());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_decision_split().equals("?")) {
                            blueWinDecSplit = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_decision_split());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_decision_unanimous().equals("?")) {
                            blueWinDecUnanimous = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_decision_unanimous());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_doc_stop().equals("?")) {
                            blueWinDocStop = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_doc_stop());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_ko_tko().equals("?")) {
                            blueWinKOTKO = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_ko_tko());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_win_by_submisision().equals("?")) {
                            blueWinSubmission = Integer.valueOf(FighterDataApplication.allFights.get(i).getR_win_by_submisision());
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_losses().equals("?")) {
                            blueLosses = FighterDataApplication.allFights.get(i).getR_losses();
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_reach_cm().equals("?")) {
                            blueReach = FighterDataApplication.allFights.get(i).getR_reach_cm();
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_age().equals("?")) {
                            blueAge = FighterDataApplication.allFights.get(i).getR_age();
                        }
                        if (!FighterDataApplication.allFights.get(i).getR_total_title_bouts().equals("?")) {
                            blueTitleBouts = FighterDataApplication.allFights.get(i).getR_total_title_bouts();
                        }
                        blueTotalWins = blueWinDecMajority + blueWinDecSplit + blueWinDecUnanimous + 
                                blueWinDocStop + blueWinKOTKO + blueWinSubmission;
                    }

                } 
                // If the selected blue fighter name matches a name in the blue
                // fighter property of allFights then gather all data
                else if (selectedBlueFighter.equals(FighterDataApplication.allFights.get(i).getBlue_fighter())) {
                    // Count occurence
                    blueCount += 1;
                    // Reset total strikes to calculate new total strikes based on 
                    // the date
                    blueTotalStrikes = 0;
                    // Update strike bar values
                    if (!FighterDataApplication.allFights.get(i).getB_avg_body_landed().equals("?")) {
                        blueBodyStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_body_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_head_landed().equals("?")) {
                        blueHeadStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_head_landed());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_leg_landed().equals("?")) {
                        blueLegStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_leg_landed());
                    }
                    // Update stat bar values
                    if (!FighterDataApplication.allFights.get(i).getB_avg_kd().equals("?")) {
                        blueKnockDowns += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_kd());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_pass().equals("?")) {
                        bluePasses += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_pass());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_rev().equals("?")) {
                        blueReversals += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_rev());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_sub_att().equals("?")) {
                        blueSubmissionAttempts += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_sub_att());
                    }
                    if (!FighterDataApplication.allFights.get(i).getB_avg_td_landed().equals("?")) {
                        blueTakedowns += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_td_landed());
                    }
                    // Update line chart values
                    if (!FighterDataApplication.allFights.get(i).getDate().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getB_avg_body_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getB_avg_head_landed().equals("?") &&
                        !FighterDataApplication.allFights.get(i).getB_avg_leg_landed().equals("?")) {
                        blueDate = new SimpleDateFormat("dd/MM/yyyy").parse(FighterDataApplication.allFights.get(i).getDate());
                        blueTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_body_landed());
                        blueTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_head_landed());
                        blueTotalStrikes += Double.valueOf(FighterDataApplication.allFights.get(i).getB_avg_leg_landed());
                        // Save date and totalStrike pair so that total Strikes can be
                        // retrieved after sorting the dates.
                        blueDateStrikeTable.put(blueDate, blueTotalStrikes);
                        blueDateList.add(blueDate);
                        allDateList.add(blueDate);
                    }
                    // Collect pie chart values
                    if (blueDate.compareTo(blueLastFight) > 0) {
                        // Update last fight
                        blueLastFight = blueDate;
                        // Update pie chart values
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_decision_majority().equals("?")) {
                            blueWinDecMajority = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_decision_majority());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_decision_split().equals("?")) {
                            blueWinDecSplit = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_decision_split());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_decision_unanimous().equals("?")) {
                            blueWinDecUnanimous = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_decision_unanimous());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_doc_stop().equals("?")) {
                            blueWinDocStop = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_doc_stop());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_ko_tko().equals("?")) {
                            blueWinKOTKO = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_ko_tko());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_win_by_submisision().equals("?")) {
                            blueWinSubmission = Integer.valueOf(FighterDataApplication.allFights.get(i).getB_win_by_submisision());
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_losses().equals("?")) {
                            blueLosses = FighterDataApplication.allFights.get(i).getB_losses();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_reach_cm().equals("?")) {
                            blueReach = FighterDataApplication.allFights.get(i).getB_reach_cm();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_age().equals("?")) {
                            blueAge = FighterDataApplication.allFights.get(i).getB_age();
                        }
                        if (!FighterDataApplication.allFights.get(i).getB_total_title_bouts().equals("?")) {
                            blueTitleBouts = FighterDataApplication.allFights.get(i).getB_total_title_bouts();
                        }
                        blueTotalWins = blueWinDecMajority + blueWinDecSplit + blueWinDecUnanimous + 
                                blueWinDocStop + blueWinKOTKO + blueWinSubmission;
                    }   
                } 
            } // END of for loop
            // Sort dates to be used in line chart
            Collections.sort(allDateList);
            Collections.sort(redDateList);
            Collections.sort(blueDateList);
            // Add values to the red line set
            for (int i = 0; i < redDateList.size(); i++) {
                redLineSet.getData().add(new XYChart.Data(
                        // Date
                        new SimpleDateFormat("dd/MM/yyyy").format(redDateList.get(i)), 
                        // Strikes pulled from dictionary using the date
                        redDateStrikeTable.get(redDateList.get(i))));
            }
            // Add values to the blue line set
            for (int i = 0; i < blueDateList.size(); i++) {
                blueLineSet.getData().add(new XYChart.Data(
                        // Date
                        new SimpleDateFormat("dd/MM/yyyy").format(blueDateList.get(i)), 
                        // Strikes pulled from dictionary using the date
                        blueDateStrikeTable.get(blueDateList.get(i))));
            }
            // add values to the empty line set
            for (int i = 0; i < allDateList.size(); i++) {
                differenceLineSet.getData().add(new XYChart.Data(
                        // Date
                        new SimpleDateFormat("dd/MM/yyyy").format(allDateList.get(i)), 
                        // Strikes pulled from dictionary using the date
                        0));
            }
            // Calculate red fighter averages
            redBodyStrikes /= redCount;
            redHeadStrikes /= redCount;
            redLegStrikes /= redCount;
            redKnockDowns /= redCount;
            redPasses /= redCount;
            redReversals /= redCount;
            redSubmissionAttempts /= redCount;
            redTakedowns /= redCount;
            // Calculate blue fighter averages
            blueBodyStrikes /= blueCount;
            blueHeadStrikes /= blueCount;
            blueLegStrikes /= blueCount;
            blueKnockDowns /= blueCount;
            bluePasses /= blueCount;
            blueReversals /= blueCount;
            blueSubmissionAttempts /= blueCount;
            blueTakedowns /= blueCount;
            // Add values to the red strike bar set
            redStrikeBarSet.getData().add(new XYChart.Data("Head", redHeadStrikes));
            redStrikeBarSet.getData().add(new XYChart.Data("Body", redBodyStrikes));
            redStrikeBarSet.getData().add(new XYChart.Data("Legs", redLegStrikes));
            // Add values to the blue strike bar set
            blueStrikeBarSet.getData().add(new XYChart.Data("Head", blueHeadStrikes));
            blueStrikeBarSet.getData().add(new XYChart.Data("Body", blueBodyStrikes));
            blueStrikeBarSet.getData().add(new XYChart.Data("Legs", blueLegStrikes));
            // Add values to the red stat bar set
            redStatBarSet.getData().add(new XYChart.Data("Knock Downs", redKnockDowns));
            redStatBarSet.getData().add(new XYChart.Data("Passes", redPasses));
            redStatBarSet.getData().add(new XYChart.Data("Reversals", redReversals));
            redStatBarSet.getData().add(new XYChart.Data("Submission attempts", redSubmissionAttempts));
            redStatBarSet.getData().add(new XYChart.Data("Takedowns", redTakedowns));
            // Add values to the blue stat bar set
            blueStatBarSet.getData().add(new XYChart.Data("Knock Downs", blueKnockDowns));
            blueStatBarSet.getData().add(new XYChart.Data("Passes", bluePasses));
            blueStatBarSet.getData().add(new XYChart.Data("Reversals", blueReversals));
            blueStatBarSet.getData().add(new XYChart.Data("Submission attempts", blueSubmissionAttempts));
            blueStatBarSet.getData().add(new XYChart.Data("Takedowns", blueTakedowns));
            // Set strike bar chart values
            strikeBarChart.getData().addAll(redStrikeBarSet,blueStrikeBarSet);
            // Set stat bar chart values
            statBarChart.getData().addAll(redStatBarSet,blueStatBarSet);
            // Set line chart values
            lineChart.getData().addAll(differenceLineSet,redLineSet,blueLineSet);
            // Set pie chart values
            pieChartList.add(new PieChart.Data("Red Wins = " + redTotalWins, redTotalWins));
            pieChartList.add(new PieChart.Data("Blue Wins = " + blueTotalWins, blueTotalWins));
            pieChart.setData(pieChartList);
            // Generate fight
            generatedFight = (new Fight(
                    selectedRedFighter,
                    selectedBlueFighter,
                    "?",
                    "?", 
                    "?",
                    "?", 
                    "?",
                    String.valueOf(blueBodyStrikes), 
                    String.valueOf(blueHeadStrikes),
                    String.valueOf(blueKnockDowns),
                    String.valueOf(blueLegStrikes),
                    String.valueOf(bluePasses),
                    String.valueOf(blueReversals),
                    String.valueOf(blueSubmissionAttempts),
                    String.valueOf(blueTakedowns),
                    String.valueOf(blueLosses),
                    String.valueOf(blueTitleBouts),
                    String.valueOf(blueWinDecMajority),
                    String.valueOf(blueWinDecSplit),
                    String.valueOf(blueWinDecUnanimous),
                    String.valueOf(blueWinKOTKO),
                    String.valueOf(blueWinSubmission),
                    String.valueOf(blueWinDocStop),
                    "?",
                    "?",
                    String.valueOf(blueReach),
                    String.valueOf(redBodyStrikes), 
                    String.valueOf(redHeadStrikes),
                    String.valueOf(redKnockDowns),
                    String.valueOf(redLegStrikes),
                    String.valueOf(redPasses),
                    String.valueOf(redReversals),
                    String.valueOf(redSubmissionAttempts),
                    String.valueOf(redTakedowns),
                    String.valueOf(redLosses),
                    String.valueOf(redTitleBouts),
                    String.valueOf(redWinDecMajority),
                    String.valueOf(redWinDecSplit),
                    String.valueOf(redWinDecUnanimous),
                    String.valueOf(redWinKOTKO),
                    String.valueOf(redWinSubmission),
                    String.valueOf(redWinDocStop),
                    "?",
                    "?",
                    String.valueOf(redReach),
                    String.valueOf(blueAge),
                    String.valueOf(redAge)));   
            // Set flag to true to allow the user to generate a prediction
            analyzeComplete = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No fighters selected!");
            alert.setContentText("Please select two fighters.");
            alert.showAndWait();
        }
    }
    // -------------------------------------------------------------------------
    
    @FXML public void changeWeightClassComboBox (ActionEvent event) {
        String selectedWeightClass;
        String currentWeightClass;
        String currentRedFighter;
        String currentBlueFighter;
        boolean found;
        // Clear all tables
        clearTables();
        // Clear observable lists
        redFighterList.clear();
        blueFighterList.clear();
        pieChartList.clear();
        // Get selected value from weight class combobox
        selectedWeightClass = weightClassComboBox.getValue();
        // Search all fights for the selected weight class
        for (int i = 0; i < FighterDataApplication.allFights.size(); i ++) { 
            // Get the current weight class
           currentWeightClass = FighterDataApplication.allFights.get(i).getWeight_class();
           // If current weight class equals the selected weight class
            if (currentWeightClass.equals(selectedWeightClass)){
                // Get names of both fighters
                currentRedFighter = FighterDataApplication.allFights.get(i).getRed_fighter();
                currentBlueFighter = FighterDataApplication.allFights.get(i).getBlue_fighter();
                // Reset boolean and search index j
                found = false;
                int j = 0;
                // Search red fighter list to see if the current name already
                // exists in the list
                while (!found && j < redFighterList.size()) {
                    if (redFighterList.get(j).equals(currentRedFighter)) {
                        found = true;
                    }
                    j++;
                }
                // If the current name is not found, then add it to the red
                // fighter list
                if (!found) {
                    redFighterList.add(FighterDataApplication.allFights.get(i).getRed_fighter());
                }
                // Reset boolean and search index j
                found = false;
                j = 0;
                // Search blue fighter list to see if the current name already
                // exists in the list
                while (!found && j < blueFighterList.size()) {
                    if (blueFighterList.get(j).equals(currentBlueFighter)) {
                        found = true;
                    }
                    j++;
                }
                // If the current name is not found, then add it to the blue
                // fighter list
                if (!found) {
                    blueFighterList.add(FighterDataApplication.allFights.get(i).getBlue_fighter());
                }
            }
        }
        // Sort both lists
        FXCollections.sort(redFighterList);
        FXCollections.sort(blueFighterList);    
    }
    // -------------------------------------------------------------------------
    
    @FXML public void generatePredictionButtonPressed(ActionEvent event) throws IOException {
        if (analyzeComplete) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/GeneratePredictionScreen.fxml"));

            Parent changeParent = loader.load();
            Scene changeScene = new Scene(changeParent);

            // Send the generated fight to the generate prediction controller
            GeneratePredictionController controller = loader.getController();
            controller.sendFight(generatedFight);

            Stage stage;
            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
            stage.setScene(changeScene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Analyze not complete!");
            alert.setContentText("Please analyze two fighters before "
                    + "generating a prediction.");
            alert.showAndWait();
        }
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
        weightClassComboBox.setItems(weightClassList);
        redFighterComboBox.setItems(redFighterList);
        blueFighterComboBox.setItems(blueFighterList);
        
    }    
    
}
