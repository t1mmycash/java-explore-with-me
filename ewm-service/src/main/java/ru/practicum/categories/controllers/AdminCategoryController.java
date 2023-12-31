package ru.practicum.categories.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import ru.practicum.categories.service.CategoryService;
import ru.practicum.categories.dto.CategoryDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryDto saveCategory(@Valid @RequestBody CategoryDto newCategory) {
        log.info("Creating category {}", newCategory);
        return categoryService.saveCategory(newCategory);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable(value = "catId") Long catId,
                                      @Valid @RequestBody CategoryDto dto) {
        log.info("Updating category {} by id {}", dto, catId);
        return categoryService.updateCategoryById(catId, dto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "catId") Long catId) {
        log.info("Deleting category by id {}", catId);
        categoryService.deleteCategoryById(catId);
    }
}