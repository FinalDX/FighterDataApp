package fighterdataapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Fight;

/**
 *
 * @author Sean Fenner
 */
public class FighterDataApplication extends Application {
    // List of fights taken from csv
    public static ObservableList<Fight> allFights = FXCollections.observableArrayList();
    
    // =========================================================================
    // MEHTODS
    // =========================================================================
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    // -------------------------------------------------------------------------
    
    private static void load_csv_into_list() {
        String csvName = "src/files/HistoricalFightDataFixed.csv";
        File csvFile = new File(csvName);
        String fieldDelimiter = ",";
        String line;
        String[] fields;
        BufferedReader csvReader;
        String red_fighter, blue_fighter, date, winner, title_bout, weight_class,
        num_of_rounds, b_avg_body_landed, b_avg_head_landed, b_avg_kd,
        b_avg_leg_landed, b_avg_pass, b_avg_rev, b_avg_sub_att, b_avg_td_landed,
        b_losses, b_total_title_bouts, b_win_by_decision_majority, 
        b_win_by_decision_split, b_win_by_decision_unanimous, b_win_by_ko_tko,
        b_win_by_submission, b_win_by_doc_stop, b_stance, b_height_cm,
        b_reach_cm, r_avg_body_landed, r_avg_head_landed, r_avg_kd,
        r_avg_leg_landed, r_avg_pass, r_avg_rev, r_avg_sub_att, r_avg_td_landed,
        r_losses, r_total_title_bouts, r_win_by_decision_majority,
        r_win_by_decision_split, r_win_by_decision_unanimous,
        r_win_by_ko_tko, r_win_by_submisision, r_win_by_doc_stop,
        r_stance, r_height_cm, r_reach_cm, b_age, r_age;
        
        try {
            csvReader = new BufferedReader(new FileReader(csvFile));
            // Remove headers in csv
            csvReader.readLine();
 
            // Create Fighte objects using csv and add them to the List
            while((line = csvReader.readLine()) != null){
                fields = line.split(fieldDelimiter);
                
                red_fighter = fields[0];
                blue_fighter = fields[1];
                date = fields[2];
                winner = fields[3];
                title_bout = fields[4];
                weight_class = fields[5];
                num_of_rounds = fields[6];
                b_avg_body_landed = fields[7];
                b_avg_head_landed = fields[8];
                b_avg_kd = fields[9];
                b_avg_leg_landed = fields[10];
                b_avg_pass = fields[11];
                b_avg_rev = fields[12];
                b_avg_sub_att = fields[13];
                b_avg_td_landed = fields[14];
                b_losses = fields[15];
                b_total_title_bouts = fields[16];
                b_win_by_decision_majority = fields[17];
                b_win_by_decision_split = fields[18];
                b_win_by_decision_unanimous = fields[19];
                b_win_by_ko_tko = fields[20];
                b_win_by_submission = fields[21];
                b_win_by_doc_stop = fields[22];
                b_stance = fields[23];
                b_height_cm = fields[24];
                b_reach_cm = fields[25];
                r_avg_body_landed = fields[26];
                r_avg_head_landed = fields[27];
                r_avg_kd = fields[28];
                r_avg_leg_landed = fields[29];
                r_avg_pass = fields[30];
                r_avg_rev = fields[31];
                r_avg_sub_att = fields[32];
                r_avg_td_landed = fields[33];
                r_losses = fields[34];
                r_total_title_bouts = fields[35];
                r_win_by_decision_majority = fields[36];
                r_win_by_decision_split = fields[37];
                r_win_by_decision_unanimous = fields[38];
                r_win_by_ko_tko = fields[39];
                r_win_by_submisision = fields[40];
                r_win_by_doc_stop = fields[41];
                r_stance = fields[42];
                r_height_cm = fields[43];
                r_reach_cm = fields[44];
                b_age = fields[45];
                r_age = fields[46];
                
                allFights.add(new Fight(red_fighter, blue_fighter, date,
                winner, title_bout, weight_class, num_of_rounds,
                b_avg_body_landed, b_avg_head_landed, b_avg_kd, 
                b_avg_leg_landed, b_avg_pass, b_avg_rev, b_avg_sub_att,
                b_avg_td_landed, b_losses, b_total_title_bouts,
                b_win_by_decision_majority, b_win_by_decision_split,
                b_win_by_decision_unanimous, b_win_by_ko_tko, 
                b_win_by_submission, b_win_by_doc_stop, b_stance, 
                b_height_cm, b_reach_cm,
                r_avg_body_landed, r_avg_head_landed, r_avg_kd, 
                r_avg_leg_landed, r_avg_pass, r_avg_rev, r_avg_sub_att,
                r_avg_td_landed, r_losses, r_total_title_bouts,
                r_win_by_decision_majority, r_win_by_decision_split,
                r_win_by_decision_unanimous, r_win_by_ko_tko, 
                r_win_by_submisision, r_win_by_doc_stop, r_stance, 
                r_height_cm, r_reach_cm, b_age, r_age));  
                
                
            }
            
        } catch(FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }  
    } 
    // -------------------------------------------------------------------------
    
    // =========================================================================
    // MAIN METHOD
    // =========================================================================

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Import data from csv into observable list
        load_csv_into_list();
        // Launch the applictaion
        launch(args);
    }   
}
