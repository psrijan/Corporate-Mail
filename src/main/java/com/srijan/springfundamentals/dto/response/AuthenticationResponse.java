package com.srijan.springfundamentals.dto.response;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends ModelBase {
    private String message;
    private String username;
    private String email;
}
