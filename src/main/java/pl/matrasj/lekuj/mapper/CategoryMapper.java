package pl.matrasj.lekuj.mapper;

import org.springframework.stereotype.Component;
import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.entity.CategoryEntity;
import pl.matrasj.lekuj.payload.category.CategoryQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
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
                .subCategories(toQuery(category.getSubCategories()))
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
