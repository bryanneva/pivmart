package io.pivotal.cartapi;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain( ServerHttpSecurity http ) {
        return http
                .authorizeExchange()
                    .matchers( EndpointRequest.toAnyEndpoint() ).permitAll()
                    .pathMatchers( HttpMethod.POST,"/api/cart" ).hasAuthority( "SCOPE_user.cart" )
                    .pathMatchers( HttpMethod.GET, "/api/cart" ).permitAll()
                    .anyExchange().authenticated()
                        .and()

                .oauth2ResourceServer()
                    .jwt()
                        .and()
                    .and()

                .build();
    }
}
