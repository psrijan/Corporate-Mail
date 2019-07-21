package com.srijan.springfundamentals.dto.request.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
public class UpdateClientRequest {


    @JsonIgnore
    private Long id;

    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String emailAddress;
    @NotNull
    @Past
    private Date birthday;
    private String relation;
    @NotNull
    private Long profileId;
    @NotNull
    private Long applicationUserId;
}
