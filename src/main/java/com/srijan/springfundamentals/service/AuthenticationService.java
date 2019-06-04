package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.dto.request.AuthenticationRequest;
import com.srijan.springfundamentals.dto.server.AuthenticationDetail;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {
    AuthenticationDetail authenticate(AuthenticationRequest authenticationRequest);

}
