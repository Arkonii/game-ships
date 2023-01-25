package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Klasa LoginController odpowiada ze wpisywanie emaila
 */
public class LoginController {

    private static String email="";

    @FXML
    TextField emailField;

    @FXML
    private void backLogin() throws IOException{
        App.setRoot("primary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        //App.setRoot("primary");
    }
    @FXML
    private void switchToDifficulty() throws IOException{
        email = emailField.getText();
        App.setRoot("difficulty-view");
    }

    public static String getEmail() {
        return email;
    }
}