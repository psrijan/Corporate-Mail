package com.srijan.springfundamentals.others;

import com.srijan.springfundamentals.constants.AppConstants;
import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Mail;
import com.srijan.springfundamentals.dto.Occassion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MailMapper {
    public static Mail convertToEmail(EmailDetail emailDetail) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Mail mail = new Mail();
        mail.setMailFrom(emailDetail.getSenderEmail());
        mail.setMailTo(emailDetail.getReceiverEmail());
        mail.setMailSubject(emailDetail.getEventName() + " for " + emailDetail.getData());

        Map<String, Object> model = new HashMap<>();

        model.put(AppConstants.MailConstants.DATE, sdf.format(new Date()));
        model.put(AppConstants.MailConstants.LOGO, emailDetail.getEventUrl());
        model.put(AppConstants.MailConstants.NAME, emailDetail.getReceiverName());

        if (emailDetail.getOccasion() == Occassion.BIRTHDAYALERT) {
            model.put(AppConstants.MailConstants.SUBJECT_TAG, emailDetail.getSubject() + " " + emailDetail.getData());
            model.put(AppConstants.MailConstants.APPLICATION_NAME, "Client Wish");
            model.put(AppConstants.MailConstants.SENDER_NAME, "Birthday Alert Server");
            model.put(AppConstants.MailConstants.EVENT_NAME, emailDetail.getEventName());
            model.put(AppConstants.MailConstants.BIRTHDAY_OF, emailDetail.getData());
            model.put(AppConstants.MailConstants.THEME_COLOR, "#fafa21");
        } else {
            model.put(AppConstants.MailConstants.SUBJECT_TAG, emailDetail.getSubject() + " " + emailDetail.getReceiverName() + "!!!");
            model.put(AppConstants.MailConstants.APPLICATION_NAME, "Client Wish");
            model.put(AppConstants.MailConstants.THEME_COLOR, "#fa123f");
            model.put(AppConstants.MailConstants.DATE, sdf.format(new Date()));
            model.put(AppConstants.MailConstants.EVENT_NAME, emailDetail.getEventName());
            model.put(AppConstants.MailConstants.SENDER_NAME, emailDetail.getSenderName());
        }
        mail.setModel(model);
        return mail;
    }

}
