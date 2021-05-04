package Lepric.Tools;

import Lepric.Interfaceses.IGetInfo;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail  implements IGetInfo{
    String email="lepricbot@gmail.com";
    String pass="txbutsspdyrhizyn";

    public void Send(String mailAdress,int lineCount,int usableLineCount) {
        
    System.out.println("SSLEmail Start");
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
    props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
    props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
    props.put("mail.smtp.port", "465"); //SMTP Port
    System.out.println("Puts Start");
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication("lepricbot@gmail.com","txbutsspdyrhizyn");
        }
    });   
    Message mesaj = new MimeMessage(session);
    
        try {
            mesaj.setFrom(new InternetAddress("lepricbot@gmail.com"));
            mesaj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailAdress));
            mesaj.setSubject("İşlem tamamlanmıştır");
            mesaj.setText("Toplam "+lineCount+" domain taranmıştır.\nUygun olan "+usableLineCount+"'tanesi Alınabilir domain listesi.xls dosyasına eklenmiştir. \nİyi çalışmalar dileriz. \nDestek için: https://lepric.com/index.php/destek-ol/");
            Transport.send(mesaj);
    
        System.out.println("Mesaj gönderildi");
            
            
        } catch (AddressException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    @Override
    public void PrintInfo() {
        System.out.println(
                        email+" Adlı mailden"+
                        pass+" Şifresi kullanılarak"+
                        " Aşağıdaki formatta mail gönderilmektedir\n"+
                        "Toplam +lineCount+ domain taranmıştır.\n"+ 
                        "Uygun olan +usableLineCount tanesi Alınabilir domain listesi.xls dosyasına eklenmiştir. \n"+ 
                        "İyi çalışmalar dileriz. \n"+ 
                        "Destek için: https://lepric.com/index.php/destek-ol/");
    }

    

    

}

