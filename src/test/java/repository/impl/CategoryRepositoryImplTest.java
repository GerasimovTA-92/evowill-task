package repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import model.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.CategoryRepository;
import util.SessionFactoryUtil;

class CategoryRepositoryImplTest {
    private static CategoryRepository categoryRepository;

    @BeforeAll
    public static void setUp() {
        categoryRepository = new CategoryRepositoryImpl(SessionFactoryUtil.getSessionFactory(), Category.class);
    }

    @Test
    public void save_correctData_Ok() {
        Category category = new Category();
        category.setCategoryName(Category.CategoryName.ENTERTAINMENT);
        categoryRepository.save(category);
        assertEquals(15, category.getId());
        categoryRepository.delete(category.getId());
    }

    @Test
    public void save_null_NotOk() {
        assertThrows(RuntimeException.class,() -> categoryRepository.save(null));
    }

    @Test
    public void findAll_Ok() {
        assertEquals(6, categoryRepository.findAll().size());
    }

    @Test
    public void findByCategoryName_correctData_Ok() {
        assertEquals(1, categoryRepository.findByCategoryName(Category.CategoryName.FOOD).getId());
        assertEquals(Category.CategoryName.FOOD, categoryRepository.findByCategoryName(Category.CategoryName.FOOD).getCategoryName());
    }

    @Test
    public void findByCategoryName_null_NotOk() {
        assertThrows(RuntimeException.class, () -> categoryRepository.findByCategoryName(null));
    }

    @Test
    public void get_correctData_Ok() {
        Category category = new Category();
        categoryRepository.save(category);
        assertEquals(categoryRepository.get(category.getId()).get(), category);
    }

    @Test
    public void get_null_NotOk() {
        assertThrows(RuntimeException.class, () -> categoryRepository.get(null));
    }
}