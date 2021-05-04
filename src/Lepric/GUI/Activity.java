/*
import Lepric.Options.Options;
import Lepric.Tools.PrintTo;
import Lepric.Tools.SendMail;
import Lepric.Tools.WhoisApi;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

    abstract class xyz(){
    
        } 





    class SideAction implements Runnable{
        Options options = new Options();
        JProgressBar pbar;
        SideAction(JProgressBar pbar){
            pbar = pbProgressBar;
            pbProgressBar.setStringPainted(true);
        }
        public void run(){
            try{
               
                BufferedReader bufferedReader;
                PrintTo printTo;
                try (FileReader fileReader = new FileReader(options.getFile())) {
                    //lstLogScreen.add("Program başladı"); 
                    options.countAndSetLineCount();
                    printTo = new PrintTo();
                    bufferedReader = new BufferedReader(fileReader);
                    String wordData;
                    WhoisApi whoisApi = new WhoisApi();
                    while((wordData=bufferedReader.readLine())!=null){

                        options.setLiveDomain(wordData,false);
                        options.countLineCount();
                        //Verilen kelime datasının uzunluk kontrolü
                        if((wordData.length()<options.getWordLengthMax())&& wordData.length()>options.getWordLengthMin()){
                            //Whois sorgulaması yapar eğer kayıt Yoksa Alınabilir listesine ekler
                            if(whoisApi.checkWhois(wordData,options.getExtensions(),options.getTimeOut())!=null){
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
                /*
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

        
    }*/