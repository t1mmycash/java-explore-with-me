package ru.practicum.ewm.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.practicum.ewm.model.User;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    User mapToUser(ru.practicum.ewm.dto.NewUserRequest newUserRequest);

    ru.practicum.ewm.dto.UserDto mapToUserDto(User user);

    List<ru.practicum.ewm.dto.UserDto> mapToUserDtoList(List<User> users);
}
