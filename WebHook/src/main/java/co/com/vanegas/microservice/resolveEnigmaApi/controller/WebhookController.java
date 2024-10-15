package co.com.vanegas.microservice.resolveEnigmaApi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebhookController {
    @PostMapping("/webhook")
    public Mono<String> receiveMessage(@RequestBody String message) {
        System.out.println("Recibido el mensaje del orquestador: " + message);
        return Mono.just("Mensaje recibido exitosamente");
    }
}
