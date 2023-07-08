package br.com.grupo63.techchallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Tech Challenge", description = "Grupo 63", version = "${app.version}"))
@SpringBootApplication
public class TechchallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechchallengeApplication.class, args);
    }

}
