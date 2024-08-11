package pl.matrasj.lekuj.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.mapper.CategoryMapper;
import pl.matrasj.lekuj.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    public List<CategoryCommand> createCategories(List<CategoryCommand> categories) {
        return categoryMapper.toCommand(categoryRepository.saveAll(categoryMapper.toEntity(categories)));
    }
}
