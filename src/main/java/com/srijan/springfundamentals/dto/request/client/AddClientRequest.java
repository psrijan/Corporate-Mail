package com.srijan.springfundamentals.dto.request.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddClientRequest {

    private String name;
    private String emailAddress;
    private Date birthday;
    private String relation;
    private Long profileId;

}
