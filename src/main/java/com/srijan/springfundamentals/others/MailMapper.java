package com.srijan.springfundamentals.others;

import com.srijan.springfundamentals.constants.AppConstants;
import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Mail;
import com.srijan.springfundamentals.dto.Occassion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MailMapper {

    private static String applicationName = "Friend Wish";

    public Mail convertToEmail(EmailDetail emailDetail) {

        Mail mail = new Mail();
        mail.setMailFrom(emailDetail.getSenderEmail());
        mail.setMailTo(emailDetail.getReceiverEmail());
        mail.setMailSubject(emailDetail.getEventName());


        if (emailDetail.getOccasion().equals(Occassion.BIRTHDAYALERT)) {
            buildBirthdayAlertModel(emailDetail, mail);
        } else if (emailDetail.getOccasion().equals(Occassion.BIRTHDAY)) {
            buildBirthdayWishModel(emailDetail , mail);
        } else {
            buildFestivalWishModel(emailDetail , mail);
        }
        return mail;
    }


    private void buildBirthdayAlertModel(EmailDetail emailDetail, Mail mail) {

        mail.setMailSubject("Birthday Alert For " + emailDetail.getFriend().getName());

        Map<String, Object> model = new HashMap<>();
        model.put(AppConstants.MailConstants.DATE, DateUtil.formatDateToString(new Date(), DateUtil.STANDARD));
        model.put(AppConstants.MailConstants.NAME, emailDetail.getReceiverName());
        model.put(AppConstants.MailConstants.SUBJECT_TAG, emailDetail.getSubject());
        model.put(AppConstants.MailConstants.BIRTHDAY_OF, emailDetail.getFriend().getName());
        model.put(AppConstants.MailConstants.EMAIL, emailDetail.getFriend().getEmailAddress());
        model.put(AppConstants.MailConstants.RELATION, emailDetail.getFriend().getRelation());
        model.put(AppConstants.MailConstants.REMARKS, emailDetail.getFriend().getRemarks());
        model.put(AppConstants.MailConstants.DATE_OF_BIRTH, emailDetail.getDateOfBirth());
        model.put(AppConstants.MailConstants.APPLICATION_NAME, applicationName);
        mail.setModel(model);
    }

    private void buildBirthdayWishModel(EmailDetail emailDetail, Mail mail) {

        mail.setMailSubject("Happy Birthday " + emailDetail.getFriend().getName());

        Map<String, Object> model = new HashMap<>();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.put(AppConstants.MailConstants.DATE, DateUtil.formatDateToString(new Date(), DateUtil.STANDARD));
        model.put(AppConstants.MailConstants.LOGO, emailDetail.getEventUrl());
        model.put(AppConstants.MailConstants.NAME, emailDetail.getReceiverName());
        model.put(AppConstants.MailConstants.SUBJECT_TAG, emailDetail.getSubject());
        model.put(AppConstants.MailConstants.APPLICATION_NAME, applicationName);
        model.put(AppConstants.MailConstants.THEME_COLOR, "#fa123f");
        model.put(AppConstants.MailConstants.DATE, sdf.format(new Date()));
        model.put(AppConstants.MailConstants.EVENT_NAME, emailDetail.getEventName());
        model.put(AppConstants.MailConstants.SENDER_NAME, emailDetail.getSenderName());
        model.put(AppConstants.MailConstants.CUSTOMIZED_BODY, emailDetail.getBody());
        model.put(AppConstants.MailConstants.BIRTHDAY_OF, emailDetail.getFriend().getName());
        mail.setModel(model);
    }


    private void buildFestivalWishModel(EmailDetail emailDetail, Mail mail) {

        mail.setMailSubject("Happy " + emailDetail.getEventName());

        Map<String, Object> model = new HashMap<>();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.put(AppConstants.MailConstants.DATE, DateUtil.formatDateToString(new Date(), DateUtil.STANDARD));
        model.put(AppConstants.MailConstants.LOGO, emailDetail.getEventUrl());
        model.put(AppConstants.MailConstants.NAME, emailDetail.getReceiverName());
        model.put(AppConstants.MailConstants.SUBJECT_TAG, emailDetail.getSubject());
        model.put(AppConstants.MailConstants.APPLICATION_NAME, applicationName);
        model.put(AppConstants.MailConstants.THEME_COLOR, "#fa123f");
        model.put(AppConstants.MailConstants.DATE, sdf.format(new Date()));
        model.put(AppConstants.MailConstants.EVENT_NAME, emailDetail.getEventName());
        model.put(AppConstants.MailConstants.SENDER_NAME, emailDetail.getSenderName());

        mail.setModel(model);

    }
}
