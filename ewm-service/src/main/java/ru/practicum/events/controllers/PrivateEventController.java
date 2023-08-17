package ru.practicum.events.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import ru.practicum.events.service.EventService;
import ru.practicum.events.dto.*;
import ru.practicum.requests.dto.EventRequestStatusUpdateResult;
import ru.practicum.requests.dto.EventRequestStatusUpdateRequest;
import ru.practicum.requests.dto.ParticipationRequestDto;
import ru.practicum.requests.service.RequestService;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users/{userId}/events")
@Slf4j
@Validated
@RequiredArgsConstructor
public class PrivateEventController {

    private final RequestService requestService;
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventFullDto saveEvent(@PathVariable(value = "userId") Long userId,
                                  @Valid @RequestBody NewEventDto eventDto) {
        log.info("Creating event {} by user Id {}", eventDto, userId);
        return eventService.saveEventByIdUser(userId, eventDto);
    }

    @PatchMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    public EventRequestStatusUpdateResult updateEventRequestStatus(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "eventId") Long eventId,
            @RequestBody EventRequestStatusUpdateRequest updateRequest) {
        log.info("Updating status participation request by user Id {} and event Id {}", userId, eventId);
        return requestService.updateEventRequestStatus(userId,eventId, updateRequest);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto updateEvent(@PathVariable(value = "userId") Long userId,
                                    @PathVariable(value = "eventId") Long eventId,
                                    @Valid @RequestBody UpdateEventDto eventDto) {
        log.info("Updating event {} by userId {} and event Id {}", eventDto, userId, eventId);
        return eventService.updateEventById(userId, eventId, eventDto);
    }

    @GetMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<ParticipationRequestDto> getParticipationRequestByIdEvent(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "eventId") Long eventId) {
        log.info("Get participation request by user Id {} and event Id {}", userId, eventId);
        return requestService.getParticipationRequest(userId,eventId);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto getEventById(@PathVariable(value = "userId") Long userId,
                                     @PathVariable(value = "eventId") Long eventId) {
        log.info("Get event by userId {} and event Id {}", userId, eventId);
        return eventService.getEventById(userId, eventId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Collection<EventShortDto> getEventsByUserId(@PathVariable(value = "userId") Long userId,
                                                       @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                                       @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("Get events by userId {} with parameters from {} size {}", userId, from, size);
        return eventService.getAllEventsByUserId(userId, from, size);
    }
}