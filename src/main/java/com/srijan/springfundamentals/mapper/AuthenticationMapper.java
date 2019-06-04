package com.srijan.springfundamentals.mapper;

import com.srijan.springfundamentals.dto.server.AuthenticationDetail;
import com.srijan.springfundamentals.entities.ApplicationUser;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public static AuthenticationDetail loginResponse(ApplicationUser applicationUser, String token) {

        return AuthenticationDetail.builder()
                .email(applicationUser.getEmailAddress())
                .username(applicationUser.getUsername())
                .token(token)
                .build();
    }
}
