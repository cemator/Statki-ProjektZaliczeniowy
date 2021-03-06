/*
 Wojna morska rozbudowana
Gra odbywa się ze zwiększonym kompletem jednostek o innym kształcie na zwiększonej planszy  (22 × 14), 
podzielonej umownie na rejony „morski” i „lądowy”. 
Niektóre jednostki mogą zostać umieszczone tylko na lądzie, inne tylko na morzu; 
jeden rodzaj jednostki (samoloty w kształcie litery T) na obu rejonach. 
Wszystkie jednostki w rozbudowanej wojnie morskiej mogą być umieszczone na polu 
walki w dowolnym położeniu, także „odwrócone na drugą stronę”. 
Pozostałe zasady gry nie ulegają zmianie.

Funkcjonalności:
Edytor i ustawianie statków (1,2,3,4masztowce wodne, 2,3,4elementowe pojazdy lądowe, samoloty T).
Podpowiadanie o braku możliwości ustawienia statków aby te się stykały.
Gra z komputerem.
Zapis + odczyt planszy ze statkami do pliku tekstowego.
Ładny, graficzny wygląd z GUI.
Kontrola liczby statków
Ergonomiczny interfejs (m.in. dwie plansze – swoja oraz „do strzelania”, zaznaczanie miejsc już trafianych, itp.)
Informacja o liczbie strzałów.   !!!!
Wszystko z użyciem wzorców projektowych.
Możliwość losowego umieszczenia statków na planszy użytkownika. !!!!
* Efekty dźwiękowe
* Gra sieciowa dwóch graczy (po Socketach)
* Różne stopnie trudności gry + nienaiwny algorytm strzałów komputera (tj. przykładowo – nie strzela w pojedyncze miejsca, gdy ma za zadanie trafić dwumasztowce)
Przejrzystość kodu, nazwy zmiennych i komponentów.
Ergonomia interfejsu użytkownika, w tym przejrzyste menu oraz wygodne sterowanie.
Odporność na błędy.
Wykorzystanie wzorców projektowych (np. Singleton do zapisu konfiguracji, Dekorator/Fabryka do statków/samolotów/czołgów, itp.)
Funkcjonalności oznaczone znakiem * są opcjonalne.

 

 */
package projektzaliczeniowyv1;

import java.awt.Color;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Seweryn
 */
public class OknoGry extends javax.swing.JFrame  {

    /**
     * Creates new form OknoGry
     */
    
    
    public Edytor Edytor = new Edytor(OknoGry.this);
    public Silnik_Gry silnik = new Silnik_Gry(Edytor, this);
    public Gra gra = new Gra(silnik, Edytor);
    public Zapis_Odczyt zapis_odczyt = new Zapis_Odczyt(silnik, Edytor);
    public boolean start = false;
    public StrzelanieKomputera strzelaniekomputera;
    
    public Gra_Celowanie gra_celowanie = new Gra_Celowanie(silnik, Edytor, OknoGry.this);
    
    public OknoGry() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel1 = gra;
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel_Celowanie = gra_celowanie;
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();

        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\Users\\Seweryn\\Documents\\NetBeansProjects\\ProjektZaliczeniowyv1"));
        jFileChooser1.setFileFilter(new FileNameExtensionFilter("Zapisy Gry", "game"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jButton1.setText("Cofnij");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 544, -1, -1));

        jButton2.setText("ObrocP");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 544, -1, -1));

        jButton3.setText("ObrocL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 544, -1, -1));

        jButton4.setText("Odbij");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 544, -1, -1));

        jButton5.setText(" 4 masztowiec wodny");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 150, 43));

        jButton6.setText("3 masztowiec wodny");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 150, 40));

        jButton8.setText(" 2 masztowiec wodny");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 150, 43));

        jButton9.setText("1 masztowiec wodny");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 150, 40));

        jButton10.setText("2 masztowiec ladowy");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 150, 40));

        jButton11.setText("4 masztowiec ladowy");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 150, 40));

        jButton12.setText(" 3 masztowiec ladowy");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 150, 43));

        jButton13.setText("samolot T");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 150, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 26, 43));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 26, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 26, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("1");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 26, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("4");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 26, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("3");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 26, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("2");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 26, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("1");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 26, -1));

        jButton14.setText("Zapisz");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 71, -1));

        jButton15.setText("Wczytaj");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, -1, -1));

        javax.swing.GroupLayout jPanel_CelowanieLayout = new javax.swing.GroupLayout(jPanel_Celowanie);
        jPanel_Celowanie.setLayout(jPanel_CelowanieLayout);
        jPanel_CelowanieLayout.setHorizontalGroup(
            jPanel_CelowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel_CelowanieLayout.setVerticalGroup(
            jPanel_CelowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_Celowanie, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 300, 452));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("2");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 26, 41));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("1");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, 26, 42));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("1");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 26, 41));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("2");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 26, 41));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("3");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 26, 41));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("1");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 220, 26, 41));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("4");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 26, 41));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("3");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 26, 41));

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton16.setText("START");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 560, -1, -1));

        jButton7.setText("LosowaMapa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 499, -1, -1));

        jButton17.setText("Zeruj mape");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 499, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, 530, 120));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 80, 50));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextField4.setText("0");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 80, 50));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextField5.setText("0");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, 80, 50));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextField6.setText("0");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 80, 50));

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextField7.setText("0");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 80, 50));

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jTextField8.setText("0");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 80, 50));

        jLabel17.setText("Strzałów");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, -1, -1));

        jLabel18.setText("Strzałów");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        jLabel19.setText("Pudeł");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, -1, -1));

        jLabel20.setText("Pudeł");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, -1, -1));

        jLabel21.setText("Trafionych pól");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, -1, -1));

        jLabel22.setText("Trafionych pól");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, -1));

        jButton18.setText("Nowa Gra");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (start==false){        ///Gra gra = new Gra();
        Edytor.cofnij();
        silnik.cofnij();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Edytor.obrocEdytorP();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       Edytor.obrocEdytorL();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Edytor.odbijEdytor();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Edytor.ustaw4wodny();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Edytor.ustaw3wodny();// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Edytor.ustaw2wodny();// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Edytor.ustaw1wodny();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Edytor.ustaw2ladowy();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Edytor.ustaw4ladowy();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Edytor.ustaw3ladowy();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Edytor.ustawSamolotT(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int wysOkno = jFileChooser1.showSaveDialog(this); 
        String nazwa_pliku = "";
        if(wysOkno == javax.swing.JFileChooser.APPROVE_OPTION){           
            java.io.File plik = jFileChooser1.getSelectedFile();
            nazwa_pliku = plik.toString( );
            try {
                zapis_odczyt.Zapisz(nazwa_pliku);       
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }    
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        
        int wysOkno = jFileChooser1.showOpenDialog(this); 
        String nazwa_pliku = "";
        if(wysOkno == javax.swing.JFileChooser.APPROVE_OPTION){           
            java.io.File plik = jFileChooser1.getSelectedFile();
            nazwa_pliku = plik.toString( );
            try {
                zapis_odczyt.Odczyt(nazwa_pliku);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
               System.out.println(ex.toString());
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
        strzelaniekomputera = new StrzelanieKomputera(Edytor, silnik, this);
        gra_celowanie.setStrzelanieKomputera(strzelaniekomputera);
        gra_celowanie.start();
        start = true;        // TODO add your handling code here:
        
        //Sekwencja_Ruchu sekwencja_ruchu = new Sekwencja_Ruchu(gra_celowanie, strzelaniekomputera);
        //sekwencja_ruchu.run();
        /*for(;;){
            if(gra_celowanie.getPauza()==true){
                strzelaniekomputera.strzelajLosowo();
                try{Thread.sleep(1000);} catch(InterruptedException e){}
            }
            if(strzelaniekomputera.getPudlo()==true){
                gra_celowanie.setPauza(false);
            }
        
        }*/
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Uwaga! Wylosowanie mapy spowoduje zmazanie obecnego ustawienia mapy bez możliwości cofnięcia. Kontynuować? \n");
        LosowaMapa mapa = new LosowaMapa(); 
        jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Wylosowano mapę! \n");
        silnik.zeroj();
        Edytor.zeroj();   // mozna by tu wstawić dodawanie losowej mapy jako poprostu klejna "zmiana" ale narazie, aby nie powodować błędów niech losowanie wyzeruje mape. Dodać komunikac ostrzegający przed wyzerowaniem wczesniejszego ustawienia mapy!
        silnik.setPlansza(mapa.getPlansza());
        Edytor.setWspolrzendneStatkow(mapa.getWspolrzendneStatkow());
        Edytor.setIlosciStatkow(mapa.getIlosciStatkow());   
        Edytor.setZmian(0);
        //int[][] buforiloscistatkow
        
        Edytor.setBuforIlosciStatkow(mapa.getBuforIlosciStatkow());
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        silnik.zeroj();
        Edytor.zeroj();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        Edytor.zeruj();
        gra_celowanie.zeruj();
        silnik.zeruj();
        strzelaniekomputera.zeruj(); /*
        Edytor = new Edytor(OknoGry.this);
        silnik = new Silnik_Gry(Edytor, this);
        gra = new Gra(silnik, Edytor);
        zapis_odczyt = new Zapis_Odczyt(silnik, Edytor);
        start = false;
        gra_celowanie = new Gra_Celowanie(silnik, Edytor, OknoGry.this);
        */
        jTextField3.setText("0");
        jTextField4.setText("0");
        jTextField5.setText("0");
        jTextField6.setText("0");
        jTextField7.setText("0");
        jTextField8.setText("0");
        
        jLabel1.setForeground(Color.black);
        jLabel2.setForeground(Color.black);
        jLabel3.setForeground(Color.black);
        jLabel4.setForeground(Color.black);
        jLabel5.setForeground(Color.black);
        jLabel6.setForeground(Color.black);
        jLabel7.setForeground(Color.black);
        jLabel8.setForeground(Color.black);
        jLabel9.setForeground(Color.black);
        jLabel10.setForeground(Color.black);
        jLabel11.setForeground(Color.black);
        jLabel12.setForeground(Color.black);
        jLabel13.setForeground(Color.black);
        jLabel14.setForeground(Color.black);
        jLabel15.setForeground(Color.black);
        jLabel16.setForeground(Color.black);
        
        jLabel1.setText("1");//
        jLabel2.setText("2");
        jLabel3.setText("3");
        jLabel4.setText("1");//
        jLabel5.setText("4");
        jLabel6.setText("3");
        jLabel7.setText("2");
        jLabel8.setText("1");//
        jLabel9.setText("2");
        jLabel10.setText("1");//
        jLabel11.setText("1");//
        jLabel12.setText("2");
        jLabel13.setText("3");
        jLabel14.setText("1");//
        jLabel15.setText("4");
        jLabel16.setText("3");
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OknoGry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OknoGry().setVisible(true);
                
            }
        });
       
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFileChooser jFileChooser1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JOptionPane jOptionPane1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Celowanie;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}

/*
26.02 funkcjonalnosci do wprowadzenia:
///-podswietlanie na czerwono nuerka statku trafionego lecz nie zatopionego
-przycisk od zerowania calkowitego gry (mapa przeciwnika, plansza do celowania, nasza plansza
-mechanizm sekwencji ruchów (kto kiedy może strzelać) wraz z kontrolkami
///-lekkie opoznienie komputera (zeby gra naturalniej wygladała)
///-liczenie wykonanych ruchów
///-ustalanie kto wygrał na podstawie zatopionych statkow
//-robienie obwódki wokoło zatopionego statku (lesli uda sie z opóznieniem to bedzie juz super...)
- menu z wyborem kolorów dla plansz - color chooser!

rzeczy konieczne:
///-mechanizm losowego strzelania komputera do naszych statkow
///-nie zakreslanie przy oddaniu naszych strzalow statkow na naszej mapie
///-kolorwanie "pudeł" na rozne kolory w zaleznosci od wody i ladu a nie na fiolet ten sam


*/

// aktulizacja nastepuje po ruchu gracza bo cała gra toczy sie w metodzie klikniecia, a wiec nawet ruch gracza jest odppowiedzia na klikniecie co ma priorytet, a wiec wszystkie inne aktualizacje moga zastac wykanone dopiero po skonczeniu wykonywania reakcja na klik