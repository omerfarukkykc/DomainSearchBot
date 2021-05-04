
package Lepric.GUI;

import Lepric.Options.Options;
import Lepric.Tools.PrintTo;
import Lepric.Tools.SendMail;
import Lepric.Tools.WhoisApi;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


public class MainActivity extends javax.swing.JFrame {
    Options options = new Options();
    SideAction action; 
    Thread th;
    private Component lblMinHarfSayisi;
    
    public MainActivity() {
        initComponents();
        this.action = new SideAction(pbProgressBar);
    }
    
    
    class SideAction implements Runnable{
        JProgressBar pbar;
        SideAction(JProgressBar pbar){
            pbar = pbProgressBar;
            pbProgressBar.setStringPainted(true);
        }
        @Override
        public void run(){
            try{
               
                BufferedReader bufferedReader;
                PrintTo printTo;
                try (FileReader fileReader = new FileReader(options.getFile())) {
                    lstLogScreen.add("Program başladı"); 
                    options.countAndSetLineCount();
                    printTo = new PrintTo();
                    bufferedReader = new BufferedReader(fileReader);
                    String wordData;
                    WhoisApi whoisApi = new WhoisApi(options.getTimeOut());
                    while((wordData=bufferedReader.readLine())!=null){

                        options.setLiveDomain(wordData,false);
                        options.countLineCount();
                        //Verilen kelime datasının uzunluk kontrolü
                        if((wordData.length()<options.getWordLengthMax())&& wordData.length()>options.getWordLengthMin()){
                            //Whois sorgulaması yapar eğer kayıt Yoksa Alınabilir listesine ekler
                            if(whoisApi.checkWhois(wordData,options.getExtensions())!=null){
                                options.setLiveDomain(wordData,true);
                                printTo.Put(wordData,options.getExtensions());
                                options.countUsableLineCount();

                            }
                        }
                        lstLogScreen.add(options.getLiveDomainLog());
                        lstLogScreen.select(lstLogScreen.getItemCount()-1);
                        //txtLogScreen.append(options.getLiveDomainLog()+"\n");
                        pbProgressBar.setValue(options.progressCalculator(options.getLineCountAlive()));
                    }
                    fileReader.close();
                    bufferedReader.close();
                }
                printTo.Print(options.getPrintDirectory());
                if(chkboxSendCheck.isSelected()){
                    SendMail sendMail = new SendMail();
                    sendMail.Send(txtMailAdress.getText(),options.getLineCount(),options.getUsableLineCountAlive());
                    lstLogScreen.add("Mail Gönderildi Program sonlandı");
                    options.reset();
            }
            }catch(IOException e){
                e.getMessage();
            }catch(java.lang.NullPointerException e){
                JOptionPane.showMessageDialog(null, "Seçtiğiniz dosya bulunamadı.", "Lepric bot sistemleri", JOptionPane.PLAIN_MESSAGE);
            }
            
        }

        
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        pbProgressBar = new javax.swing.JProgressBar();
        Start = new javax.swing.JButton();
        CoBoxExtensions = new javax.swing.JComboBox<>();
        chkboxSendCheck = new javax.swing.JCheckBox();
        lblMaxHarfSayisi1 = new javax.swing.JLabel();
        lstLogScreen = new java.awt.List();
        txtMailAdress = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        txtChoosableFile = new javax.swing.JTextField();
        txtChoosableLocation = new javax.swing.JTextField();
        btnChooseLocation = new javax.swing.JButton();
        btnChooseFile = new javax.swing.JButton();
        SetWordLength = new javax.swing.JPanel();
        lblMaxHarfSayisi = new javax.swing.JLabel();
        spinMaxWordLength = new javax.swing.JSpinner();
        spinMinWordLength = new javax.swing.JSpinner();
        lblMaxHarfSayisi3 = new javax.swing.JLabel();
        lblMaxHarfSayisi2 = new javax.swing.JLabel();
        timeOut = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Çoklu alınabilir alan adı sorgu botu");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images.png"));
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Öneri ve talep için : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, 20));

        pbProgressBar.setForeground(new java.awt.Color(0, 0, 255));
        getContentPane().add(pbProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 420, 20));

        Start.setBackground(new java.awt.Color(30, 31, 36));
        Start.setForeground(new java.awt.Color(255, 255, 255));
        Start.setText("Başlat");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        getContentPane().add(Start, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 70, 30));

        CoBoxExtensions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".com", ".net", ".edu" }));
        CoBoxExtensions.setMinimumSize(new java.awt.Dimension(61, 23));
        CoBoxExtensions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoBoxExtensionsActionPerformed(evt);
            }
        });
        getContentPane().add(CoBoxExtensions, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 70, 30));

        chkboxSendCheck.setBackground(new java.awt.Color(30, 31, 36));
        chkboxSendCheck.setForeground(new java.awt.Color(255, 255, 255));
        chkboxSendCheck.setText("Bittiğinde bana e posta gönder");
        chkboxSendCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkboxSendCheckActionPerformed(evt);
            }
        });
        getContentPane().add(chkboxSendCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 210, -1));

        lblMaxHarfSayisi1.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxHarfSayisi1.setText("Aranacak uzantı");
        getContentPane().add(lblMaxHarfSayisi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 100, 30));

        lstLogScreen.setBackground(new java.awt.Color(30, 31, 36));
        lstLogScreen.setForeground(new java.awt.Color(0, 204, 51));
        getContentPane().add(lstLogScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 420, 190));

        txtMailAdress.setBackground(new java.awt.Color(30, 31, 36));
        txtMailAdress.setForeground(new java.awt.Color(255, 255, 255));
        txtMailAdress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMailAdress.setText("ornek@mail.com");
        txtMailAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailAdressActionPerformed(evt);
            }
        });
        getContentPane().add(txtMailAdress, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 200, 30));

        jButton2.setBackground(new java.awt.Color(30, 31, 36));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Destek olmak için tıklayın");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 180, 20));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(30, 31, 36));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("Max ve Min Harf Sayısı : Kelime datanızdaki maksimum ve minimum \nharf sayısını belirler\n\nAranacak Uzantı : Almak istediğiniz alan adının uzantısı\n\nZaman aşımı : Sorgu esnasında siteden cevap gelmez ise beklenecek \nsüredir. 5s idealdir\n\nYanlızca txt uzantılı dosyaları seçebilirsiniz ve kelimeler alt alta olmalıdır \nornek.txt dosyasını inceleyiniz\n\nAlınabilir Domainler Seçtiğiniz kayıt yerine \"Alınabilir domain listesi.xls\"\nexcel dosyasına kaydedilecektir \n\nornek@gmail.com yerine E posta adresinizi girer ve kutucuğu \nişaretlerseniz E posta adresinize işlem bittikten sonra taranan \nve kullanılabilir olan domain sayısını bilgi olarak gönderir");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 420, 290));

        jPanel1.setBackground(new java.awt.Color(30, 31, 36));

        txtChoosableFile.setEditable(false);
        txtChoosableFile.setBackground(new java.awt.Color(30, 31, 36));
        txtChoosableFile.setForeground(new java.awt.Color(255, 255, 255));
        txtChoosableFile.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChoosableFile.setText("ornek.txt");
        txtChoosableFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChoosableFileActionPerformed(evt);
            }
        });

        txtChoosableLocation.setEditable(false);
        txtChoosableLocation.setBackground(new java.awt.Color(30, 31, 36));
        txtChoosableLocation.setForeground(new java.awt.Color(255, 255, 255));

        btnChooseLocation.setBackground(new java.awt.Color(30, 31, 36));
        btnChooseLocation.setForeground(new java.awt.Color(255, 255, 255));
        btnChooseLocation.setText("Kayıt yeri seç");
        btnChooseLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseLocationActionPerformed(evt);
            }
        });

        btnChooseFile.setBackground(new java.awt.Color(30, 31, 36));
        btnChooseFile.setForeground(new java.awt.Color(255, 255, 255));
        btnChooseFile.setText("Dosya Seç");
        btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtChoosableLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txtChoosableFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChooseLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(btnChooseFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChoosableFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChoosableLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 390, 70));

        SetWordLength.setBackground(new java.awt.Color(30, 31, 36));

        lblMaxHarfSayisi.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxHarfSayisi.setText("Max harf sayısı");

        spinMaxWordLength.setModel(new javax.swing.SpinnerNumberModel(10, 3, 30, 1));

        spinMinWordLength.setModel(new javax.swing.SpinnerNumberModel(2, 0, 30, 1));

        lblMaxHarfSayisi3.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxHarfSayisi3.setText("Min harf sayısı");

        javax.swing.GroupLayout SetWordLengthLayout = new javax.swing.GroupLayout(SetWordLength);
        SetWordLength.setLayout(SetWordLengthLayout);
        SetWordLengthLayout.setHorizontalGroup(
            SetWordLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetWordLengthLayout.createSequentialGroup()
                .addGroup(SetWordLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaxHarfSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaxHarfSayisi3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(SetWordLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SetWordLengthLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(spinMaxWordLength, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                    .addGroup(SetWordLengthLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinMinWordLength)))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        SetWordLengthLayout.setVerticalGroup(
            SetWordLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetWordLengthLayout.createSequentialGroup()
                .addGroup(SetWordLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxHarfSayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinMaxWordLength, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SetWordLengthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinMinWordLength, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaxHarfSayisi3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        getContentPane().add(SetWordLength, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 170, 80));

        lblMaxHarfSayisi2.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxHarfSayisi2.setText("Zaman aşımı(s)");
        getContentPane().add(lblMaxHarfSayisi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 100, 30));

        timeOut.setModel(new javax.swing.SpinnerNumberModel(5, 3, 10, 1));
        getContentPane().add(timeOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 70, 30));

        jButton1.setBackground(new java.awt.Color(30, 31, 36));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("https://www.lepric.com/");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 180, 20));

        Background.setBackground(new java.awt.Color(30, 31, 36));
        Background.setForeground(new java.awt.Color(30, 31, 36));
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lepric/GUI/Template.jpg"))); // NOI18N
        Background.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed

        if(th==null){
        
        options.setWordLength((int)spinMaxWordLength.getValue(),(int)spinMinWordLength.getValue());
        options.setExtension(CoBoxExtensions.getSelectedItem().toString());
        options.setTimeOut((int)timeOut.getValue());
        th = new Thread(action);
        th.start();
        }else if(!th.isAlive()) {
        
        options.setWordLength((int)spinMaxWordLength.getValue(),(int)spinMinWordLength.getValue());
        options.setExtension(CoBoxExtensions.getSelectedItem().toString());
        options.setTimeOut((int)timeOut.getValue());
        th = new Thread(action);
        th.start();
        }else{
            JOptionPane.showMessageDialog(null, "Lütfen sıradaki işlemin bitmesini bekleyiniz", "Lepric bot sistemleri", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_StartActionPerformed

    private void txtChoosableFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChoosableFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChoosableFileActionPerformed

    private void CoBoxExtensionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoBoxExtensionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CoBoxExtensionsActionPerformed

    private void chkboxSendCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkboxSendCheckActionPerformed
        
    }//GEN-LAST:event_chkboxSendCheckActionPerformed

    private void btnChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseFileActionPerformed
        if(th == null){
            options.setFileLocation();
            txtChoosableFile.setText(options.extractFileName(options.getFile()));
        }else if (!th.isAlive()){
            options.setFileLocation();
            txtChoosableFile.setText(options.extractFileName(options.getFile()));
        }else{
            JOptionPane.showMessageDialog(null, "Dosya seçmek için lütfen sıradaki işlemin bitmesini bekleyiniz", "Lepric bot sistemleri", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_btnChooseFileActionPerformed

    private void btnChooseLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseLocationActionPerformed
        
        if(th == null){
            options.ChoosePrintDirectory();
            txtChoosableLocation.setText(options.getPrintDirectory());
        }else if (!th.isAlive()){
            options.ChoosePrintDirectory();
            txtChoosableLocation.setText(options.getPrintDirectory());
        }else{
        JOptionPane.showMessageDialog(null, "Program başladıktan sonra dizin değiştirilemez", "Lepric bot sistemleri", JOptionPane.PLAIN_MESSAGE);
    }
        
    }//GEN-LAST:event_btnChooseLocationActionPerformed

    private void txtMailAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailAdressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailAdressActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://www.lepric.com"));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://lepric.com/index.php/destek-ol/"));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainActivity().setVisible(true);
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JComboBox<String> CoBoxExtensions;
    private javax.swing.JPanel SetWordLength;
    private javax.swing.JButton Start;
    private javax.swing.JButton btnChooseFile;
    private javax.swing.JButton btnChooseLocation;
    private javax.swing.JCheckBox chkboxSendCheck;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblMaxHarfSayisi;
    private javax.swing.JLabel lblMaxHarfSayisi1;
    private javax.swing.JLabel lblMaxHarfSayisi2;
    private javax.swing.JLabel lblMaxHarfSayisi3;
    private java.awt.List lstLogScreen;
    private javax.swing.JProgressBar pbProgressBar;
    private javax.swing.JSpinner spinMaxWordLength;
    private javax.swing.JSpinner spinMinWordLength;
    private javax.swing.JSpinner timeOut;
    private javax.swing.JTextField txtChoosableFile;
    private javax.swing.JTextField txtChoosableLocation;
    private javax.swing.JTextField txtMailAdress;
    // End of variables declaration//GEN-END:variables

}