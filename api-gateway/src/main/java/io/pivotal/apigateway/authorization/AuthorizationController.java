/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.pivotal.apigateway.authorization;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class AuthorizationController {

    private final WebClient webClient;

    public AuthorizationController( final WebClient webClient ) {


        this.webClient = webClient;

    }

    @PostMapping( value = "/authorize", params = "grant_type=authorization_code" )
    public Mono<String[]> authorizationCodeGrant(
            @RequestBody Credentials credentials,
            @RegisteredOAuth2AuthorizedClient( "web-client-authorization-code" ) OAuth2AuthorizedClient authorizedClient
    ) {

        return this.webClient
                .get()
                .uri("http://localhost:9000/")
                .attributes( oauth2AuthorizedClient( authorizedClient ) )
                .retrieve()
                .bodyToMono( String[].class );
    }

    static class Credentials {

        private final String username;
        private final String password;

        Credentials( String username, String password ) {

            this.username = username;
            this.password = password;

        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

    }

}
