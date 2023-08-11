package ru.practicum.ewm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.dto.NewCategoryDto;
import ru.practicum.ewm.exception.EntityCannotBeDeletedException;
import ru.practicum.ewm.exception.EntityNotFoundException;
import ru.practicum.ewm.mapper.CategoryMapper;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.repository.CategoryRepository;
import ru.practicum.ewm.service.AdminCategoryService;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        return categoryMapper.mapToCategoryDto(categoryRepository
                .save(categoryMapper.mapToCategoryFromNewCategoryDto(newCategoryDto)));
    }

    @Override
    @Transactional
    public void deleteCategory(Long catId) {
        Category category = getOrThrow(catId);
        if (!category.getEvents().isEmpty()) {
            throw new EntityCannotBeDeletedException("The category is not empty");
        }
        categoryRepository.deleteById(catId);
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long catId, NewCategoryDto newCategoryDto) {
        Category category = getOrThrow(catId);
        category.setName(newCategoryDto.getName());
        return categoryMapper.mapToCategoryDto(categoryRepository.save(category));
    }

    private Category getOrThrow(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Category with id = %d not found", catId)));
    }
}
