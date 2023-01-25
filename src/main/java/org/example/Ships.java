package org.example;

/**
 * Klasa Ships przechowuje numery pól na których są umieszczone statki gracza
 * Zawiera ona domyślne ustawienie statków gracza
 * Jest ona wykorzystywa tylko w ShipPositionController
 */
public class Ships {
    public int[] position_memory_one;
    public int[] position_memory_two;
    public int[] position_memory_three;
    public int[] position_memory_four;
    public int[] ship_memory;
    public Ships(){
        this.position_memory_one=new int[1];
        this.position_memory_two=new int[2];
        this.position_memory_three=new int[3];
        this.position_memory_four=new int [4];
        this.ship_memory=new int[1];
        firstSetting();
    }
    public void firstSetting(){
        this.position_memory_one[0]=100;

        this.position_memory_two[0]=82;
        this.position_memory_two[1]=83;

        this.position_memory_three[0]=58;
        this.position_memory_three[1]=59;
        this.position_memory_three[2]=60;

        this.position_memory_four[0]=32;
        this.position_memory_four[1]=33;
        this.position_memory_four[2]=34;
        this.position_memory_four[3]=35;

        this.ship_memory[0]=0;
    }

    /**
     * Funkcja getShip zwraca pozycje statku o które jest zapytanie
     * @param a numer statku
     * @return pole na których się statek znajduje
     */
    public int[] getShip(int a){
        switch(a){
            case 1:
                return position_memory_one;
            case 2:
                return position_memory_two;
            case 3:
                return position_memory_three;
            case 4:
                return position_memory_four;
        }
        return null;
    }
    /**
     * Funkcja zwraca numer statku , o które jest zapytanie
     * @param a numer pól jakie zajmuje statek
     * @return numer statku
     */
    public int getNumberOfShip(int[] a){
        if(a==position_memory_one)return 1;
        if(a==position_memory_two)return 2;
        if(a==position_memory_three)return 3;
        if(a==position_memory_four)return 4;
        return 0;
    }
    /**
     * Funkcja zwraca numer statku , o które jest zapytanie
     * @param a nazwa statku
     * @return numer statku
     */
    public int fromNameGetNumber(String a){
        if(a=="The One")return 1;
        if(a=="The Two")return 2;
        if(a=="The Tree")return 3;
        if(a=="The Four")return 4;
        return 0;
    }
}
