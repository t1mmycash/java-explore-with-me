package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.EndpointHit;
import ru.practicum.dto.ViewStats;
import ru.practicum.exception.InvalidTimestampException;
import ru.practicum.service.StatService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.dto.Constant.TIMESTAMP_PATTERN;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {
    private final StatService statService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEndpointHit(@Valid @RequestBody EndpointHit endpointHit) {
        log.info("Add EndpointHit {}", endpointHit);
        statService.addEndpointHit(endpointHit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(
            @RequestParam(name = "start") @DateTimeFormat(pattern = TIMESTAMP_PATTERN) LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = TIMESTAMP_PATTERN) LocalDateTime end,
            @RequestParam(name = "unique", defaultValue = "false") Boolean isUnique,
            @RequestParam(name = "uris") List<String> uris
    ) {
        if (start.isAfter(end)) {
            throw new InvalidTimestampException("Start can`t be after than start");
        }
        log.info("get stats with: start - {}, end - {}, unique - {}, uris - {}", start, end, isUnique, uris);
        return statService.getStats(start, end, isUnique, uris);
    }
}
