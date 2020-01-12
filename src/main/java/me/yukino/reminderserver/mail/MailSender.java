package me.yukino.reminderserver.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Yukino Yukinoshita
 */

public class MailSender {

    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    public static boolean sendMail(String to, String subject, String content) {
        final String from = "feedback_yukino@163.com";
        final String host = "smtp.163.com";
        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.port", "***");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("feedback_yukino@163.com", "yukino0auth0");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException mex) {
            logger.error("MailSender#sendMail", mex);
            return false;
        }
        return true;
    }

}
