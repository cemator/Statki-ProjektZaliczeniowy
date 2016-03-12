/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzaliczeniowyv1;


import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.JOptionPane;


/**
 *
 * @author Seweryn
 */
public class Gra_Celowanie extends javax.swing.JPanel implements Runnable, MouseListener  {
  
    
    private static final byte SZEROKOSC_PLANSZY = 14;
    private static final byte WYSOKOSC_PLANSZY = 22;
    private static final byte POLE_SZ = 20, POLE_WY = 20;
    private static final int SZEROKOSC = SZEROKOSC_PLANSZY*POLE_SZ ;
    private static final int WYSOKOSC = WYSOKOSC_PLANSZY*POLE_WY ;
    
    private StrzelanieKomputera strzelaniekomputera;
    
    private Image obraz;
    private Graphics2D grafika;
    private Thread watek;
    private int opoznienie = 100;
 
    private int plansza[][] = new int[SZEROKOSC_PLANSZY][WYSOKOSC_PLANSZY];
    //private int[][] edytor;
    Silnik_Gry silnik;  
    Edytor Edytor;
    
    private int[][][] wspolrzendneStatkow = new int[17][14][22];
    private int[] zestrzelonePolaStatkow = new int[17];
    
    private int samolotyDoTrafienia = 1;                   //numer statku = 0 [0]
    private int czteroMasztowceWodneDoTrafienia = 1;       //numer statku = 1 [1]
    private int trzyMasztowceWodneDoTrafienia = 2;         //numer statku = 2 [2][3]
    private int dwuMasztowceWodneDoTrafienia = 3;          //numer statku = 3 [4][5][6]
    private int jednoMasztowceWodneDoTrafienia = 4;        //numer statku = 4 [7][8][9][10]
    private int czteroMasztowceLadoweDoTrafienia = 1;      //numer statku = 5 [11]
    private int trzyMasztowceLadoweDoTrafienia = 2;        //numer statku = 6 [12][13]
    private int dwuMasztowceLadoweDoTrafienia = 3;         //numer statku = 7 [14][15][16]
    private OknoGry oknogry;
    public boolean start = false;
    private boolean pauza = false;
    private boolean pudlo = false;
    private LosowaMapa losowamapa;
    
    private int wykonanychRuchowGracza =0;
    private int ustrzelonychPol = 0;
    

    
   
    
    private int[] trafionePoleX = new int[12];  // w tych zmiennych beda trzymane wspolrzedne pola trafionego ale nie zatopionego statku w celu trafienia okolicznych pół
    private int[] trafionePoleY = new int[12];
    private int trafionych = 0; // ta zmienna mowi czy jakis statek zostal nieskonczony. Jesli statek zostanie zatopiony wartosc przyjmuje znow false
    
    
    
    
    public Gra_Celowanie(Silnik_Gry silnik, Edytor Edytor,OknoGry oknogry){
        super();
        this.oknogry = oknogry;  
        this.silnik = silnik;
        this.Edytor = Edytor;
        
        obraz = new BufferedImage(SZEROKOSC, WYSOKOSC, BufferedImage.TYPE_INT_BGR);
        grafika =(Graphics2D)obraz.getGraphics();   
        
    }
    
    public void setStrzelanieKomputera(StrzelanieKomputera strzelaniekomputera){
    this.strzelaniekomputera = strzelaniekomputera;
    }
    
    @Override
    public void addNotify(){ // faza uruchomienia
        super.addNotify();
        watek = new Thread(this);
        addMouseListener(this);
        watek.start();
    }

    @Override
    public void run() {       
        for(int x = 0 ; x<SZEROKOSC_PLANSZY ; x++){
            for(int y=0; y<WYSOKOSC_PLANSZY ; y++){
                if(y>15){
                    plansza[x][y] = 1;
                }
                else {
                    plansza[x][y] = 0;
                }
            }
        }
        ustalPola();
       
        do {
            //plansza = silnik.getPlansza();
            //edytor = Edytor.getEdytor();
            
            
            drukGrafika();
            drukNaEkran();
            
            
            try {
                watek.sleep(opoznienie);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();          //sekwencja do-while jest powtarzana na okrągło z odstepem 100ms(opoznienie)
            } 
        }    
        while (true);
    }
    
    
    private void drukGrafika(){ 
        grafika.setColor(Color.WHITE);
        grafika.fillRect(0, 0, SZEROKOSC, WYSOKOSC);
        drukPlansza();
         
    }
    
    private void drukPlansza(){
        for (int x=0; x<SZEROKOSC_PLANSZY; x++){
            for (int y=0; y<WYSOKOSC_PLANSZY; y++){
                if (plansza[x][y]==1) {
                    grafika.setColor(Color.YELLOW);
                } 
                else if (plansza[x][y]==0){
                    grafika.setColor(Color.CYAN);
                }
                else if (plansza[x][y]==2){
                    grafika.setColor(Color.RED);
                }
                else if (plansza[x][y]==3){
                    if(y<16)grafika.setColor(Color.magenta);
                    else grafika.setColor(Color.pink);
                    // trafione pudło
                }
                
                grafika.fillRect(x*POLE_SZ, y*POLE_WY, POLE_SZ, POLE_WY);
                grafika.setColor(Color.LIGHT_GRAY);
                grafika.drawRect(x*POLE_SZ, y*POLE_WY, POLE_SZ, POLE_WY);
                
            }   
        }
       
  
    }
    
    private void drukNaEkran(){
        Graphics ekran = getGraphics();
        ekran.drawImage(obraz, 0, 0, null);
        ekran.dispose();
    }
    
    public void start(){
        losowamapa = new LosowaMapa();
       // wspolrzendneStatkow=Edytor.getWspolrzendneStatkow(); //stara wersja z celowaniem do wlasniej mapy!
        wspolrzendneStatkow = losowamapa.getWspolrzendneStatkow();
        start=true;
    }
    
    public void setPauza(boolean pauza){
        this.pauza = pauza;
    }
    
    public boolean getPauza(){
        return pauza;
    }
    
    public boolean getPudlo(){
        return pudlo;
    }
    
    private void sprawdzPole(int x, int y){
        sprawdzPole(x,y,true);
    }
    
    private void sprawdzPole(int x, int y, boolean komunikaty){
        
        boolean rysujObwodke = false;
        int rodzajStatku = 20;  // 20 nie reprezentuje zadnego pojazdu. Jesli po przeleceniu petli ta wartosc zostanie, znaczy że trafiono pudło.
       
        if(start == false) oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Aby rozpocząć naciśnij przycisk Start. \n");
        else if(pauza==true) oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Poczekaj, przeciwnik nie zakończył jeszcze ruchu \n");
            else{
            if (plansza[x][y] == 2 || plansza[x][y] == 3){if(komunikaty==true)oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"To pole zostało już przez Ciebie trafione! \n");} // tutaj warunek czy pole ktore klika urzytkownik nie jest juz sprawdzone(zatopiony pojazd, pudło). Jesli tak to zakonczyc na tym etapie
            else{
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
                        if(rodzajStatku==0){ oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Samolot! \n"); oknogry.jLabel10.setForeground(Color.RED);}
                        else if(rodzajStatku==1) {oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Czteromasztowiec Wodny! \n"); oknogry.jLabel11.setForeground(Color.RED);}
                        else if(rodzajStatku==2 || rodzajStatku==3){oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Trzymasztowiec Wodny! \n"); oknogry.jLabel12.setForeground(Color.RED);}
                        else if((rodzajStatku>3) && (rodzajStatku<7)){ oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Dwumasztowiec Wodny! \n"); oknogry.jLabel13.setForeground(Color.RED);}
                        else if((rodzajStatku>6) && (rodzajStatku<11)){oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Jednomasztowiec Wodny! \n"); oknogry.jLabel15.setForeground(Color.RED);}
                        else if(rodzajStatku==11){ oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Czteromasztowiec Ladowy! \n"); oknogry.jLabel14.setForeground(Color.RED);}
                        else if((rodzajStatku>11) && (rodzajStatku<14)){ oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Trzymasztowiec Ladowy! \n"); oknogry.jLabel9.setForeground(Color.RED);}
                        else if((rodzajStatku>13) && (rodzajStatku<17)){ oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Trafiono Dwumasztowiec Ladowy! \n"); oknogry.jLabel16.setForeground(Color.RED);}
                        wykonanyRuch();
                        strzelonoPole();
                        trafionych++; 
                        trafionePoleX[trafionych] = x; trafionePoleY[trafionych] = y; // ustwia wspolrzedne ostatnio trafionego pola
                        }
                    else if (zestrzelonePolaStatkow[rodzajStatku]==1) 
                        {zestrzelonePolaStatkow[rodzajStatku]--;
                        if(rodzajStatku==0){ 
                            oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Samolot! \n");
                            samolotyDoTrafienia--;
                            oknogry.jLabel10.setForeground(Color.BLACK);
                        }
                        else if(rodzajStatku==1){ 
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Czteromasztowiec Wodny! \n");
                                czteroMasztowceWodneDoTrafienia--;
                                oknogry.jLabel11.setForeground(Color.BLACK);
                                }
                        else if(rodzajStatku==2 || rodzajStatku==3){ 
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Trzymasztowiec Wodny! \n");
                                trzyMasztowceWodneDoTrafienia--;
                                oknogry.jLabel12.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>3) && (rodzajStatku<7)){ 
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Dwumasztowiec Wodny! \n");
                                dwuMasztowceWodneDoTrafienia--;
                                oknogry.jLabel13.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>6) && (rodzajStatku<11)){ 
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Jednomasztowiec Wodny! \n");
                                jednoMasztowceWodneDoTrafienia--;
                                oknogry.jLabel15.setForeground(Color.BLACK);
                                }
                        else if(rodzajStatku==11){
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Czteromasztowiec Ladowy! \n");
                                czteroMasztowceLadoweDoTrafienia--;
                                oknogry.jLabel14.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>11) && (rodzajStatku<14)){
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Trzymasztowiec Ladowy! \n");
                                trzyMasztowceLadoweDoTrafienia--;
                                oknogry.jLabel9.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>13) && (rodzajStatku<17)){
                                oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Zatopiono Dwumasztowiec Ladowy! \n");
                                dwuMasztowceLadoweDoTrafienia--;
                                oknogry.jLabel16.setForeground(Color.BLACK);
                                }
                        wykonanyRuch();
                        strzelonoPole();
                        trafionych++; 
                        trafionePoleX[trafionych] = x; trafionePoleY[trafionych] = y;
                        rysujObwodke = true;
                        }
                    else oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Bład! Strzelono statek juz zatopiony \n");

                    }
                else{
                    plansza[x][y] = 3; //stala pole planszy na pudlo
                    if(komunikaty==true){
                        oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Pudło! \n");pauza = true;oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Teraz ruch Komputera \n"); ruchGracza(1);
                        wykonanyRuch();}
                    
                    }
                //silnik.trafPole(x,y); // odwolywanie sie do ustawionej mapy gracza i zaznaczanie trafionego pola

                ustawNapisy();
                
                if(rysujObwodke == true){
                   zaznaczObwod();
                   trafionych = 0;
                }
            }
        }
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
                     //try{Thread.sleep(150);} catch(InterruptedException e){} proba wprowadzenia opoznienia azeby obwodkowanie statkow ladnie wygladało, ale wystepuje to nieregularnie i nie jest ladne. Trzeba by wyznaczyc inny system otaczania trafionych pól.
                    sprawdzPole(x,y,false);
                }   
            }
        }
    }
    
    public void wykonanyRuch(){
        wykonanychRuchowGracza++;
        oknogry.jTextField3.setText(""+wykonanychRuchowGracza);
        oknogry.jTextField7.setText(""+(wykonanychRuchowGracza-ustrzelonychPol));
    }
    public void strzelonoPole(){
        ustrzelonychPol++;
        oknogry.jTextField5.setText(""+ustrzelonychPol);
        oknogry.jTextField7.setText(""+(wykonanychRuchowGracza-ustrzelonychPol));
        if(ustrzelonychPol>=47){
           JOptionPane.showMessageDialog(null, "Gratuluję, wygrałeś z wynikiem " + wykonanychRuchowGracza + " ruchów!"); //wygrał gracz
           start=false;
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
                                                                              
    
    public void ruchGracza(int gracz){ // metoda malujacz tło napisów na zielono w zaleznosci od tego kto ma ruch. ruch uzytkownika ==0, komputera ==1
        /*if (gracz==0){
            oknogry.jTextField2.setBackground(Color.green);
            oknogry.jTextField2.setText("ok");
            oknogry.jTextField1.setBackground(Color.red);
            oknogry.jTextField1.setText("no");
        }
        if(gracz==1){
            oknogry.jTextField1.setBackground(Color.green);
            oknogry.jTextField1.setText("ok");
            oknogry.jTextField2.setBackground(Color.red);
            oknogry.jTextField2.setText("no");
           
        }
        */
    }
    
    
    public void ustawNapisy(){
        oknogry.jLabel11.setText(""+czteroMasztowceWodneDoTrafienia);
        oknogry.jLabel12.setText(""+trzyMasztowceWodneDoTrafienia);
        oknogry.jLabel13.setText(""+dwuMasztowceWodneDoTrafienia);
        oknogry.jLabel15.setText(""+jednoMasztowceWodneDoTrafienia);
        oknogry.jLabel14.setText(""+czteroMasztowceLadoweDoTrafienia);
        oknogry.jLabel9.setText(""+trzyMasztowceLadoweDoTrafienia);
        oknogry.jLabel16.setText(""+dwuMasztowceLadoweDoTrafienia);
        oknogry.jLabel10.setText(""+samolotyDoTrafienia);
        
    // tu bedzie odwolywanie się do labeli w oknie i ustalanie liczby statków
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
       start = false;
       pauza = false;
       pudlo = false;
       wykonanychRuchowGracza =0;
       ustrzelonychPol = 0;
       trafionych = 0; 
       for(int x = 0 ; x<SZEROKOSC_PLANSZY ; x++){
            for(int y=0; y<WYSOKOSC_PLANSZY ; y++){
                if(y>15){
                    plansza[x][y] = 1;
                }
                else {
                    plansza[x][y] = 0;
                }
            }
        }
        ustalPola();

    }
    
    
    @Override
    public void mousePressed(MouseEvent me) {//pobranie pola klikniecia myszki i zamiana wspolrzednych na wartosc tablicy
        if (me.getY()<WYSOKOSC_PLANSZY*POLE_WY && me.getX()<SZEROKOSC_PLANSZY*POLE_SZ){  //przechwytuje klikniecie na plansze
            int x = me.getX()/POLE_SZ; 
            int y = me.getY()/POLE_WY;
            sprawdzPole(x,y);                                                         //przkazuje silnikowi tylko i wylacznie informacje o tym w ktory punkt planszy kliknieto 
        }
        
        if(start==true){
            while(pauza==true){
                if(strzelaniekomputera.getPudlo()==false){
                    strzelaniekomputera.strzelajLosowo();
                    if(strzelaniekomputera.ustrzelonychPol>=47){start=false;}
                }
                else {pauza=false; strzelaniekomputera.setPudlo(false); oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Teraz ruch Gracza \n"); 
                ruchGracza(0);}
            }
            
           // oknogry.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Teraz ruch Gracza \n");
        }
    }
 
    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
    @Override
    public void mouseReleased(MouseEvent me) {}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
