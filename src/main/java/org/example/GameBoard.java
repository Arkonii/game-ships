package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Klasa GameBoard przechowuje plansze i jej wartośći w pamięci (0 pole puste , 1 pole gdzie jest statek , 2 pole trafione , 3 pole "żółte")
 * klasa ta również posiada zestaw 4 ustawień planszy AI , które jest losowane w GameController
 */
public class GameBoard {
    public int[] ground_tab;
    private String[] buttons_in_string;
    private int[] buttons_in_int;
    private int temp_button_int;
    public GameBoard(){
        this.ground_tab = new int [101];
        this.buttons_in_string = new String[]{null,"A11", "A21", "A31", "A41", "A51", "A61", "A71", "A81", "A91", "A101", "B11", "B21", "B31", "B41", "B51", "B61", "B71", "B81", "B91", "B101", "C11", "C21", "C31", "C41", "C51", "C61", "C71", "C81", "C91", "C101", "D11", "D21", "D31", "D41", "D51", "D61", "D71", "D81", "D91", "D101", "E11", "E21", "E31", "E41", "E51", "E61", "E71", "E81", "E91", "E101", "F11", "F21", "F31", "F41", "F51", "F61", "F71", "F81", "F91", "F101", "G11", "G21", "G31", "G41", "G51", "G61", "G71", "G81", "G91", "G101", "H11", "H21", "H31", "H41", "H51", "H61", "H71", "H81", "H91", "H101", "I11", "I21", "I31", "I41", "I51", "I61", "I71", "I81", "I91", "I101", "J11", "J21", "J31", "J41", "J51", "J61", "J71", "J81", "J91", "J101"};
        this.buttons_in_int=new int[101];
        this.temp_button_int=0;
        for(int i = 1 ;i<=10;i++){
            for(int j=0;j<100;j+=10){
                this.temp_button_int++;
                try {
                    this.buttons_in_int[temp_button_int] = i+j;

                }catch(Exception e){}
            }
        }
    }

    /**
     * Konstruktor dla AI
     * @param rand wylosowana liczba 1 - 4 , wybiera jeden z zestawów położenia AI
     */
    public GameBoard(int rand){
        this.ground_tab = new int [101];
        String zdanie="";
        switch(rand) {
            case 1:
                try {
                    File file = new File("conf1.txt");
                    Scanner in = new Scanner(file);
                    zdanie = in.nextLine();
                } catch (FileNotFoundException e) {
                }
                break;
            case 2:
                try {
                    File file = new File("conf2.txt");
                    Scanner in = new Scanner(file);
                    zdanie = in.nextLine();
                } catch (FileNotFoundException e) {}
                break;
            case 3:
                try {
                    File file = new File("conf3.txt");
                    Scanner in = new Scanner(file);
                    zdanie = in.nextLine();
                } catch (FileNotFoundException e) {}
                break;
            case 4:
                try {
                    File file = new File("conf4.txt");
                    Scanner in = new Scanner(file);
                    zdanie = in.nextLine();
                } catch (FileNotFoundException e) {}
                break;
        }
        int []tab=new int [10];
        tab=confFromFile(zdanie);
        for(int i=0;i<tab.length;i++){
            this.ground_tab[tab[i]]=1;
        }

        this.buttons_in_string = new String[]{null,"A11", "A21", "A31", "A41", "A51", "A61", "A71", "A81", "A91", "A101", "B11", "B21", "B31", "B41", "B51", "B61", "B71", "B81", "B91", "B101", "C11", "C21", "C31", "C41", "C51", "C61", "C71", "C81", "C91", "C101", "D11", "D21", "D31", "D41", "D51", "D61", "D71", "D81", "D91", "D101", "E11", "E21", "E31", "E41", "E51", "E61", "E71", "E81", "E91", "E101", "F11", "F21", "F31", "F41", "F51", "F61", "F71", "F81", "F91", "F101", "G11", "G21", "G31", "G41", "G51", "G61", "G71", "G81", "G91", "G101", "H11", "H21", "H31", "H41", "H51", "H61", "H71", "H81", "H91", "H101", "I11", "I21", "I31", "I41", "I51", "I61", "I71", "I81", "I91", "I101", "J11", "J21", "J31", "J41", "J51", "J61", "J71", "J81", "J91", "J101"};
        this.buttons_in_int=new int[101];
        this.temp_button_int=0;
        for(int i = 1 ;i<=10;i++){
            for(int j=0;j<100;j+=10){
                this.temp_button_int++;
                try {
                    this.buttons_in_int[temp_button_int] = i+j;
                }catch(Exception e){}
            }
        }
    }

    /**
     * Funckja confFromFile , ma na celu przektowertowanie tekstu z pliku i ułożenie dzieki niej planszy AI
     * @param text tekst z pliku
     * @return polozenie statków AI
     */
    public int[] confFromFile(String text){
        int []tab=new int[10];
        int temp_tab=0;
        String temp_string = "";
        for(int i=0;i<text.length();i++){
            try{
                if(text.charAt(i)==' '){
                    int number = Integer.parseInt(temp_string);
                    tab[temp_tab]=number;
                    temp_tab++;
                    temp_string="";
                }
                else {
                    temp_string+=text.charAt(i);

                }
            }catch(NumberFormatException ex){}
        }
        int number = Integer.parseInt(temp_string);
        tab[temp_tab]=number;
        temp_string="";

        for(int i=0;i<tab.length;i++){
            System.out.print(tab[i]+" ");
        }
        return tab;
    }
    /**
     * Zmiana wartości pól , na których jest statek którym chcemy poruszać z 1 na 2 (inaczej nie mógłby się poruszać po planszy ponieważ sam siebie by blokował w ShipPositonController)
     * @param ship_memory położenie statku którym chcemy poruszać
     */
    public void accesToMove(int[] ship_memory) {
        for(int i=0;i<ship_memory.length;i++){
            ground_tab[ship_memory[i]]=2;
        }
    }
    /**
     * Zmiana wartości wszystkich pól z wartościa 2 na 1
     */
    public void shipChanging() {
        for(int i=0;i<ground_tab.length;i++){
            if(ground_tab[i]==2){
                ground_tab[i]=1;
            }
        }
    }

    /**
     * Funkcja ta zwraca numer pola klikniętego przez gracza
     * @param button id przycisku , który gracz klinkął
     * @return numer pola , który gracz kliknął
     */
    public int buttonAssignment(String button){
        for(int i = 1 ;i<buttons_in_string.length;i++){
            if(button.equals(buttons_in_string[i])){
                return buttons_in_int[i];
            }
        }
        return 0;
    }
}
