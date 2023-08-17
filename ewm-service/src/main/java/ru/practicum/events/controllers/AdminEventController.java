package ru.practicum.events.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import ru.practicum.events.service.EventService;
import ru.practicum.events.dto.UpdateEventDto;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.EventState;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collection;

import static ru.practicum.util.Constants.PATTERN_DATE;

@RestController
@RequestMapping("/admin/events")
@Validated
@Slf4j
@RequiredArgsConstructor
public class AdminEventController {

    private final EventService eventService;

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto updateEventAdmin(@PathVariable(value = "eventId") Long eventId,
                                         @Valid @RequestBody UpdateEventDto eventDto) {
        log.info("Updating event {} by event Id {}", eventDto, eventId);
        return eventService.updateEventByIdAdmin(eventId, eventDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<EventFullDto> getEvents(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = PATTERN_DATE) LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = PATTERN_DATE) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Get events users {} with parameters: states {}, categories {}, rangeStart {}, rangeEnd {}, from {}, " +
                "size {}", users, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getAllEventsAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }
}