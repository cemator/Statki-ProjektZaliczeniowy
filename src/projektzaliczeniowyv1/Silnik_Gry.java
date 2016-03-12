/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzaliczeniowyv1;

import java.util.Calendar;

/**
 *
 * @author Seweryn
 */



public class Silnik_Gry {
    
    private int[][] plansza;
    private int[][] edytor;
 
    private int[][][] temp;
    private int zmian = 0;
    private static final byte SZEROKOSC_PLANSZY = 14;
    private static final byte WYSOKOSC_PLANSZY = 22;
    private Edytor Edytor;
    private int podloze;
    private OknoGry oknogry;
    
                                                                              
                                                        /*   Singleton wyłączony
    private static Silnik_Gry inst;
    
    
    public static Silnik_Gry inst() {
        if(inst == null) {
            inst = new Silnik_Gry();
        }
        return inst;
        }                                                */
    
    
    
    public Silnik_Gry(Edytor Edytor, OknoGry oknogry){
        this.oknogry = oknogry;  
        this.Edytor = Edytor;
        plansza = new int[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];       
        temp = new int[10][SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];
        zerojPlansze();
                                                     
    }
    
    public void zerojPlansze(){
         for(int x = 0 ; x<SZEROKOSC_PLANSZY ; x++){
            for(int y=0; y<WYSOKOSC_PLANSZY ; y++){
                if(y>15){
                    plansza[x][y] = 1;

                    temp[zmian][x][y] = 1;
                }
                else {
                    plansza[x][y] = 0;

                    temp[zmian][x][y] = 0;
                }
            }
        }
        
    }
    
    public void zeroj(){
        for (int i = 9; i>=0 ; i--){
            this.zmian = i;
            zerojPlansze();
        }
        
    }
    
    
    public void setPole(int x, int y){   //do tej metody odwoluje sie tylko klasa Gra , informuje o tym w ktore miejsce na planszy kliknieto     
        if (Edytor.CzyPulaStakowNiepusta()==false){oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zasoby tego typu pojazdu już zostały wyczerpane! \n");}   // sprawdza czy limit danego statku nie został wyczerpany. Jeśli został, dodawanie nie odbędzie się.
        else{   

            schowekDodaj();
            
            int[][] wspolrzedne = new int[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];

            podloze = Edytor.getPodloze();
            edytor = Edytor.getEdytor();
            boolean blad = false;

            boolean pozycja_xe_plus_1;
            boolean pozycja_xe_minus_1;
            boolean pozycja_ye_plus_1;
            boolean pozycja_ye_minus_1;

            boolean pozycja_xe_plus_1_ye_plus_1;
            boolean pozycja_xe_minus_1_ye_minus_1;
            boolean pozycja_ye_plus_1_xe_minus_1;
            boolean pozycja_ye_minus_1_xe_plus_1;



            for(int xe = 0 ; xe<5 ; xe++){
                for(int ye=0; ye<5 ; ye++){
                    if (edytor[xe][ye] == 2){

                        if ((x-2+xe) >= SZEROKOSC_PLANSZY || (y-2+ye) >= WYSOKOSC_PLANSZY || (y-2+ye)<0 || (x-2+xe)<0  ){System.out.println("Próbujesz umieścić pojazd poza obszarem mapy!");blad = true; break;}  // warunek wykraczania ktorejs z pozycji dodawanego statku poza tablice planszy


                        if (x-2+xe>=13) pozycja_xe_plus_1= true;                        // sprawdzanie wartosci wprost przylegajacych do elementu statku stawianego czy w poblizu nie ma innego statku
                        else pozycja_xe_plus_1 = temp[zmian][x-2+xe+1][y-2+ye] != 2;
                        if (x-2+xe<=0) pozycja_xe_minus_1 = true;  // sprawdza czy pozycja nie wychodzi poza zakres planszy
                        else pozycja_xe_minus_1 = temp[zmian][x-2+xe-1][y-2+ye] != 2;   // jesli nie wychodzi to sprawdza czy w poprzednim ukladzie tablicy (temp[zmian]) w okoliczny polu przylegajacym nie ma [ppola statku
                        if (y-2+ye>=21) pozycja_ye_plus_1 = true;
                        else pozycja_ye_plus_1 = temp[zmian][x-2+xe][y-2+ye+1] != 2;
                        if(y-2+ye<=0) pozycja_ye_minus_1 = true;
                        else pozycja_ye_minus_1 = temp[zmian][x-2+xe][y-2+ye-1] != 2;


                        if (x-2+xe>=13 || y-2+ye>=21) pozycja_xe_plus_1_ye_plus_1= true;                // sprawdzanie wartosci ukosnie przylegajacych do elementu statku stawianego czy w poblizu nie ma innego statku
                        else pozycja_xe_plus_1_ye_plus_1 = temp[zmian][x-2+xe+1][y-2+ye+1] != 2;
                        if (x-2+xe<=0 || y-2+ye<=0) pozycja_xe_minus_1_ye_minus_1 = true;
                        else pozycja_xe_minus_1_ye_minus_1 = temp[zmian][x-2+xe-1][y-2+ye-1] != 2;
                        if (y-2+ye>=21 || x-2+xe<=0) pozycja_ye_plus_1_xe_minus_1 = true;
                        else pozycja_ye_plus_1_xe_minus_1 = temp[zmian][x-2+xe-1][y-2+ye+1] != 2;
                        if(y-2+ye<=0 || x-2+xe>=13) pozycja_ye_minus_1_xe_plus_1 = true;
                        else pozycja_ye_minus_1_xe_plus_1 = temp[zmian][x-2+xe+1][y-2+ye-1] != 2;


                        if(pozycja_xe_plus_1 && pozycja_xe_minus_1 && pozycja_ye_plus_1 && pozycja_ye_minus_1 &&
                                pozycja_xe_plus_1_ye_plus_1 && pozycja_xe_minus_1_ye_minus_1 && 
                                pozycja_ye_plus_1_xe_minus_1 && pozycja_ye_minus_1_xe_plus_1){


                                if (podloze==9){
                                    plansza[x-2+xe][y-2+ye] = edytor[xe][ye];
                                    wspolrzedne[x-2+xe][y-2+ye] = edytor[xe][ye];
                                    }
                                else if (plansza[x-2+xe][y-2+ye]==podloze)
                                    {plansza[x-2+xe][y-2+ye] = edytor[xe][ye];
                                    wspolrzedne[x-2+xe][y-2+ye] = edytor[xe][ye];}  /// gdzieś w tym mijescu powinny byc dodawane dodane pole do tymczasowej tabllicy ktora zawierac bedzie spolzedne danego obiektu
                                else {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Próbujesz umiescic pojazd na niewlaściwym podłożu! \n");blad = true; break;}


                        }    
                        else {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Pojazd nie może dotykać innych pojazdów(nawet ukośnie)! \n");blad = true; break;}
                    }   
                if (blad == true) break;
                }
              if (blad == true) break;  
            }
            if (blad == true) {cofnij(); } 
            else Edytor.zatwierdzStatek(wspolrzedne);                                                                          /// ktry w tym miejscu bedzie przekazywany do edytora ktory go zatwierdzi i zapisze . Trzeba znalezc odpowiednia tablice do przechowywania aby pozniejsze poszukiwanie obiektów było łatwe. Pomysł na szybko -> dla kazdego statku osobna tablica wielkosci planszzy  i wszystkie te teablice w jednej tablicy. Łatwe przeszukiwanie ich bedzie.

        }
    }
    
    
    
    
    public void schowekDodaj(){
        
        if (zmian==9){                                          //zmiany maja nr 9, nastepuje cofanie
            for (int i = 0 ; i <9 ; i++){
                for(int xs = 0 ; xs<SZEROKOSC_PLANSZY ; xs++){
                    for(int ys=0; ys<WYSOKOSC_PLANSZY ; ys++){          
                        temp[i][xs][ys]=temp[i+1][xs][ys];     // cofanie konczy sie na tym, ze na pozycji 8 wedruja wartosci z pozycji 9
                    }
                }
            }
            zmian--;                                          // bufor zmiania sie na 8
        }
        zmian++;
        for(int xs = 0 ; xs<SZEROKOSC_PLANSZY ; xs++){
            for(int ys=0; ys<WYSOKOSC_PLANSZY ; ys++){
                temp[zmian][xs][ys]=plansza[xs][ys];
            }
        }
    
    }
    
    
    
    
    public void cofnij(){
        if (zmian>=1){
            for(int x = 0 ; x<SZEROKOSC_PLANSZY ; x++){
                for(int y=0; y<WYSOKOSC_PLANSZY ; y++){
                    plansza[x][y] = temp[zmian][x][y];
                }
            }
            zmian--;
        }

    }
    
    public void trafPole(int x, int y){ // metoda odpowiedzialna za trafianie przez komputer naszych statków, zmieniajaca kolor trafionych pól
        if (plansza[x][y]==2) plansza[x][y]=4;
        else plansza[x][y]=3;  
    }
                                          
    public int[][] getPlansza(){
        return plansza;
    }
    
    /////////////////////////////////////////////// Metody potrzebne do zapisu i odczytu! \/ \/ \/

    public int[][][] getTemp(){
        return temp;
    }
    
    public int getZmian(){
        return zmian;
    }
    
    
    public void setTemp(int[][][] temp){
        this.temp = temp;
    }
    
    public void setPlansza(int[][] plansza){
        this.plansza = plansza;
    }
    
    public void setZmian(int zmian){
        this.zmian = zmian;
    }
        /////////////////////////////////////////////// Metody potrzebne do zapisu i odczytu! /\ /\ /\
        /////////////////////////////////////////////// Metodyu dla klasy Gra_Celowanie   \/\/\/
    
    public void zeruj(){
        zmian = 0;
        zerojPlansze();
    }
}
