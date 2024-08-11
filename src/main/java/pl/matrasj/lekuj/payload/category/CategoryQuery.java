package pl.matrasj.lekuj.payload.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryQuery {
    Long id;
    String name;
    Long parentCategoryId;
    List<CategoryQuery> subCategories;
}
