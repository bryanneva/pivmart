package io.pivotal.apigateway.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain( ServerHttpSecurity http ) {

        return http

                .cors().disable()

                .csrf().disable()

                .authorizeExchange( authorizeExchange -> authorizeExchange
                        .matchers( EndpointRequest.toAnyEndpoint(), PathRequest.toStaticResources().atCommonLocations() ).permitAll()
                        .pathMatchers( HttpMethod.POST, "/api/cart" ).hasAuthority( "SCOPE_user.cart" )
                        .pathMatchers( HttpMethod.DELETE, "/api/cart/**" ).hasAuthority( "SCOPE_user.cart" )
                        .pathMatchers( HttpMethod.GET, "/api/purchases" ).hasAuthority( "SCOPE_user.purchases" )
                        .anyExchange().permitAll()
                )

                .oauth2Login( withDefaults() )

                .build();
    }

}
