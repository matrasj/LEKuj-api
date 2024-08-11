package pl.matrasj.lekuj;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.matrasj.lekuj.config.security.SecurityConfig;
import pl.matrasj.lekuj.controller.CategoryController;
import pl.matrasj.lekuj.entity.CategoryEntity;
import pl.matrasj.lekuj.mapper.CategoryMapper;
import pl.matrasj.lekuj.payload.category.CategoryCommand;
import pl.matrasj.lekuj.payload.category.CategoryQuery;
import pl.matrasj.lekuj.repository.CategoryRepository;
import pl.matrasj.lekuj.service.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@Import(SecurityConfig.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CategoryMapper categoryMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategories() throws Exception {
        // Given
        List<CategoryEntity> entities = Arrays.asList(
                CategoryEntity.builder().id(1L).name("Medycyna").parentCategoryId(null).build(),
                CategoryEntity.builder().id(2L).name("Dermatologia").parentCategoryId(1L).build()
        );
        List<CategoryQuery> queries = Arrays.asList(
                CategoryQuery.builder().id(1L).name("Medycyna").build(),
                CategoryQuery.builder().id(2L).name("Dermatologia").build()
        );

        when(categoryRepository.findAll()).thenReturn(entities);
        when(categoryMapper.toQuery(anyList())).thenReturn(queries);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Medycyna"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Dermatologia"));
    }

    @Test
    public void testCreateCategories() throws Exception {
        // Given
        List<CategoryCommand> categories = Arrays.asList(
                CategoryCommand.builder().id(1L).name("Medycyna").build(),
                CategoryCommand.builder().id(2L).name("Dermatologia").build()
        );
        when(categoryService.createCategories(anyList())).thenReturn(categories);

        // When & Then
        mockMvc.perform(post("/api/category/create-many")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categories)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Medycyna"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Dermatologia"));
    }
}
