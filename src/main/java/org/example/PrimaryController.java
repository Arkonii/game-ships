package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Klasa PrimaryController to pierwszy fxml który jest pokazywany podczas działania aplikacji ,
 * jest ekranem "powitalnym".
 */
public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("login-view");
    }
}
