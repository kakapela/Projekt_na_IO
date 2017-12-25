package sample.Model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderModel {
    public static boolean sendMail(String from, String password, String message, String to[]){
        String host="smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",from);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.auth","true");
        Session session= Session.getDefaultInstance(props,null);
        MimeMessage mimeMessage=new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            //pobranie adres odbiorcy wiadomosci
            InternetAddress[] toAddress= new InternetAddress[to.length];
            for(int i=0;i<to.length;i++){

                toAddress[i]=new InternetAddress(to[i]);



            }
            //dodaj adres odbiorcy do wiadomosci

            for (int i=0;i<toAddress.length;i++){

                mimeMessage.addRecipient(Message.RecipientType.TO,toAddress[i]);
            }


            mimeMessage.setSubject("Email otrzymany z aplikacji");
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host,from,password);
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
            return true;
        }
        catch (MessagingException me){
            me.printStackTrace();
        }


        return false;
    }
}
