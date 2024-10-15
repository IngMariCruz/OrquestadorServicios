package co.com.vanegas.microservice.resolveEnigmaApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BatchService {
    private final WebClient.Builder webClientBuilder;


    @Value("http://localhost:8083/orques/steps/orchestration")
    private String orchestratorServiceUrl;

    @Autowired
    public BatchService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Scheduled(fixedRate = 5000)
    public void runBatchJob() {
        System.out.println("Iniciando batch job...");

        // Crear el cuerpo de la solicitud
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("id", "12345");
        header.put("type", "TestGiraffeRefrigerator");

        Map<String, Object> enigmaData = new HashMap<>();
        enigmaData.put("header", header);
        enigmaData.put("enigma", "how to put a giraffe into a refrigerator");

        List<Map<String, Object>> dataList = new ArrayList<>();
        dataList.add(enigmaData);

        requestBody.put("data", dataList);

        // Llamar al servicio orquestador a través de WebClient
        try {
            String response = webClientBuilder.build()
                    .post()
                    .uri(orchestratorServiceUrl) // Asegúrate de que la URL sea correcta
                    .contentType(MediaType.APPLICATION_JSON) // Establecer el tipo de contenido
                    .bodyValue(requestBody) // Enviar el cuerpo de la solicitud
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("Respuesta del servicio orquestador: " + response);
        } catch (Exception e) {
            System.err.println("Error al llamar al servicio orquestador: " + e.getMessage());
        }

        System.out.println("Batch job completado.");
    }
}
