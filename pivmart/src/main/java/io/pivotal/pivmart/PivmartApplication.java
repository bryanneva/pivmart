package io.pivotal.pivmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PivmartApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PivmartApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
    }

}
