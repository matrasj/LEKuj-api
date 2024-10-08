package pl.matrasj.lekuj.payload.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCommand {
    Long id;
    String name;
    Long parentCategoryId;
}
