package ru.practicum.ewm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.NewUserRequest;
import ru.practicum.ewm.dto.UserDto;
import ru.practicum.ewm.exception.EntityNotFoundException;
import ru.practicum.ewm.mapper.UserMapper;
import ru.practicum.ewm.repository.UserRepository;
import ru.practicum.ewm.service.AdminUserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        return userMapper.mapToUserDtoList(userRepository.getUserByParameters(ids, from, size));
    }

    @Override
    @Transactional
    public UserDto addUser(NewUserRequest newUserRequest) {
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(newUserRequest)));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new EntityNotFoundException(String.format("User with id = %d not found", userId));
        }
        userRepository.deleteById(userId);
    }
}
