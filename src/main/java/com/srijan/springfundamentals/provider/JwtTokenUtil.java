package com.srijan.springfundamentals.provider;

import com.srijan.springfundamentals.entities.ApplicationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expires_in}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token, Claims::getAudience);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException ex) {
            log.error("Exception : " + ex.getMessage());
            return null;
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername(), null);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String audience) {
        try {
            final Date createdDate = new Date();
            final Date expirationDate = calculateExpirationDate(createdDate);

            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
                    .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret.getBytes("UTF-8")).compact();
        } catch (UnsupportedEncodingException ex) {
            log.error("Exception : " + ex.getMessage());
            return null;
        }
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token));
        //|| ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        try {
            final Date createdDate = new Date();
            final Date expirationDate = calculateExpirationDate(createdDate);

            final Claims claims = getAllClaimsFromToken(token);
            claims.setIssuedAt(createdDate);
            claims.setExpiration(expirationDate);

            return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret.getBytes("UTF-8")).compact();
        } catch (UnsupportedEncodingException ex) {
            log.error("Exception : " + ex.getMessage());
            return null;
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

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
