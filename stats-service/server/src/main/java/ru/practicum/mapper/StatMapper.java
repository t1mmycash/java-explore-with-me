package ru.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.dto.EndpointHit;
import ru.practicum.model.Stat;

@Component
public class StatMapper {
    public Stat MapEndpointHitToStat(EndpointHit endpointHit) {
        return Stat.builder()
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(endpointHit.getTimestamp())
                .build();
    }
}
