package pl.matrasj.lekuj.mapper;

import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.entity.CategoryEntity;
import pl.matrasj.lekuj.payload.category.CategoryQuery;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryCommand toCommand(CategoryEntity category) {
        return CategoryCommand.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId())
                .build();
    }

    public static List<CategoryCommand> toCommand(List<CategoryEntity> categories) {
        return categories.stream().map(CategoryMapper::toCommand).collect(Collectors.toList());
    }

    public static CategoryQuery toQuery(CategoryEntity category) {
        return CategoryQuery.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId())
                .subCategories(toQuery(category.getSubCategories()))
                .build();
    }

    public static List<CategoryQuery> toQuery(List<CategoryEntity> categories) {
        return categories.stream().map(CategoryMapper::toQuery).collect(Collectors.toList());
    }

    public static CategoryEntity toEntity(CategoryCommand category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId())
                .build();
    }

    public static List<CategoryEntity> toEntity(List<CategoryCommand> categories) {
        return categories.stream().map(CategoryMapper::toEntity).collect(Collectors.toList());
    }
}
