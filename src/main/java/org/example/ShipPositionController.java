package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa ShipPositionController odpowiada za wybór pozycji statków gracza przed rozpoczęciem rozgrywki .
 */
public class ShipPositionController implements Initializable {
    /**
     * Deklaracja przycisków
     */
    @FXML
    public Button A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,D1,D2,D3,D4,D5,D6,D7,D8,D9,D10, E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,H1,H2,H3,H4,H5,H6,H7,H8,H9,H10,I1,I2,I3,I4,I5,I6,I7,I8,I9,I10,J1,J2,J3,J4,J5,J6,J7,J8,J9,J10;
    public Button next;
    /**
     * Deklaracja etykiet
     */
    @FXML
    private Label myLabel;
    @FXML
    private Label inst;
    /**
     * Deklaracja paska wyboru
     */
    @FXML
    private ChoiceBox<String> shipChoiceBox;
    /**
     * @param ships_name Nazwy statków , odpowiadają ilości pól jakie zajmują
     * @param flag_to_switch Zabezpieczenie aby użytkownik nie mógł przejść do następnego ekranu nie załadowując pozycji statków
     * @param ships obiekt przechowujący koordynaty statków gracza
     * @param ground obiekt przechowujący tablice int , która odpowiada planszy w grze
     */
    private String[] ships_name = {"The One","The Two","The Tree","The Four"};
    public int flag_to_switch = 0;
    public static Ships ships =new Ships();
    public static GameBoard ground=new GameBoard();

    public static GameBoard getGround() {
        return ground;
    }
    public static Ships getShips(){
        return ships;
    }
    /**
     * Funckja refresh , jest wywoływana po każdym dokonanym wyborze w choicboxie, odpowiada za zapisywanie który statek jest poruszany oraz
     * wyznaczanie , które statki mają mieć wyznaczone "pola żółte" - czyli takie pola które nie mogą być zajęte przez inny statek oprócz nich samych . Statek poruszany nie ma wyznaczonych pól żółtych dopóki nie zmienimy na inny
     * Jest również inicjalizacją ustawień statków , które domyślnie są wyznaczone w klasie Ships i dopiero po pierwszym wyborze statku z choiceboxa zapisują się w klasie GameBoard oraz wizualnie na ekranie
     * @param j jest to nazwa statku , który chcemy zacząć ruszać
     */
    private void refresh(String j){
        flag_to_switch=1;
        borderDeleting(ships.getShip(ships.fromNameGetNumber(j)));
        ships.ship_memory= ships.position_memory_one;
        position(0);
        if(ships.getShip(ships.fromNameGetNumber(j))!= ships.position_memory_one)borderDrawing(ships.ship_memory);

        ships.ship_memory= ships.position_memory_two;
        position(0);
        if(ships.getShip(ships.fromNameGetNumber(j))!= ships.position_memory_two)borderDrawing(ships.ship_memory);

        ships.ship_memory= ships.position_memory_three;
        position(0);
        if(ships.getShip(ships.fromNameGetNumber(j))!= ships.position_memory_three)borderDrawing(ships.ship_memory);

        ships.ship_memory= ships.position_memory_four;
        position(0);
        if(ships.getShip(ships.fromNameGetNumber(j))!= ships.position_memory_four)borderDrawing(ships.ship_memory);

        ships.ship_memory= ships.getShip(ships.fromNameGetNumber(j));
        ground.shipChanging();
        ground.accesToMove(ships.ship_memory);
    }
    /**
     * Funkcja initializie , odpowiada za konfiguracje choiceboxa , nadanie mu nazw oraz przypisanie akcji przy wyborze
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        shipChoiceBox.getItems().addAll(ships_name);
        shipChoiceBox.setOnAction(this::getShip);
        inst.setText("Na środku okna znajduje się przycisk,\n" +
                "gdy go klikniesz rozwinie się opcja statku, który chcesz ułożyć na planszy.\n" +
                "Wybierzesz statek, następnie za pomocą czterech strzałek będziesz mógł\n" +
                "przemieszczać statek po planszy. \n" +
                "Jeżeli ustawienie statków będzie dla ciebie satysfakcjonujące \n" +
                "przyciśnij przycisk AKCEPTUJ i zacznij bitwę. POWODZENIA!");
    }

    /**
     * Funkcja getShip jest wywoływana przez choicebox , nadpisuje statek który wybraliśmy w pamięci statku którym poruszaliśmy
     * @param actionEvent
     */
    private void getShip(ActionEvent actionEvent) {
        String myShip = shipChoiceBox.getValue();
        refresh(myShip);
        myLabel.setText(myShip+ " " + ships.getNumberOfShip(ships.ship_memory));
    }

    /**
     * Funkcje goRight , goLeft , goUp , goDown to funkcje wywoływane przez przyciski ruchu(na ekranie szczałki) , za ich pomocą przekazujemy do position() pozycje jaką chcemy osiągnąć na planszy
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void goRight(ActionEvent actionEvent) throws IOException {
        position(1);
    }
    public void goLeft(ActionEvent actionEvent) {
        position(-1);
    }
    public void goUp(ActionEvent actionEvent) {
        position(-10);
    }
    public void goDown(ActionEvent actionEvent) {
        position(10);
    }
    /**
     * Position , to rdzeń całego systemu ustawiania statków na planszy . Do jej działań należy wykrywanie jaki statek mamy zapisany w pamięci , poruszanie nim jeśli jest to możliwe (nie jest zablokowany przez inny statek bądź krawędź planszy)
     * @param i
     */
    private void position(int i){
        //System.out.println(ships.getNumberOfShip(ships.ship_memory));
        switch(ships.getNumberOfShip(ships.ship_memory)) {
            case 1:
                //System.out.println(" ");
                int[] new_position_memory_one=new int[1];
                new_position_memory_one[0]= ships.position_memory_one[0];
                new_position_memory_one[0]+=i;
                //whereIAm(new_position_memory_one);
                if(ifOnBoard(new_position_memory_one)){
                    //System.out.println(ground_tab);
                    if (ifClear(new_position_memory_one)) {
                        ships.position_memory_one = overwritte(new_position_memory_one, ships.position_memory_one);
                        ships.ship_memory= ships.position_memory_one;
                    }
                }
                break;
            case 2:
                int[] new_position_position_two=new int[2];
                new_position_position_two[0]= ships.position_memory_two[0];
                new_position_position_two[1]= ships.position_memory_two[1];
                new_position_position_two[0]+=i;
                new_position_position_two[1]+=i;
                //whereIAm(new_position_position_two);
                if(ifOnBoard(new_position_position_two)){
                    //System.out.println(ground_tab);
                        if (ifClear(new_position_position_two)) {
                            ships.position_memory_two = overwritte(new_position_position_two, ships.position_memory_two);
                            ships.ship_memory= ships.position_memory_two;
                    }
                }
                break;
            case 3:
                int[] new_position_memory_three=new int[3];
                new_position_memory_three[0]= ships.position_memory_three[0];
                new_position_memory_three[1]= ships.position_memory_three[1];
                new_position_memory_three[2]= ships.position_memory_three[2];
                new_position_memory_three[0]+=i;
                new_position_memory_three[1]+=i;
                new_position_memory_three[2]+=i;
                //whereIAm(new_position_memory_four);
                if(ifOnBoard(new_position_memory_three)){
                    //System.out.println(ground_tab);
                    if (ifClear(new_position_memory_three)) {
                        ships.position_memory_three = overwritte(new_position_memory_three, ships.position_memory_three);
                        ships.ship_memory= ships.position_memory_three;
                    }
                }
                break;
            case 4:
                int[] new_position_memory_four=new int[4];
                new_position_memory_four[0]= ships.position_memory_four[0];
                new_position_memory_four[1]= ships.position_memory_four[1];
                new_position_memory_four[2]= ships.position_memory_four[2];
                new_position_memory_four[3]= ships.position_memory_four[3];
                new_position_memory_four[0]+=i;
                new_position_memory_four[1]+=i;
                new_position_memory_four[2]+=i;
                new_position_memory_four[3]+=i;
                //whereIAm(new_position_memory_four);
                if(ifOnBoard(new_position_memory_four)){
                    //System.out.println(ground_tab);
                    if (ifClear(new_position_memory_four)) {
                        ships.position_memory_four = overwritte(new_position_memory_four, ships.position_memory_four);
                        ships.ship_memory= ships.position_memory_four;
                    }
                }
                break;

        }
    }

    /**
     * Funkcja ta nadpisuje położenie statku w pamięci i na ekranie
     * @param new_position nowa pozycja statku
     * @param position_memory stara pozycja statku(pamięć)
     * @return
     */
    private int[] overwritte(int[] new_position, int[] position_memory){
        for(int i=0;i<new_position.length;i++){
            buttonArray(position_memory[i]).setStyle("-fx-background-color: #039BE5;");
            ground.ground_tab[position_memory[i]]=0;
        }
        for(int i=0;i<new_position.length;i++){
            buttonArray(new_position[i]).setStyle("-fx-background-color: red;");
            ground.ground_tab[new_position[i]]=2;
        }
        return new_position;
    }

    /**
     * Funkcja borderDrawing odpowiada za wyznaczanie "żółtych pól" na planszy
     * @param position_memory pamięć w której przetrzymujemy położenie statku który jest w użyciu
     */
    private void borderDrawing(int[] position_memory){
        for(int i=0;i<position_memory.length;i++){
            try{
                buttonArray(position_memory[i]+10).setStyle("-fx-background-color: yellow;");
                ground.ground_tab[position_memory[i]+10]=3;
            } catch (Exception e) {}
            try{
                buttonArray(position_memory[i]-10).setStyle("-fx-background-color: yellow;");
                ground.ground_tab[position_memory[i]-10]=3;
            } catch (Exception e) {}
            try{
                if((position_memory[0]-1)%10!=0){
                    buttonArray(position_memory[0] - 1).setStyle("-fx-background-color: yellow;");
                    ground.ground_tab[position_memory[0] - 1] = 3;
                }
            } catch (Exception e) {
            }
            try{
                if((position_memory[position_memory.length-1]+1)%10!=1){
                    buttonArray(position_memory[position_memory.length-1]+1).setStyle("-fx-background-color: yellow;");
                    ground.ground_tab[position_memory[position_memory.length-1]+1]=3;
                }
            } catch (Exception e) {}
        }
    }

    /**
     * Funkcja ta odpowiada za usuwanie "żółtych pól" , pozwala na poruszanie statku który wybraliśmy (inaczej jego żółte pola by na to nie pozwoliły)
     * @param position_memory pamięć w której przetrzymujemy położenie statku który jest w użyciu
     */
    private void borderDeleting(int[] position_memory){
        for(int i=0;i<position_memory.length;i++){
            try{
                buttonArray(position_memory[i]+10).setStyle("-fx-background-color: #039BE5;");
                ground.ground_tab[position_memory[i]+10]=0;
            } catch (Exception e) {}
            try{
                buttonArray(position_memory[i]-10).setStyle("-fx-background-color: #039BE5;");
                ground.ground_tab[position_memory[i]-10]=0;
            } catch (Exception e) {}
            try{
                if((position_memory[0]-1)%10!=0){
                    buttonArray(position_memory[0] - 1).setStyle("-fx-background-color: #039BE5;");
                    ground.ground_tab[position_memory[0] - 1] = 0;
                }
            } catch (Exception e) {}
            try{
                if((position_memory[position_memory.length-1]+1)%10!=1){
                    buttonArray(position_memory[position_memory.length-1]+1).setStyle("-fx-background-color: #039BE5;");
                    ground.ground_tab[position_memory[position_memory.length-1]+1]=0;
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * Funkcja ifOnBoard , sprawdza czy nowe położenie statku które chcemy osiągnąć jest możliwe czyli ,
     * czy nie blokuje go krawędź planszy
     * @param new_position nowa pozycja statku
     * @return
     */
    private boolean ifOnBoard(int[] new_position){
        if(ships.getNumberOfShip(ships.ship_memory)==1){
            if((new_position[0]%10==0&& ships.position_memory_one[0]%10==1)||(new_position[0]%10==1&& ships.position_memory_one[0]%10==0)){
                return false;
            }
        }
        for(int i=0;i<new_position.length;i++){
            try{
                if(new_position[i]<=0||new_position[i]>=101){
                    return false;
                }
            } catch (Exception e) {}
            try{
                if((new_position[i]%10==0&&new_position[i+1]%10==1)||(new_position[i]%10==0&&new_position[i-1]%10==1)){
                    return false;
                }
            } catch (Exception e) {}
        }
        return true;
    }

    /**
     * Funkcja ifClear , sprawdza czy nowe położenie statku które chcemy osiągnąć jest możliwe czyli ,
     * czy nie znajduje się tam inny statek bądź "żółte pola"
     * @param new_position nowa pozycja statku
     * @return
     */
    private boolean ifClear(int[] new_position)  {
        for(int i=0;i<new_position.length;i++){
            try{
                if(ground.ground_tab[new_position[i]]==3){
                    return false;
                }
            } catch (Exception e) {}
            try{
                if(ground.ground_tab[new_position[i]]==1){
                    return false;
                }
            } catch (Exception e) {}
        }
        return true;
    }

    /**
     * Funkcji buttonArray zadaniem jest zwrot przycisku który chcemy nadpisać (zmienić kolor) w funkcji overwritte
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
     * Funkcja switchToGame , odpowiada za zmiane fxml na następny
     * @throws IOException
     */
    @FXML
    private void switchToGame() throws IOException{
        borderDrawing(ships.ship_memory);
        ground.shipChanging();
        if(flag_to_switch==1){
            App.setRoot("game-view");
        }
    }
}
