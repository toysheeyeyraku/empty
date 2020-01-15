package com.gemicle.messaging.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

import java.util.Collections;

//@Component
//@Primary
public class TokenServices implements ResourceServerTokenServices {
    private RemoteTokenServices tokenServices;
    private OAuth2ClientContext context;
    private static final String ACCESS_TOKEN = "access_token";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public TokenServices(RemoteTokenServices tokenServices, OAuth2ClientContext context, UserDetailsService userDetailsService) {
        this.tokenServices = tokenServices;
        this.context = context;

        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
        userTokenConverter.setUserDetailsService(userDetailsService);
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        tokenServices.setAccessTokenConverter(accessTokenConverter);
    }

    /**
     * This method is needed for Feign client in order to provide access_token
     * */
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        context.setAccessToken(DefaultOAuth2AccessToken
                .valueOf(Collections.singletonMap(ACCESS_TOKEN, accessToken)));
        return tokenServices.loadAuthentication(accessToken);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        return tokenServices.readAccessToken(accessToken);
    }
}