/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzaliczeniowyv1;

import java.util.Random;

/**
 *
 * @author Seweryn
 */
public class LosowaMapa {
    private int[][] plansza ;
     private int[][] temp;  // plansza tymczosowo trzy,majaca wszyskie wspolrzedne aby w przypadku niepowodzenia ustawienia nowego statku, przywrocic z niej nalezyte połozenia na plansze własciwą
    
    private int samoloty = 1;                   //numer statku = 0 [0]
    private int czteroMasztowceWodne = 1;       //numer statku = 1 [1]
    private int trzyMasztowceWodne = 2;         //numer statku = 2 [2][3]
    private int dwuMasztowceWodne = 3;          //numer statku = 3 [4][5][6]
    private int jednoMasztowceWodne = 4;        //numer statku = 4 [7][8][9][10]
    private int czteroMasztowceLadowe = 1;      //numer statku = 5 [11]
    private int trzyMasztowceLadowe = 2;        //numer statku = 6 [12][13]
    private int dwuMasztowceLadowe = 3;         //numer statku = 7 [14][15][16]
    private int[][][] wspolrzendneStatkow = new int[17][14][22];  // tu beda przechowywane tablice na ktorych beda umieszczone wspolrzedne tylko jednego konkretnego statku. i tak, na tablicy [0] bedzie umieszczony samolot, na tablicy[1] pierwszy czteromasztowiec wodny, na tabliczy [2] drugi czteromasztowiec wodny itd.
                                                                // nie jestem pewien czy do poprawnego inicjowanie nie będzie potrzebne zaiinicjowanie 2 ostatnich wartosci tablicy jako szerokosc i wysokosc planszy, ale to się okarze.
    private int numerStatku = 9;  // 9 NIE SYMBOLIZUJE ZADNEGO RODZAJU POJAZDU, WIEC NIECH BEDZIE NA STARCIE ABY aplikacja blednie nie odejmowala liczby w przypadku gdy zadny pojazd nie zostanie wybrany. Zrobione poniewaz null dla inta = 0 a 0 symbolizowalo samolot.
    private int podloze;
    
    private static final byte SZEROKOSC_PLANSZY = 14;
    private static final byte WYSOKOSC_PLANSZY = 22;
    
    /// pole wziete z edytora :D
    
    private int[][] edytor ;
    
    private boolean udaneDodanie = false;
    
    public LosowaMapa(){
        plansza = new int[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY]; 
        temp = new int[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];  
       
        for(int x = 0 ; x<SZEROKOSC_PLANSZY ; x++){
            for(int y=0; y<WYSOKOSC_PLANSZY ; y++){
                if(y>15){
                    plansza[x][y] = 1;
                    temp[x][y] = 1;
                }
                else {
                    plansza[x][y] = 0;
                    temp[x][y] = 0;
                }
            }
        }
        
        edytor = new int[5][5];  
            for(int x = 0 ; x<5 ; x++){
                for(int y=0; y<5 ; y++){
                    edytor[x][y] = 0;
                }
            }
        
        ustawStatki();
    }
    
    
    public void setPole(int x, int y){   //do tej metody odwoluje sie tylko klasa Gra , informuje o tym w ktore miejsce na planszy kliknieto     
     
            //temp = plansza; // tu lezy problem. trzeba zrobic bezposrednie przypisywanie partosc po wartosci bo przy takim zapisie jak tylko sie ruszy cos z planszy to od razu temp tez jest zmieniany
                    for (int ytt = 0; ytt<22 ; ytt++){
                        for(int xtt=0; xtt<14 ; xtt++){
                            temp[xtt][ytt] = plansza[xtt][ytt];
                        }
                    }
            
            
            
            int[][] wspolrzedne = new int[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];

        
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
                    if (edytor[xe][ye] == 2){ // warunek izolujacy tylko dla tych pol edytora ktore zawieraja elementy statku

                        if ((x-2+xe) >= SZEROKOSC_PLANSZY || (y-2+ye) >= WYSOKOSC_PLANSZY || (y-2+ye)<0 || (x-2+xe)<0  ){/*System.out.println("Próbujesz umieścić pojazd poza obszarem mapy!");*/blad = true; break;}  // warunek wykraczania ktorejs z pozycji dodawanego statku poza tablice planszy


                        if (x-2+xe>=13) pozycja_xe_plus_1= true;                        // sprawdzanie wartosci wprost przylegajacych do elementu statku stawianego czy w poblizu nie ma innego statku
                        else pozycja_xe_plus_1 = temp[x-2+xe+1][y-2+ye] != 2;
                        if (x-2+xe<=0) pozycja_xe_minus_1 = true; // sprawdza czy pozycja nie wychodzi poza zakres planszy
                        else pozycja_xe_minus_1 = temp[x-2+xe-1][y-2+ye] != 2; // jesli nie wychodzi to sprawdza czy w poprzednim ukladzie tablicy (temp[zmian]) w okoliczny polu przylegajacym nie ma [ppola statku
                        if (y-2+ye>=21) pozycja_ye_plus_1 = true;
                        else pozycja_ye_plus_1 = temp[x-2+xe][y-2+ye+1] != 2;
                        if(y-2+ye<=0) pozycja_ye_minus_1 = true;
                        else pozycja_ye_minus_1 = temp[x-2+xe][y-2+ye-1] != 2;


                        if (x-2+xe>=13 || y-2+ye>=21) pozycja_xe_plus_1_ye_plus_1= true;                // sprawdzanie wartosci ukosnie przylegajacych do elementu statku stawianego czy w poblizu nie ma innego statku
                        else pozycja_xe_plus_1_ye_plus_1 = temp[x-2+xe+1][y-2+ye+1] != 2;
                        if (x-2+xe<=0 || y-2+ye<=0) pozycja_xe_minus_1_ye_minus_1 = true;
                        else pozycja_xe_minus_1_ye_minus_1 = temp[x-2+xe-1][y-2+ye-1] != 2;
                        if (y-2+ye>=21 || x-2+xe<=0) pozycja_ye_plus_1_xe_minus_1 = true;
                        else pozycja_ye_plus_1_xe_minus_1 = temp[x-2+xe-1][y-2+ye+1] != 2;
                        if(y-2+ye<=0 || x-2+xe>=13) pozycja_ye_minus_1_xe_plus_1 = true;
                        else pozycja_ye_minus_1_xe_plus_1 = temp[x-2+xe+1][y-2+ye-1] != 2;


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
                                else {/*System.out.println("Próbujesz umiescic pojazd na niewlaściwym podłożu!");*/blad = true; break;}


                        }    
                        else {/*System.out.println("Pojazd nie może dotykać innych pojazdów(nawet ukośnie)!");*/blad = true; break;}
                    }   
                if (blad == true) break;
                }
              if (blad == true) break;  
            }
            
            if (blad == true) {//plansza = temp;
                        for (int ytt = 0; ytt<22 ; ytt++){
                            for(int xtt=0; xtt<14 ; xtt++){
                                plansza[xtt][ytt] = temp[xtt][ytt];
                            }
                        }
            
                } 
            else {zatwierdzStatek(wspolrzedne); udaneDodanie=true;}                                              /// ktory w tym miejscu bedzie przekazywany do edytora ktory go zatwierdzi i zapisze . Trzeba znalezc odpowiednia tablice do przechowywania aby pozniejsze poszukiwanie obiektów było łatwe. Pomysł na szybko -> dla kazdego statku osobna tablica wielkosci planszzy  i wszystkie te teablice w jednej tablicy. Łatwe przeszukiwanie ich bedzie.

           // po zakonczonym powodzeniu dodawania przepisac wartosci danego statku do osobnej tablicy, a na poczatku gry ustalic jakiego typu statek dodajemy. Dodawac nalezy od najwiekszego do najmniejszego. Statki nalezy losowo obracac.
    }
    
    public void ustaw4wodny(){
       // edytowalny = true;
        podloze = 0;
        edytorWyzeroj();
     
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        edytor[4][2] = 2;
        numerStatku=1;
    
    }
    
    public void ustaw3wodny(){
       // edytowalny = true;
        podloze = 0;
        edytorWyzeroj();
 
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        numerStatku=2;

    }
    
    public void ustaw2wodny(){
       // edytowalny = false;
        podloze = 0;
        edytorWyzeroj();
     
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        numerStatku=3;
    }
    
    public void ustaw1wodny(){
       // edytowalny = false;
        podloze = 0;
        edytorWyzeroj();
  
        edytor[2][2] = 2;
        numerStatku=4;
    }
    
     public void ustaw4ladowy(){
       // edytowalny = true;
        podloze = 1;
        edytorWyzeroj();
       
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        edytor[4][2] = 2;
        numerStatku=5;
    
    }
    
    public void ustaw3ladowy(){
       // edytowalny = true;
        podloze = 1;
        edytorWyzeroj();
      
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        numerStatku=6;

    }
    
    public void ustaw2ladowy(){
       // edytowalny = false;
        podloze = 1;
        edytorWyzeroj();
       
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        numerStatku=7;
    }
    
    public void ustawSamolotT(){
       // edytowalny = false;
        podloze = 0;
        edytorWyzerojT();
   
        edytor[2][0] = 2;
        edytor[2][1] = 2;
        edytor[2][2] = 2;
        edytor[2][3] = 2;
        edytor[2][4] = 2;
        edytor[0][4] = 2;
        edytor[1][4] = 2;
        edytor[3][4] = 2;
        edytor[4][4] = 2;
        edytor[4][3] = 2;
        edytor[0][3] = 2;
        podloze=9;
        numerStatku=0;
    }
    
    public void edytorWyzerojT(){
        edytorWyzeroj();
        podloze=1;
        int coDruga = 0;
        for (int x = 0 ; x<5 ; x++){
            for (int y = 0 ; y<5 ; y++){
                if(coDruga%2==1)
                edytor[x][y] = podloze;
                coDruga++;
            }
        }
      
    }
    
    public void edytorWyzeroj(){
        for (int x = 0 ; x<5 ; x++){
            for (int y = 0 ; y<5 ; y++){
                edytor[x][y] = podloze;
            }
        }
    }
    
    public void obrocEdytorP(){
        int[][] temp = new int[5][5];
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                temp[4-x][y] = edytor[y][x];
            }
        }
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                edytor[x][y] = temp[x][y];
            }
        }
    }
    
    public void zatwierdzStatek(int[][] wspolrzedne){
      //  schowekDodaj();
        switch(numerStatku){
            case 0:
                wspolrzendneStatkow[0] = wspolrzedne;
                samoloty--; break;
            case 1:wspolrzendneStatkow[1] = wspolrzedne;
                czteroMasztowceWodne--; break;
            case 2: if(trzyMasztowceWodne==2){
                        wspolrzendneStatkow[2]=wspolrzedne;
                        trzyMasztowceWodne--;
                        break;}
                    else{
                        wspolrzendneStatkow[3]=wspolrzedne; 
                        trzyMasztowceWodne--; 
                        break;}
            case 3:if(dwuMasztowceWodne==3){
                        wspolrzendneStatkow[4]=wspolrzedne;
                        dwuMasztowceWodne--;
                        break;
                    }
                    else if(dwuMasztowceWodne==2){
                        wspolrzendneStatkow[5]=wspolrzedne;
                        dwuMasztowceWodne--;
                        break;
                    }
                    else {
                        wspolrzendneStatkow[6]=wspolrzedne;
                        dwuMasztowceWodne--;
                        break;
                    }
            case 4:if(jednoMasztowceWodne==4){
                        wspolrzendneStatkow[7]=wspolrzedne;
                        jednoMasztowceWodne--;
                        break;
                    }
                    else if(jednoMasztowceWodne==3){
                        wspolrzendneStatkow[8]=wspolrzedne;
                        jednoMasztowceWodne--;
                        break;
                    }
                    else if(jednoMasztowceWodne==2){
                        wspolrzendneStatkow[9]=wspolrzedne;
                        jednoMasztowceWodne--;
                        break;
                    }
                    else {
                        wspolrzendneStatkow[10]=wspolrzedne;
                        jednoMasztowceWodne--;
                        break;
                    }
            case 5:wspolrzendneStatkow[11] = wspolrzedne;
                czteroMasztowceLadowe--; break;
            case 6:if(trzyMasztowceLadowe==2){
                        wspolrzendneStatkow[12]=wspolrzedne;
                        trzyMasztowceLadowe--;
                        break;}
                    else{
                        wspolrzendneStatkow[13]=wspolrzedne; 
                        trzyMasztowceLadowe--; 
                        break;}
            case 7:if(dwuMasztowceLadowe==3){
                        wspolrzendneStatkow[14]=wspolrzedne;
                        dwuMasztowceLadowe--;
                        break;
                    }
                    else if(dwuMasztowceLadowe==2){
                        wspolrzendneStatkow[15]=wspolrzedne;
                        dwuMasztowceLadowe--;
                        break;
                    }
                    else {
                        wspolrzendneStatkow[16]=wspolrzedne;
                        dwuMasztowceLadowe--;
                        break;
                    }
            
        }
        
    }
    
    public void ustawStatki(){  /// wprowadzic limit czasowy -> jesli ustalanie losowej tablicy bedzie trwalo zbyt dlugo sprobowac od nowa 
     Random ran = new Random();
     int x;
     int y;
     int obroc;
     
     ustawSamolotT();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
       // System.out.println(x + " " + y);
        setPole(x,y);  
     }
     
     //System.out.println("Ustawiono Samolot"); 
     ////////////////////////////////////////////////////
     ustaw4ladowy();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        //System.out.println(x + " " + y);
        setPole(x,y);  
     }
    
     //System.out.println("Ustawiono 4Ladowy");
    ///////////////////////////////////////////////////
      ustaw4wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     //System.out.println("Ustawiono 4Wodny");
     ////////////////////////////////////////////////////
      ustaw3ladowy();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     ustaw3ladowy();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     //System.out.println("Ustawiono 3LAdowe");
     ////////////////////////////////////////////////////////////////
     
     ustaw2ladowy();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     ustaw2ladowy();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     ustaw2ladowy();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     

     //System.out.println("Ustawiono 2 Ladowe");
      ////////////////////////////////////////////////////
      ustaw3wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     ustaw3wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
    // System.out.println("Ustawiono 3 Wodne");
     ////////////////////////////////////////////////////////////////
     
      ustaw2wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     ustaw2wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     ustaw2wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        obroc = ran.nextInt(2);
        if(obroc == 1) obrocEdytorP();
        setPole(x,y);  
     }
     
     //System.out.println("Ustawiono 2 Wodne");
     //////////////////////////////////////////////////////////
     
      ustaw1wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setPole(x,y);  
     }
     
     ustaw1wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setPole(x,y);  
     }
     
     ustaw1wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setPole(x,y);  
     }
     
     ustaw1wodny();
     udaneDodanie = false;
     while(udaneDodanie == false){
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setPole(x,y);  
     }
     
     //System.out.println("Ustawiono 1 Wodne");
     //////////////////////////////////////////////////////
     
    }
    
    public int[][] getPlansza(){
        return plansza;
    }

    public int[] getIlosciStatkow(){
        int[] iloscistatkow = new int[8];
        iloscistatkow[0]=samoloty ;  
        iloscistatkow[1]=czteroMasztowceWodne;   
        iloscistatkow[2]=trzyMasztowceWodne;      
        iloscistatkow[3]=dwuMasztowceWodne;          
        iloscistatkow[4]=jednoMasztowceWodne; 
        iloscistatkow[5]=czteroMasztowceLadowe;       
        iloscistatkow[6]=trzyMasztowceLadowe;        
        iloscistatkow[7]=dwuMasztowceLadowe;
        return iloscistatkow;
    }
    
    public int[][][] getWspolrzendneStatkow(){
        return wspolrzendneStatkow;
    }
    
    public int[][] getBuforIlosciStatkow(){
        
        int[][] buforIlosciStatkow = new int[10][8];
        for(int zmian = 0; zmian<10 ; zmian++){
            for(int i = 0; i<8 ; i++){
                buforIlosciStatkow[zmian][i] = 0;   
            }
        }
        return buforIlosciStatkow;
    
    }
   
}



