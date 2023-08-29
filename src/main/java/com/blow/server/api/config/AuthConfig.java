package com.blow.server.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AuthConfig {

    @Value("${jwt.secret}")
    private String jwtSecretKey;
}
