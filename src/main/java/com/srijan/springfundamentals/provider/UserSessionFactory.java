package com.srijan.springfundamentals.provider;

import com.srijan.springfundamentals.entities.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserSessionFactory {

    public ApplicationUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ApplicationUser applicationUser = ((JwtUser) userDetails).getApplicationUser();
        log.info("User: username- {} id- {} ", applicationUser.getUsername(), applicationUser.getId());
        return applicationUser;
    }
}
