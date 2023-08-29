package com.blow.server.api.config.jwt;

import com.blow.server.api.common.exception.TokenException;
import com.blow.server.api.common.message.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (!uri.startsWith("/api/v1/auth")) {
            val token = getToken(request);

            if (Objects.isNull(token)) {
                throw new TokenException(ExceptionMessage.EMPTY_TOKEN.getMessage(), HttpStatus.BAD_REQUEST);
            }

            val isTokenAvailable = checkJwtAvailable(token);
            if (!isTokenAvailable) {
                throw new TokenException(ExceptionMessage.INVALID_TOKEN.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            val auth = jwtTokenManager.getAuthentication(token);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        val headerAuth = request.getHeader("Authorization");
        return (StringUtils.hasText(headerAuth)) ? headerAuth : null;
    }

    private boolean checkJwtAvailable (String jwtToken) {
        return jwtTokenManager.verifyAuthToken(jwtToken);
    }
}
