package co.com.vanegas.microservice.resolveEnigmaApi.api;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orques/steps")
public class GetStepApiController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/step-one")
    public ResponseEntity<String> getStepOne(@RequestBody String requestBody) {
        String response = producerTemplate.requestBody("direct:getStepOne", requestBody, String.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/step-two")
    public ResponseEntity<String> getStepTwo(@RequestBody String requestBody) {
        String response = producerTemplate.requestBody("direct:getStepTwo", requestBody, String.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/step-three")
    public ResponseEntity<String> getStepThree(@RequestBody String requestBody) {
        String response = producerTemplate.requestBody("direct:getStepThree", requestBody, String.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/orchestration")
    public ResponseEntity<String> startOrchestration(@RequestBody String requestBody) {
        String response = producerTemplate.requestBody("direct:startOrchestration", requestBody, String.class);
        return ResponseEntity.ok(response);
    }
}