package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.example.DifficultyController.getDifficulty;
import static org.example.LoginController.getEmail;

public class SendEmailSSL {

    /**
     * Klasa SendEmailSSL służy do wysyłania e-maili
     */
    SendEmailSSL(String addressList, String messageText, String title) {

        final String username = "holdalukasz77@gmail.com";
        final String password = "Luk2204@";


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(addressList)
            );
            message.setSubject(title);
            message.setText(messageText);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     *Funkcja sendEmail jest to funkcja wysyłająca e-maila z szczegółami na temat rozgrywki
     */
    //true - wygrana gracza, false - przegrana gracza
    public static void sendEmail(boolean win){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String message = "Szczegóły rozgrywki w grę Statki\n\nData: " + formatter.format(date) + "\nPoziom trudności: " + getDifficulty() + "\nTwój wynik: ";
        String title = "Statki - rozgrywka z " + formatter.format(date);
        String address = getEmail();

        if (win){
            message+= "WYGRANA";
        }
        else{
            message+= "PRZEGRANA";
        }

        message+= "\n\nWiadomość wygenerowana automatycznie, prosimy na nią nie odpowiadać";
        SendEmailSSL send = new SendEmailSSL(address, message, title);
    }

}