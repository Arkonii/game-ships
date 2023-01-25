package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import static org.example.LoginController.getEmail;
/**
 * Klasa GameController odpowiada za rozgrywke w aplikacji , gracz vs AI
 */
public class GameController  {
    /**
     * @param ground plansza gracza którą utworzyłw ShipPositionController
     * @param difficulty poziom trudności jaki gracz wybrał w DifficultyController
     * @param groundAI plansza AI
     * @param progress_Player liczba 0.0 do 1.0 , jest wprowadzana do progressbar
     * @param progress_AI liczba 0.0 do 1.0 , jest wprowadzana do progressbar
     * @param AI_difficulty jest to "szansa" jaką AI ma na trafienie , jest ono zależne od wyboru trudności
     * @param HP_player liczba naturalna , oznacza ilość pól jaka została graczowi z statkami
     * @param HP_AI liczba naturalna , oznacza ilość pól jaka została AI z statkami
     */
    private GameBoard ground = ShipPositionController.getGround();
    private String difficulty=DifficultyController.getDifficulty();
    public GameBoard groundAI;
    private double progress_Player;
    private double progress_AI;
    private int AI_difficulty;
    private int HP_player;
    private int HP_AI;
    public static String winner;
    /**
     * Deklaracja przycisków
     */
    public Button A11,A21,A31,A41,A51,A61,A71,A81,A91,A101,B11,B21,B31,B41,B51,B61,B71,B81,B91,B101,C11,C21,C31,C41,C51,C61,C71,C81,C91,C101,D11,D21,D31,D41,D51,D61,D71,D81,D91,D101, E11,E21,E31,E41,E51,E61,E71,E81,E91,E101,F11,F21,F31,F41,F51,F61,F71,F81,F91,F101,G11,G21,G31,G41,G51,G61,G71,G81,G91,G101,H11,H21,H31,H41,H51,H61,H71,H81,H91,H101,I11,I21,I31,I41,I51,I61,I71,I81,I91,I101,J11,J21,J31,J41,J51,J61,J71,J81,J91,J101;
    public Button A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,D1,D2,D3,D4,D5,D6,D7,D8,D9,D10, E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,H1,H2,H3,H4,H5,H6,H7,H8,H9,H10,I1,I2,I3,I4,I5,I6,I7,I8,I9,I10,J1,J2,J3,J4,J5,J6,J7,J8,J9,J10;
    /**
     * Deklaracja etykiet
     */
    public Label label1;
    /**
     * Deklaracja pasków postępu
     */
    @FXML
    public ProgressBar progressBar_Player;
    @FXML
    public ProgressBar progressBar_AI;

    /**
     * Funkcja initalize odpowiada za ustawienia początkowe rozgrywki , nadaje przyciskom możliwośc pobierania ID , jesteśmy wtedy w stanie wiedzieć w jakie pole na planszy AI wybrał gracz
     * losuje ustawienie statków AI
     * ustawia "szanse" na trafienie AI w pole gracza
     *
     */
    public void initialize(){
        //System.out.println("initialize!");
        A11.setOnAction(event -> game((Button) event.getSource()));
        A21.setOnAction(event -> game((Button) event.getSource()));
        A31.setOnAction(event -> game((Button) event.getSource()));
        A41.setOnAction(event -> game((Button) event.getSource()));
        A51.setOnAction(event -> game((Button) event.getSource()));
        A61.setOnAction(event -> game((Button) event.getSource()));
        A71.setOnAction(event -> game((Button) event.getSource()));
        A81.setOnAction(event -> game((Button) event.getSource()));
        A91.setOnAction(event -> game((Button) event.getSource()));
        A101.setOnAction(event -> game((Button) event.getSource()));

        B11.setOnAction(event -> game((Button) event.getSource()));
        B21.setOnAction(event -> game((Button) event.getSource()));
        B31.setOnAction(event -> game((Button) event.getSource()));
        B41.setOnAction(event -> game((Button) event.getSource()));
        B51.setOnAction(event -> game((Button) event.getSource()));
        B61.setOnAction(event -> game((Button) event.getSource()));
        B71.setOnAction(event -> game((Button) event.getSource()));
        B81.setOnAction(event -> game((Button) event.getSource()));
        B91.setOnAction(event -> game((Button) event.getSource()));
        B101.setOnAction(event -> game((Button) event.getSource()));

        C11.setOnAction(event -> game((Button) event.getSource()));
        C21.setOnAction(event -> game((Button) event.getSource()));
        C31.setOnAction(event -> game((Button) event.getSource()));
        C41.setOnAction(event -> game((Button) event.getSource()));
        C51.setOnAction(event -> game((Button) event.getSource()));
        C61.setOnAction(event -> game((Button) event.getSource()));
        C71.setOnAction(event -> game((Button) event.getSource()));
        C81.setOnAction(event -> game((Button) event.getSource()));
        C91.setOnAction(event -> game((Button) event.getSource()));
        C101.setOnAction(event -> game((Button) event.getSource()));

        D11.setOnAction(event -> game((Button) event.getSource()));
        D21.setOnAction(event -> game((Button) event.getSource()));
        D31.setOnAction(event -> game((Button) event.getSource()));
        D41.setOnAction(event -> game((Button) event.getSource()));
        D51.setOnAction(event -> game((Button) event.getSource()));
        D61.setOnAction(event -> game((Button) event.getSource()));
        D71.setOnAction(event -> game((Button) event.getSource()));
        D81.setOnAction(event -> game((Button) event.getSource()));
        D91.setOnAction(event -> game((Button) event.getSource()));
        D101.setOnAction(event -> game((Button) event.getSource()));

        E11.setOnAction(event -> game((Button) event.getSource()));
        E21.setOnAction(event -> game((Button) event.getSource()));
        E31.setOnAction(event -> game((Button) event.getSource()));
        E41.setOnAction(event -> game((Button) event.getSource()));
        E51.setOnAction(event -> game((Button) event.getSource()));
        E61.setOnAction(event -> game((Button) event.getSource()));
        E71.setOnAction(event -> game((Button) event.getSource()));
        E81.setOnAction(event -> game((Button) event.getSource()));
        E91.setOnAction(event -> game((Button) event.getSource()));
        E101.setOnAction(event -> game((Button) event.getSource()));

        F11.setOnAction(event -> game((Button) event.getSource()));
        F21.setOnAction(event -> game((Button) event.getSource()));
        F31.setOnAction(event -> game((Button) event.getSource()));
        F41.setOnAction(event -> game((Button) event.getSource()));
        F51.setOnAction(event -> game((Button) event.getSource()));
        F61.setOnAction(event -> game((Button) event.getSource()));
        F71.setOnAction(event -> game((Button) event.getSource()));
        F81.setOnAction(event -> game((Button) event.getSource()));
        F91.setOnAction(event -> game((Button) event.getSource()));
        F101.setOnAction(event -> game((Button) event.getSource()));

        G11.setOnAction(event -> game((Button) event.getSource()));
        G21.setOnAction(event -> game((Button) event.getSource()));
        G31.setOnAction(event -> game((Button) event.getSource()));
        G41.setOnAction(event -> game((Button) event.getSource()));
        G51.setOnAction(event -> game((Button) event.getSource()));
        G61.setOnAction(event -> game((Button) event.getSource()));
        G71.setOnAction(event -> game((Button) event.getSource()));
        G81.setOnAction(event -> game((Button) event.getSource()));
        G91.setOnAction(event -> game((Button) event.getSource()));
        G101.setOnAction(event -> game((Button) event.getSource()));

        H11.setOnAction(event -> game((Button) event.getSource()));
        H21.setOnAction(event -> game((Button) event.getSource()));
        H31.setOnAction(event -> game((Button) event.getSource()));
        H41.setOnAction(event -> game((Button) event.getSource()));
        H51.setOnAction(event -> game((Button) event.getSource()));
        H61.setOnAction(event -> game((Button) event.getSource()));
        H71.setOnAction(event -> game((Button) event.getSource()));
        H81.setOnAction(event -> game((Button) event.getSource()));
        H91.setOnAction(event -> game((Button) event.getSource()));
        H101.setOnAction(event -> game((Button) event.getSource()));

        I11.setOnAction(event -> game((Button) event.getSource()));
        I21.setOnAction(event -> game((Button) event.getSource()));
        I31.setOnAction(event -> game((Button) event.getSource()));
        I41.setOnAction(event -> game((Button) event.getSource()));
        I51.setOnAction(event -> game((Button) event.getSource()));
        I61.setOnAction(event -> game((Button) event.getSource()));
        I71.setOnAction(event -> game((Button) event.getSource()));
        I81.setOnAction(event -> game((Button) event.getSource()));
        I91.setOnAction(event -> game((Button) event.getSource()));
        I101.setOnAction(event -> game((Button) event.getSource()));

        J11.setOnAction(event -> game((Button) event.getSource()));
        J21.setOnAction(event -> game((Button) event.getSource()));
        J31.setOnAction(event -> game((Button) event.getSource()));
        J41.setOnAction(event -> game((Button) event.getSource()));
        J51.setOnAction(event -> game((Button) event.getSource()));
        J61.setOnAction(event -> game((Button) event.getSource()));
        J71.setOnAction(event -> game((Button) event.getSource()));
        J81.setOnAction(event -> game((Button) event.getSource()));
        J91.setOnAction(event -> game((Button) event.getSource()));
        J101.setOnAction(event -> game((Button) event.getSource()));

        int min=1;
        int max=4;
        int rand_position_AI = (int)Math.floor(Math.random()*(max-min+1)+min);
        groundAI = new GameBoard(rand_position_AI);
        switch(difficulty){
            case "easy":
                AI_difficulty=55;
                break;
            case "normal":
                AI_difficulty=65;
                break;
            case "hard":
                AI_difficulty=90;
                break;
        }
        HP_player=10;
        HP_AI=10;
        label1.setText("Na planszy po prawej stronie \npojawiło się pole przeciwnika\n " +
                "klikając w nie spróbuj znaleźć\n i zatopić jego statki.\n " +
                "Przeciwnik będzie próbował\n zatopić twoje statki,\n BĄDŹ LEPSZY I WYGRAJ.\n" +
                "W tym miejscu będą pojawiać się\n komunikaty czy trafiłeś w statek\n przeciwnika czy spudłowałeś.\n" +
                "Po zakończeniu gry dostaniesz\n komunikat o wyniku gry,\n który zostanie przesłany\n na e-mail. POWODZENIA!");
        progress_AI=1.0;
        progress_Player=1.0;
    }

    /**
     * Funkcja game odpowiada za działanie rozgrywki w czasie kliknięcie pola AI przez gracza ,
     * sprawdza czy na polu znajdue się statek , wyświetla odpowiedni komunikat i daje kolejny ruch graczowi , bądź AI jeśli nie trafił
     * Ruch AI zawsze następuje po ruchu gracza , AI losuje "szanse" na trafienie i powtarza to dopóki trafia , jeśli nie trafi , ruch otrzymuje z powrotem gracz
     * każde uderzone pole zostaje naznaczone odpowiednim kolorem
     * jeśli HP_AI spadło do 0 wygrywa gracz , jeśli HP_player spadło do 0 wygrywa AI
     * @param source id przycisku który został kliknięty
     */
    private void game(Button source) {
        boolean playermove = true;
        if (playermove) {
            String temp = source.getId();
            switch (groundAI.ground_tab[groundAI.buttonAssignment(temp)]) {
                case 2:
                    label1.setText("Nie mozna wybierac tego\n samego pola 2 razy");
                    break;
                case 1:
                    source.setStyle("-fx-background-color: red;");
                    label1.setText("Trafienie!");
                    groundAI.ground_tab[groundAI.buttonAssignment(temp)] = 2;
                    HP_AI--;
                    progress_AI=progress_AI-0.1;
                    progressBar_AI.setProgress(progress_AI);
                    break;
                case 0:
                    source.setStyle("-fx-background-color: black;");
                    label1.setText("Pudło!");
                    groundAI.ground_tab[groundAI.buttonAssignment(temp)] = 2;
                    playermove = false;
                    break;
            }

        }
        if(HP_AI<=0){
            finish("Player");
        }
        while (playermove == false&&HP_player>0) {
            int min = 1;
            int max = 100;
            int AI_move = (int) Math.floor(Math.random() * (AI_difficulty - min + 1) + min);
            if (AI_move >= 50||glitchCatcher()) {
                HP_player--;
                boolean flag_AI = true;
                while (flag_AI) {
                    int AI_shoot = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    if (ground.ground_tab[AI_shoot] == 1) {
                        buttonArray(AI_shoot).setStyle("-fx-background-color: red;");
                        ground.ground_tab[AI_shoot]=2;
                        progress_Player=progress_Player-0.1;
                        progressBar_Player.setProgress(progress_Player);
                        flag_AI=false;
                    }
                }
            } else {
                boolean flag_AI = true;
                while (flag_AI) {
                    int AI_shoot = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    if (ground.ground_tab[AI_shoot] == 0) {
                        buttonArray(AI_shoot).setStyle("-fx-background-color: black;");
                        ground.ground_tab[AI_shoot]=2;
                        flag_AI=false;
                    }
                }
                playermove = true;
            }
        }
        if(HP_player<=0){
            finish("AI");
        }

    }

    /**
     * Funkcja glitchCatcher odpowiada za sprawdzanie czy na planszy gracza zostało jakieś pole na którym nie ma statku (ponieważ inaczej AI zawsze "trafi")
     * @return
     */
    private boolean glitchCatcher() {
        for(int i=1;i<ground.ground_tab.length;i++){
            if(ground.ground_tab[i]==0){
                return false;
            }
        }
        return true;
    }


    /**
     * Funkcji buttonArray zadaniem jest zwrot przycisku który chcemy nadpisać (zmienić kolor) w funkcji game , wykorzystuje je AI
     * @param i położenie pola na planszy
     * @return
     */
    private Button buttonArray(int i) {
        switch(i){
            //case 0:return A1;
            case 1:return A1; case 11:return A2; case 21:return A3; case 31:return A4; case 41:return A5; case 51:return A6; case 61:return A7; case 71:return A8; case 81:return A9; case 91:return A10;

            case 2:return B1; case 12:return B2; case 22:return B3; case 32:return B4; case 42:return B5; case 52:return B6; case 62:return B7; case 72:return B8; case 82:return B9; case 92:return B10;

            case  3:return C1; case  13:return C2; case  23:return C3; case  33:return C4; case  43:return C5; case  53:return C6; case  63:return C7; case  73:return C8; case  83:return C9; case  93:return C10;

            case  4:return D1; case  14:return D2; case  24:return D3; case  34:return D4; case  44:return D5; case  54:return D6; case  64:return D7; case  74:return D8; case  84:return D9; case  94:return D10;

            case  5:return E1; case  15:return E2; case  25:return E3; case  35:return E4; case  45:return E5; case  55:return E6; case  65:return E7; case  75:return E8; case  85:return E9; case  95:return E10;

            case  6:return F1; case  16:return F2; case  26:return F3; case  36:return F4; case  46:return F5; case  56:return F6; case  66:return F7; case  76:return F8; case  86:return F9; case  96:return F10;

            case  7:return G1; case  17:return G2; case  27:return G3; case  37:return G4; case  47:return G5; case  57:return G6; case  67:return G7; case  77:return G8; case  87:return G9; case  97:return G10;

            case  8:return H1; case  18:return H2; case  28:return H3; case  38:return H4; case  48:return H5; case  58:return H6; case  68:return H7; case  78:return H8; case  88:return H9; case  98:return H10;

            case  9:return I1; case  19:return I2; case  29:return I3; case  39:return I4; case  49:return I5; case  59:return I6; case  69:return I7; case  79:return I8; case  89:return I9; case  99:return I10;

            case  10:return J1; case  20:return J2; case  30:return J3; case  40:return J4; case  50:return J5; case  60:return J6; case  70:return J7; case  80:return J8; case  90:return J9; case  100:return J10;
        }
        return null;
    }

    /**
     * Funkcja finish zapisuje zwycięzce
     * @param who_won
     */
    private void finish(String who_won){
        winner=who_won;
        try {
            switchToEndScreen();
        } catch (IOException e) {}
    }

    /**
     * Funkcja getWinner zwraca zwycięzce
     * @return
     */
    public static String getWinner() {
        return winner;
    }

    /**
     * Funkcja switchToEndScreen odpowiada za zmiane fxml na następny
     * @throws IOException
     */
    @FXML
    private void switchToEndScreen() throws IOException{
        App.setRoot("end-screen-view");
    }


}


