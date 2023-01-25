package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Klasa DifficultyController odpowiada za wybór trudności gry który jest potem przekazywany do GameController
 */
public class DifficultyController {
    private static String difficulty;

    private void switchToShipPosition() throws IOException{
        App.setRoot("ship-position-view");
    }
    @FXML
    private void backDifficulty() throws IOException{
        App.setRoot("login-view");
    }
    @FXML
    private void easy() throws IOException {
        difficulty="easy";
        switchToShipPosition();
    }
    @FXML
    private void normal() throws IOException {
        difficulty="normal";
        switchToShipPosition();
    }
    @FXML
    private void hard() throws IOException {
        difficulty="hard";
        switchToShipPosition();
    }
    public static String getDifficulty(){
        return difficulty;
    }
}