package co.com.vanegas.microservice.resolveEnigmaApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "co.com.vanegas.microservice.resolveEnigmaApi", "co.com.vanegas.microservice.resolveEnigmaApi.api" , "co.com.vanegas.microservice.resolveEnigmaApi.config"})
public class Swagger2SpringBoot {


    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

}
