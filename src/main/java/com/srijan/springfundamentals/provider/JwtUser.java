package com.srijan.springfundamentals.provider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srijan.springfundamentals.entities.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;
    private final ApplicationUser applicationUser;

    public JwtUser(
            ApplicationUser applicationUser, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.applicationUser = applicationUser;
    }

    @JsonIgnore
    public Long getId() {
        return applicationUser.getId();
    }

    @Override
    public String getUsername() {
        return applicationUser.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getName() {
        return applicationUser.getName();
    }

    public String getEmail() {
        return applicationUser.getEmailAddress();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return applicationUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return applicationUser.getActive() == 'Y';
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }
}
