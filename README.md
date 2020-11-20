# Pivmart

## WIP Startup

_For Joe Grandja_

Edit _/etc/hosts_
1. Add the following:
```
127.0.0.1 auth-server gateway
```

Startup instructions:
1. Start the Auth Service
  - `auth-server/src/main/java/io/pivotal/pivmartauthserver/AuthorizationServerApplication.java`
2. Start the API Gateway
  - `api-gateway/src/main/java/io/pivotal/apigateway/ApiGatewayApplication.java`
3. Open the gateway in chrome: [http://gateway:8765](http://gateway:8765)

