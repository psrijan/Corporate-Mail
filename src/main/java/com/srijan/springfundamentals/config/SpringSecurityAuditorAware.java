package com.srijan.springfundamentals.config;

import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.provider.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SpringSecurityAuditorAware implements AuditorAware<ApplicationUser> {

    @Override
    public Optional<ApplicationUser> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.ofNullable(null);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ApplicationUser applicationUser = ((JwtUser) userDetails).getApplicationUser();
        log.info("User for Auditing: username- {} id- {} ", applicationUser.getUsername(), applicationUser.getId());
        return Optional.ofNullable(applicationUser);
    }
}
