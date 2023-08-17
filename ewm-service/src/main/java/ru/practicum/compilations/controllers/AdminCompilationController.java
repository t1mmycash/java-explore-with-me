package ru.practicum.compilations.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import ru.practicum.compilations.service.CompilationService;
import ru.practicum.compilations.dto.NewCompilationDto;
import ru.practicum.compilations.dto.UpdateCompilationRequest;
import ru.practicum.compilations.dto.CompilationDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
@Slf4j
@RequiredArgsConstructor
public class AdminCompilationController {

    private final CompilationService compilationService;

    @DeleteMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "compId") Long compId) {
        log.info("Deleting compilation by Id {}", compId);
        compilationService.deleteCompilationById(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(@PathVariable(value = "compId") Long compId,
                                            @Valid @RequestBody UpdateCompilationRequest compilationDto) {
        log.info("Update compilation {} by Id {}", compilationDto, compId);
        return compilationService.updateCompilationByID(compId, compilationDto);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompilationDto saveCompilation(@Valid @RequestBody NewCompilationDto compilationDto) {
        log.info("Creating compilation {}", compilationDto);
        return compilationService.saveCompilation(compilationDto);
    }
}