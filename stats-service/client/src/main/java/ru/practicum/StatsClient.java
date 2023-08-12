package ru.practicum;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.dto.EndpointHit;
import ru.practicum.dto.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class StatsClient {

    private final WebClient client;

    public StatsClient() {
        String baseUrl = "http://localhost:9090";
        this.client = WebClient.create(baseUrl);
    }

    public void saveStats(String app, String uri, String ip, LocalDateTime timestamp) {
        final EndpointHit endpointHit = EndpointHit.builder()
                .app(app)
                .ip(ip)
                .uri(uri)
                .timestamp(timestamp)
                .build();

        this.client.post()
                .uri("/hit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(endpointHit, EndpointHit.class)
                .retrieve()
                .toBodilessEntity()
                .doOnNext(c -> log.info("Save endpointHit {}", endpointHit))
                .block();
    }

    public ResponseEntity<List<ViewStats>> getStats(String start, String end, List<String> urls, Boolean isUnique) {
        String paramsUri = String.join(",", urls);
        return this.client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stats")
                        .queryParam("start", start)
                        .queryParam("end", end)
                        .queryParam("uris", paramsUri)
                        .queryParam("unique", isUnique)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntityList(ViewStats.class)
                .doOnNext(c -> log.info("Get stats with: start - {}, end - {}, uris - {}, unique - {}",
                        start, end, paramsUri, isUnique))
                .block();
    }
}
