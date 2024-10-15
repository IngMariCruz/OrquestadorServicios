package co.com.vanegas.microservice.resolveEnigmaApi.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetStepOneClientRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Ruta para el primer microservicio
        from("direct:getStepOne")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("freemarker:templates/GetStepOneClientTemplate.ftl")
                .to("http://localhost:8080/getStep")
                .log("Step 1 response: ${body}")
                .setProperty("step1Response", body());

        // Ruta para el segundo microservicio
        from("direct:getStepTwo")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setBody().simple("{\"headerId\": \"12345\"}")
                .to("freemarker:templates/GetStepTwoClientTemplate.ftl")
                .to("http://localhost:8081/getStep")
                .log("Step 2 response: ${body}")
                .setProperty("step2Response", body());

        // Ruta para el tercer microservicio
        from("direct:getStepThree")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setBody().simple("{\"headerId\": \"12345\"}")
                .to("freemarker:templates/GetStepThreeClientTemplate.ftl")
                .to("http://localhost:8082/getStep")
                .log("Step 3 response: ${body}")
                .setProperty("step3Response", body());

        // Orquestación
        from("direct:startOrchestration")
                .log("Starting orchestration...")
                .doTry()
                .to("direct:getStepOne")
                .process(exchange -> {
                    String step1Response = exchange.getIn().getBody(String.class);
                    String step1Answer = extractAnswer(step1Response);
                    exchange.setProperty("step1Answer", step1Answer);
                })
                .to("direct:getStepTwo")
                .process(exchange -> {
                    String step2Response = exchange.getIn().getBody(String.class);
                    String step2Answer = extractAnswer(step2Response);
                    exchange.setProperty("step2Answer", step2Answer);
                })
                .to("direct:getStepThree")
                .process(exchange -> {
                    String step3Response = exchange.getIn().getBody(String.class);
                    String step3Answer = extractAnswer(step3Response);
                    exchange.setProperty("step3Answer", step3Answer);
                })
                .process(exchange -> {
                    String step1Answer = exchange.getProperty("step1Answer", String.class);
                    String step2Answer = exchange.getProperty("step2Answer", String.class);
                    String step3Answer = exchange.getProperty("step3Answer", String.class);

                    String finalAnswer = String.format(
                            "{\"data\": [{\"header\": {\"id\": \"12345\", \"type\": \"TestGiraffeRefrigerator\"}, \"answer\": \"Step1: %s - Step2: %s - Step3: %s\"}]}",
                            step1Answer, step2Answer, step3Answer
                    );

                    exchange.getIn().setBody(finalAnswer);
                })
                .log("Orchestration completed with answer: ${body}")
                .doCatch(Exception.class)
                .log("An error occurred: ${exception.message}")
                .setBody(constant("{ \"errors\": [{ \"code\": \"ERR001\", \"detail\": \"Failed to process one of the steps\", \"id\": \"12345\", \"source\": \"orchestrator\", \"status\": \"500\", \"title\": \"Internal Error\" }] }"))
                .end();
    }

    // Función para extraer el valor de "answer" del body de la respuesta JSON
    private String extractAnswer(String jsonResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        // Verifica si el nodo raíz es un arreglo
        if (rootNode.isArray() && rootNode.size() > 0) {
            JsonNode firstElement = rootNode.get(0); // Toma el primer elemento del array raíz

            // Verifica si "data" es un array y extrae el primer elemento
            JsonNode dataNode = firstElement.path("data");
            if (dataNode.isArray() && dataNode.size() > 0) {
                JsonNode firstDataElement = dataNode.get(0); // Primer elemento del array "data"
                JsonNode answerNode = firstDataElement.path("answer"); // Extrae el valor de "answer"

                if (!answerNode.isMissingNode()) {
                    return answerNode.asText(); // Retorna el valor del campo "answer"
                }
            }
        }

        return ""; // Retorna vacío si no encuentra el campo "answer"
    }


}