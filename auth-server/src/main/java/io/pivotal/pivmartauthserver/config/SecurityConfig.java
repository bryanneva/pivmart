package io.pivotal.pivmartauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.UUID;

@EnableWebSecurity
@Import( OAuth2AuthorizationServerConfiguration.class )
public class SecurityConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient registeredClient = RegisteredClient.withId( UUID.randomUUID().toString() )
                .clientId( "pivmart-client" )
                .clientSecret( "secret" )
                .clientAuthenticationMethod( ClientAuthenticationMethod.BASIC )
                .authorizationGrantType( AuthorizationGrantType.AUTHORIZATION_CODE )
                .authorizationGrantType( AuthorizationGrantType.CLIENT_CREDENTIALS )
                .redirectUri( "http://gateway:8765/login/oauth2/code/web-client-authorization-code" )
                .scope( "user.purchases" )
                .scope( "user.cart" )
//                .clientSettings( clientSettings -> clientSettings.requireUserConsent( true ) )
                .build();

        return new InMemoryRegisteredClientRepository( registeredClient );
    }

    @Bean
    public KeyManager keyManager() {

        return new StaticKeyGeneratingKeyManager();
    }

    @Bean
    public UserDetailsService users() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username( "user1" )
                .password( "pw" )
                .roles( "user.purchases", "user.cart" )
                .build();

        return new InMemoryUserDetailsManager( user );
    }

}
