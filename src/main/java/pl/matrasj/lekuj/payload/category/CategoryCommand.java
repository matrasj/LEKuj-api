package pl.matrasj.lekuj.payload.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryCommand {
    Long id;
    String name;
    Long parentCategoryId;
}
