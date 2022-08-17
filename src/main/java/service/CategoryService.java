package service;

import model.Category;
import java.util.List;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category findByCategoryName(Category.CategoryName categoryName);
}
