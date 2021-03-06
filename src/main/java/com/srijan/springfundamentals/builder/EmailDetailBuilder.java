package com.srijan.springfundamentals.builder;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.others.DateUtil;

public class EmailDetailBuilder {

    public static EmailDetail buildBirthdayDetail(ApplicationUser applicationUser , Client friend , Festival birthdayFestival) {
        return EmailDetail.builder()
                .senderEmail(applicationUser.getEmailAddress())
                .senderName(applicationUser.getName())
                .receiverEmail(friend.getEmailAddress())
                .subject("Many Many Happy Returns of the Day!!!")
                .receiverName(friend.getName())
                .eventCode(Occassion.BIRTHDAY.toString())
                .occasion(Occassion.BIRTHDAY)
                .eventName(birthdayFestival.getName())
                .eventUrl(birthdayFestival.getLogoUrl())
                .friend(friend)
                .body(friend.getMessage())
                .applicationUser(applicationUser)
                .build();
    }

    public static EmailDetail buildFestivalDetail(ApplicationUser applicationUser ,
                                                  Festival festival , Client friend) {
        return EmailDetail.builder()
                .senderName(applicationUser.getName())
                .senderEmail(applicationUser.getEmailAddress())
                .receiverName(friend.getName())
                .receiverEmail(friend.getEmailAddress())
                .eventUrl(festival.getLogoUrl())
                .subject("Wishing you wealth and success in this Auspicious Occasion of " + festival.getName())
                .eventName(festival.getName())
                .eventCode(festival.getCode())
                .occasion(Occassion.FESTIVAL)
                .body(friend.getMessage())
                .friend(friend)
                .applicationUser(applicationUser)
                .build();

    }

    public static  EmailDetail buildBirthdayAlertDetail(ApplicationUser applicationUser, Client friend) {

        return EmailDetail.builder()
                .senderEmail(applicationUser.getEmailAddress())
                .senderName(applicationUser.getName())
                .receiverEmail(applicationUser.getEmailAddress())
                .receiverName(applicationUser.getName())
                .eventCode(Occassion.BIRTHDAYALERT.toString())
                .eventName("Birthday Alert")
                .dateOfBirth(DateUtil.formatDateToString(friend.getBirthday() , DateUtil.STANDARD))
                .eventUrl(null)
                .occasion(Occassion.BIRTHDAYALERT)
                .friend(friend)
                .applicationUser(applicationUser)
                .build();

    }

}
