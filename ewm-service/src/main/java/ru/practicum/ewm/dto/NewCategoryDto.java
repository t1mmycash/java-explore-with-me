package ru.practicum.ewm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class NewCategoryDto {
    @NotBlank
    private String name;
}
