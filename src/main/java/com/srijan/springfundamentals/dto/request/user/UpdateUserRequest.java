package com.srijan.springfundamentals.dto.request.user;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserRequest extends ModelBase {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String emailAddress;
    @NotNull
    private Long profileId;
}
