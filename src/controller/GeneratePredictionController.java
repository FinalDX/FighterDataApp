package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Fight;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.Logistic;

/**
 * FXML Controller class
 *
 * @author Sean Fenner
 */
public class GeneratePredictionController implements Initializable {
    // Fight object 
    private Fight predictedFight;
    // Labels
    @FXML Label redFighterName;
    @FXML Label blueFighterName;
    @FXML Label redOutcomeLabel;
    @FXML Label blueOutcomeLabel;
    @FXML Label confusionMatrix;
    @FXML Label predictionModel;
    
    // =========================================================================
    // MEHTODS
    // =========================================================================
    
    public void sendFight(Fight sentFight) {
        predictedFight = sentFight;
        redFighterName.setText(predictedFight.getRed_fighter());
        blueFighterName.setText(predictedFight.getBlue_fighter());
        try {
            createTestARFF(predictedFight);
            System.out.println("Test.arff created");
            System.out.println(predictedFight.toString());
            runTest();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // -------------------------------------------------------------------------
    
    @FXML public void backButtonPressed(ActionEvent event) throws IOException {
        Parent changeParent = FXMLLoader.load(getClass().getResource("/view/DashboardScreen.fxml"));
        Scene changeScene = new Scene(changeParent);
        changeScene.getStylesheets().add(getClass().getResource("/files/chartStyle.css").toExternalForm());

        Stage stage;
        stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.setScene(changeScene);
        stage.show();       
    } 
    // -------------------------------------------------------------------------
    
    public void runTest() throws Exception {
      // Load dataset
      DataSource source = new DataSource("src/files/FightDataForLogisticRegression.arff");
      Instances dataset = source.getDataSet();
      // Set class index to first attribute which is nomial
      dataset.setClassIndex(0);
      // Create and build classifier
      Logistic logReg = new Logistic();
      logReg.buildClassifier(dataset);
      // Test dataset for evaluation
      DataSource testSource = new DataSource("src/files/Test.arff");
      Instances testDataset = testSource.getDataSet();
      testDataset.setClassIndex(0);
      Evaluation eval = new Evaluation(dataset);
      Random rand = new Random(1);
      int folds = 10;
      eval.crossValidateModel(logReg, dataset, folds, rand);
      System.out.println(eval.toSummaryString("Results:\n", false));
      predictionModel.setText(eval.toSummaryString("Results:\n", false));
      // Loop through test data
      for (int i = 0; i < testDataset.numInstances(); i++) {
          Instance newInst = testDataset.instance(i);
          double prediction = logReg.classifyInstance(newInst);
          System.out.println(prediction);
          if (prediction == 0.0) {
              redOutcomeLabel.setText("Win");
              blueOutcomeLabel.setText("Lose");
          }
          else if (prediction == 1.0) {
              redOutcomeLabel.setText("Lose");
              blueOutcomeLabel.setText("Win");
          }
      }
      confusionMatrix.setText(eval.toMatrixString("=== Overall Confusion Matrix ===\n"));
    }
    // -------------------------------------------------------------------------
    
    private void createTestARFF (Fight fight) throws IOException {
        FileWriter csvWriter = new FileWriter("src/files/Test.arff", false);
        csvWriter.append("@relation Test \n \n"
                + "@attribute Winner {Red, Blue, Draw}\n"
                + "@attribute B_avg_BODY_landed numeric\n"
                + "@attribute B_avg_HEAD_landed numeric\n"
                + "@attribute B_avg_KD numeric\n"
                + "@attribute B_avg_LEG_landed numeric\n"
                + "@attribute B_avg_PASS numeric\n"
                + "@attribute B_avg_REV numeric\n"
                + "@attribute B_avg_SUB_ATT numeric\n"
                + "@attribute B_avg_TD_landed numeric\n"
                + "@attribute B_losses numeric\n"
                + "@attribute B_total_title_bouts numeric\n"
                + "@attribute B_win_by_Decision_Majority numeric\n"
                + "@attribute B_win_by_Decision_Split numeric\n"
                + "@attribute B_win_by_Decision_Unanimous numeric\n"
                + "@attribute B_win_by_KO/TKO numeric\n"
                + "@attribute B_win_by_Submission numeric\n"
                + "@attribute B_win_by_TKO_Doctor_Stoppage numeric\n"
                + "@attribute B_Reach_cms numeric\n"
                + "@attribute R_avg_BODY_landed numeric\n"
                + "@attribute R_avg_HEAD_landed numeric\n"
                + "@attribute R_avg_KD numeric\n"
                + "@attribute R_avg_LEG_landed numeric\n"
                + "@attribute R_avg_PASS numeric\n"
                + "@attribute R_avg_REV numeric\n"
                + "@attribute R_avg_SUB_ATT numeric\n"
                + "@attribute R_avg_TD_landed numeric\n"
                + "@attribute R_losses numeric\n"
                + "@attribute R_total_title_bouts numeric\n"
                + "@attribute R_win_by_Decision_Majority numeric\n"
                + "@attribute R_win_by_Decision_Split numeric\n"
                + "@attribute R_win_by_Decision_Unanimous numeric\n"
                + "@attribute R_win_by_KO/TKO numeric\n"
                + "@attribute R_win_by_Submission numeric\n"
                + "@attribute R_win_by_TKO_Doctor_Stoppage numeric\n"
                + "@attribute R_Reach_cms numeric\n"
                + "@attribute B_age numeric\n"
                + "@attribute R_age numeric\n\n");
        csvWriter.append("@data\n"
                + "?," +
            fight.getB_avg_body_landed() + "," +
            fight.getB_avg_head_landed()+ "," +
            fight.getB_avg_kd()+ "," +
            fight.getB_avg_leg_landed()+ "," +
            fight.getB_avg_pass()+ "," +
            fight.getB_avg_rev()+ "," +
            fight.getB_avg_sub_att()+ "," +
            fight.getB_avg_td_landed()+ "," +
            fight.getB_losses()+ "," +
            fight.getB_total_title_bouts()+ "," +
            fight.getB_win_by_decision_majority()+ "," +
            fight.getB_win_by_decision_split()+ "," +
            fight.getB_win_by_decision_unanimous()+ "," +
            fight.getB_win_by_ko_tko()+ "," +
            fight.getB_win_by_submisision()+ "," +
            fight.getB_win_by_doc_stop()+ "," +
            fight.getB_reach_cm()+ "," +
            fight.getR_avg_body_landed() + "," +
            fight.getR_avg_head_landed()+ "," +
            fight.getR_avg_kd()+ "," +
            fight.getR_avg_leg_landed()+ "," +
            fight.getR_avg_pass()+ "," +
            fight.getR_avg_rev()+ "," +
            fight.getR_avg_sub_att()+ "," +
            fight.getR_avg_td_landed()+ "," +
            fight.getR_losses()+ "," +
            fight.getR_total_title_bouts()+ "," +
            fight.getR_win_by_decision_majority()+ "," +
            fight.getR_win_by_decision_split()+ "," +
            fight.getR_win_by_decision_unanimous()+ "," +
            fight.getR_win_by_ko_tko()+ "," +
            fight.getR_win_by_submisision()+ "," +
            fight.getR_win_by_doc_stop()+ "," +
            fight.getR_reach_cm()+ "," +
            fight.getB_age()+ "," +
            fight.getR_age() + "\n");
        csvWriter.flush();
        csvWriter.close();
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
        
    }    
    
}
