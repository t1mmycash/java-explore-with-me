package ru.practicum.compilations.service;

import ru.practicum.compilations.dto.CompilationDto;
import ru.practicum.compilations.dto.NewCompilationDto;
import ru.practicum.compilations.dto.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {

    CompilationDto updateCompilationByID(Long compId, UpdateCompilationRequest compilationDto);

    void deleteCompilationById(Long compId);

    CompilationDto saveCompilation(NewCompilationDto compilationDto);

    CompilationDto getCompilationById(Long id);

    List<CompilationDto> getAllCompilation(Boolean pinned, Integer from, Integer size);
}