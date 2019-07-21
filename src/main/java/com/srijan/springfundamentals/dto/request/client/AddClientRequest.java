package com.srijan.springfundamentals.dto.request.client;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AddClientRequest {

    @NotEmpty
    private String name;
    @Email
    private String emailAddress;
    private Date birthday;
    private String relation;
    @NotNull
    private Long profileId;

    @NotNull
    private Long applicationUserId;

}
