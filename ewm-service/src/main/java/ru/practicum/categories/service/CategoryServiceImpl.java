package ru.practicum.categories.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.util.PaginationSetup;
import ru.practicum.events.repository.EventRepository;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.handler.NotFoundException;
import ru.practicum.handler.ValidateException;
import ru.practicum.categories.dto.CategoryDto;
import ru.practicum.categories.dto.CategoryMapper;
import ru.practicum.categories.model.Category;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.util.Messages.*;
import static ru.practicum.categories.dto.CategoryMapper.toCategoryDto;
import static ru.practicum.categories.dto.CategoryMapper.toCategory;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(toCategory(categoryDto));
        log.info(SAVE_MODEL.getMessage(), category);
        return toCategoryDto(category);
    }

    @Transactional
    @Override
    public CategoryDto updateCategoryById(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id=" + id + " was not found"));
        category.setName(categoryDto.getName());
        log.info(UPDATE_MODEL.getMessage(), category);
        return toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Category with id=" + catId + " was not found"));
        log.info(GET_MODEL_BY_ID.getMessage(), catId);
        return toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategory(int from, int size) {
        log.info(GET_MODELS.getMessage());
        return categoryRepository.findAll(new PaginationSetup(from, size, Sort.unsorted())).stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        if (eventRepository.countByCategoryId(id) > 0) {
            throw new ValidateException("Cannot delete category with linked events");
        }
        categoryRepository.delete(category);
    }
}