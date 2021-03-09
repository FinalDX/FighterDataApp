
package model;

import java.util.Date;

/**
 *
 * @author Sean Fenner
 */
public class Fight {

    private String red_fighter;
    private String blue_fighter;
    private String date;
    private String winner;
    private String title_bout;
    private String weight_class;
    private String num_of_rounds;
    private String b_avg_body_landed;
    private String b_avg_head_landed;
    private String b_avg_kd;
    private String b_avg_leg_landed;
    private String b_avg_pass;
    private String b_avg_rev;
    private String b_avg_sub_att;
    private String b_avg_td_landed;
    private String b_losses;
    private String b_total_title_bouts;
    private String b_win_by_decision_majority;
    private String b_win_by_decision_split;
    private String b_win_by_decision_unanimous;
    private String b_win_by_ko_tko;
    private String b_win_by_submisision;
    private String b_win_by_doc_stop;
    private String b_stance;
    private String b_height_cm;
    private String b_reach_cm;
    private String r_avg_body_landed;
    private String r_avg_head_landed;
    private String r_avg_kd;
    private String r_avg_leg_landed;
    private String r_avg_pass;
    private String r_avg_rev;
    private String r_avg_sub_att;
    private String r_avg_td_landed;
    private String r_losses;
    private String r_total_title_bouts;
    private String r_win_by_decision_majority;
    private String r_win_by_decision_split;
    private String r_win_by_decision_unanimous;
    private String r_win_by_ko_tko;
    private String r_win_by_submisision;
    private String r_win_by_doc_stop;
    private String r_stance;
    private String r_height_cm;
    private String r_reach_cm;
    private String b_age;
    private String r_age;

    @Override
    public String toString() {
        return "Fight{" + "red_fighter=" + red_fighter + ", blue_fighter=" + blue_fighter + ", date=" + date + ", winner=" + winner + ", title_bout=" + title_bout + ", weight_class=" + weight_class + ", num_of_rounds=" + num_of_rounds + ", b_avg_body_landed=" + b_avg_body_landed + ", b_avg_head_landed=" + b_avg_head_landed + ", b_avg_kd=" + b_avg_kd + ", b_avg_leg_landed=" + b_avg_leg_landed + ", b_avg_pass=" + b_avg_pass + ", b_avg_rev=" + b_avg_rev + ", b_avg_sub_att=" + b_avg_sub_att + ", b_avg_td_landed=" + b_avg_td_landed + ", b_losses=" + b_losses + ", b_total_title_bouts=" + b_total_title_bouts + ", b_win_by_decision_majority=" + b_win_by_decision_majority + ", b_win_by_decision_split=" + b_win_by_decision_split + ", b_win_by_decision_unanimous=" + b_win_by_decision_unanimous + ", b_win_by_ko_tko=" + b_win_by_ko_tko + ", b_win_by_submisision=" + b_win_by_submisision + ", b_win_by_doc_stop=" + b_win_by_doc_stop + ", b_stance=" + b_stance + ", b_height_cm=" + b_height_cm + ", b_reach_cm=" + b_reach_cm + ", r_avg_body_landed=" + r_avg_body_landed + ", r_avg_head_landed=" + r_avg_head_landed + ", r_avg_kd=" + r_avg_kd + ", r_avg_leg_landed=" + r_avg_leg_landed + ", r_avg_pass=" + r_avg_pass + ", r_avg_rev=" + r_avg_rev + ", r_avg_sub_att=" + r_avg_sub_att + ", r_avg_td_landed=" + r_avg_td_landed + ", r_losses=" + r_losses + ", r_total_title_bouts=" + r_total_title_bouts + ", r_win_by_decision_majority=" + r_win_by_decision_majority + ", r_win_by_decision_split=" + r_win_by_decision_split + ", r_win_by_decision_unanimous=" + r_win_by_decision_unanimous + ", r_win_by_ko_tko=" + r_win_by_ko_tko + ", r_win_by_submisision=" + r_win_by_submisision + ", r_win_by_doc_stop=" + r_win_by_doc_stop + ", r_stance=" + r_stance + ", r_height_cm=" + r_height_cm + ", r_reach_cm=" + r_reach_cm + ", b_age=" + b_age + ", r_age=" + r_age + '}';
    }
    

    public Fight(String red_fighter, String blue_fighter, String date, String winner, String title_bout, String weight_class, String num_of_rounds, String b_avg_body_landed, String b_avg_head_landed, String b_avg_kd, String b_avg_leg_landed, String b_avg_pass, String b_avg_rev, String b_avg_sub_att, String b_avg_td_landed, String b_losses, String b_total_title_bouts, String b_win_by_decision_majority, String b_win_by_decision_split, String b_win_by_decision_unanimous, String b_win_by_ko_tko, String b_win_by_submisision, String b_win_by_doc_stop, String b_stance, String b_height_cm, String b_reach_cm, String r_avg_body_landed, String r_avg_head_landed, String r_avg_kd, String r_avg_leg_landed, String r_avg_pass, String r_avg_rev, String r_avg_sub_att, String r_avg_td_landed, String r_losses, String r_total_title_bouts, String r_win_by_decision_majority, String r_win_by_decision_split, String r_win_by_decision_unanimous, String r_win_by_ko_tko, String r_win_by_submisision, String r_win_by_doc_stop, String r_stance, String r_height_cm, String r_reach_cm, String b_age, String r_age) {
        this.red_fighter = red_fighter;
        this.blue_fighter = blue_fighter;
        this.date = date;
        this.winner = winner;
        this.title_bout = title_bout;
        this.weight_class = weight_class;
        this.num_of_rounds = num_of_rounds;
        this.b_avg_body_landed = b_avg_body_landed;
        this.b_avg_head_landed = b_avg_head_landed;
        this.b_avg_kd = b_avg_kd;
        this.b_avg_leg_landed = b_avg_leg_landed;
        this.b_avg_pass = b_avg_pass;
        this.b_avg_rev = b_avg_rev;
        this.b_avg_sub_att = b_avg_sub_att;
        this.b_avg_td_landed = b_avg_td_landed;
        this.b_losses = b_losses;
        this.b_total_title_bouts = b_total_title_bouts;
        this.b_win_by_decision_majority = b_win_by_decision_majority;
        this.b_win_by_decision_split = b_win_by_decision_split;
        this.b_win_by_decision_unanimous = b_win_by_decision_unanimous;
        this.b_win_by_ko_tko = b_win_by_ko_tko;
        this.b_win_by_submisision = b_win_by_submisision;
        this.b_win_by_doc_stop = b_win_by_doc_stop;
        this.b_stance = b_stance;
        this.b_height_cm = b_height_cm;
        this.b_reach_cm = b_reach_cm;
        this.r_avg_body_landed = r_avg_body_landed;
        this.r_avg_head_landed = r_avg_head_landed;
        this.r_avg_kd = r_avg_kd;
        this.r_avg_leg_landed = r_avg_leg_landed;
        this.r_avg_pass = r_avg_pass;
        this.r_avg_rev = r_avg_rev;
        this.r_avg_sub_att = r_avg_sub_att;
        this.r_avg_td_landed = r_avg_td_landed;
        this.r_losses = r_losses;
        this.r_total_title_bouts = r_total_title_bouts;
        this.r_win_by_decision_majority = r_win_by_decision_majority;
        this.r_win_by_decision_split = r_win_by_decision_split;
        this.r_win_by_decision_unanimous = r_win_by_decision_unanimous;
        this.r_win_by_ko_tko = r_win_by_ko_tko;
        this.r_win_by_submisision = r_win_by_submisision;
        this.r_win_by_doc_stop = r_win_by_doc_stop;
        this.r_stance = r_stance;
        this.r_height_cm = r_height_cm;
        this.r_reach_cm = r_reach_cm;
        this.b_age = b_age;
        this.r_age = r_age;
    }

    public String getRed_fighter() {
        return red_fighter;
    }

    public void setRed_fighter(String red_fighter) {
        this.red_fighter = red_fighter;
    }

    public String getBlue_fighter() {
        return blue_fighter;
    }

    public void setBlue_fighter(String blue_fighter) {
        this.blue_fighter = blue_fighter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getTitle_bout() {
        return title_bout;
    }

    public void setTitle_bout(String title_bout) {
        this.title_bout = title_bout;
    }

    public String getWeight_class() {
        return weight_class;
    }

    public void setWeight_class(String weight_class) {
        this.weight_class = weight_class;
    }

    public String getNum_of_rounds() {
        return num_of_rounds;
    }

    
    public void setNum_of_rounds(String num_of_rounds) {
        this.num_of_rounds = num_of_rounds;
    }

    public String getB_avg_body_landed() {
        return b_avg_body_landed;
    }

    public void setB_avg_body_landed(String b_avg_body_landed) {
        this.b_avg_body_landed = b_avg_body_landed;
    }

    public String getB_avg_head_landed() {
        return b_avg_head_landed;
    }

    public void setB_avg_head_landed(String b_avg_head_landed) {
        this.b_avg_head_landed = b_avg_head_landed;
    }

    public String getB_avg_kd() {
        return b_avg_kd;
    }

    public void setB_avg_kd(String b_avg_kd) {
        this.b_avg_kd = b_avg_kd;
    }

    public String getB_avg_leg_landed() {
        return b_avg_leg_landed;
    }

    public void setB_avg_leg_landed(String b_avg_leg_landed) {
        this.b_avg_leg_landed = b_avg_leg_landed;
    }

    public String getB_avg_pass() {
        return b_avg_pass;
    }

    public void setB_avg_pass(String b_avg_pass) {
        this.b_avg_pass = b_avg_pass;
    }

    public String getB_avg_rev() {
        return b_avg_rev;
    }

    public void setB_avg_rev(String b_avg_rev) {
        this.b_avg_rev = b_avg_rev;
    }

    public String getB_avg_sub_att() {
        return b_avg_sub_att;
    }

    public void setB_avg_sub_att(String b_avg_sub_att) {
        this.b_avg_sub_att = b_avg_sub_att;
    }

    public String getB_avg_td_landed() {
        return b_avg_td_landed;
    }

    public void setB_avg_td_landed(String b_avg_td_landed) {
        this.b_avg_td_landed = b_avg_td_landed;
    }

    public String getB_losses() {
        return b_losses;
    }

    public void setB_losses(String b_losses) {
        this.b_losses = b_losses;
    }

    public String getB_total_title_bouts() {
        return b_total_title_bouts;
    }

    public void setB_total_title_bouts(String b_total_title_bouts) {
        this.b_total_title_bouts = b_total_title_bouts;
    }

    public String getB_win_by_decision_majority() {
        return b_win_by_decision_majority;
    }

    public void setB_win_by_decision_majority(String b_win_by_decision_majority) {
        this.b_win_by_decision_majority = b_win_by_decision_majority;
    }

    public String getB_win_by_decision_split() {
        return b_win_by_decision_split;
    }

    public void setB_win_by_decision_split(String b_win_by_decision_split) {
        this.b_win_by_decision_split = b_win_by_decision_split;
    }

    public String getB_win_by_decision_unanimous() {
        return b_win_by_decision_unanimous;
    }

    public void setB_win_by_decision_unanimous(String b_win_by_decision_unanimous) {
        this.b_win_by_decision_unanimous = b_win_by_decision_unanimous;
    }

    public String getB_win_by_ko_tko() {
        return b_win_by_ko_tko;
    }

    public void setB_win_by_ko_tko(String b_win_by_ko_tko) {
        this.b_win_by_ko_tko = b_win_by_ko_tko;
    }

    public String getB_win_by_submisision() {
        return b_win_by_submisision;
    }

    public void setB_win_by_submisision(String b_win_by_submisision) {
        this.b_win_by_submisision = b_win_by_submisision;
    }

    public String getB_win_by_doc_stop() {
        return b_win_by_doc_stop;
    }

    public void setB_win_by_doc_stop(String b_win_by_doc_stop) {
        this.b_win_by_doc_stop = b_win_by_doc_stop;
    }

    public String getB_stance() {
        return b_stance;
    }

    public void setB_stance(String b_stance) {
        this.b_stance = b_stance;
    }

    public String getB_height_cm() {
        return b_height_cm;
    }

    public void setB_height_cm(String b_height_cm) {
        this.b_height_cm = b_height_cm;
    }

    public String getB_reach_cm() {
        return b_reach_cm;
    }

    public void setB_reach_cm(String b_reach_cm) {
        this.b_reach_cm = b_reach_cm;
    }

    public String getR_avg_body_landed() {
        return r_avg_body_landed;
    }

    public void setR_avg_body_landed(String r_avg_body_landed) {
        this.r_avg_body_landed = r_avg_body_landed;
    }

    public String getR_avg_head_landed() {
        return r_avg_head_landed;
    }

    public void setR_avg_head_landed(String r_avg_head_landed) {
        this.r_avg_head_landed = r_avg_head_landed;
    }

    public String getR_avg_kd() {
        return r_avg_kd;
    }

    public void setR_avg_kd(String r_avg_kd) {
        this.r_avg_kd = r_avg_kd;
    }

    public String getR_avg_leg_landed() {
        return r_avg_leg_landed;
    }

    public void setR_avg_leg_landed(String r_avg_leg_landed) {
        this.r_avg_leg_landed = r_avg_leg_landed;
    }

    public String getR_avg_pass() {
        return r_avg_pass;
    }

    public void setR_avg_pass(String r_avg_pass) {
        this.r_avg_pass = r_avg_pass;
    }

    public String getR_avg_rev() {
        return r_avg_rev;
    }

    public void setR_avg_rev(String r_avg_rev) {
        this.r_avg_rev = r_avg_rev;
    }

    public String getR_avg_sub_att() {
        return r_avg_sub_att;
    }

    public void setR_avg_sub_att(String r_avg_sub_att) {
        this.r_avg_sub_att = r_avg_sub_att;
    }

    public String getR_avg_td_landed() {
        return r_avg_td_landed;
    }

    public void setR_avg_td_landed(String r_avg_td_landed) {
        this.r_avg_td_landed = r_avg_td_landed;
    }

    public String getR_losses() {
        return r_losses;
    }

    public void setR_losses(String r_losses) {
        this.r_losses = r_losses;
    }

    public String getR_total_title_bouts() {
        return r_total_title_bouts;
    }

    public void setR_total_title_bouts(String r_total_title_bouts) {
        this.r_total_title_bouts = r_total_title_bouts;
    }

    public String getR_win_by_decision_majority() {
        return r_win_by_decision_majority;
    }

    public void setR_win_by_decision_majority(String r_win_by_decision_majority) {
        this.r_win_by_decision_majority = r_win_by_decision_majority;
    }

    public String getR_win_by_decision_split() {
        return r_win_by_decision_split;
    }

    public void setR_win_by_decision_split(String r_win_by_decision_split) {
        this.r_win_by_decision_split = r_win_by_decision_split;
    }

    public String getR_win_by_decision_unanimous() {
        return r_win_by_decision_unanimous;
    }

    public void setR_win_by_decision_unanimous(String r_win_by_decision_unanimous) {
        this.r_win_by_decision_unanimous = r_win_by_decision_unanimous;
    }

    public String getR_win_by_ko_tko() {
        return r_win_by_ko_tko;
    }

    public void setR_win_by_ko_tko(String r_win_by_ko_tko) {
        this.r_win_by_ko_tko = r_win_by_ko_tko;
    }

    public String getR_win_by_submisision() {
        return r_win_by_submisision;
    }

    public void setR_win_by_submisision(String r_win_by_submisision) {
        this.r_win_by_submisision = r_win_by_submisision;
    }

    public String getR_win_by_doc_stop() {
        return r_win_by_doc_stop;
    }

    public void setR_win_by_doc_stop(String r_win_by_doc_stop) {
        this.r_win_by_doc_stop = r_win_by_doc_stop;
    }

    public String getR_stance() {
        return r_stance;
    }

    public void setR_stance(String r_stance) {
        this.r_stance = r_stance;
    }

    public String getR_height_cm() {
        return r_height_cm;
    }

    public void setR_height_cm(String r_height_cm) {
        this.r_height_cm = r_height_cm;
    }

    public String getR_reach_cm() {
        return r_reach_cm;
    }

    public void setR_reach_cm(String r_reach_cm) {
        this.r_reach_cm = r_reach_cm;
    }

    public String getB_age() {
        return b_age;
    }

    public void setB_age(String b_age) {
        this.b_age = b_age;
    }

    public String getR_age() {
        return r_age;
    }

    public void setR_age(String r_age) {
        this.r_age = r_age;
    }

    
}
