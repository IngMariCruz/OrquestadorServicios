package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.service.GetStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orques/steps")
public class GetStepApiController {

    @Autowired
    private GetStepService getStepService;
    private WebClient webClient;

    @PostMapping("/orchestration")
    public Mono<ResponseEntity<String>> startOrchestration(@RequestBody JsonApiBodyRequest body) {
        return getStepService.orchestrateSteps(body)
                .map(response -> ResponseEntity.ok(response))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error during orchestration: " + e.getMessage())));
    }

}
