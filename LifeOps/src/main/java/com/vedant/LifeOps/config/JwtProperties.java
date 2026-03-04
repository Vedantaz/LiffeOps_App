package com.vedant.LifeOps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    // for jwt expiration and refresh token implementation

    private long refreshExpiration;

    public long getFreshExpiration() { return refreshExpiration; }
    public void setRefreshExpiration(long refreshExpiration) {this.refreshExpiration = refreshExpiration; }

    private String secret;
    private long expiration;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }

    public long getExpiration() { return expiration; }
    public void setExpiration(long expiration) { this.expiration = expiration; }
}