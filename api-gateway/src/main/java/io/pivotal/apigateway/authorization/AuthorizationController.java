//package io.pivotal.apigateway.authorization;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.server.*;
//import reactor.core.publisher.Mono;
//
//import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;
//import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
//@Controller
//public class AuthorizationController {
//
//    private final WebClient webClient;
//    private final OAuth2AuthorizedClient authorizedClient;
//
//    public AuthorizationController(
//            final WebClient webClient,
//            @RegisteredOAuth2AuthorizedClient( "pivmart-authorization-code" ) OAuth2AuthorizedClient authorizedClient
//    ) {
//
//        this.webClient = webClient;
//        this.authorizedClient = authorizedClient;
//
//    }
//
//    @Bean
//    RouterFunction<ServerResponse> authorizationRoutes() {
//
//        return route -> route()
//                .GET(
//                        "/authorize", queryParam( "grant_type", "authorization_code" ),
//                        this::authorizationCodeGrant
//                )
//                .GET(
//                        "/authorized", queryParam( OAuth2ParameterNames.ERROR, "*" ),
//                        this::authorizationFailed
//                )
//                .build();
//    }
//
//    Mono<ServerResponse> authorizationCodeGrant( ServerRequest request ) {
//
//        return ServerResponse.ok()
//                .body(
//                        this.webClient
//                            .get()
//                            .attributes( oauth2AuthorizedClient( authorizedClient ) )
//                            .retrieve(),
//                        String[].class
//                );
//    }
//
//    Mono<ServerResponse> authorizationFailed( ServerRequest request ) {
//
//        return ServerResponse.()
//                .body(
//                        this.webClient
//                                .get()
//                                .attributes( oauth2AuthorizedClient( authorizedClient ) )
//                                .retrieve(),
//                        String[].class
//                );
//    }
//
//    @GetMapping( value = "/authorize", params = "grant_type=authorization_code" )
//    public String authorizationCodeGrant(
//            Model model,
//            @RegisteredOAuth2AuthorizedClient( "pivmart-authorization-code" ) OAuth2AuthorizedClient authorizedClient
//    ) {
//
//        String[] messages = this.webClient
//                .get()
////                .uri( this.messagesBaseUri )
//                .attributes( oauth2AuthorizedClient( authorizedClient ) )
//                .retrieve()
//                .bodyToMono( String[].class )
//                .block();
//
//        model.addAttribute("messages", messages);
//
//        return "index";
//    }
//
//    // '/authorized' is the registered 'redirect_uri' for authorization_code
//    @GetMapping( value = "/authorized", params = OAuth2ParameterNames.ERROR )
//    public String authorizationFailed( Model model, ServerRequest request) {
//
//        String errorCode = request.queryParam( OAuth2ParameterNames.ERROR ).get();
//        if( StringUtils.hasText( errorCode ) ) {
//            model.addAttribute("error",
//                    new OAuth2Error(
//                            errorCode,
//                            request.queryParam( OAuth2ParameterNames.ERROR_DESCRIPTION ).get(),
//                            request.queryParam( OAuth2ParameterNames.ERROR_URI ).get()
//                    )
//            );
//        }
//
//        return "index";
//    }
//
////    @GetMapping( value = "/authorize", params = "grant_type=client_credentials" )
////    public String clientCredentialsGrant( Model model ) {
////
////        String[] messages = this.webClient
////                .get()
////                .uri( this.messagesBaseUri )
////                .attributes( clientRegistrationId( "pivmart-client-client-credentials" ) )
////                .retrieve()
////                .bodyToMono( String[].class )
////                .block();
////        model.addAttribute("messages", messages);
////
////        return "index";
////    }
//
//}
