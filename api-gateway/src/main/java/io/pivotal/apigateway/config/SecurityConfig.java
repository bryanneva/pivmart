package io.pivotal.apigateway.config;

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

                .csrf().disable()

                .authorizeExchange()
                    .pathMatchers( HttpMethod.POST, "/api/**" ).authenticated()
//                    .pathMatchers( HttpMethod.GET, "/api/**" ).permitAll()
//                    .pathMatchers( "/**", "/static/**", "/static/js/**", "/manifest.json", "/favicon.ico", "/authorize", "/authorized" ).permitAll()
//                    .pathMatchers( "/login/oauth2/code/**" ).permitAll()
                    .anyExchange().permitAll()
                        .and()

//                .httpBasic()
//                    .disable()
//
//                .formLogin( withDefaults() )

//                .oauth2Client( withDefaults() )

                    .oauth2Login()
                        .and()

                .build();
    }

}
