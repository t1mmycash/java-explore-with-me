package ru.practicum.events.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practicum.categories.dto.CategoryDto;
import ru.practicum.events.model.Location;
import ru.practicum.events.EventState;
import ru.practicum.users.dto.UserShortDto;

import java.time.LocalDateTime;

import static ru.practicum.util.Constants.PATTERN_DATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventFullDto {

    private Long id;
    private String title;
    private String annotation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN_DATE)
    private LocalDateTime eventDate;
    private Location location;
    private boolean paid;
    private int participantLimit;
    private int confirmedRequests;
    private long views;
    private EventState state;

    @JsonUnwrapped
    public AdditionalEventInformation eventInformation;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    protected static class AdditionalEventInformation {

        private String description;
        private CategoryDto category;
        private boolean requestModeration;
        private UserShortDto initiator;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN_DATE)
        private LocalDateTime publishedOn;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN_DATE)
        private LocalDateTime createdOn;
    }
}