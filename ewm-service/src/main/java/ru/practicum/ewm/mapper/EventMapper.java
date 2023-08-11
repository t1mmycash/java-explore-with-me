package ru.practicum.ewm.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.dto.EventFullDto;
import ru.practicum.ewm.dto.UserShortDto;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.model.Event;
import ru.practicum.ewm.model.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EventMapper {
    EventFullDto mapToEventFullDtoFromEvent(Event event);

    ///

    UserShortDto mapToUserShortDto(User user);

    CategoryDto mapToCategoryDto(Category category);
}
