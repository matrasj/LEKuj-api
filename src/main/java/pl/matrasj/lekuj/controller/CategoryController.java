package pl.matrasj.lekuj.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.mapper.CategoryMapper;
import pl.matrasj.lekuj.payload.category.CategoryQuery;
import pl.matrasj.lekuj.repository.CategoryRepository;
import pl.matrasj.lekuj.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryQuery> getCategories() {
        return CategoryMapper.toQuery(categoryRepository.findAll());
    }

    @PostMapping("create-many")
    public List<CategoryCommand> createCategories(@RequestBody List<CategoryCommand> categories) {
        return categoryService.createCategories(categories);
    }
}
