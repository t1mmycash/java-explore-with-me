package ru.practicum.compilations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.util.PaginationSetup;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    Page<Compilation> findAllByPinned(Boolean pinned, PaginationSetup paginationSetup);
}