package io.pivotal.apigateway.authorization;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class AuthorizationController {

    private final WebClient webClient;

    public AuthorizationController(
            final WebClient webClient
    ) {

        this.webClient = webClient;

    }

    @GetMapping( value = "/authorize", params = "grant_type=authorization_code" )
    public String authorizationCodeGrant(
            Model model,
            @RegisteredOAuth2AuthorizedClient( "pivmart-authorization-code" ) OAuth2AuthorizedClient authorizedClient
    ) {

//        String[] messages = this.webClient
//                .get()
//                .uri( this.messagesBaseUri )
//                .attributes( oauth2AuthorizedClient( authorizedClient ) )
//                .retrieve()
//                .bodyToMono( String[].class )
//                .block();
//
//        model.addAttribute("messages", messages);

        return "index";
    }

}
