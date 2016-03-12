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





/**
 *
 * @author Seweryn
 */
public class Gra extends javax.swing.JPanel implements Runnable, MouseListener  {
  
    
    private static final byte SZEROKOSC_PLANSZY = 14;
    private static final byte WYSOKOSC_PLANSZY = 22;
    private static final byte POLE_SZ = 20, POLE_WY = 20;
    private static final int SZEROKOSC = SZEROKOSC_PLANSZY*POLE_SZ + 130;
    private static final int WYSOKOSC = (WYSOKOSC_PLANSZY*POLE_WY)+15;
    
    private Image obraz;
    private Graphics2D grafika;
    private Thread watek;
    private int opoznienie = 100;
 
    private int plansza[][];
    private int[][] edytor;
    Silnik_Gry silnik;
    Edytor Edytor;
    
    
    public Gra(Silnik_Gry silnik, Edytor Edytor){
        super();
        this.silnik = silnik;
        this.Edytor = Edytor;
        obraz = new BufferedImage(SZEROKOSC, WYSOKOSC, BufferedImage.TYPE_INT_BGR);
        grafika =(Graphics2D)obraz.getGraphics();   
        
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
       
        do {
            plansza = silnik.getPlansza();
            edytor = Edytor.getEdytor();
            drukGrafika();
            drukNaEkran();
            
            
            try {
                watek.sleep(opoznienie);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();          //sekwencja do-wile jest powtarzana na okrągło z odstepem 100ms
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
                    grafika.setColor(Color.BLACK);
                }
                else if (plansza[x][y]==3){
                    if(y<16)grafika.setColor(Color.magenta);
                    else grafika.setColor(Color.pink);
                    // trafione pudło
                }
                else if (plansza[x][y]==4){
                    grafika.setColor(Color.RED); // trafiony statek
                }
                
                
                
                
                grafika.fillRect(x*POLE_SZ, y*POLE_WY, POLE_SZ, POLE_WY);
                grafika.setColor(Color.LIGHT_GRAY);
                grafika.drawRect(x*POLE_SZ, y*POLE_WY, POLE_SZ, POLE_WY);
                
            }   
        }
        
        for (int x=0; x<5; x++){
            for (int y=0; y<5; y++){
                
                if (edytor[x][y]==2) {
                    grafika.setColor(Color.BLACK);
                } 
                else if (edytor[x][y]==0){
                    grafika.setColor(Color.CYAN);
                }
                else if (edytor[x][y]==1){
                    grafika.setColor(Color.YELLOW);
                }
                
                grafika.fillRect((x+15)*POLE_SZ , y*POLE_WY, POLE_SZ, POLE_WY);
                grafika.setColor(Color.LIGHT_GRAY);
                grafika.drawRect((x+15)*POLE_SZ, y*POLE_WY, POLE_SZ, POLE_WY);
                
            }   
        }
  
    }
    
    private void drukNaEkran(){
        Graphics ekran = getGraphics();
        ekran.drawImage(obraz, 0, 0, null);
        ekran.dispose();
    }
    
    
                                                                              
    

    @Override
    public void mousePressed(MouseEvent me) {//pobranie pola klikniecia myszki i zamiana wspolrzednych na wartosc tablicy
           
        
        if (me.getY()<WYSOKOSC_PLANSZY*POLE_WY && me.getX()<SZEROKOSC_PLANSZY*POLE_SZ){  //przechwytuje klikniecie na plansze
            int x = me.getX()/POLE_SZ; 
            int y = me.getY()/POLE_WY;
            silnik.setPole(x,y);                                                         //przkazuje silnikowi tylko i wylacznie informacje o tym w ktory punkt planszy kliknieto
            
        } 
        else if (me.getY()<5*POLE_WY && me.getX()>15*POLE_SZ && me.getX()<20*POLE_SZ){  //przechwytuje klikniecie na edytor
            int x = (me.getX()-(15*POLE_SZ))/POLE_SZ; 
            int y = me.getY()/POLE_WY;
            Edytor.setEdytor(x,y);                                                      //przkazuje edytorowi tylko i wylacznie informacje o tym w ktory punkt edytor kliknieto
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
