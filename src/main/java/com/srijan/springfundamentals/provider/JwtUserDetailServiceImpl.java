package com.srijan.springfundamentals.provider;

import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optionalApplicationUser = applicationUserRepository.findApplicationUserByUsername(username);
        ApplicationUser applicationUser = optionalApplicationUser.orElseThrow(this.usernameNotFound);
        return new JwtUser(applicationUser, null);
    }

    Supplier<EntityNotFoundException> usernameNotFound = () -> new EntityNotFoundException("Username Not found");

    private static List<GrantedAuthority> grantedAuthorities() {
        return null;
    }
}
