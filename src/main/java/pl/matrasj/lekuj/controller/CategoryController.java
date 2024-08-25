package pl.matrasj.lekuj.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.mapper.CategoryMapper;
import pl.matrasj.lekuj.payload.category.CategoryQuery;
import pl.matrasj.lekuj.repository.CategoryRepository;
import pl.matrasj.lekuj.service.CategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin
public class CategoryController {
    CategoryService categoryService;
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @PostMapping("create-many")
    public ResponseEntity<List<CategoryCommand>> createCategories(@RequestBody List<CategoryCommand> categories) {
        return ResponseEntity.status(CREATED).body(categoryService.createCategories(categories));
    }

    @PostMapping
    public ResponseEntity<List<CategoryCommand>> createCategory(@RequestBody CategoryCommand categoryCommand) {
        return ResponseEntity.status(CREATED).body(categoryService.createCategories(List.of(categoryCommand)));
    }

    @GetMapping("/top-level")
    public ResponseEntity<List<CategoryQuery>> getTopLevelCategories() {
        return ResponseEntity.ok(categoryMapper.toQuery(categoryRepository.findTopLevelCategories()));
    }

    @GetMapping("/children/{categoryId}")
    public ResponseEntity<List<CategoryQuery>> getChildrenCategories(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryMapper.toQuery(categoryRepository.findAllChildren(categoryId)));
    }
}
