package pl.matrasj.lekuj.entity;

import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Formula;

import java.util.List;

@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "parent_category_id")
    Long parentCategoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    List<CategoryEntity> subCategories = Lists.newArrayList();

    @Formula("(SELECT COUNT(*) FROM question q WHERE q.category_id = id)")
    Long questionsQuantity;
}
