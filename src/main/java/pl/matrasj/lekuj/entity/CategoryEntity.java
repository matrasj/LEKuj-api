package pl.matrasj.lekuj.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    List<CategoryEntity> subCategories;
}
