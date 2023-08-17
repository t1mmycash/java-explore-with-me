package ru.practicum.compilations.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.service.CompilationService;
import ru.practicum.compilations.dto.CompilationDto;

import java.util.Collection;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/compilations")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PublicCompilationController {

    private final CompilationService serviceCompilation;

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable Long compId) {
        log.info("Get compilation with id {}", compId);
        return serviceCompilation.getCompilationById(compId);
    }

    @GetMapping
    public Collection<CompilationDto> getCompilation(
            @RequestParam(required = false) Boolean pinned,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Get compilations with parameters pinned {} from {} size {}", pinned, from, size);
        return serviceCompilation.getAllCompilation(pinned, from, size);
    }
}