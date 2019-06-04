package com.srijan.springfundamentals.dto.server;

import com.srijan.springfundamentals.dto.ModelBase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationDetail extends ModelBase {
    private String username;
    private String email;
    private String token;
}
