package oksana.dvornitska;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Friend Locator API", version = "1.0"))
public class JavaWeb {
    public static void main(String[] args) {
        SpringApplication.run(JavaWeb.class, args);
    }
}
