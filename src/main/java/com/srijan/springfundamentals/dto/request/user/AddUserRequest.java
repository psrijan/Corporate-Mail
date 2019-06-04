package com.srijan.springfundamentals.dto.request.user;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddUserRequest extends ModelBase {

    @NotEmpty
    private String username;
    @NotEmpty
    private String name;
//    @IsEmail
    @NotEmpty
    private String emailAddress;
}
