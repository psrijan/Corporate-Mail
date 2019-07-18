package com.srijan.springfundamentals.builder;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Festival;

public class EmailDetailBuilder {

    public static EmailDetail buildBirthdayDetail(ApplicationUser applicationUser , Client friend , Festival birthdayFestival) {
        return EmailDetail.builder()
                .senderEmail(applicationUser.getEmailAddress())
                .senderName(applicationUser.getName())
                .receiverEmail(friend.getEmailAddress())
                .subject(birthdayFestival.getSubject())
                .receiverName(friend.getName())
                .eventCode(Occassion.BIRTHDAY.toString())
                .occasion(Occassion.BIRTHDAY)
                .eventName(birthdayFestival.getName())
                .eventUrl(birthdayFestival.getLogoUrl())
                .friend(friend)
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
                .subject(festival.getSubject())
                .eventName(festival.getName())
                .eventCode(festival.getCode())
                .occasion(Occassion.FESTIVAL)
                .friend(friend)
                .applicationUser(applicationUser)
                .build();

    }
}
