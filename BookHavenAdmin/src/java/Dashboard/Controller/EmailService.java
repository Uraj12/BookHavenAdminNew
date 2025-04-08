
package Dashboard.Controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    public static void sendEmail(String to, String subject, String body) {
        if (to == null || to.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipient email address cannot be null or empty");
        }
        
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Email subject cannot be null or empty");
        }
        
        if (body == null || body.trim().isEmpty()) {
            throw new IllegalArgumentException("Email body cannot be null or empty");
        }

        // Sender's email and password
        final String username = "sahushubham5249@gmail.com";
        final String password = "nkdqblgaqyuyegnl";

        // SMTP server information
        String host = "smtp.gmail.com";
        int port = 587;

        // Set properties for sending email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(username));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Set the actual message
            message.setText(body);

            System.setProperty("https.protocols", "TLSv1.2");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}