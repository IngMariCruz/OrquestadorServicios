package co.com.vanegas.microservice.resolveEnigmaApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchSpringBoot {
    public static void main(String[] args) throws Exception {
        new SpringApplication(BatchSpringBoot.class).run(args);
    }
}
