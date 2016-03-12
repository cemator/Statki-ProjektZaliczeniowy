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
public class Edytor {
    private int[][] edytor;
    private int podloze = 0;   // 0 = woda 1 = piasek 9=cokolwiek
    private int limit; //okresla z ilu pol musi skladac się statek //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
    private boolean pobrano = false; //okresla czy z edytora zostało usuniete pole statku. Odblokowuje to mozliwosc ulozenia pola w innym miejscu (edycji statku)
    private boolean edytowalny = true;
    private OknoGry oknogry;
    
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
    
    private int[][] buforIlosciStatkow = new int[10][8];
    private int zmian = 0;
        
    
    
   
    
    public Edytor(OknoGry oknogry){
        this.oknogry = oknogry;    
        edytor = new int[5][5];  
            for(int x = 0 ; x<5 ; x++){
                for(int y=0; y<5 ; y++){
                edytor[x][y] = 0;
                }
            }
        buforIlosciStatkow[zmian][0] = samoloty;
        buforIlosciStatkow[zmian][1] = czteroMasztowceWodne;   
        buforIlosciStatkow[zmian][2] = trzyMasztowceWodne;      
        buforIlosciStatkow[zmian][3] = dwuMasztowceWodne;          
        buforIlosciStatkow[zmian][4] = jednoMasztowceWodne;        
        buforIlosciStatkow[zmian][5] = czteroMasztowceLadowe;       
        buforIlosciStatkow[zmian][6] = trzyMasztowceLadowe;         
        buforIlosciStatkow[zmian][7] = dwuMasztowceLadowe;
        zerojWspolrzedne();
    }
    
    
    public int[][] getEdytor(){
        return edytor;
    }
    
    public void ustaw4wodny(){
        edytowalny = true;
        podloze = 0;
        edytorWyzeroj();
        limit = 4; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        edytor[4][2] = 2;
        numerStatku=1;
    
    }
    
    public void ustaw3wodny(){
        edytowalny = true;
        podloze = 0;
        edytorWyzeroj();
        limit = 3; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        numerStatku=2;

    }
    
    public void ustaw2wodny(){
        edytowalny = false;
        podloze = 0;
        edytorWyzeroj();
        limit = 2; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        numerStatku=3;
    }
    
    public void ustaw1wodny(){
        edytowalny = false;
        podloze = 0;
        edytorWyzeroj();
        limit = 1; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[2][2] = 2;
        numerStatku=4;
    }
    
     public void ustaw4ladowy(){
        edytowalny = true;
        podloze = 1;
        edytorWyzeroj();
        limit = 4; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        edytor[4][2] = 2;
        numerStatku=5;
    
    }
    
    public void ustaw3ladowy(){
        edytowalny = true;
        podloze = 1;
        edytorWyzeroj();
        limit = 3; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        edytor[3][2] = 2;
        numerStatku=6;

    }
    
    public void ustaw2ladowy(){
        edytowalny = false;
        podloze = 1;
        edytorWyzeroj();
        limit = 2; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
        edytor[1][2] = 2;
        edytor[2][2] = 2;
        numerStatku=7;
    }
    
    public void ustawSamolotT(){
        edytowalny = false;
        podloze = 0;
        edytorWyzerojT();
        limit = 2; //prawdopodobnie jednak nie jest to uzywane ale musze sprawdzic
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
    
    
    public void edytorWyzeroj(){
        for (int x = 0 ; x<5 ; x++){
            for (int y = 0 ; y<5 ; y++){
                edytor[x][y] = podloze;
            }
        }
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
    
     public void setEdytor(int x, int y){ // zmiania pola w edytorze
        if (edytowalny == true){
            boolean pozycja_x_plus_1;
            if (x==4) pozycja_x_plus_1= false;
            else pozycja_x_plus_1 = edytor[x+1][y] != podloze;

            boolean pozycja_x_minus_1;
            if (x==0) pozycja_x_minus_1 = false;
            else pozycja_x_minus_1 = edytor[x-1][y] != podloze;

            boolean pozycja_y_plus_1;
            if (y==4) pozycja_y_plus_1 = false;
            else pozycja_y_plus_1 = edytor[x][y+1] != podloze;

            boolean pozycja_y_minus_1;
            if(y==0) pozycja_y_minus_1 = false;
            else pozycja_y_minus_1 = edytor[x][y-1] != podloze;


            if (pobrano == false && edytor[x][y] != podloze && sprawdzCzyNieSam(x,y)==false){  //ten moduł ustala czy mozliwe jest "pobranie" elementu statku
                edytor[x][y]=podloze;                                                          // sprawdzanie polega na tym czy miejsce z ktorego jest pobierane jest statkiem, czy statek jest w schowku oraz czy po zabraniu ktorys element statku nie zostanie samotny
                pobrano = true;
            }  

            else if (pobrano == true && edytor[x][y] == podloze &&                       // ten moduł sprawdza czy mozliwe jest połozenie elementu statku
                    ( pozycja_x_plus_1 || pozycja_x_minus_1           // sprawdzanie polega na tym czy w okolcznych polach jest jakis element statku do ktorego moze zostac nowy element "przyklejony" 
                    || pozycja_y_plus_1 || pozycja_y_minus_1 )){ // algorytmem do sprawdzania czy statek nie zostanie rozdzielony na pol albo wiecej czesci niech bedzie sprawdzenie czy w momencie zabrania pola jakis element statku nie pozostanie sam...
                edytor[x][y]=2;
                pobrano = false;
            }
            else if ( sprawdzCzyNieSam(x,y)==true){oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Podniesienie tego elementu statku spowoduje jego rozdzielenie! Sprubój podnieść inny element. \n");}  // uwaga taki typ else if moze powodowac problemy przy edytorze! w razie problemów sprawdzić tutaj.
            else if (pobrano == true  ){oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Opuść element na wolnym polu tak aby dotykał krawędzią(nie narożnikiem) innego pola statku! \n");}
        }
        else{oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Ten typ statku nie jest edytowalny. Sprubój obrócić. \n");}
       
    }
    
    private boolean sprawdzCzyNieSam(int x, int y){
        
        int[][] temp = new int[5][5];
        for (int xt = 0; xt<5 ;xt++){
            for (int yt = 0; yt<5 ;yt++){
                temp[xt][yt] = edytor[xt][yt];
            }
        }
        temp[x][y]=podloze;
        
        boolean pozycja_xt_plus_1;
        boolean pozycja_xt_minus_1;
        boolean pozycja_yt_plus_1;
        boolean pozycja_yt_minus_1;
        
        
        for (int xt = 0; xt<5 ;xt++){
            for (int yt = 0; yt<5 ;yt++){
                if(temp[xt][yt]==2){
                    
                    if (xt==4) pozycja_xt_plus_1= true;
                    else pozycja_xt_plus_1 = temp[xt+1][yt] == podloze;
                    if (xt==0) pozycja_xt_minus_1 = true;
                    else pozycja_xt_minus_1 = temp[xt-1][yt] == podloze;
                    if (yt==4) pozycja_yt_plus_1 = true;
                    else pozycja_yt_plus_1 = temp[xt][yt+1] == podloze;
                    if(yt==0) pozycja_yt_minus_1 = true;
                    else pozycja_yt_minus_1 = temp[xt][yt-1] == podloze;
                    
                    if(pozycja_xt_plus_1 && pozycja_xt_minus_1 && pozycja_yt_plus_1 && pozycja_yt_minus_1){ // warunek sprawdza czy po teoretycznym zabraniu jakiego elementu statku, elementy statku ktore zostana nie beda iały wokoło siebie samego podłoza. Jesli beda mialy zwraca true cosugeruje ze zabranie jest niemozliwe
                        return true;
                    }
                }
            }
        }
        
    
        return false;
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
    
    public void obrocEdytorL(){
        int[][] temp = new int[5][5];
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                temp[x][4-y] = edytor[y][x];
            }
        }
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                edytor[x][y] = temp[x][y];
            }
        }
    }
    
    public void odbijEdytor(){
        int[][] temp = new int[5][5];
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                temp[x][y] = edytor[y][x];
            }
        }
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                edytor[x][y] = temp[x][y];
            }
        }
    }
    
    public int getPodloze(){
        return podloze;
    }
    
    public void ustawNapisy(){
        oknogry.jLabel1.setText(""+czteroMasztowceWodne);
        oknogry.jLabel2.setText(""+trzyMasztowceWodne);
        oknogry.jLabel3.setText(""+dwuMasztowceWodne);
        oknogry.jLabel5.setText(""+jednoMasztowceWodne);
        oknogry.jLabel4.setText(""+czteroMasztowceLadowe);
        oknogry.jLabel7.setText(""+trzyMasztowceLadowe);
        oknogry.jLabel6.setText(""+dwuMasztowceLadowe);
        oknogry.jLabel8.setText(""+samoloty);
        
    // tu bedzie odwolywanie się do labeli w oknie i ustalanie liczby statków
    }
    
    public void zatwierdzStatek(int[][] wspolrzedne){
        //schowekDodaj();
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
            case 3: if(dwuMasztowceWodne==3){
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
        schowekDodaj();
        ustawNapisy();
    
    
    }
    
    public boolean CzyPulaStakowNiepusta(){
        boolean niepuste=true;
        switch(numerStatku){
            case 0:if (samoloty == 0)               niepuste = false; break;                 
            case 1:if (czteroMasztowceWodne == 0)   niepuste = false; break;        
            case 2:if (trzyMasztowceWodne == 0)     niepuste = false; break;         
            case 3:if (dwuMasztowceWodne == 0)      niepuste = false; break;          
            case 4:if (jednoMasztowceWodne == 0)    niepuste = false; break;         
            case 5:if (czteroMasztowceLadowe == 0)  niepuste = false; break;       
            case 6:if (trzyMasztowceLadowe == 0)    niepuste = false; break;         
            case 7:if (dwuMasztowceLadowe == 0)     niepuste = false; break;         
        }
        return niepuste;
    
    }  
        //schowek docelowo bedzie przniesiony na odzdzielna klase
        
    
    
    
    public void schowekDodaj(){
        if (zmian==9){                                          //zmiany maja nr 9, nastepuje cofanie
            for (int i = 0 ; i <9 ; i++){
                for (int j = 0 ; j<8 ; j++){
                    buforIlosciStatkow[i][j]=buforIlosciStatkow[i+1][j];  // cofanie konczy sie na tym, ze na pozycji 8 wedruja wartosci z pozycji 9
                }
            }
        zmian--;                                                // bufor zmiania sie na 8
        }
        
        zmian++;                                                //na pozycji zmian0 jest pierwotna ilosc statkow ktore sa wpisywane juz w konstruktorze
        buforIlosciStatkow[zmian][0] = samoloty;
        buforIlosciStatkow[zmian][1] = czteroMasztowceWodne;   
        buforIlosciStatkow[zmian][2] = trzyMasztowceWodne;      
        buforIlosciStatkow[zmian][3] = dwuMasztowceWodne;          
        buforIlosciStatkow[zmian][4] = jednoMasztowceWodne;        
        buforIlosciStatkow[zmian][5] = czteroMasztowceLadowe;       
        buforIlosciStatkow[zmian][6] = trzyMasztowceLadowe;         
        buforIlosciStatkow[zmian][7] = dwuMasztowceLadowe;
        
    }
    
    public void cofnij(){
        
        if (zmian>=1){
            zmian--;
            samoloty                = buforIlosciStatkow[zmian][0];  
            czteroMasztowceWodne    = buforIlosciStatkow[zmian][1];   
            trzyMasztowceWodne      = buforIlosciStatkow[zmian][2];      
            dwuMasztowceWodne       = buforIlosciStatkow[zmian][3];          
            jednoMasztowceWodne     = buforIlosciStatkow[zmian][4]; 
            czteroMasztowceLadowe   = buforIlosciStatkow[zmian][5];       
            trzyMasztowceLadowe     = buforIlosciStatkow[zmian][6];        
            dwuMasztowceLadowe      = buforIlosciStatkow[zmian][7]; 
            //zmian--;
            ustawNapisy();                                              /// tu jest problem, bo zawsze zapisuje ilosci ze zmian i przy wczytywaniu tez tak robi, czyli tak jakby 2 krotnie jest to samo -> zmian trzyma ostatni ruch w odczycie a przy grze przedostatni, zmienic to
           /* for(int i = 0 ; i<10 ; i++){
            System.out.print(" zmian " + i); System.out.println("samoloty "+buforIlosciStatkow[i][0]);
            System.out.print(" czteroMasztowceWodne "+buforIlosciStatkow[i][1] );
            System.out.print(" trzyMasztowceWodne "+buforIlosciStatkow[i][2]);
            System.out.print(" dwuMasztowceWodne " +buforIlosciStatkow[i][3]);
            System.out.print(" jednoMasztowceWodne "+buforIlosciStatkow[i][4]);   
            System.out.print(" czteroMasztowceLadowe "+buforIlosciStatkow[i][5]);
            System.out.print(" trzyMasztowceLadowe "+buforIlosciStatkow[i][6]); 
            System.out.print(" dwuMasztowceLadowe "+buforIlosciStatkow[i][7]);
            System.out.println();
            } */                      //       była to faza testowa bo nie działało odpowiednie zliczanie w cofaniu. Naprawiono, zostawiono w razie kolejnego debugowania.
        }
        else oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Można cofnąć tylko do 10 ruchów! \n");
        

    }
    
    /*public void wyswietlWspolrzedne(){
    
        for (int i = 0 ; i< 17 ; i++){
            System.out.println("Pojazd nr "+ i);
                for (int x = 0 ; x<14 ; x++){
                    for (int y = 0 ; y<22 ; y++){
                            System.out.print(" ["+wspolrzendneStatkow[i][x][y]+"]");
                    }
                }
        
        
        }
    
    
    }*/
    
    public void zerojWspolrzedne(){
    
        for (int i = 0 ; i< 17 ; i++){
            for (int x = 0 ; x<14 ; x++){
                for (int y = 0 ; y<22 ; y++){
                        wspolrzendneStatkow[i][x][y]=0;  // nie do koncza wiem czy jest ta metoda potrzeba bo przeciez tablica intowa, niezdefiniowana czyż nie powinna zamiac nulli miec zer? usunąc na koncu!
                }
            }
        }
    }
    
    public void zeroj(){
        zerojWspolrzedne();
        samoloty = 1;                  
        czteroMasztowceWodne = 1;       
        trzyMasztowceWodne = 2;         
        dwuMasztowceWodne = 3;          
        jednoMasztowceWodne = 4;        
        czteroMasztowceLadowe = 1;      
        trzyMasztowceLadowe = 2;        
        dwuMasztowceLadowe = 3;         
        ustawNapisy();
    }
    
    
    
    /////////////////////////////////////////////////////////////////// metody potrzebne do odczytu i zapisu! \/ \/ \/
    public int[][][] getWspolrzendneStatkow(){
      
        return wspolrzendneStatkow;
    }
    public int[][] getBuforIlosciStatkow(){
        // dodanie tu schowekDodaj(); powoduje rozwiazanie problemu ale przekszatłcenie go w druga strone -> statki sie dodaja ale pozniej nie cofaja od razu
        //schowekDodaj();
        return buforIlosciStatkow;
    } 
    public int getZmian(){
        return zmian;
    }
    
    public void setWspolrzendneStatkow(int[][][] wspolrzedneStatkow){
        this.wspolrzendneStatkow = wspolrzedneStatkow;
    }
    public void setBuforIlosciStatkow(int[][] buforIlosciStatkow){
        this.buforIlosciStatkow = buforIlosciStatkow;
            samoloty                = buforIlosciStatkow[zmian][0];  
            czteroMasztowceWodne    = buforIlosciStatkow[zmian][1];   
            trzyMasztowceWodne      = buforIlosciStatkow[zmian][2];      
            dwuMasztowceWodne       = buforIlosciStatkow[zmian][3];          
            jednoMasztowceWodne     = buforIlosciStatkow[zmian][4]; 
            czteroMasztowceLadowe   = buforIlosciStatkow[zmian][5];       
            trzyMasztowceLadowe     = buforIlosciStatkow[zmian][6];        
            dwuMasztowceLadowe      = buforIlosciStatkow[zmian][7];
            ustawNapisy();
            //cofnij();           /// roziwazuje problem pierwszego cofniecia ale jest problem z ostatnim cofnięciem. Niewiadomo jednak jak działa przy tym indeksowanie tablic w celu tropienia statków
            
    } 
    public void setZmian(int zmian){
        this.zmian = zmian;
    }
    
    
    //////////////////////////////////////////////////////////////////// metody potrzebne do odczytu i zapisu! /\ /\ /\
    
    public void setIlosciStatkow(int[] iloscistatkow){  // metoda na potrzeby losowej mapy
        this.samoloty                = iloscistatkow[0];  
        this.czteroMasztowceWodne    = iloscistatkow[1];   
        this.trzyMasztowceWodne      = iloscistatkow[2];      
        this.dwuMasztowceWodne       = iloscistatkow[3];          
        this.jednoMasztowceWodne     = iloscistatkow[4]; 
        this.czteroMasztowceLadowe   = iloscistatkow[5];       
        this.trzyMasztowceLadowe     = iloscistatkow[6];        
        this.dwuMasztowceLadowe      = iloscistatkow[7];
        ustawNapisy();
    }
    
    public void zeruj(){
        samoloty = 1;                   //numer statku = 0 [0]
        czteroMasztowceWodne = 1;       //numer statku = 1 [1]
        trzyMasztowceWodne = 2;         //numer statku = 2 [2][3]
        dwuMasztowceWodne = 3;          //numer statku = 3 [4][5][6]
        jednoMasztowceWodne = 4;        //numer statku = 4 [7][8][9][10]
        czteroMasztowceLadowe = 1;      //numer statku = 5 [11]
        trzyMasztowceLadowe = 2;        //numer statku = 6 [12][13]
        dwuMasztowceLadowe = 3;         //numer statku = 7 [14][15][16]
        numerStatku = 9;  
        zmian = 0;
        for(int x = 0 ; x<5 ; x++){
            for(int y=0; y<5 ; y++){
            edytor[x][y] = 0;
            }
        }
        buforIlosciStatkow[zmian][0] = samoloty;
        buforIlosciStatkow[zmian][1] = czteroMasztowceWodne;   
        buforIlosciStatkow[zmian][2] = trzyMasztowceWodne;      
        buforIlosciStatkow[zmian][3] = dwuMasztowceWodne;          
        buforIlosciStatkow[zmian][4] = jednoMasztowceWodne;        
        buforIlosciStatkow[zmian][5] = czteroMasztowceLadowe;       
        buforIlosciStatkow[zmian][6] = trzyMasztowceLadowe;         
        buforIlosciStatkow[zmian][7] = dwuMasztowceLadowe;
        zerojWspolrzedne();
    }
    
    
    
    
}
////// zapis i wczytanie teoretycnie działa, ale cofniecie ostatniego pola powoduje zgubienie istatniego statku w edytorze. //note 26.022016 h 13:43 // zmienszono liczbe cofniec w silniku gry co weliminowalo problem