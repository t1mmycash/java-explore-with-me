package ru.practicum.compilations.dto;

import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompilationRequest {
    private Set<Long> events;
    private Boolean pinned = false;

    @Size(min = 1, max = 50)
    private String title;
}
