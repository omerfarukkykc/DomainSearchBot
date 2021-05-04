
package Lepric.Options;

import Lepric.Interfaceses.IGetInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Options implements IGetInfo{
    FileReader fileReader;
    BufferedReader bufferedReader;
    JFileChooser jFileChooser;
    SimpleDateFormat bicim3;
    GregorianCalendar gcalender;
    private String fileLocation;
    private String printDirectory;
    private int maxLength=10;
    private int minLength=2;
    private String JonPostel;//Kendisi .com alan adının mucididir Alan adımı tutan değişkene onun adını vermmey kara verdim
    private int lineCount;
    private int usableLineCount;
    private int countLineCount;
    private String liveDomain;
    private boolean liveDomainUsability = true;
    private int timeOut;
    
    
    
    
    public String extractFileName(String fullPathFile){
        try {
            Pattern regex = Pattern.compile("([^\\\\/:*?\"<>|\r\n]+$)");
            Matcher regexMatcher = regex.matcher(fullPathFile);
            if (regexMatcher.find()){
                return regexMatcher.group(1);
            }
        } catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        }
        return fullPathFile;
    }
    public void setFileLocation(){
        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setDialogTitle("Lütfen dosya seçiniz");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt dosyaları seçebilirsiniz", new String[]{"txt"});
        jFileChooser.setFileFilter(filter);
        int donendeger = jFileChooser.showOpenDialog(null);
        
        switch (donendeger) {
            case JFileChooser.APPROVE_OPTION -> {
                this.fileLocation= jFileChooser.getSelectedFile().getAbsolutePath();
            }
            case JFileChooser.CANCEL_OPTION -> {
                fileLocation = "./ornek.txt";
            }
            case JFileChooser.ERROR_OPTION -> {
                System.out.println("Tekrar deneyiniz");
                fileLocation = "./ornek.txt";
                JOptionPane.showMessageDialog(null, "Dosya seçimi sırasında hata oluştu lütfen tekrar deneyiniz tekrarı halinde programı kapatıp açınız destek için : 05435210939", "Lepric bot sistemleri", JOptionPane.PLAIN_MESSAGE);
            }
            default -> {
                fileLocation = "./ornek.txt";
            }
        }

        
    }
    public void ChoosePrintDirectory(){
        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setDialogTitle("Lütfen kaydedilecek yeri seçiniz");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int printDir = jFileChooser.showOpenDialog(null);
        
        switch (printDir) {
            case JFileChooser.APPROVE_OPTION -> {
                this.printDirectory= jFileChooser.getSelectedFile().getAbsolutePath();
            }
            case JFileChooser.CANCEL_OPTION -> {
                this.printDirectory = ".";
            }
            case JFileChooser.ERROR_OPTION -> {
                System.out.println("Tekrar deneyiniz");
                this.printDirectory = ".";
            }
            default -> {
                this.printDirectory = ".";
            }
        }
        
    }
    public String getPrintDirectory(){
        return printDirectory;
    }
    public String getFile(){
        return fileLocation;
    }
    public void setWordLength(int maxLength,int minLength){
        this.maxLength=maxLength;
        this.minLength=minLength;
    }
    public int getWordLengthMax(){
        return maxLength;
    }
    public int getWordLengthMin(){
        return minLength;
    }
    public void setExtension(String extension){
        this.JonPostel=extension;
    }
    public String getExtensions(){
        return JonPostel;
    }
    public void countAndSetLineCount(){
        try {
            fileReader = new FileReader(fileLocation);
            bufferedReader = new BufferedReader(fileReader);
        int size=0;
        while(null!=(bufferedReader.readLine())){
            size++;
        }
        this.lineCount=size;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int getLineCount(){
        return lineCount;
    }
    public int progressCalculator(int progress){
        return (((progress)*100)/lineCount);
    }
    public void countUsableLineCount(){
        // Eğer daha önce kullanıldıysa sorun çıkarabilir
        this.usableLineCount++;
    }
    public int getUsableLineCountAlive(){
        return usableLineCount;
    }
    public void countLineCount() {
        this.countLineCount++;
    }
    public int getLineCountAlive(){
        return this.countLineCount;
    }
    
    
    public void setUsableLineCount(int usableLineCount){
        this.usableLineCount=usableLineCount;
    }
    public void setLiveDomain(String domain,boolean usability){
        this.liveDomain=domain;
        this.liveDomainUsability=usability;
    }
    public String getLiveDomainLog(){
        bicim3=new SimpleDateFormat("hh:mm:ss");
        gcalender=new GregorianCalendar();
        if (liveDomainUsability==true){
            return "--["+bicim3.format(gcalender.getTime())+"]   "+liveDomain+JonPostel+" Kullanılabilir";    
        }else{
            return "--["+bicim3.format(gcalender.getTime())+"]   "+liveDomain+JonPostel+" Kullanıllanılamaz";
        }
        

    }
    
    public void setTimeOut(int timeOut){
        this.timeOut= timeOut;
    }
    public int getTimeOut(){
        return this.timeOut;
    }
    public void reset(){

    lineCount=1;
    usableLineCount=0;
    liveDomain="";
    liveDomainUsability = true;
    countLineCount=0;
    }
    @Override
    public void PrintInfo() {
        System.out.println(
                "Dosya konumu: "+fileLocation+
                "\nÇıktı konumu: "+printDirectory+
                "\nMaximum harf: "+maxLength+
                "\nMinimum harf: "+minLength+
                "\nUzantı: "+JonPostel+
                "\nZaman aşımı: "+timeOut);
    }
}
