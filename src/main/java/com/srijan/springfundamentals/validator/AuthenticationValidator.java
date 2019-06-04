package com.srijan.springfundamentals.validator;

import com.srijan.springfundamentals.dto.server.ValidatorResponse;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.exception.UnauthorizedLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationValidator {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ValidatorResponse validateLogin(ApplicationUser user, String password) {
        ValidatorResponse response = new ValidatorResponse();
        if (user != null && user.getActive() == 'Y') {
            if (passwordEncoder.matches(password, user.getPassword())) {
                response.setSuccess(true);
            } else {
                response.setSuccess(false);
                response.setMessage("Username or password is invalid.");
            }
        } else {
            log.error("User UserGroup or Parent UserGroup is blocked/Deleted");
            throw new UnauthorizedLoginException("User/UserGroup blocked or invalid");
        }
        return response;
    }
}
