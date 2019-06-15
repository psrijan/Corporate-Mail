package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.AuthenticationRequest;
import com.srijan.springfundamentals.dto.response.AuthenticationResponse;
import com.srijan.springfundamentals.dto.server.AuthenticationDetail;
import com.srijan.springfundamentals.service.AuthenticationService;
import com.srijan.springfundamentals.util.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.srijan.springfundamentals.constants.AppConstants.SecurityConstants;
import javax.validation.Valid;

@RequestMapping(path = "/auth")
@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        log.info("Entering Authentiction API... {} ", authenticationRequest);
        AuthenticationDetail authenticationDetail = authenticationService.authenticate(authenticationRequest);
        AuthenticationResponse authenticationResponse = ObjectMapper.map(authenticationDetail, AuthenticationResponse.class);
        HttpHeaders header = new HttpHeaders();
        header.add(SecurityConstants.SECURITY_HEADER_STRING, authenticationDetail.getToken());
        return new ResponseEntity<>(authenticationResponse, header, HttpStatus.OK);
    }

}

