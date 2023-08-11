package ru.practicum.ewm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    @Email
    private String email;
    @NotBlank
    private String name;
}
