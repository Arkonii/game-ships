package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa App , odpowiada za ładowanie poszczególnych fxml (ekranów) podczas działania aplikacji .
 * Jest również klasą startową.
 */
public class App extends Application {
    /**
     * Prywatna zmienna która przetrzymuje aktualnie używany fxml
     */
    public static Scene scene;

    /**
     * Załadowanie pierwszego ekranu fxml
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 770, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Wskazanie na klase która ma się wykonywać podczas działania aplikacji przy poszczególnym fxml
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Wskazanie fxml , na który chcemy zmienić ekran aplikacji
     * @param fxml
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    

}