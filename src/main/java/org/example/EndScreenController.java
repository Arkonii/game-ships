package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.example.LoginController.getEmail;
import static org.example.SendEmailSSL.sendEmail;

/**
 * Klasa EndScreenController odpowiada za ekran końcowy aplikacji , pozwala na zagranie ponownie lub zamknięcie jej
 */
public class EndScreenController {
    private String winner=GameController.getWinner();
    public Label label1,label2;
    public Button play_again,exit;
    public void initialize(){
        if(winner == "AI"){
            label1.setText("");
            label2.setText("Przegrałeś!");
        }
        if (getEmail()!=""){
            sendEmail(false);
        }
        if (getEmail()!=""){
            sendEmail(true);
        }
    }
    @FXML
    private void switchToDifficulty() throws IOException{
        App.setRoot("difficulty-view");
    }
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }


}