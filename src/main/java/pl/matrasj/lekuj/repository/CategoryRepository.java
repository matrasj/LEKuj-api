package pl.matrasj.lekuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.matrasj.lekuj.entity.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
     @Query("SELECT c FROM CategoryEntity c WHERE c.parentCategoryId IS NULL ORDER BY c.name")
     List<CategoryEntity> findTopLevelCategories();

     @Query("SELECT c FROM CategoryEntity c WHERE (c.parentCategoryId = :categoryId) ORDER BY c.name")
     List<CategoryEntity> findAllChildren(@Param("categoryId") Long categoryId);

     @Query(value = "WITH RECURSIVE CategoryHierarchy AS (" +
             "SELECT id, parent_category_id, 0 AS level " +
             "FROM category " +
             "WHERE id = :categoryId " +
             "UNION ALL " +
             "SELECT c.id, c.parent_category_id, ch.level + 1 " +
             "FROM category c " +
             "INNER JOIN CategoryHierarchy ch ON c.id = ch.parent_category_id " +
             ") " +
             "SELECT MAX(level) AS levels_to_top " +
             "FROM CategoryHierarchy", nativeQuery = true)
     int findLevelForCategory(Long categoryId);

}
