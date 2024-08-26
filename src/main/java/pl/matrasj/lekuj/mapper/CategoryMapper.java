package pl.matrasj.lekuj.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.entity.CategoryEntity;
import pl.matrasj.lekuj.payload.category.CategoryQuery;
import pl.matrasj.lekuj.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryMapper {
    CategoryRepository categoryRepository;

    public CategoryCommand toCommand(CategoryEntity category) {
        return CategoryCommand.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId())
                .build();
    }

    public List<CategoryCommand> toCommand(List<CategoryEntity> categories) {
        return categories.stream().map(this::toCommand).collect(Collectors.toList());
    }

    public CategoryQuery toQuery(CategoryEntity category) {
        return CategoryQuery.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId())
                .questionsQuantity(category.getQuestionsQuantity())
                .hasNestedCategories(!category.getSubCategories().isEmpty())
                .level(categoryRepository.findLevelForCategory(category.getId()))
                .build();
    }

    public List<CategoryQuery> toQuery(List<CategoryEntity> categories) {
        return categories.stream().map(this::toQuery).collect(Collectors.toList());
    }

    public CategoryEntity toEntity(CategoryCommand category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId())
                .build();
    }

    public List<CategoryEntity> toEntity(List<CategoryCommand> categories) {
        return categories.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
