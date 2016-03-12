/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzaliczeniowyv1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset; */

/**
 *
 * @author Seweryn
 */
public class Zapis_Odczyt implements Serializable{
    private Silnik_Gry silnik;
    private Edytor edytor;
    
    public Zapis_Odczyt(Silnik_Gry silnik, Edytor edytor){
        this.silnik = silnik;
        this.edytor = edytor;
    }
    
    
    public void Zapisz(String nazwa_pliku) throws IOException{
            /*                                                           
            Writer out;
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nazwa_pliku)));
           // out.write(jTextPane1.getText());
            out.flush();
            out.close();
        } catch (IOException iOException) {
        }
    */
             ObjectOutputStream pl=null;
        try{
            pl=new ObjectOutputStream(new FileOutputStream(nazwa_pliku));
            pl.writeObject(silnik.getPlansza());
            pl.writeObject(silnik.getTemp()); //int[][][]
            pl.writeObject(silnik.getZmian()); //int 
             
            pl.writeObject(edytor.getWspolrzendneStatkow());  //int[][][] 
            pl.writeObject(edytor.getBuforIlosciStatkow());
            pl.writeObject(edytor.getZmian());  //int 
                     
            pl.flush();
        }
        finally{
            if(pl!=null)
                pl.close();
        }
            
    }
    
    public void Odczyt(String nazwa_pliku) throws IOException, ClassNotFoundException{
        ObjectInputStream pl2=null;
        int[][] plansza = null;
        int[][][] temp = null;
        int szmian = 0;
        
        int[][][] wspolrzendneStatkow = null;
        int[][] buforIlosciStatkow = null;
        int ezmian = 0;
        
        try{
            pl2=new ObjectInputStream(new FileInputStream(nazwa_pliku));
            plansza=(int[][])pl2.readObject();
            temp = (int[][][])pl2.readObject();
            szmian = (int)pl2.readObject();
            
            wspolrzendneStatkow = (int[][][])pl2.readObject();
            buforIlosciStatkow = (int[][])pl2.readObject();
            ezmian = (int)pl2.readObject();
          
 
        } catch (EOFException ex) {
            System.out.println("Koniec pliku");
        }
 
        finally{
            if(pl2!=null)
                pl2.close();
        }
        
        silnik.setPlansza(plansza);
        silnik.setTemp(temp);
        silnik.setZmian(szmian);
        
        edytor.setZmian(ezmian);
        edytor.setWspolrzendneStatkow(wspolrzendneStatkow);
        edytor.setBuforIlosciStatkow(buforIlosciStatkow);
        
        
        
        
        /*                                              sprawdzanie konsolowe czy wszystko działa
        System.out.println("plansza: \n");
        for (int x = 0 ; x<14 ; x++){
                    for (int y = 0 ; y<22 ; y++){
                            System.out.print(" ["+plansza[x][y]+"]");
                    }
                    System.out.println();
                }
        
        System.out.println("temp: \n");
        
        for (int i = 0 ; i< 10 ; i++){
            System.out.println(""+ i);
                for (int x = 0 ; x<14 ; x++){
                    for (int y = 0 ; y<22 ; y++){
                            System.out.print(" ["+temp[i][x][y]+"]");
                    }
                }
        }
        
        System.out.println("szmian");
        System.out.println(szmian);
        
        
        System.out.println("wspolrzednestatkow \n");
        for (int i = 0 ; i< 17 ; i++){
            System.out.println("Pojazd nr "+ i);
                for (int x = 0 ; x<14 ; x++){
                    for (int y = 0 ; y<22 ; y++){
                            System.out.print(" ["+wspolrzendneStatkow[i][x][y]+"]");
                    }
                }
        }
        System.out.println("bufrstatkow: \n");
        for (int x = 0 ; x<10 ; x++){
                    for (int y = 0 ; y<8 ; y++){
                            System.out.print(" ["+buforIlosciStatkow[x][y]+"]");
                    }
                    System.out.println();
                }
        System.out.println(ezmian);
    */}
}
