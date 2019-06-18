package com.srijan.springfundamentals.dto.request.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateClientRequest {


    @JsonIgnore
    private Long id;

    private String name;
    private String emailAddress;
    private Date birthday;
    private String relation;
    private Long profileId;
}
