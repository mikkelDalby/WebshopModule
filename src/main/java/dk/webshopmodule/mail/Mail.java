package dk.webshopmodule.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public void sendNewOrderAdmin(){

        // write your code here
        // change accordingly
        final String username = "tryllemikkel@gmail.com";

        // change accordingly
        final String password = "dcc59vez";

        // Get system properties
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth", "true");

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", "true");

        // Setup mail server
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS Port
        props.put("mail.smtp.port", "587");

        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        return new PasswordAuthentication(username,
                                password);
                    }
                });

        try {
            // compose the message
            // javax.mail.internet.MimeMessage class is
            // mostly used for abstraction.
            Message message = new MimeMessage(session);

            // header field of the header.
            message.setFrom(new InternetAddress("BallonkompagnietIVS"));

            //Set admin email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mikkel@dalbynielsen.dk"));
            message.setSubject("New order on website");

            message.setText("New order placed on your website");

            //send Message
            Transport.send(message);
            System.out.println("Email sent to admin - New order");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendNewOrderCustomer(String email){

        // write your code here
        // change accordingly
        final String username = "tryllemikkel@gmail.com";

        // change accordingly
        final String password = "dcc59vez";

        // Get system properties
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth", "true");

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", "true");

        // Setup mail server
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS Port
        props.put("mail.smtp.port", "587");

        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        return new PasswordAuthentication(username,
                                password);
                    }
                });

        try {
            // compose the message
            // javax.mail.internet.MimeMessage class is
            // mostly used for abstraction.
            Message message = new MimeMessage(session);

            // header field of the header.
            message.setFrom(new InternetAddress("BallonkompagnietIVS"));

            //Set admin email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Tak for din ordre");

            message.setText("Vi sender dine varer hurtigst muligt");

            //send Message
            Transport.send(message);
            System.out.println("Email sent to customer - New order");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}