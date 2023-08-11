package ru.practicum.ewm.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.dto.NewCategoryDto;
import ru.practicum.ewm.model.Category;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    CategoryDto mapToCategoryDto(Category category);

    Category mapToCategoryFromCategoryDto(CategoryDto categoryDto);

    Category mapToCategoryFromNewCategoryDto(NewCategoryDto newCategoryDto);

    List<CategoryDto> mapToListCategoryDto(List<Category> categories);
}
