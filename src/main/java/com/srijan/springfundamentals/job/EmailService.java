package com.srijan.springfundamentals.job;


import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Mail;
import com.srijan.springfundamentals.others.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private EmailTemplateService emailTemplateService;

    private String username = "dolletulsi@gmail.com";
    private String password = "CatalaN!29";

    public boolean sendIndividualMail(EmailDetail emailDetail) {

        try {
            Mail mail = MailMapper.convertToEmail(emailDetail);

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username, false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDetail.getReceiverEmail()));
            msg.setSubject(mail.getMailSubject());
            msg.setSentDate(new Date());

            String contentString = emailTemplateService.getTemplate(mail, emailDetail.getOccasion());
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(contentString, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
            return true;
        } catch (Exception ex) {
            log.error("Exception: " , ex );
            return false;
        }
    }

}
