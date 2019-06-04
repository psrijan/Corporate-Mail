package com.srijan.springfundamentals.filter;

import com.srijan.springfundamentals.provider.JwtTokenUtil;
import com.srijan.springfundamentals.provider.JwtUserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.srijan.springfundamentals.constants.AppConstants.SecurityConstants;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //.*\/auth
        if (httpServletRequest.getRequestURI().contains("swagger") || httpServletRequest.getRequestURI().contains("api-docs")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        } else if (Pattern.matches(SecurityConstants.AUTH_URL_REGEX,httpServletRequest.getRequestURI()) ||Pattern.matches(SecurityConstants.TOTP_REGEX,httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        } else if (httpServletRequest.getRequestURI().equalsIgnoreCase("/")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        final String requestHeader = httpServletRequest.getHeader(SecurityConstants.SECURITY_HEADER_STRING);
        String token = null;
        if (requestHeader != null && requestHeader.length() > 7 && requestHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = requestHeader.substring(7);

            String username = jwtTokenUtil.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                log.error("Invalid token");
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
