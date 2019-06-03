package com.srijan.springfundamentals.dto;

import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Friend;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailDetail {

    private String senderName;
    private String senderEmail;

    private String receiverName;
    private String receiverEmail;
    private String relation;
    private String counterRelation;

    private String eventName;
    private String eventCode;
    private String eventUrl;

    private Object data;

    private String subject;
    private Occassion occasion;

    private Friend friend;
    private ApplicationUser applicationUser;
}
