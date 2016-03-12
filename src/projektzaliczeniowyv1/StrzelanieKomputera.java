/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzaliczeniowyv1;

import java.awt.Color;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Seweryn
 */
public class StrzelanieKomputera implements Runnable{
    
    private Edytor edytor;
    private Silnik_Gry silnik;
    private OknoGry oknogry;
    private int[][][] wspolrzendneStatkow = new int[17][14][22];
    private int plansza[][] = new int[14][22];
    
    private int samolotyDoTrafienia = 1;                   //numer statku = 0 [0]
    private int czteroMasztowceWodneDoTrafienia = 1;       //numer statku = 1 [1]
    private int trzyMasztowceWodneDoTrafienia = 2;         //numer statku = 2 [2][3]
    private int dwuMasztowceWodneDoTrafienia = 3;          //numer statku = 3 [4][5][6]
    private int jednoMasztowceWodneDoTrafienia = 4;        //numer statku = 4 [7][8][9][10]
    private int czteroMasztowceLadoweDoTrafienia = 1;      //numer statku = 5 [11]
    private int trzyMasztowceLadoweDoTrafienia = 2;        //numer statku = 6 [12][13]
    private int dwuMasztowceLadoweDoTrafienia = 3;         //numer statku = 7 [14][15][16]
    
    private int[] zestrzelonePolaStatkow = new int[17];
    
    private int[] trafionePoleX = new int[12];  // w tych zmiennych beda trzymane wspolrzedne pola trafionego ale nie zatopionego statku w celu trafienia okolicznych pół
    private int[] trafionePoleY = new int[12];
    private int trafionych = 0; // ta zmienna mowi czy jakis statek zostal nieskonczony. Jesli statek zostanie zatopiony wartosc przyjmuje znow false
    
    private boolean pudlo = false;
    
    private int wykonanychRuchowKomputera =0;
    public int ustrzelonychPol=0;
    
    
    public StrzelanieKomputera(Edytor edytor, Silnik_Gry silnik,OknoGry oknogry){
        this.oknogry = oknogry;  
        this.silnik = silnik;
        this.edytor = edytor;
        
        wspolrzendneStatkow=edytor.getWspolrzendneStatkow();
        plansza = silnik.getPlansza();
        ustalPola();
        
    }

    @Override
    public void run() {
       
    }
    
    private void sprawdzPole(int x, int y){
        sprawdzPole(x,y,true);
    }
    
    private void sprawdzPole(int x, int y, boolean komunikaty){
        boolean rysujObwodke = false;
        int rodzajStatku = 20;  // 20 nie reprezentuje zadnego pojazdu. Jesli po przeleceniu petli ta wartosc zostanie, znaczy że trafiono pudło.
       
        
        
        for (int i = 0 ; i< 17 ; i++){
            if (wspolrzendneStatkow[i][x][y]==2){
                rodzajStatku = i;                   // znaleziono wspolrzedne pola ze statkiem. w robryce else bedzie modul zaznaczania pudla, tutaj bedzie modol zaznaczania sdtatku
                wspolrzendneStatkow[i][x][y]=0; // ustala pole statku juz zatopionego na 0;
                plansza[x][y] = 2; //ustala pole planszy na trafione
            }                  
        }
        if (rodzajStatku!=20){
            if (zestrzelonePolaStatkow[rodzajStatku]>1) 
                {zestrzelonePolaStatkow[rodzajStatku]--;
                if(rodzajStatku==0) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Samolot! \n"); oknogry.jLabel8.setForeground(Color.RED);}
                else if(rodzajStatku==1) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Czteromasztowiec Wodny! \n"); oknogry.jLabel1.setForeground(Color.RED);}
                else if(rodzajStatku==2 || rodzajStatku==3) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Trzymasztowiec Wodny! \n"); oknogry.jLabel2.setForeground(Color.RED);}
                else if((rodzajStatku>3) && (rodzajStatku<7)) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Dwumasztowiec Wodny! \n"); oknogry.jLabel3.setForeground(Color.RED);}
                else if((rodzajStatku>6) && (rodzajStatku<11)) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Jednomasztowiec Wodny! \n"); oknogry.jLabel5.setForeground(Color.RED);}// prawdopodobnie ta wartosc zstanie usunieta bo nie moze byc uzyta
                else if(rodzajStatku==11) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Czteromasztowiec Ladowy! \n"); oknogry.jLabel4.setForeground(Color.RED);}
                else if((rodzajStatku>11) && (rodzajStatku<14)) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Trzymasztowiec Ladowy! \n"); oknogry.jLabel7.setForeground(Color.RED);}
                else if((rodzajStatku>13) && (rodzajStatku<17)) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Trafiono Dwumasztowiec Ladowy! \n"); oknogry.jLabel6.setForeground(Color.RED);}
                wykonanyRuch();
                strzelonoPole();
                trafionych++; 
                trafionePoleX[trafionych] = x; trafionePoleY[trafionych] = y; // ustwia wspolrzedne ostatnio trafionego pola
                }
            else if (zestrzelonePolaStatkow[rodzajStatku]==1) 
                {zestrzelonePolaStatkow[rodzajStatku]--;
                if(rodzajStatku==0){ 
                    oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Samolot! \n");
                    samolotyDoTrafienia--;
                    oknogry.jLabel8.setForeground(Color.BLACK);
                }
                else if(rodzajStatku==1){ 
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Czteromasztowiec Wodny! \n");
                        czteroMasztowceWodneDoTrafienia--;
                        oknogry.jLabel1.setForeground(Color.BLACK);
                        }
                else if(rodzajStatku==2 || rodzajStatku==3){ 
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Trzymasztowiec Wodny! \n");
                        trzyMasztowceWodneDoTrafienia--;
                        oknogry.jLabel2.setForeground(Color.BLACK);
                        }
                else if((rodzajStatku>3) && (rodzajStatku<7)){ 
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Dwumasztowiec Wodny! \n");
                        dwuMasztowceWodneDoTrafienia--;
                        oknogry.jLabel3.setForeground(Color.BLACK);
                        }
                else if((rodzajStatku>6) && (rodzajStatku<11)){ 
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Jednomasztowiec Wodny! \n");
                        jednoMasztowceWodneDoTrafienia--;
                        oknogry.jLabel5.setForeground(Color.BLACK);
                        }
                else if(rodzajStatku==11){
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Czteromasztowiec Ladowy! \n");
                        czteroMasztowceLadoweDoTrafienia--;
                        oknogry.jLabel4.setForeground(Color.BLACK);
                        }
                else if((rodzajStatku>11) && (rodzajStatku<14)){
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Trzymasztowiec Ladowy! \n");
                        trzyMasztowceLadoweDoTrafienia--;
                        oknogry.jLabel7.setForeground(Color.BLACK);
                        }
                else if((rodzajStatku>13) && (rodzajStatku<17)){
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Zatopiono Dwumasztowiec Ladowy! \n");
                        dwuMasztowceLadoweDoTrafienia--;
                        oknogry.jLabel6.setForeground(Color.BLACK);
                        }
                wykonanyRuch();
                strzelonoPole();
                trafionych++; 
                trafionePoleX[trafionych] = x; trafionePoleY[trafionych] = y;
                rysujObwodke = true;
                  // po zestrzeleniu jakiegos pojazdu wartosc ta przybiera 0 poniewaz statek zostal zakonczony
               // trafionePoleX[trafionych] = -1; trafionePoleY[trafionych] = -1; // wartosci sa zerowane poniewaz nie trzeba juz szuukac pola do trafienia wokolo
                }
            else oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Bład! Strzelono statek juz zatopiony \n");

            }
        else{
            plansza[x][y] = 3; //stala pole planszy na pudlo
            if(komunikaty==true){
                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Komp Pudło! \n");
                wykonanyRuch();}
            pudlo = true;
            }
        silnik.trafPole(x,y); // odwolywanie sie do ustawionej mapy gracza i zaznaczanie trafionego pola

        ustawNapisy();
        
        if(rysujObwodke == true){
            zaznaczObwod();
            trafionych = 0;
        }    
    }
    
    public boolean getPudlo(){
        return pudlo;
    }
    
    public void setPudlo(boolean pudlo){
        this.pudlo = pudlo;
    }
    
    public void wykonanyRuch(){
        wykonanychRuchowKomputera++;
        oknogry.jTextField4.setText(""+wykonanychRuchowKomputera);
        oknogry.jTextField8.setText(""+(wykonanychRuchowKomputera-ustrzelonychPol));
    }
    
    public void strzelonoPole(){
        ustrzelonychPol++;
        oknogry.jTextField6.setText(""+ustrzelonychPol);
        oknogry.jTextField8.setText(""+(wykonanychRuchowKomputera-ustrzelonychPol));
        if(ustrzelonychPol>=47){
            JOptionPane.showMessageDialog(null, "Przegrałeś! Wygrał Komputer!"); //wygrał gracz//wygrał Komputer
        }
    }
    
    private void ustalPola(){
        zestrzelonePolaStatkow[0]=11;                     // samolot = 1 , numer statku = 0 [0]?
        zestrzelonePolaStatkow[1]=4;                      // czteroMasztowceWodne = 2 nume1r statku = 1 [1]
        zestrzelonePolaStatkow[2]=3;  
        zestrzelonePolaStatkow[3]=3;                      // trzyMasztowceWodne = 2 numer statku = 2 [2][3]
        zestrzelonePolaStatkow[4] = 2;  
        zestrzelonePolaStatkow[5] = 2;  
        zestrzelonePolaStatkow[6] = 2;                    //dwuMasztowceWodne = 3 numer statku = 3 [4][5][6]
        zestrzelonePolaStatkow[7] = 1; 
        zestrzelonePolaStatkow[8] = 1; 
        zestrzelonePolaStatkow[9] = 1; 
        zestrzelonePolaStatkow[10] = 1;                   // jednoMasztowceWodne = 4 numer statku = 4 [7][8][9][10]
        zestrzelonePolaStatkow[11] = 4;                   //czteroMasztowceLadowe = 1 numer statku = 5 [11]
        zestrzelonePolaStatkow[12] = 3; 
        zestrzelonePolaStatkow[13] = 3;                   //trzyMasztowceLadowe = 2numer statku = 6 [12][13]
        zestrzelonePolaStatkow[14] = 2;
        zestrzelonePolaStatkow[15] = 2;
        zestrzelonePolaStatkow[16] = 2;                   //dwuMasztowceLadowe = 3 numer statku = 7 [14][15][16]
    
    }
    
    public void ustawNapisy(){
        oknogry.jLabel1.setText(""+(1-czteroMasztowceWodneDoTrafienia));
        oknogry.jLabel2.setText(""+(2-trzyMasztowceWodneDoTrafienia));
        oknogry.jLabel3.setText(""+(3-dwuMasztowceWodneDoTrafienia));
        oknogry.jLabel5.setText(""+(4-jednoMasztowceWodneDoTrafienia));
        oknogry.jLabel4.setText(""+(1-czteroMasztowceLadoweDoTrafienia));
        oknogry.jLabel7.setText(""+(2-trzyMasztowceLadoweDoTrafienia));
        oknogry.jLabel6.setText(""+(3-dwuMasztowceLadoweDoTrafienia));
        oknogry.jLabel8.setText(""+(1-samolotyDoTrafienia));
        
    // tu bedzie odwolywanie się do labeli w oknie i ustalanie liczby statków zestrzelonych przez komputer
    }
    

    
    public void strzelajLosowo(){
        pudlo = false;
        Random m = new Random();
        boolean trafiony = true;
        int x = 0;
        int y = 0;
        int proba = 0;
        if(trafionych>0){
            while(trafiony){
                proba = 1+m.nextInt(trafionych); // musi zostac wylosowane raz, aby i x i y odnosił sie do jednego pola a nie dwoch roznych
                
                x = trafionePoleX[proba] - 1 + m.nextInt(3);
                if( x == trafionePoleX[proba]){
                    y = trafionePoleY[proba] - 1 + m.nextInt(3);
                }
                else{
                    y = trafionePoleY[proba];
                }
                
                if ((x<14 && x>=0 && y<22 && y>=0) && plansza[x][y] != 4 && plansza[x][y] != 3){
                    trafiony = false;
                }   
            }
        
        }
        else{
            while(trafiony){
                x = m.nextInt(14);
                y = m.nextInt(22);
                if (plansza[x][y] != 4 && plansza[x][y] != 3){
                    trafiony = false;
                }   
            }
        }        
        try{Thread.sleep(1000);} catch(InterruptedException e){}
        sprawdzPole(x,y);
    }
    
    public void zaznaczObwod(){
        
        try{Thread.sleep(1000);} catch(InterruptedException e){}
        int x = 0;
        int y = 0;
     
        for(int proba = trafionych ; proba>0 ; proba--){
            for(int i = 0 ; i<8 ; i++){
                switch(i){
                    case 0:{x=trafionePoleX[proba]-1; y=trafionePoleY[proba]-1; break;}
                    case 1:{x++;break;}
                    case 2:{x++;break;}
                    case 3:{y++;break;}
                    case 4:{y++;break;}
                    case 5:{x--;break;}
                    case 6:{x--;break;}
                    case 7:{y--;break;}
                }
                if ((x<14 && x>=0 && y<22 && y>=0) && plansza[x][y] != 4 && plansza[x][y] != 3){
                    sprawdzPole(x,y,false);
                }   
            }
        }
    }
    
    public void zeruj(){
        samolotyDoTrafienia = 1;                  
        czteroMasztowceWodneDoTrafienia = 1;       
        trzyMasztowceWodneDoTrafienia = 2;      
        dwuMasztowceWodneDoTrafienia = 3;         
        jednoMasztowceWodneDoTrafienia = 4;        
        czteroMasztowceLadoweDoTrafienia = 1;    
        trzyMasztowceLadoweDoTrafienia = 2;        
        dwuMasztowceLadoweDoTrafienia = 3;  
        pudlo = false;
    
        wykonanychRuchowKomputera =0;
        ustrzelonychPol=0;
        wspolrzendneStatkow=edytor.getWspolrzendneStatkow();
        plansza = silnik.getPlansza();
    }
    
    
}
