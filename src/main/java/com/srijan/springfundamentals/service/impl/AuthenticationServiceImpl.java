package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.request.AuthenticationRequest;
import com.srijan.springfundamentals.dto.server.AuthenticationDetail;
import com.srijan.springfundamentals.dto.server.ValidatorResponse;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.exception.UnauthorizedLoginException;
import com.srijan.springfundamentals.mapper.AuthenticationMapper;
import com.srijan.springfundamentals.provider.JwtTokenUtil;
import com.srijan.springfundamentals.provider.JwtUserDetailServiceImpl;
import com.srijan.springfundamentals.repository.UserRepository;
import com.srijan.springfundamentals.service.AuthenticationService;
import com.srijan.springfundamentals.validator.AuthenticationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository applicationUserRepository;

    @Autowired
    private AuthenticationValidator authenticationValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthenticationDetail authenticate(AuthenticationRequest authenticationRequest) {
        Optional<ApplicationUser> optionalApplicationUser = applicationUserRepository.findApplicationUserByUsername(authenticationRequest.getUsername());
        ApplicationUser applicationUser = optionalApplicationUser.orElseThrow(() -> new UnauthorizedLoginException("Username deleted or Invalid"));
        ValidatorResponse validatorResponse = authenticationValidator.validateLogin(applicationUser, authenticationRequest.getPassword());

        if (validatorResponse.isSuccess()) {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(applicationUser.getUsername(),
                    authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Successfully Authenticated {}", authenticationRequest.getUsername());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            return AuthenticationMapper.loginResponse(applicationUser, token);
        } else {
            throw new UnauthorizedLoginException(validatorResponse.getMessage());
        }
    }

}
