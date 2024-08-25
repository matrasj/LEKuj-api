package pl.matrasj.lekuj.payload.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryQuery {
    Long id;
    String name;
    Long parentCategoryId;
    Long questionsQuantity;
    boolean hasNestedCategories;
    @Builder.Default
    int level = 0;
}
