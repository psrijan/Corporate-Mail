package com.srijan.springfundamentals.dto;

import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailDetail extends ModelBase{

    private String senderName;
    private String senderEmail;

    private String receiverName;
    private String receiverEmail;
    private String relation;
    private String counterRelation;

    private String eventName;
    private String eventCode;
    private String eventUrl;
    private String body;
    private String dateOfBirth;

    private String subject;
    private Occassion occasion;

    private Client friend;
    private ApplicationUser applicationUser;
}
