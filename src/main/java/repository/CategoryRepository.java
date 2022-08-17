package repository;

import model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);

    List<Category> findAll();

    Category findByCategoryName(Category.CategoryName categoryName);

    void delete(Long id);

    Optional<Category> get(Long id);
}
