package ntnu.idatt2105.madlads.FullstackAPI.serviceOLD;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Class for servies related to sending an email
 */
public class EmailService extends Thread{

    private final String toMail;
    private final String text;

    public EmailService(String toMail, String text) {
        this.toMail = toMail;
        this.text = text;
    }

    /**
     * Generates an email, and sends it with wanted context
     * Runs as an own thread to avoid extra waiting time for the user.
     */
    public void run(){
        String from = "QS99Madlads@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("idatt2105.madlads@gmail.com", "mtswdplnlklxstui");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            message.setSubject("You have been added as a user at NTNU QS \\n " +
                    "Remember to go to your profile and change your password or save this email");
            message.setText(text);
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
